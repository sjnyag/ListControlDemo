package com.sjn.demo.listcontroldemo.demo.swipetorefresh

import android.graphics.Color
import android.os.Bundle
import android.os.Handler
import android.support.v4.widget.SwipeRefreshLayout
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.sjn.demo.listcontroldemo.R
import com.sjn.demo.listcontroldemo.demo.BaseAdapter
import com.sjn.demo.listcontroldemo.demo.BaseFragment
import com.sjn.demo.listcontroldemo.demo.BaseItem
import eu.davidea.flexibleadapter.common.SmoothScrollLinearLayoutManager
import org.joda.time.LocalDate

open class SwipeToRefreshFragment : BaseFragment(), SwipeRefreshLayout.OnRefreshListener {

    private var count = 0

    override fun onRefresh() {
        Handler().postDelayed({
            mSwipeRefreshLayout?.isRefreshing = false
            mAdapter?.let {
                count++
                it.updateDataSet(dateList(30 + count, LocalDate.now().plusDays(count)).map { BaseItem(it) })
            }
        }, 2000L)
    }

    private var mSwipeRefreshLayout: SwipeRefreshLayout? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val rootView = inflater.inflate(R.layout.fragment_swipe_to_refresh, container, false)
        mAdapter = BaseAdapter(dateList(30).map { BaseItem(it) }, this)
        mRecyclerView = rootView?.findViewById(R.id.recycler_view)
        mRecyclerView?.let {
            it.layoutManager = SmoothScrollLinearLayoutManager(context)
            it.adapter = mAdapter
        }
        mSwipeRefreshLayout = rootView?.findViewById(R.id.swipe_refresh_layout)
        mSwipeRefreshLayout?.let {
            it.setOnRefreshListener(this)
            it.setColorSchemeColors(Color.RED, Color.GREEN, Color.BLUE, Color.YELLOW)
        }
        setUpActivity()
        showFab()
        return rootView
    }

}