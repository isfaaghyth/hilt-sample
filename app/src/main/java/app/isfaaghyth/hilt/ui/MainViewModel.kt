package app.isfaaghyth.hilt.ui

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.ViewModel
import app.isfaaghyth.hilt.data.entity.Note
import app.isfaaghyth.hilt.data.repository.NoteRepository
import app.isfaaghyth.hilt.ui.dataview.NoteDataView
import app.isfaaghyth.hilt.ui.dataview.NoteDataView.Companion.mapToDataView
import app.isfaaghyth.hilt.util.ObservableHandler.IO

interface MainContract {
    fun addNote(title: String, note: String)
    fun deleteNote(note: NoteDataView)
    fun getNotes()
}

class MainViewModel @ViewModelInject constructor(
    private val noteRepository: NoteRepository
) : ViewModel(), MainContract {

    private val _notes = MediatorLiveData<List<NoteDataView>>()
    val notes: LiveData<List<NoteDataView>> get() = _notes

    init {
        getNotes()
    }

    override fun addNote(title: String, note: String) {
        IO.execute {
            noteRepository.addNote(
                Note(
                    title = title,
                    note = note
                )
            )
        }
    }

    override fun getNotes() {
        val notes = noteRepository.getNotes()
        _notes.addSource(notes) {
            _notes.value = mapToDataView(it)
        }
    }

    override fun deleteNote(note: NoteDataView) {
        IO.execute {
            noteRepository.deleteNote(
                mapToDataView(note)
            )
        }
    }

}