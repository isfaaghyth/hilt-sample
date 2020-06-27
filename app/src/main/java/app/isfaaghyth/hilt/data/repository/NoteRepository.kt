package app.isfaaghyth.hilt.data.repository

import androidx.lifecycle.LiveData
import app.isfaaghyth.hilt.data.entity.Note

interface NoteRepository {
    fun add(note: Note)
    fun isExist(id: Long): List<Note>
    fun notes(): LiveData<List<Note>>
    fun noteById(id: Long): Note
    fun delete(id: Long)
    fun update(note: Note)
}