package com.dragdroprecyclerview

import android.graphics.Canvas
import android.os.Build
import android.view.View
import androidx.core.view.ViewCompat
import androidx.recyclerview.widget.ItemTouchUIUtil
import androidx.recyclerview.widget.RecyclerView

internal class ItemTouchUtilImpl : ItemTouchUIUtil {
    override fun onDraw(
        c: Canvas, recyclerView: RecyclerView, view: View, dX: Float, dY: Float,
        actionState: Int, isCurrentlyActive: Boolean
    ) {
        if (Build.VERSION.SDK_INT >= 21) {
            if (isCurrentlyActive) {
                var originalElevation = view.getTag(R.id.item_touch_helper_previous_elevation)
                if (originalElevation == null) {
                    originalElevation = ViewCompat.getElevation(view)
                    val newElevation = 1f + findMaxElevation(recyclerView, view)
                    ViewCompat.setElevation(view, newElevation)
                    view.setTag(R.id.item_touch_helper_previous_elevation, originalElevation)
                }
            }
        }
        view.translationX = dX
        view.translationY = dY
    }

    override fun onDrawOver(
        c: Canvas, recyclerView: RecyclerView, view: View, dX: Float, dY: Float,
        actionState: Int, isCurrentlyActive: Boolean
    ) {
    }

    override fun clearView(view: View) {
        if (Build.VERSION.SDK_INT >= 21) {
            val tag = view.getTag(R.id.item_touch_helper_previous_elevation)
            if (tag is Float) {
                ViewCompat.setElevation(view, tag)
            }
            view.setTag(R.id.item_touch_helper_previous_elevation, null)
        }
        view.translationX = 0f
        view.translationY = 0f
    }

    override fun onSelected(view: View) {}

    companion object {
        val INSTANCE: ItemTouchUIUtil = ItemTouchUtilImpl()
        private fun findMaxElevation(recyclerView: RecyclerView, itemView: View): Float {
            val childCount = recyclerView.childCount
            var max = 0f
            for (i in 0 until childCount) {
                val child = recyclerView.getChildAt(i)
                if (child === itemView) {
                    continue
                }
                val elevation = ViewCompat.getElevation(child)
                if (elevation > max) {
                    max = elevation
                }
            }
            return max
        }
    }
}