package app.isfaaghyth.hilt.ui.detail

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import app.isfaaghyth.hilt.R
import app.isfaaghyth.hilt.ui.dataview.DetailViewState
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.activity_detail.*

@AndroidEntryPoint
class NoteDetailActivity : AppCompatActivity() {

    private val viewModel: NoteDetailViewModel by viewModels()
    private lateinit var viewState: DetailViewState

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        initObservable()
        getParam()
    }

    private fun getParam() {
        intent?.data?.let {
            Pair(it.host, it.lastPathSegment)
        }?.let {
            // get view state based on appLink's host
            viewState = when (it.first) {
                PAGE_DETAIL -> DetailViewState.Detail
                PAGE_ADD -> DetailViewState.Add
                else -> DetailViewState.None
            }

            // get note id from last segment
            it.second?.let { noteId ->
                viewModel.getNoteById(noteId.toLong())
            }
        }
    }

    private fun initObservable() {
        viewModel.note.observe(this, Observer {
            edtTitle.setText(it.title)
            edtNote.setText(it.note)
        })
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }

    override fun onBackPressed() {
        onDetailDismiss()
        super.onBackPressed()
    }

    private fun onDetailDismiss() {
        val title = edtTitle.text.toString()
        val note = edtNote.text.toString()

        when (viewState) {
            is DetailViewState.Add -> {
                viewModel.addNote(title, note)
            }
            is DetailViewState.Detail -> {
                viewModel.updateNote(title, note)
            }
        }

        finish()
    }

    companion object {
        private const val PAGE_ADD = "add"
        private const val PAGE_DETAIL = "detail"
    }

}