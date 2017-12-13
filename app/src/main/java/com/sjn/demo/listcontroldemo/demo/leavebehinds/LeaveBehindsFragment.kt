package com.sjn.demo.listcontroldemo.demo.leavebehinds

import android.os.Bundle
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.sjn.demo.listcontroldemo.R
import com.sjn.demo.listcontroldemo.demo.BaseAdapter
import com.sjn.demo.listcontroldemo.demo.BaseFragment
import eu.davidea.flexibleadapter.FlexibleAdapter
import eu.davidea.flexibleadapter.common.SmoothScrollLinearLayoutManager
import eu.davidea.flexibleadapter.helpers.UndoHelper

open class LeaveBehindsFragment : BaseFragment(), FlexibleAdapter.OnItemSwipeListener, UndoHelper.OnUndoListener {
    override fun onActionConfirmed(action: Int, event: Int) {
    }

    override fun onActionCanceled(action: Int) {
        mAdapter?.restoreDeletedItems()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val rootView = inflater.inflate(R.layout.fragment_base, container, false)
        mAdapter = BaseAdapter(dateList(30).map { LeaveBehindsItem(it) }, this)
        mRecyclerView = rootView?.findViewById(R.id.recycler_view)
        mRecyclerView?.let {
            it.layoutManager = SmoothScrollLinearLayoutManager(context)
            it.adapter = mAdapter
        }
        mAdapter?.isSwipeEnabled = true
        setUpActivity()
        showFab()
        return rootView
    }

    override fun onItemSwipe(position: Int, direction: Int) {
        mAdapter?.let {
            val positions = mutableListOf(position)
            val abstractItem = it.getItem(position)
            val message = StringBuilder()
            message.append(abstractItem.toString()).append(" ")
            if (abstractItem!!.isSelectable) {
                it.setRestoreSelectionOnUndo(false)
            }
            message.append("Deleted")
            it.isPermanentDelete = false
            this@LeaveBehindsFragment.activity?.let {
                UndoHelper(mAdapter, this@LeaveBehindsFragment)
                        .withPayload(null)
                        .withConsecutive(true)
                        .start(positions, it.findViewById(R.id.coordinatorLayout), message, "Undo", UndoHelper.UNDO_TIMEOUT)
            }

        }

    }

    override fun onActionStateChanged(viewHolder: RecyclerView.ViewHolder?, actionState: Int) {
    }

}