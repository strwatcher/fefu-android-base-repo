package ru.fefu.activitytracker.views.main.viewholders

import android.annotation.SuppressLint
import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import ru.fefu.activitytracker.models.IListItem
import ru.fefu.activitytracker.R
import ru.fefu.activitytracker.models.IActivity
import ru.fefu.activitytracker.models.MyActivity

open class ActivityViewHolder(itemView: View, listener: (Int, IActivity) -> Unit)
    : ListItemViewHolder(itemView) {
    private val tvMetric: TextView = itemView.findViewById(R.id.tvMetric)
    private val tvTime: TextView = itemView.findViewById(R.id.tvTime)
    private val tvActivityType: TextView = itemView.findViewById(R.id.tvActivityName)
    private val tvDate: TextView = itemView.findViewById(R.id.tvDate)
    protected lateinit var data: IActivity

    init {
        itemView.setOnClickListener {
            if (absoluteAdapterPosition != RecyclerView.NO_POSITION)
                listener.invoke(absoluteAdapterPosition, data)
        }
    }

    @SuppressLint
    override fun bind(listItem: IListItem) {
        listItem as IActivity
        data = listItem
        tvMetric.text = listItem.metric
        tvTime.text = listItem.duration
        tvActivityType.text = listItem.name
        tvDate.text = listItem.finishDate
    }

}