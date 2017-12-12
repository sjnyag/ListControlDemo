package com.sjn.demo.listcontroldemo.demo.filter

import com.sjn.demo.listcontroldemo.demo.BaseItem
import eu.davidea.flexibleadapter.FlexibleAdapter
import eu.davidea.flexibleadapter.items.IFilterable
import eu.davidea.flexibleadapter.utils.FlexibleUtils
import eu.davidea.viewholders.FlexibleViewHolder
import org.joda.time.LocalDate

class FilterItem(date: LocalDate) : BaseItem(date), IFilterable {
    override fun filter(constraint: String?): Boolean {
        if (constraint == null) {
            return true
        }
        return title.toLowerCase().trim({ it <= ' ' }).contains(constraint)
    }

    override fun bindViewHolder(adapter: FlexibleAdapter<*>, holder: FlexibleViewHolder, position: Int, payloads: List<*>) {
        if (holder is BaseViewHolder) {
            if (adapter.hasSearchText()) {
                FlexibleUtils.highlightText(holder.title, title, adapter.searchText)
            } else {
                holder.title.text = title
            }
        }
    }
}