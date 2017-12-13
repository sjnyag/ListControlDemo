package com.sjn.demo.listcontroldemo.demo.endlessscroll

import android.os.Bundle
import android.os.Handler
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.sjn.demo.listcontroldemo.R
import com.sjn.demo.listcontroldemo.demo.BaseFragment
import com.sjn.demo.listcontroldemo.demo.BaseItem
import com.sjn.demo.listcontroldemo.demo.fastscroll.FastScrollAdapter
import eu.davidea.flexibleadapter.FlexibleAdapter
import eu.davidea.flexibleadapter.common.SmoothScrollLinearLayoutManager

open class EndlessScrollFragment : BaseFragment(), FlexibleAdapter.EndlessScrollListener {

    private var mProgressItem = ProgressItem()

    override fun noMoreLoad(newItemsSize: Int) {

    }

    override fun onLoadMore(lastPosition: Int, currentPage: Int) {
        Handler().postDelayed({
            if (mAdapter != null) {
                val item = mAdapter!!.currentItems[mAdapter!!.currentItems.lastIndex - 1]
                if (item is BaseItem) {
                    val newItems = dateList(10, item.date.minusDays(1)).map { BaseItem(it) }
                    mAdapter?.onLoadMoreComplete(newItems, 3000L)
                }
            }
        }, 2000L)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val rootView = inflater.inflate(R.layout.fragment_fast_scroll, container, false)
        mAdapter = FastScrollAdapter(dateList(30).map { BaseItem(it) }, this)
        mRecyclerView = rootView?.findViewById(R.id.recycler_view)
        mRecyclerView?.let {
            it.layoutManager = SmoothScrollLinearLayoutManager(context)
            it.adapter = mAdapter
        }
        mAdapter?.setEndlessScrollListener(this, mProgressItem)
        setUpActivity()
        showFab()
        return rootView
    }
}