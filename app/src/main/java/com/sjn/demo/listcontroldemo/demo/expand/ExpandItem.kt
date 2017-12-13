package com.sjn.demo.listcontroldemo.demo.expand

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import com.sjn.demo.listcontroldemo.R
import com.sjn.demo.listcontroldemo.demo.BaseItem
import eu.davidea.flexibleadapter.FlexibleAdapter
import eu.davidea.flexibleadapter.items.AbstractExpandableItem
import eu.davidea.flexibleadapter.items.IExpandable
import eu.davidea.viewholders.ExpandableViewHolder
import org.joda.time.LocalDate


class ExpandItem(val date: LocalDate) : AbstractExpandableItem<ExpandableViewHolder, BaseItem>(), IExpandable<ExpandableViewHolder, BaseItem> {
    val title = date.toString("yyyy/MM")!!

    override fun equals(other: Any?): Boolean {
        if (other is ExpandItem) {
            return this.title == other.title
        }
        return false
    }

    override fun hashCode(): Int {
        return title.hashCode()
    }

    override fun getLayoutRes(): Int {
        return R.layout.item_expand
    }

    override fun createViewHolder(view: View, adapter: FlexibleAdapter<*>): ExpandableViewHolder {
        return ExpandViewHolder(view, adapter)
    }

    override fun bindViewHolder(adapter: FlexibleAdapter<*>, holder: ExpandableViewHolder, position: Int, payloads: List<*>) {
        if (holder is ExpandViewHolder) {
            holder.title.text = title
        }
    }

    internal class ExpandViewHolder(view: View, adapter: FlexibleAdapter<*>) : ExpandableViewHolder(view, adapter, true) {

        internal var title: TextView = view.findViewById(R.id.title)
        private var expand: ImageView = view.findViewById(R.id.expand)
        private var rotationAngle = 0

        override fun onClick(view: View?) {
            super.onClick(view)
            rotationAngle = if (rotationAngle == 0) 180 else 0
            expand.animate().rotation(rotationAngle.toFloat()).setDuration(500).start()
        }
    }


}