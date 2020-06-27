package app.isfaaghyth.hilt.ui.detail

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import app.isfaaghyth.hilt.data.repository.NoteRepository
import app.isfaaghyth.hilt.ui.dataview.NoteDataView
import app.isfaaghyth.hilt.ui.dataview.NoteDataView.Companion.mapToDataView
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

interface NoteDetailContract {
    fun getNoteById(id: Long)
    fun setNoteId(id: Long)
    fun addNote(title: String, note: String)
    fun updateNote(title: String, note: String)
}

class NoteDetailViewModel @ViewModelInject constructor(
    private val noteRepository: NoteRepository
) : ViewModel(), NoteDetailContract {

    private val _note = MutableLiveData<NoteDataView>()
    val note: LiveData<NoteDataView> get() = _note

    private val _noteId = MutableLiveData<Long>()
    val noteId: LiveData<Long> get() = _noteId

    override fun setNoteId(id: Long) {
        _noteId.value = id
    }

    override fun getNoteById(id: Long) {
        viewModelScope.launch(Dispatchers.IO) {
            val dataNote = noteRepository.noteById(id)
            withContext(Dispatchers.Main) {
                _note.value = mapToDataView(dataNote)
                setNoteId(id)
            }
        }
    }

    override fun addNote(title: String, note: String) {
        if (note.isEmpty()) return

        viewModelScope.launch(Dispatchers.IO) {
            val data = mapToDataView(
                title = title,
                note = note
            )
            noteRepository.add(data)
        }
    }

    override fun updateNote(title: String, note: String) {
        viewModelScope.launch(Dispatchers.IO) {
            val data = mapToDataView(
                title = title,
                note = note
            )
            noteRepository.update(data)
        }
    }

}