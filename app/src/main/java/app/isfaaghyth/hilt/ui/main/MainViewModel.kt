package app.isfaaghyth.hilt.ui.main

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import app.isfaaghyth.hilt.data.repository.NoteRepository
import app.isfaaghyth.hilt.ui.dataview.NoteDataView
import app.isfaaghyth.hilt.ui.dataview.NoteDataView.Companion.mapToDataView
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

interface MainContract {
    fun getNotes()
    fun deleteNote(id: Long)
}

class MainViewModel @ViewModelInject constructor(
    private val noteRepository: NoteRepository
) : ViewModel(), MainContract {

    private val _notes = MediatorLiveData<List<NoteDataView>>()
    val notes: LiveData<List<NoteDataView>> get() = _notes

    init {
        getNotes()
    }

    override fun getNotes() {
        val notes = noteRepository.notes()
        _notes.addSource(notes) {
            _notes.setValue(mapToDataView(it))
        }
    }

    override fun deleteNote(id: Long) {
        viewModelScope.launch(Dispatchers.IO) {
            noteRepository.delete(id)
        }
    }

}