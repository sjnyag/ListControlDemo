package com.sjn.demo.listcontroldemo.demo.stickyheader

import android.view.View
import android.widget.TextView
import com.sjn.demo.listcontroldemo.R
import eu.davidea.flexibleadapter.FlexibleAdapter
import eu.davidea.flexibleadapter.items.AbstractFlexibleItem
import eu.davidea.flexibleadapter.items.IHeader
import eu.davidea.viewholders.FlexibleViewHolder
import org.joda.time.LocalDate

class HeaderItem(val date: LocalDate) : AbstractFlexibleItem<FlexibleViewHolder>(), IHeader<FlexibleViewHolder> {
    val title = date.toString("yyyy/MM")!!

    override fun equals(other: Any?): Boolean {
        if (other is HeaderItem) {
            return this.title == other.title
        }
        return false
    }

    override fun hashCode(): Int {
        return title.hashCode()
    }

    override fun getLayoutRes(): Int {
        return R.layout.item_header
    }

    override fun createViewHolder(view: View, adapter: FlexibleAdapter<*>): FlexibleViewHolder {
        return HeaderViewHolder(view, adapter)
    }

    override fun bindViewHolder(adapter: FlexibleAdapter<*>, holder: FlexibleViewHolder, position: Int, payloads: List<*>) {
        if (holder is HeaderViewHolder) {
            holder.title.text = title
        }
    }

    internal class HeaderViewHolder(view: View, adapter: FlexibleAdapter<*>) : FlexibleViewHolder(view, adapter, true) {

        internal var title: TextView = view.findViewById(R.id.title)
    }


}