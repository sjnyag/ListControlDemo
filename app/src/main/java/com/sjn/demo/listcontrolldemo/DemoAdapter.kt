package com.sjn.demo.listcontrolldemo

import eu.davidea.flexibleadapter.FlexibleAdapter
import eu.davidea.flexibleadapter.items.AbstractFlexibleItem

class DemoAdapter(items: List<AbstractFlexibleItem<*>>, listeners: Any) : FlexibleAdapter<AbstractFlexibleItem<*>>(items, listeners, true) {

    override fun onCreateBubbleText(position: Int): String {
        var position = position
        position -= when {
            position < scrollableHeaders.size -> return "Top"
            position >= itemCount - scrollableFooters.size -> return "Bottom"
            else -> scrollableHeaders.size
        }
        return super.onCreateBubbleText(position)
    }
}