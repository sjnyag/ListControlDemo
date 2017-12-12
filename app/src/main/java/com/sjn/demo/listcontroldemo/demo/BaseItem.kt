package com.sjn.demo.listcontroldemo.demo

import android.view.View
import android.widget.TextView
import com.sjn.demo.listcontroldemo.R
import eu.davidea.flexibleadapter.FlexibleAdapter
import eu.davidea.flexibleadapter.items.AbstractFlexibleItem
import eu.davidea.viewholders.FlexibleViewHolder
import org.joda.time.LocalDate

open class BaseItem(date: LocalDate) : AbstractFlexibleItem<FlexibleViewHolder>() {
    val title = date.toString("yyyy/MM/dd")!!

    override fun equals(other: Any?): Boolean {
        if (other is BaseItem) {
            return this.title == other.title
        }
        return false
    }

    override fun hashCode(): Int {
        return title.hashCode()
    }

    override fun getLayoutRes(): Int {
        return R.layout.item_demo
    }

    override fun createViewHolder(view: View, adapter: FlexibleAdapter<*>): FlexibleViewHolder {
        return BaseViewHolder(view, adapter)
    }

    override fun bindViewHolder(adapter: FlexibleAdapter<*>, holder: FlexibleViewHolder, position: Int, payloads: List<*>) {
        if (holder is BaseViewHolder) {
            holder.title.text = title
        }
    }

    open class BaseViewHolder(view: View, adapter: FlexibleAdapter<*>) : FlexibleViewHolder(view, adapter) {

        internal var title: TextView = view.findViewById(R.id.title)
    }

}