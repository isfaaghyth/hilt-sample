package app.isfaaghyth.hilt.ui.dataview

import android.os.Parcelable
import app.isfaaghyth.hilt.base.BaseDataView
import app.isfaaghyth.hilt.data.entity.Note
import app.isfaaghyth.hilt.ui.factory.ItemTypeFactory
import kotlinx.android.parcel.Parcelize

@Parcelize
data class NoteDataView(
    val id: Long = 0,
    val title: String = "",
    val note: String = "",
    val date: Long = 0
): BaseDataView(), Parcelable {

    override fun type(typeFactory: ItemTypeFactory): Int {
        return typeFactory.type(this)
    }

    companion object {
        fun mapToDataView(note: Note): NoteDataView {
            return NoteDataView(
                id = note.id,
                title = note.title,
                note = note.note,
                date = note.date
            )
        }

        fun mapToDataView(id: Long = 0, title: String, note: String): Note {
            return Note(
                title = title,
                note = note
            ).also { it.id = id }
        }

        fun mapToDataView(note: List<Note>): List<NoteDataView> {
            return note.map {
                NoteDataView(
                    id = it.id,
                    title = it.title,
                    note = it.note,
                    date = it.date
                )
            }.toList()
        }
    }

}