package app.isfaaghyth.hilt.ui.dataview

import app.isfaaghyth.hilt.data.entity.Note

data class NoteDataView(
    val title: String,
    val note: String,
    val date: String
) {

    companion object {
        fun mapToDataView(note: List<Note>?): List<NoteDataView>? {
            return note?.map {
                NoteDataView(
                    title = it.title,
                    note = it.note,
                    date = it.getDateFormatted()
                )
            }?.toList()
        }

        fun mapToDataView(noteDataView: NoteDataView): Note {
            return Note(
                title = noteDataView.title,
                note = noteDataView.note
            )
        }
    }

}