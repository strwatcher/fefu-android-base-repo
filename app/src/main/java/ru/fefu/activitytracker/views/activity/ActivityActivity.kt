package ru.fefu.activitytracker.views.activity

import android.Manifest
import android.app.AlertDialog
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.BitmapFactory
import android.location.Location
import android.net.Uri
import android.os.Bundle
import android.os.PersistableBundle
import android.provider.Settings
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.navigation.findNavController
import com.google.android.gms.common.ConnectionResult
import com.google.android.gms.common.GoogleApiAvailability
import com.google.android.gms.common.api.ResolvableApiException
import com.google.android.gms.location.LocationServices
import com.google.android.gms.location.LocationSettingsRequest
import org.osmdroid.config.Configuration
import org.osmdroid.events.MapEventsReceiver
import org.osmdroid.util.GeoPoint
import org.osmdroid.views.overlay.MapEventsOverlay
import org.osmdroid.views.overlay.Polyline
import org.osmdroid.views.overlay.mylocation.GpsMyLocationProvider
import org.osmdroid.views.overlay.mylocation.MyLocationNewOverlay
import ru.fefu.activitytracker.R
import ru.fefu.activitytracker.databinding.ActivityActivityBinding
import ru.fefu.activitytracker.views.activity.fragments.ActivityStarterDirections
import java.lang.Exception

class ActivityActivity: AppCompatActivity() {
    
    companion object {
        private const val REQUEST_CODE_RESOLVE_GOOGLE_API_ERROR = 1337
        private const val REQUEST_CODE_RESOLVE_GPS_ERROR = 1338
    }

    lateinit var binding: ActivityActivityBinding

    val polyline by lazy {
        Polyline().apply {
            outlinePaint.color = ContextCompat.getColor(
                this@ActivityActivity,
                R.color.primary
            )
        }
    }

    private val locationPermissionsGroup = arrayOf(
        Manifest.permission.ACCESS_FINE_LOCATION,
        Manifest.permission.ACCESS_COARSE_LOCATION
    )

    private val requestPermissionLauncher =
        registerForActivityResult(ActivityResultContracts.RequestMultiplePermissions()) { permissions ->
            permissions[Manifest.permission.ACCESS_FINE_LOCATION]?.let {
                if (it) {
                    showUserLocation()
                } else {
                    if (!shouldShowRequestPermissionRationale(Manifest.permission_group.LOCATION)
                    ) {
                        showPermissionDeniedDialog()
                    } else {
                        showRationaleDialog()
                    }
                }
            }
        }

    var activityId: Int = -1

    override fun onCreate(savedInstanceState: Bundle?) {
        val bundle = intent.extras
        activityId = bundle?.getInt("activityId") ?: -1
        println(activityId)
        super.onCreate(savedInstanceState)

        Configuration.getInstance().load(this, getPreferences(Context.MODE_PRIVATE))
        requestLocationPermissionAndDoAction(::startLocationTracking)

        binding = ActivityActivityBinding.inflate(layoutInflater)
        binding.bBack.setOnClickListener {
            finish()
        }
        setContentView(binding.root)
        initMap()

    }

    override fun onBackPressed() {
       finish()
    }


    private fun requestLocationPermissionAndDoAction(action: () -> Unit) {
        when {
            ContextCompat.checkSelfPermission(
                this,
                Manifest.permission.ACCESS_FINE_LOCATION
            ) == PackageManager.PERMISSION_GRANTED -> {
                action()
            }

            shouldShowRequestPermissionRationale(
                Manifest.permission_group.LOCATION
            ) -> {
                showRationaleDialog()
            }
            else -> {
                requestPermissionLauncher.launch(locationPermissionsGroup)
            }
        }
    }

    private fun showRationaleDialog() {
        AlertDialog.Builder(this)
            .setTitle("Требуется разрешение")
            .setMessage("Вы не можете использовать эту функцию приложения без GPS")
            .setPositiveButton("Предоставить") { _, _ ->
                requestPermissionLauncher.launch(locationPermissionsGroup)
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

    private fun initMap() {
        binding.mvMap.minZoomLevel = 4.0

        val eventReceiver = object: MapEventsReceiver {
            override fun singleTapConfirmedHelper(p: GeoPoint?): Boolean {
               return true
            }

            override fun longPressHelper(p: GeoPoint?): Boolean {
                return true
            }
        }

        binding.mvMap.overlays.add(MapEventsOverlay(eventReceiver))
        binding.mvMap.overlayManager.add(polyline)
    }

    private fun isGooglePlayServicesAvailable(): Boolean {
        val googleApiAvailability = GoogleApiAvailability.getInstance()
        val result = googleApiAvailability.isGooglePlayServicesAvailable(this)
        if (result == ConnectionResult.SUCCESS) return true
        if (googleApiAvailability.isUserResolvableError(result)) {
            googleApiAvailability.getErrorDialog(
                this,
                result,
                REQUEST_CODE_RESOLVE_GOOGLE_API_ERROR
            )?.show()
        } else {
            Toast.makeText(
                this,
                "Сервисы google недоступны",
                Toast.LENGTH_SHORT
            ).show()
        }
        return false
    }

    private fun checkIfGpsEnabled(success: () -> Unit, error: (Exception) -> Unit) {
        LocationServices.getSettingsClient(this)
            .checkLocationSettings(
                LocationSettingsRequest.Builder()
                    .addLocationRequest(
                        ActivityService.locationRequest
                    )
                    .build()
            )
            .addOnSuccessListener { success.invoke() }
            .addOnFailureListener { error.invoke(it) }
    }

    private fun showUserLocation() {
        val locationOverlay = MyLocationNewOverlay(
            object : GpsMyLocationProvider(applicationContext) {
                private var mapMoved = false
                override fun onLocationChanged(location: Location) {
                    location.removeBearing()
                    super.onLocationChanged(location)
                    if (mapMoved) return
                    mapMoved = true
                    binding.mvMap.controller.animateTo(
                        GeoPoint(
                            location.latitude,
                            location.longitude
                        ),
                        16.0,
                        1000
                    )
                }
            },
            binding.mvMap
        )

        val locationIcon = BitmapFactory.decodeResource(resources, R.drawable.ic_location_icon)
        locationOverlay.setDirectionArrow(locationIcon, locationIcon)
        locationOverlay.setPersonHotspot(locationIcon.width / 2f, locationIcon.height.toFloat())
        locationOverlay.enableMyLocation()
        binding.mvMap.overlays.add(locationOverlay)
    }

    private fun startLocationTracking() {
        if (isGooglePlayServicesAvailable()) {
            checkIfGpsEnabled(
                {
                   showUserLocation()
                },
                {
                    if (it is ResolvableApiException) {
                        it.startResolutionForResult(this, REQUEST_CODE_RESOLVE_GPS_ERROR)
                    } else {
                        Toast.makeText(this, "Служба GPS недоступна", Toast.LENGTH_SHORT).show()
                    }
                }
            )
        }
    }


}