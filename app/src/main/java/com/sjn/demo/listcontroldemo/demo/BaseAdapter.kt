package com.sjn.demo.listcontroldemo.demo

import eu.davidea.flexibleadapter.FlexibleAdapter
import eu.davidea.flexibleadapter.items.AbstractFlexibleItem

open class BaseAdapter(items: List<AbstractFlexibleItem<*>>, listeners: Any) : FlexibleAdapter<AbstractFlexibleItem<*>>(items, listeners, true)