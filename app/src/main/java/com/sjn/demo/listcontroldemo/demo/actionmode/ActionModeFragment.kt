package com.sjn.demo.listcontroldemo.demo.actionmode

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.view.ActionMode
import android.view.*
import com.sjn.demo.listcontroldemo.R
import com.sjn.demo.listcontroldemo.demo.base.BaseFragment
import eu.davidea.flexibleadapter.FlexibleAdapter
import eu.davidea.flexibleadapter.SelectableAdapter
import eu.davidea.flexibleadapter.helpers.ActionModeHelper
import eu.davidea.flexibleadapter.items.AbstractFlexibleItem
import java.util.*

class ActionModeFragment : BaseFragment(), ActionMode.Callback,
        FlexibleAdapter.OnItemClickListener,
        FlexibleAdapter.OnItemLongClickListener {

    private var mActionModeHelper: ActionModeHelper? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val rootView = super.onCreateView(inflater, container, savedInstanceState) ?: return null
        initializeActionModeHelper(SelectableAdapter.Mode.SINGLE)
        return rootView
    }

    override fun createItemList(): List<AbstractFlexibleItem<*>> {
        val itemList = ArrayList<AbstractFlexibleItem<*>>()
        for (index in 1..10) {
            val item = ActionModeItem()
            itemList.add(item)
        }
        return itemList
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