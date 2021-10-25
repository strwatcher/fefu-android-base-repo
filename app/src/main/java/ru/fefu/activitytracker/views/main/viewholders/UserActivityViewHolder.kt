package ru.fefu.activitytracker.views.main.viewholders

import android.view.View
import android.widget.TextView
import ru.fefu.activitytracker.R
import ru.fefu.activitytracker.models.IActivity
import ru.fefu.activitytracker.models.IListItem
import ru.fefu.activitytracker.models.UserActivity

class UserActivityViewHolder(itemView: View, listener: (Int, IActivity) -> Unit)
    : ActivityViewHolder(itemView, listener) {
    private val tvUserName: TextView = itemView.findViewById(R.id.tvUserName)

    override fun bind(listItem: IListItem) {
        super.bind(listItem)

        listItem as UserActivity
        tvUserName.text = listItem.userName
    }
}