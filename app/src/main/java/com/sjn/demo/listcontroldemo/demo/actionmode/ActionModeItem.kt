package com.sjn.demo.listcontroldemo.demo.actionmode

import android.support.v4.content.ContextCompat
import com.sjn.demo.listcontroldemo.R
import com.sjn.demo.listcontroldemo.demo.BaseItem
import eu.davidea.flexibleadapter.FlexibleAdapter
import eu.davidea.flexibleadapter.utils.DrawableUtils
import eu.davidea.viewholders.FlexibleViewHolder
import org.joda.time.LocalDate

class ActionModeItem(date: LocalDate) : BaseItem(date) {

    override fun bindViewHolder(adapter: FlexibleAdapter<*>, holder: FlexibleViewHolder, position: Int, payloads: List<*>) {
        super.bindViewHolder(adapter, holder, position, payloads)
        if (payloads.isEmpty()) {
            val drawable = DrawableUtils.getSelectableBackgroundCompat(
                    ContextCompat.getColor(holder.itemView.context, R.color.itemBackground),
                    ContextCompat.getColor(holder.itemView.context, R.color.itemSelected),
                    ContextCompat.getColor(holder.itemView.context, R.color.itemRipple))
            DrawableUtils.setBackgroundCompat(holder.itemView, drawable)
            DrawableUtils.setBackgroundCompat(holder.frontView, drawable)
        }
    }
}