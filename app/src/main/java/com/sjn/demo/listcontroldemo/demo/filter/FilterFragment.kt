package com.sjn.demo.listcontroldemo.demo.filter

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.sjn.demo.listcontroldemo.R
import com.sjn.demo.listcontroldemo.demo.BaseAdapter
import com.sjn.demo.listcontroldemo.demo.BaseFragment
import eu.davidea.flexibleadapter.common.SmoothScrollLinearLayoutManager

class FilterFragment : BaseFragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val rootView = inflater.inflate(R.layout.fragment_base, container, false)
        mAdapter = BaseAdapter(dateList(30).map { FilterItem(it) }, this)
        mRecyclerView = rootView?.findViewById(R.id.recycler_view)
        mRecyclerView?.let {
            it.layoutManager = SmoothScrollLinearLayoutManager(context)
            it.adapter = mAdapter
        }
        showFab()
        setUpActivity()
        return rootView
    }
}