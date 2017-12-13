package com.sjn.demo.listcontroldemo.demo.stickyheader

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.sjn.demo.listcontroldemo.R
import com.sjn.demo.listcontroldemo.demo.BaseAdapter
import com.sjn.demo.listcontroldemo.demo.BaseFragment
import eu.davidea.flexibleadapter.common.SmoothScrollLinearLayoutManager
import eu.davidea.flexibleadapter.items.AbstractFlexibleItem
import java.util.*

open class StickyHeaderFragment : BaseFragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val rootView = inflater.inflate(R.layout.fragment_sticky_header, container, false)
        mAdapter = BaseAdapter(createItemList(), this)
        mRecyclerView = rootView?.findViewById(R.id.recycler_view)
        mRecyclerView?.let {
            it.layoutManager = SmoothScrollLinearLayoutManager(context)
            it.adapter = mAdapter
        }
        mAdapter?.let {
            it.setDisplayHeadersAtStartUp(true)
            it.setStickyHeaders(true)
            it.showAllHeaders()
        }
        setUpActivity()
        showFab()
        return rootView
    }

    private fun createItemList(): List<AbstractFlexibleItem<*>> {
        val itemList = ArrayList<AbstractFlexibleItem<*>>()
        var header: HeaderItem? = null
        dateList(100).forEach {
            if (header == null || header!!.date.monthOfYear != it.monthOfYear) {
                header = HeaderItem(it)
            }
            itemList.add(SectionItem(it, header!!))
        }
        return itemList
    }

}