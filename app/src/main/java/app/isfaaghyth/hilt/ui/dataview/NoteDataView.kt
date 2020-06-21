package app.isfaaghyth.hilt.ui.dataview

import app.isfaaghyth.hilt.base.BaseDataView
import app.isfaaghyth.hilt.data.entity.Note
import app.isfaaghyth.hilt.ui.factory.ItemTypeFactory

data class NoteDataView(
    val id: Long,
    val title: String,
    val note: String,
    val date: String
): BaseDataView() {

    override fun type(typeFactory: ItemTypeFactory): Int {
        return typeFactory.type(this)
    }

    companion object {
        fun mapToDataView(note: List<Note>?): List<NoteDataView>? {
            return note?.map {
                NoteDataView(
                    id = it.id,
                    title = it.title,
                    note = it.note,
                    date = it.date
                )
            }?.toList()
        }

        fun mapToDataView(noteDataView: NoteDataView): Note {
            return Note(
                title = noteDataView.title,
                note = noteDataView.note,
                date = noteDataView.date
            ).also { it.id = noteDataView.id }
        }
    }

}