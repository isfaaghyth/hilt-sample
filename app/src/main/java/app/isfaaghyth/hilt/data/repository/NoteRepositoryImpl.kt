package app.isfaaghyth.hilt.data.repository

import androidx.lifecycle.LiveData
import app.isfaaghyth.hilt.data.entity.Note
import app.isfaaghyth.hilt.data.local.NoteDao
import javax.inject.Inject

class NoteRepositoryImpl @Inject constructor(
    private val database: NoteDao
): NoteRepository {

    override fun addNote(note: Note) {
        database.addNote(note)
    }

    override fun getNotes(): LiveData<List<Note>> {
        return database.getNotes()
    }

    override fun deleteNote(note: Note) {
        return database.deleteNote(note)
    }

}