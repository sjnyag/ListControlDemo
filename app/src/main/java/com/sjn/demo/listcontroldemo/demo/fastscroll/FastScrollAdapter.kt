package com.sjn.demo.listcontroldemo.demo.fastscroll

import com.sjn.demo.listcontroldemo.demo.base.BaseAdapter
import eu.davidea.flexibleadapter.items.AbstractFlexibleItem

open class FastScrollAdapter(items: List<AbstractFlexibleItem<*>>, listeners: Any) : BaseAdapter(items, listeners) {
    override fun onCreateBubbleText(position: Int): String {
        return position.toString()
    }
}