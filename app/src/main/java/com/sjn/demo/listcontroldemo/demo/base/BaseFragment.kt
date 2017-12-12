package com.sjn.demo.listcontroldemo.demo.base

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.sjn.demo.listcontroldemo.R
import eu.davidea.flexibleadapter.SelectableAdapter.Mode
import eu.davidea.flexibleadapter.common.SmoothScrollLinearLayoutManager
import eu.davidea.flexibleadapter.items.AbstractFlexibleItem
import java.util.*

open class BaseFragment : Fragment() {

    protected var mRecyclerView: RecyclerView? = null
    protected var mAdapter: BaseAdapter? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val rootView = inflater.inflate(R.layout.fragment_base, container, false)
        mAdapter = BaseAdapter(createItemList(), this)
        mAdapter?.let {
            it.mode = Mode.SINGLE
        }
        mRecyclerView = rootView?.findViewById(R.id.recycler_view)
        mRecyclerView?.let {
            it.layoutManager = SmoothScrollLinearLayoutManager(context)
            it.adapter = mAdapter
        }
        return rootView
    }

    protected open fun createItemList(): List<AbstractFlexibleItem<*>> {
        val itemList = ArrayList<AbstractFlexibleItem<*>>()
        for (index in 1..10) {
            val item = BaseItem()
            itemList.add(item)
        }
        return itemList
    }
}
