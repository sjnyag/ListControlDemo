package com.sjn.demo.listcontroldemo.demo.actionmode

import android.view.View
import com.sjn.demo.listcontroldemo.demo.base.BaseItem
import eu.davidea.flexibleadapter.FlexibleAdapter
import eu.davidea.viewholders.FlexibleViewHolder

class ActionModeItem : BaseItem() {

    override fun createViewHolder(view: View, adapter: FlexibleAdapter<*>): FlexibleViewHolder {
        return DemoViewHolder(view, adapter)
    }

    class DemoViewHolder(view: View, adapter: FlexibleAdapter<*>) : FlexibleViewHolder(view, adapter) {

        override fun getActivationElevation(): Float {
            return 40f
        }

    }
}