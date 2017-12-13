package com.sjn.demo.listcontroldemo.demo.expand

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.sjn.demo.listcontroldemo.R
import com.sjn.demo.listcontroldemo.demo.BaseAdapter
import com.sjn.demo.listcontroldemo.demo.BaseFragment
import com.sjn.demo.listcontroldemo.demo.BaseItem
import eu.davidea.flexibleadapter.FlexibleAdapter
import eu.davidea.flexibleadapter.common.SmoothScrollLinearLayoutManager
import eu.davidea.flexibleadapter.items.AbstractFlexibleItem
import java.util.*


class ExpandFragment : BaseFragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val rootView = inflater.inflate(R.layout.fragment_base, container, false)
        mAdapter = BaseAdapter(createItemList(), this)
        mRecyclerView = rootView?.findViewById(R.id.recycler_view)
        mRecyclerView?.let {
            it.layoutManager = SmoothScrollLinearLayoutManager(context)
            it.adapter = mAdapter
        }
        mAdapter?.let {
            it.addListener(FlexibleAdapter.OnItemClickListener {
                true
            })
        }
        setUpActivity()
        showFab()
        return rootView
    }

    private fun createItemList(): List<AbstractFlexibleItem<*>> {
        val itemList = ArrayList<AbstractFlexibleItem<*>>()
        var expandItem: ExpandItem? = null
        dateList(100).forEach {
            if (expandItem == null || it.dayOfWeek == 1) {
                expandItem = ExpandItem(it)
                expandItem?.let { itemList.add(it) }
            }
            val expand = itemList.last()
            if (expand is ExpandItem) {
                expand.addSubItem(BaseItem(it))
            }
        }
        return itemList
    }
}