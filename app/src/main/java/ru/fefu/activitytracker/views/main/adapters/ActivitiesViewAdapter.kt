package ru.fefu.activitytracker.views.main.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import ru.fefu.activitytracker.models.IListItem
import ru.fefu.activitytracker.R
import ru.fefu.activitytracker.views.main.ListItems.MyCard
import ru.fefu.activitytracker.views.main.ListItems.UserCard
import ru.fefu.activitytracker.views.main.viewholders.DateSeparatorViewHolder
import ru.fefu.activitytracker.views.main.viewholders.ListItemViewHolder
import ru.fefu.activitytracker.views.main.viewholders.ActivityViewHolder
import ru.fefu.activitytracker.views.main.viewholders.UserActivityViewHolder

class ActivitiesViewAdapter(private val activities: List<IListItem>)
    : RecyclerView.Adapter<ListItemViewHolder>() {

    override fun getItemViewType(position: Int): Int {
        return activities[position].type.ordinal
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListItemViewHolder {
        return when (viewType) {
            MyCard.ordinal -> {
                val view = LayoutInflater.from(parent.context)
                    .inflate(R.layout.activity_card, parent, false)
                ActivityViewHolder(view)
            }
            UserCard.ordinal -> {
                val view = LayoutInflater.from(parent.context)
                    .inflate(R.layout.activity_card, parent, false)
                UserActivityViewHolder(view)
            }
            else -> {
                val view = LayoutInflater.from(parent.context)
                    .inflate(R.layout.date_separator, parent, false)
                DateSeparatorViewHolder(view)
            }
        }

    }

    override fun getItemCount(): Int = activities.size

    override fun onBindViewHolder(holder: ListItemViewHolder, position: Int) {
        holder.bind(activities[position])
    }

}