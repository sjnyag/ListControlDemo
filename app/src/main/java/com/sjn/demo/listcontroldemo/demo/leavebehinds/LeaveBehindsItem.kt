package com.sjn.demo.listcontroldemo.demo.leavebehinds

import android.view.View
import com.sjn.demo.listcontroldemo.R
import com.sjn.demo.listcontroldemo.demo.BaseItem
import eu.davidea.flexibleadapter.FlexibleAdapter
import eu.davidea.viewholders.FlexibleViewHolder
import org.joda.time.LocalDate

class LeaveBehindsItem(date: LocalDate) : BaseItem(date) {
    init {
        isSwipeable = true
    }

    override fun getLayoutRes(): Int {
        return R.layout.item_leave_behinds
    }

    override fun createViewHolder(view: View, adapter: FlexibleAdapter<*>): FlexibleViewHolder {
        return LeaveBehindsViewHolder(view, adapter)
    }

    override fun bindViewHolder(adapter: FlexibleAdapter<*>, holder: FlexibleViewHolder, position: Int, payloads: List<*>) {
        if (holder is BaseViewHolder) {
            holder.title.text = title
        }
    }

    internal class LeaveBehindsViewHolder(view: View, adapter: FlexibleAdapter<*>) : BaseViewHolder(view, adapter) {
        private var frontView = view.findViewById<View>(R.id.front_view) as View
        private var rearLeftView = view.findViewById<View>(R.id.rear_left_view) as View
        private var rearRightView = view.findViewById<View>(R.id.rear_right_view) as View

        override fun getFrontView(): View {
            return frontView
        }

        override fun getRearLeftView(): View {
            return rearLeftView
        }

        override fun getRearRightView(): View {
            return rearRightView
        }

    }
}