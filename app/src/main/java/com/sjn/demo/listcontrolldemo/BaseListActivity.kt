package com.sjn.demo.listcontrolldemo

import android.os.Bundle
import android.support.v7.widget.RecyclerView
import eu.davidea.flexibleadapter.SelectableAdapter.Mode
import eu.davidea.flexibleadapter.common.SmoothScrollLinearLayoutManager
import eu.davidea.flexibleadapter.items.AbstractFlexibleItem
import java.util.*


abstract class BaseListActivity : DrawerActivity() {

    protected var mRecyclerView: RecyclerView? = null
    protected var mAdapter: DemoAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        mAdapter = DemoAdapter(createItemList(), this)
        mAdapter?.let {
            it.mode = Mode.SINGLE
        }
        mRecyclerView = findViewById(R.id.recycler_view)
        mRecyclerView?.let {
            it.layoutManager = SmoothScrollLinearLayoutManager(this)
            it.adapter = mAdapter
        }
        initializeToolbar()
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
