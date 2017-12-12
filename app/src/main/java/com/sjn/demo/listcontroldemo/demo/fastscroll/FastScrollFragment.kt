package com.sjn.demo.listcontroldemo.demo.fastscroll

import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.sjn.demo.listcontroldemo.R
import com.sjn.demo.listcontroldemo.demo.base.BaseFragment
import com.sjn.demo.listcontroldemo.demo.base.BaseItem
import eu.davidea.fastscroller.FastScroller
import eu.davidea.flexibleadapter.SelectableAdapter
import eu.davidea.flexibleadapter.common.SmoothScrollLinearLayoutManager
import eu.davidea.flexibleadapter.items.AbstractFlexibleItem
import java.util.*

class FastScrollFragment : BaseFragment(), FastScroller.OnScrollStateChangeListener {
    override fun onFastScrollerStateChange(scrolling: Boolean) {
        if (scrolling) {
            hideFab()
        } else {
            showFab()
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val rootView = inflater.inflate(R.layout.fragment_fast_scroll, container, false)
        mAdapter = FastScrollAdapter(createItemList(), this)
        mRecyclerView = rootView?.findViewById(R.id.recycler_view)
        mRecyclerView?.let {
            it.layoutManager = SmoothScrollLinearLayoutManager(context)
            it.adapter = mAdapter
        }
        mAdapter?.let {
            it.mode = SelectableAdapter.Mode.SINGLE
            it.fastScroller = setUpFastScroll(rootView)
        }
        showFab()
        return rootView
    }

    override fun createItemList(): List<AbstractFlexibleItem<*>> {
        val itemList = ArrayList<AbstractFlexibleItem<*>>()
        for (index in 1..100) {
            itemList.add(BaseItem())
        }
        return itemList
    }

    private fun setUpFastScroll(rootView: View): FastScroller {
        val fastScroller = rootView.findViewById<View>(R.id.fast_scroller) as FastScroller
        fastScroller.isAutoHideEnabled = false
        fastScroller.addOnScrollStateChangeListener(this)
        fastScroller.setBubbleAndHandleColor(Color.RED)
        return fastScroller
    }
}