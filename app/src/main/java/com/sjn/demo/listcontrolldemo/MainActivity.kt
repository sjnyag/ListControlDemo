package com.sjn.demo.listcontrolldemo

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.RecyclerView
import eu.davidea.flexibleadapter.common.SmoothScrollLinearLayoutManager
import eu.davidea.flexibleadapter.items.AbstractFlexibleItem
import java.util.*

class MainActivity : AppCompatActivity() {

    private var mRecyclerView: RecyclerView? = null
    private var mAdapter: DemoAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        mAdapter = DemoAdapter(createItemList(), this)
        mRecyclerView = findViewById(R.id.recycler_view)
        mRecyclerView?.let {
            it.layoutManager = SmoothScrollLinearLayoutManager(this)
            it.adapter = mAdapter
        }
    }

    private fun createItemList(): List<AbstractFlexibleItem<*>> {
        val itemList = ArrayList<AbstractFlexibleItem<*>>()
        for (index in 1..10) {
            val item = DemoItem()
            itemList.add(item)
        }
        return itemList
    }
}
