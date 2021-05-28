package com.dragdroprecyclerview

import android.util.Log
import androidx.recyclerview.widget.RecyclerView

class ItemMoveCallbackListener(val adapter: DragDropRecyclerAdapter) : ItemTouchHelper_Shadow.Callback() {


    override fun getMovementFlags(
        recyclerView: RecyclerView,
        viewHolder: RecyclerView.ViewHolder
    ): Int {
        val dragFlags = ItemTouchHelper_Shadow.UP or ItemTouchHelper_Shadow.DOWN
        return makeMovementFlags(dragFlags, 0)
    }

    override fun isItemViewSwipeEnabled(): Boolean {
        return false
    }

    override fun isLongPressDragEnabled(): Boolean {
        return false
    }

    override fun onMove(
        recyclerView: RecyclerView, viewHolder: RecyclerView.ViewHolder,
        target: RecyclerView.ViewHolder
    ): Boolean {
        adapter.onRowMoved(viewHolder.adapterPosition, target.adapterPosition)

        return true
    }

    override fun onSelectedChanged(viewHolder: RecyclerView.ViewHolder?, actionState: Int) {
        if (actionState != ItemTouchHelper_Shadow.ACTION_STATE_IDLE) {
            if (viewHolder is DragDropRecyclerAdapter.ItemViewHolder) {
                adapter.onRowSelected(viewHolder)
            }
        }
        super.onSelectedChanged(viewHolder, actionState)
    }

    override fun clearView(recyclerView: RecyclerView, viewHolder: RecyclerView.ViewHolder) {
        super.clearView(recyclerView, viewHolder)
        if (viewHolder is DragDropRecyclerAdapter.ItemViewHolder) {
            adapter.onRowClear(viewHolder)
        }
    }

    override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
    }

    interface Listener {
        fun onRowMoved(fromPosition: Int, toPosition: Int)
        fun onRowSelected(itemViewHolder: DragDropRecyclerAdapter.ItemViewHolder)
        fun onRowClear(itemViewHolder: DragDropRecyclerAdapter.ItemViewHolder)
    }
}