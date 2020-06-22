package app.isfaaghyth.hilt.ui

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import app.isfaaghyth.hilt.R
import app.isfaaghyth.hilt.base.BaseListAdapter
import app.isfaaghyth.hilt.ui.dataview.NoteDataView
import app.isfaaghyth.hilt.ui.factory.NoteItemTypeFactory
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.activity_main.*
import kotlin.LazyThreadSafetyMode.NONE as NONE

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private val viewModel: MainViewModel by viewModels()

    private val _adapter by lazy(NONE) {
        BaseListAdapter(NoteItemTypeFactory(::deleteNote))
    }

    private val gridLayout by lazy {
        GridLayoutManager(applicationContext, 2)
    }

    private val linearLayout by lazy {
        LinearLayoutManager(applicationContext)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        lstNote.adapter = _adapter

        initObservable()

        btnAddNote.setOnClickListener {
            NewNoteDialog { title, note ->
                viewModel.addNote(title, note)
            }.show(supportFragmentManager, NewNoteDialog.TAG)
        }
    }

    private fun layoutManager(state: Boolean) {
        applicationContext?.let {
            with(lstNote) {
                layoutManager = if (state) gridLayout else linearLayout
            }
        }
    }

    private fun initObservable() {
        viewModel.notes.observe(this, Observer {
            layoutManager(it.isNotEmpty())
            _adapter.addItem(it)
        })
    }

    private fun deleteNote(position: Int, dataView: NoteDataView) {
        _adapter.removeItemByIndex(position, dataView)
        viewModel.deleteNote(dataView)
    }

}
