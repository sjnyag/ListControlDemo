package com.sjn.demo.listcontroldemo.demo.reorder

import android.view.View
import android.widget.ImageView
import com.sjn.demo.listcontroldemo.R
import com.sjn.demo.listcontroldemo.demo.BaseItem
import eu.davidea.flexibleadapter.FlexibleAdapter
import eu.davidea.viewholders.FlexibleViewHolder
import org.joda.time.LocalDate

class ReorderItem(date: LocalDate) : BaseItem(date) {
    init {
        isDraggable = true
    }

    override fun getLayoutRes(): Int {
        return R.layout.item_reorder
    }

    override fun createViewHolder(view: View, adapter: FlexibleAdapter<*>): FlexibleViewHolder {
        return ReorderViewHolder(view, adapter)
    }

    override fun bindViewHolder(adapter: FlexibleAdapter<*>, holder: FlexibleViewHolder, position: Int, payloads: List<*>) {
        if (holder is BaseViewHolder) {
            holder.title.text = title
        }
    }


    internal class ReorderViewHolder(view: View, adapter: FlexibleAdapter<*>) : BaseViewHolder(view, adapter) {
        private var mHandleView = view.findViewById<View>(R.id.handle) as ImageView

        init {
            setDragHandleView(mHandleView)
        }

        override fun setDragHandleView(view: View) {
            if (mAdapter.isHandleDragEnabled) {
                view.visibility = View.VISIBLE
                super.setDragHandleView(view)
            } else {
                view.visibility = View.GONE
            }
        }

    }
}