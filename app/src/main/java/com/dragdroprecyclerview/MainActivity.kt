package com.dragdroprecyclerview

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity(), OnStartDragListener {

    lateinit var adapter: DragDropRecyclerAdapter
    lateinit var touchHelper: ItemTouchHelper_Shadow

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        adapter = DragDropRecyclerAdapter(this)
        populateListItem()

        val callback: ItemTouchHelper_Shadow.Callback = ItemMoveCallbackListener(adapter)

        touchHelper = ItemTouchHelper_Shadow(callback)
        touchHelper.attachToRecyclerView(recyclerView)

        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = adapter
    }

    override fun onStartDrag(viewHolder: RecyclerView.ViewHolder) {
        touchHelper.startDrag(viewHolder)
    }

    private fun populateListItem() {
        val users = listOf(
            "Apple",
            "Cherry",
            "Pear",
            "Apricot",
            "Banana",
            "Mango",
            "Melon",
            "Water Melon",
            "Berry",
            "Straw Berry",
            "Blue Berry",
            "Grape",
            "Peach"
        )
        adapter.setUsers(users)
    }


}
