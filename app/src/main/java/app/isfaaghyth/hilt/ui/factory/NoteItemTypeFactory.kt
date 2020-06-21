package app.isfaaghyth.hilt.ui.factory

import android.view.View
import app.isfaaghyth.hilt.base.AbstractViewHolder
import app.isfaaghyth.hilt.ui.viewholder.NoteListViewHolder
import app.isfaaghyth.hilt.ui.dataview.NoteDataView

class NoteItemTypeFactory(
    private val onDelete: (Int, NoteDataView) -> Unit
): ItemTypeFactory {

    override fun type(element: NoteDataView): Int {
        return NoteListViewHolder.LAYOUT
    }

    override fun createViewHolder(parent: View, type: Int): AbstractViewHolder<*> {
        return when(type) {
            NoteListViewHolder.LAYOUT -> NoteListViewHolder(parent, onDelete)
            else -> createViewHolder(parent, type)
        }
    }

}