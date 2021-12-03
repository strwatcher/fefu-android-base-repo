package ru.fefu.activitytracker.views.activity

import android.Manifest
import android.app.AlertDialog
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.provider.Settings
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import ru.fefu.activitytracker.databinding.ActivityActivityBinding

class ActivityActivity: AppCompatActivity() {
    private lateinit var binding: ActivityActivityBinding

    val requestPermissionLauncher =
        registerForActivityResult(ActivityResultContracts.RequestMultiplePermissions()) { permissions ->
            permissions[Manifest.permission.ACCESS_FINE_LOCATION]?.let {
                if (it) {
                    showLocation()
                } else {
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M &&
                        !shouldShowRequestPermissionRationale(Manifest.permission_group.LOCATION)
                    ) {
                        showPermissionDeniedDialog()
                    } else {
                        showRationaleDialog()
                    }
                }
            }
        }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityActivityBinding.inflate(layoutInflater)
        binding.bBack.setOnClickListener {
            finish()
        }
        setContentView(binding.root)
        requestLocationPermissionAndDoAction(::showLocation)
    }

    override fun onBackPressed() {
       finish()
    }


    fun requestLocationPermissionAndDoAction(action: () -> Unit) {
        when {
            ContextCompat.checkSelfPermission(
                this,
                Manifest.permission.ACCESS_FINE_LOCATION
            ) == PackageManager.PERMISSION_GRANTED -> {
                action()
            }

            Build.VERSION.SDK_INT >= Build.VERSION_CODES.M && shouldShowRequestPermissionRationale(
                Manifest.permission_group.LOCATION
            ) -> {
                showRationaleDialog()
            }
            else -> {
                requestPermissionLauncher.launch( arrayOf(
                    Manifest.permission.ACCESS_FINE_LOCATION,
                    Manifest.permission.ACCESS_COARSE_LOCATION
                ))
            }
        }
    }

    private fun showRationaleDialog() {
        AlertDialog.Builder(this)
            .setTitle("Требуется разрешение")
            .setMessage("Вы не можете использовать эту функцию приложения без GPS")
            .setPositiveButton("Предоставить") { _, _ ->
                requestPermissionLauncher.launch(arrayOf(
                    Manifest.permission.ACCESS_FINE_LOCATION,
                    Manifest.permission.ACCESS_COARSE_LOCATION
                ))
            }
            .show()
    }

    private fun showPermissionDeniedDialog() {
        AlertDialog.Builder(this)
            .setTitle("Требуется разрешение")
            .setMessage("Для использования этой функции приложения, пожалуйста " +
                    "выдайте ему соответствующие разрешения в настройках")
            .setPositiveButton("Настройки") { _, _ ->
                val intent = Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS)
                val uri = Uri.fromParts("package", packageName, null)
                intent.data = uri
                startActivity(intent)
            }
            .show()
    }

    private fun showLocation() {

    }
}