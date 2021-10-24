package ru.fefu.activitytracker.views.main.viewholders

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import ru.fefu.activitytracker.models.IListItem

abstract class ListItemViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
    abstract fun bind(listItem: IListItem)
}