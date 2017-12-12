package com.sjn.demo.listcontroldemo.demo.actionmode

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.view.ActionMode
import android.view.*
import com.sjn.demo.listcontroldemo.R
import com.sjn.demo.listcontroldemo.demo.BaseAdapter
import com.sjn.demo.listcontroldemo.demo.BaseFragment
import eu.davidea.flexibleadapter.FlexibleAdapter
import eu.davidea.flexibleadapter.SelectableAdapter
import eu.davidea.flexibleadapter.common.SmoothScrollLinearLayoutManager
import eu.davidea.flexibleadapter.helpers.ActionModeHelper

class ActionModeFragment : BaseFragment(), ActionMode.Callback,
        FlexibleAdapter.OnItemClickListener,
        FlexibleAdapter.OnItemLongClickListener {

    private var mActionModeHelper: ActionModeHelper? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val rootView = inflater.inflate(R.layout.fragment_base, container, false)
        mAdapter = BaseAdapter(dateList(30).map { ActionModeItem(it) }, this)
        mRecyclerView = rootView?.findViewById(R.id.recycler_view)
        mRecyclerView?.let {
            it.layoutManager = SmoothScrollLinearLayoutManager(context)
            it.adapter = mAdapter
        }
        initializeActionModeHelper(SelectableAdapter.Mode.SINGLE)
        mAdapter?.let {
            it.mode = SelectableAdapter.Mode.SINGLE
        }
        setUpActivity()
        showFab()
        return rootView
    }

    override fun onDestroyView() {
        super.onDestroyView()
        mActionModeHelper?.destroyActionModeIfCan()
    }

    private fun initializeActionModeHelper(@SelectableAdapter.Mode mode: Int) {
        mActionModeHelper = object : ActionModeHelper(mAdapter!!, R.menu.action_mode, this) {
            override fun updateContextTitle(count: Int) {
                mActionMode?.title = count.toString() + " 件"
            }
        }.withDefaultMode(mode)
                .disableDragOnActionMode(true)
                .disableSwipeOnActionMode(true)
        mActionModeHelper!!.withDefaultMode(SelectableAdapter.Mode.SINGLE)
    }

    override fun onItemClick(position: Int): Boolean {
        return if (mAdapter!!.mode != SelectableAdapter.Mode.IDLE && mActionModeHelper != null) {
            mActionModeHelper!!.onClick(position)
        } else {
            false
        }
    }

    override fun onItemLongClick(position: Int) {
        val activity = activity
        if (activity is AppCompatActivity) {
            mActionModeHelper!!.onLongClick(activity, position)
        }
    }

    override fun onActionItemClicked(mode: ActionMode?, item: MenuItem?): Boolean = false

    override fun onCreateActionMode(mode: ActionMode, menu: Menu): Boolean {
        activity?.window?.statusBarColor = resources.getColor(R.color.colorActionModePrimaryDark, activity?.theme)
        return true
    }

    override fun onPrepareActionMode(mode: ActionMode, menu: Menu): Boolean = false

    override fun onDestroyActionMode(mode: ActionMode) {
        activity?.window?.statusBarColor = resources.getColor(R.color.colorPrimaryDark, activity?.theme)
    }
}