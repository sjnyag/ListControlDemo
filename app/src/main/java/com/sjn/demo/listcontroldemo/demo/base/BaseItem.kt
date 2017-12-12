package com.sjn.demo.listcontroldemo.demo.base

import android.view.View
import com.sjn.demo.listcontroldemo.R
import eu.davidea.flexibleadapter.FlexibleAdapter
import eu.davidea.flexibleadapter.items.AbstractFlexibleItem
import eu.davidea.viewholders.FlexibleViewHolder

open class BaseItem : AbstractFlexibleItem<FlexibleViewHolder>() {

    override fun equals(other: Any?): Boolean {
        return false
    }

    override fun hashCode(): Int {
        return javaClass.hashCode()
    }

    override fun getLayoutRes(): Int {
        return R.layout.demo_item
    }

    override fun createViewHolder(view: View, adapter: FlexibleAdapter<*>): FlexibleViewHolder {
        return DemoViewHolder(view, adapter)
    }

    override fun bindViewHolder(adapter: FlexibleAdapter<*>, holder: FlexibleViewHolder, position: Int, payloads: List<*>) {

    }

    class DemoViewHolder(view: View, adapter: FlexibleAdapter<*>) : FlexibleViewHolder(view, adapter)
}