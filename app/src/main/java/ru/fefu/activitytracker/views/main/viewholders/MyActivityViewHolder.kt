package ru.fefu.activitytracker.views.main.viewholders

import android.annotation.SuppressLint
import android.view.View
import android.widget.TextView
import ru.fefu.activitytracker.models.ListItemModel
import ru.fefu.activitytracker.R
import ru.fefu.activitytracker.models.Date
import ru.fefu.activitytracker.models.MyActivity

class MyActivityViewHolder(itemView: View): ListItemViewHolder(itemView) {
    private val tvMetric: TextView = itemView.findViewById(R.id.metric)
    private val tvTime: TextView = itemView.findViewById(R.id.time)
    private val tvActivityType: TextView = itemView.findViewById(R.id.activityName)
    private val tvDate: TextView = itemView.findViewById(R.id.date)

    @SuppressLint
    override fun bind(listItem: ListItemModel) {
        listItem as MyActivity
        tvMetric.text = listItem.metric
        tvTime.text = listItem.finishDate.minus(listItem.startDate).formattedTime
        tvActivityType.text = listItem.name

        val nowMoreThanDayAfterFinish = Date.now().moreThanDayAfter(listItem.finishDate)
        tvDate.text = if (nowMoreThanDayAfterFinish) {
            listItem.finishDate.formattedDate
        } else {
            Date.now().minus(listItem.finishDate).formattedHoursAgo
        }
    }
}