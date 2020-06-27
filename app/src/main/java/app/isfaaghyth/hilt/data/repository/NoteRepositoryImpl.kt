package app.isfaaghyth.hilt.data.repository

import androidx.lifecycle.LiveData
import app.isfaaghyth.hilt.data.entity.Note
import app.isfaaghyth.hilt.data.local.NoteDao
import javax.inject.Inject

class NoteRepositoryImpl @Inject constructor(
    private val database: NoteDao
): NoteRepository {

    override fun add(note: Note) {
        database.add(note)
    }

    override fun notes(): LiveData<List<Note>> {
        return database.notes()
    }

    override fun noteById(id: Long): Note {
        return database.noteById(id)
    }

    override fun isExist(id: Long): List<Note> {
        return database.exist(id)
    }

    override fun delete(id: Long) {
        return database.delete(id)
    }

    override fun update(note: Note) {
        return database.update(note)
    }

}