package com.sjn.demo.listcontroldemo.demo.stickyheader

import com.sjn.demo.listcontroldemo.demo.BaseItem
import eu.davidea.flexibleadapter.items.IHeader
import eu.davidea.flexibleadapter.items.ISectionable
import eu.davidea.viewholders.FlexibleViewHolder
import org.joda.time.LocalDate

class SectionItem(date: LocalDate) : BaseItem(date), ISectionable<FlexibleViewHolder, IHeader<*>> {
    private var header: IHeader<*>? = null

    override fun getHeader(): IHeader<*> {
        return header!!
    }

    override fun setHeader(header: IHeader<*>) {
        this.header = header
    }

    constructor(date: LocalDate, header: IHeader<*>) : this(date) {
        setHeader(header)
    }
}