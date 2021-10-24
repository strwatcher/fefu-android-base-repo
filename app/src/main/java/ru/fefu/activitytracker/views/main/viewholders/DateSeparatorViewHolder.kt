package ru.fefu.activitytracker.views.main.viewholders

import android.annotation.SuppressLint
import android.view.View
import android.widget.TextView
import ru.fefu.activitytracker.R
import ru.fefu.activitytracker.models.DateSeparator
import ru.fefu.activitytracker.models.IListItem

class DateSeparatorViewHolder(itemView: View): ListItemViewHolder(itemView) {
    private val tvDate: TextView = itemView.findViewById(R.id.tvDateSeparator)

    @SuppressLint
    override fun bind(listItem: IListItem) {
        listItem as DateSeparator

        tvDate.text = listItem.formattedDate


    }
}