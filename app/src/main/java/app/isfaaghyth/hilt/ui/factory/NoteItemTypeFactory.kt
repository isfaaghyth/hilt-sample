package app.isfaaghyth.hilt.ui.factory

import android.view.View
import app.isfaaghyth.hilt.base.AbstractViewHolder
import app.isfaaghyth.hilt.ui.dataview.EmptyStateDataView
import app.isfaaghyth.hilt.ui.viewholder.NoteListViewHolder
import app.isfaaghyth.hilt.ui.dataview.NoteDataView
import app.isfaaghyth.hilt.ui.viewholder.EmptyStateViewHolder

class NoteItemTypeFactory(
    private val onDelete: (Int, NoteDataView) -> Unit
): ItemTypeFactory {

    override fun type(element: NoteDataView): Int {
        return NoteListViewHolder.LAYOUT
    }

    override fun type(element: EmptyStateDataView): Int {
        return EmptyStateViewHolder.LAYOUT
    }

    override fun createViewHolder(parent: View, type: Int): AbstractViewHolder<*> {
        return when(type) {
            NoteListViewHolder.LAYOUT -> NoteListViewHolder(parent, onDelete)
            EmptyStateViewHolder.LAYOUT -> EmptyStateViewHolder(parent)
            else -> createViewHolder(parent, type)
        }
    }

}