package com.dragdroprecyclerview

import androidx.recyclerview.widget.RecyclerView.ViewHolder


interface OnStartDragListener {
    /**
     * Called when a view is requesting a start of a drag.
     *
     * @param viewHolder The holder of the view to drag.
     */
    fun onStartDrag(viewHolder: ViewHolder)
}