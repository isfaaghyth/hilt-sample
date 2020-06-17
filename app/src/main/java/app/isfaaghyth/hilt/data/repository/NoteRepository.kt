package app.isfaaghyth.hilt.data.repository

import androidx.lifecycle.LiveData
import app.isfaaghyth.hilt.data.entity.Note

interface NoteRepository {
    fun addNote(note: Note)
    fun getNotes(): LiveData<List<Note>>
    fun deleteNote(note: Note)
}