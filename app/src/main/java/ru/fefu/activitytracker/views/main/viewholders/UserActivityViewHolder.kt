package ru.fefu.activitytracker.views.main.viewholders

import android.view.View
import android.widget.TextView
import ru.fefu.activitytracker.R
import ru.fefu.activitytracker.model.IActivity
import ru.fefu.activitytracker.model.IListItem
import ru.fefu.activitytracker.model.UserActivity

class UserActivityViewHolder(itemView: View, listener: (Int, IActivity) -> Unit)
    : ActivityViewHolder(itemView, listener) {
    private val tvUserName: TextView = itemView.findViewById(R.id.tvUserName)

    override fun bind(listItem: IListItem) {
        super.bind(listItem)
        listItem as UserActivity
        data = listItem
        tvUserName.text = listItem.userName
    }
}