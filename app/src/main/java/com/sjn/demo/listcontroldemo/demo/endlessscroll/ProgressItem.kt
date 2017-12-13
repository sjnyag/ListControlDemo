package com.sjn.demo.listcontroldemo.demo.endlessscroll

import android.view.View
import android.widget.ProgressBar
import android.widget.TextView
import com.sjn.demo.listcontroldemo.R
import eu.davidea.flexibleadapter.FlexibleAdapter
import eu.davidea.flexibleadapter.Payload
import eu.davidea.flexibleadapter.items.AbstractFlexibleItem
import eu.davidea.viewholders.FlexibleViewHolder

class ProgressItem : AbstractFlexibleItem<ProgressItem.ProgressViewHolder>() {

    private var status = StatusEnum.MORE_TO_LOAD

    override fun equals(other: Any?): Boolean {
        return this === other
    }

    override fun hashCode(): Int {
        return status.hashCode()
    }

    override fun getLayoutRes(): Int {
        return R.layout.item_progress
    }

    override fun createViewHolder(view: View, adapter: FlexibleAdapter<*>): ProgressViewHolder {
        return ProgressViewHolder(view, adapter)
    }

    override fun bindViewHolder(adapter: FlexibleAdapter<*>, holder: ProgressViewHolder,
                                position: Int, payloads: List<*>) {
        holder.progressBar.visibility = View.GONE
        holder.progressMessage.visibility = View.VISIBLE

        if (!adapter.isEndlessScrollEnabled) {
            status = StatusEnum.DISABLE_ENDLESS
        } else if (payloads.contains(Payload.NO_MORE_LOAD)) {
            status = StatusEnum.NO_MORE_LOAD
        }

        when (this.status) {
            ProgressItem.StatusEnum.NO_MORE_LOAD -> {
                holder.progressMessage.text = "No more items to load. Refresh to retry."
                status = StatusEnum.MORE_TO_LOAD
            }
            ProgressItem.StatusEnum.DISABLE_ENDLESS -> holder.progressMessage.text = "No more data"
            ProgressItem.StatusEnum.ON_CANCEL -> {
                holder.progressMessage.text = "Failed to load data"
                status = StatusEnum.MORE_TO_LOAD
            }
            ProgressItem.StatusEnum.ON_ERROR -> {
                holder.progressMessage.text = "Failed to load data"
                status = StatusEnum.MORE_TO_LOAD
            }
            else -> {
                holder.progressBar.visibility = View.VISIBLE
                holder.progressMessage.visibility = View.GONE
            }
        }
    }

    class ProgressViewHolder(view: View, adapter: FlexibleAdapter<*>) : FlexibleViewHolder(view, adapter) {

        var progressBar: ProgressBar = view.findViewById(R.id.progress_bar)
        var progressMessage: TextView = view.findViewById(R.id.progress_message)
    }

    private enum class StatusEnum {
        MORE_TO_LOAD, //Default = should have an empty Payload
        DISABLE_ENDLESS, //Endless is disabled because user has set limits
        NO_MORE_LOAD, //Non-empty Payload = Payload.NO_MORE_LOAD
        ON_CANCEL,
        ON_ERROR
    }

}