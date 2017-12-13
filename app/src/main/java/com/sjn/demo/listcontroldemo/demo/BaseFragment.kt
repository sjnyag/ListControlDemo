package com.sjn.demo.listcontroldemo.demo

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.DefaultItemAnimator
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.sjn.demo.listcontroldemo.R
import com.sjn.demo.listcontroldemo.activity.MainActivity
import eu.davidea.flexibleadapter.common.SmoothScrollLinearLayoutManager
import org.joda.time.LocalDate
import java.util.*

open class BaseFragment : Fragment() {

    protected var mRecyclerView: RecyclerView? = null
    protected var mAdapter: BaseAdapter? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val rootView = inflater.inflate(R.layout.fragment_base, container, false)
        mAdapter = BaseAdapter(dateList(30).map { BaseItem(it) }, this)
        mRecyclerView = rootView?.findViewById(R.id.recycler_view)
        mRecyclerView?.let {
            it.layoutManager = SmoothScrollLinearLayoutManager(context)
            it.adapter = mAdapter
            it.itemAnimator = DefaultItemAnimator()
        }
        setUpActivity()
        showFab()
        return rootView
    }

    fun dateList(days: Int): List<LocalDate> {
        val dateList = ArrayList<LocalDate>()
        var date = LocalDate.now()
        while (date.isAfter(LocalDate.now().minusDays(days))) {
            dateList.add(date)
            date = date.minusDays(1)
        }
        return dateList
    }

    fun showFab() {
        val activity = activity as? MainActivity ?: return
        activity.showFab()
    }

    fun hideFab() {
        val activity = activity as? MainActivity ?: return
        activity.hideFab()
    }

    fun setUpActivity() {
        val activity = activity as? MainActivity ?: return
        activity.mAdapter = mAdapter
    }
}
