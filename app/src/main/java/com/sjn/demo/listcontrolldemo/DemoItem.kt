package com.sjn.demo.listcontrolldemo

import android.view.View

import eu.davidea.flexibleadapter.FlexibleAdapter
import eu.davidea.flexibleadapter.items.AbstractFlexibleItem
import eu.davidea.viewholders.FlexibleViewHolder

class DemoItem : AbstractFlexibleItem<DemoItem.DemoViewHolder>() {

    override fun equals(other: Any?): Boolean {
        return false
    }

    override fun hashCode(): Int {
        return javaClass.hashCode()
    }

    override fun getLayoutRes(): Int {
        return R.layout.demo_item
    }

    override fun createViewHolder(view: View, adapter: FlexibleAdapter<*>): DemoItem.DemoViewHolder {
        return DemoViewHolder(view, adapter)
    }

    override fun bindViewHolder(adapter: FlexibleAdapter<*>, holder: DemoItem.DemoViewHolder, position: Int, payloads: List<*>) {

    }

    class DemoViewHolder(view: View, adapter: FlexibleAdapter<*>) : FlexibleViewHolder(view, adapter)
}