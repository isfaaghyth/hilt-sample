package app.isfaaghyth.hilt.ui.factory

import android.view.View
import app.isfaaghyth.hilt.base.AbstractViewHolder
import app.isfaaghyth.hilt.ui.dataview.EmptyStateDataView
import app.isfaaghyth.hilt.ui.dataview.NoteDataView

interface ItemTypeFactory {
    fun type(element: NoteDataView): Int
    fun type(element: EmptyStateDataView): Int
    fun createViewHolder(parent: View, type: Int): AbstractViewHolder<*>
}