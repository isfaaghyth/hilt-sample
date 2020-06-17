package app.isfaaghyth.hilt.ui

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import app.isfaaghyth.hilt.R
import app.isfaaghyth.hilt.ui.adapter.NoteAdapter
import app.isfaaghyth.hilt.ui.dataview.NoteDataView
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.activity_main.*

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private val viewModel: MainViewModel by viewModels()

    private val notes = arrayListOf<NoteDataView>()
    private val noteAdapter by lazy {
        NoteAdapter(notes, ::deleteNote)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initView()
        initObservable()

        btnAddNote.setOnClickListener {
            NewNoteDialog { title, note ->
                viewModel.addNote(title, note)
            }.show(supportFragmentManager, NewNoteDialog.TAG)
        }
    }

    private fun initView() {
        with(lstNote) {
            adapter = noteAdapter
        }
    }

    private fun initObservable() {
        viewModel.notes.observe(this, Observer {
            notes.clear()
            notes.addAll(it)
            noteAdapter.notifyDataSetChanged()
        })
    }

    private fun deleteNote(position: Int, dataView: NoteDataView) {
        viewModel.deleteNote(dataView)
        notes.removeAt(position)
        noteAdapter.notifyDataSetChanged()
    }

}
