package app.isfaaghyth.hilt.ui.main

import android.app.Activity
import android.content.Intent
import android.content.res.Configuration
import android.content.res.Configuration.UI_MODE_NIGHT_MASK
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import app.isfaaghyth.hilt.R
import app.isfaaghyth.hilt.base.BaseListAdapter
import app.isfaaghyth.hilt.ui.dataview.NewNoteDataView
import app.isfaaghyth.hilt.ui.dataview.NoteDataView
import app.isfaaghyth.hilt.ui.detail.NoteDetailActivity
import app.isfaaghyth.hilt.ui.detail.NoteDetailActivity.Companion.KEY_NOTE
import app.isfaaghyth.hilt.ui.detail.NoteDetailActivity.Companion.REQUEST_CODE
import app.isfaaghyth.hilt.ui.factory.NoteItemTypeFactory
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.layout_empty_state.*
import kotlin.LazyThreadSafetyMode.NONE

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private val viewModel: MainViewModel by viewModels()

    private val gridLayout by lazy { GridLayoutManager(applicationContext, 2) }
    private val linearLayout by lazy { LinearLayoutManager(applicationContext) }

    private val _adapter by lazy(NONE) {
        BaseListAdapter(NoteItemTypeFactory(
            ::onNoteDeleted,
            ::onNoteClicked
        ))
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        lstNote.adapter = _adapter
        initObservable()

        btnAddNote.setOnClickListener {
            NoteDetailActivity.route(this)
        }
    }

    private fun initObservable() {
        viewModel.notes.observe(this, Observer {
            layoutManager(it.isNotEmpty())
            _adapter.addItem(it)
        })
    }

    override fun onActivityResult(
        requestCode: Int,
        resultCode: Int,
        data: Intent?
    ) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == REQUEST_CODE) {
            if (resultCode == Activity.RESULT_OK) {
                data?.let {
                    it.getParcelableExtra(KEY_NOTE) as NewNoteDataView
                }.also {
                    it?.let {
                        viewModel.addNote(
                            it.title,
                            it.note
                        )
                    }
                }
            }
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.mnDarkMode -> {
                val uiMode = resources.configuration.uiMode and UI_MODE_NIGHT_MASK
                AppCompatDelegate.setDefaultNightMode(
                    if (uiMode == Configuration.UI_MODE_NIGHT_NO) {
                        AppCompatDelegate.MODE_NIGHT_YES
                    } else {
                        AppCompatDelegate.MODE_NIGHT_AUTO_BATTERY
                    }
                )
            }
        }
        return super.onOptionsItemSelected(item)
    }

    private fun layoutManager(state: Boolean) {
        applicationContext?.let {
            with(lstNote) {
                layoutManager = if (state) gridLayout else linearLayout
            }
        }
    }

    private fun onNoteClicked(dataView: NoteDataView) {
        NoteDetailActivity.route(this, dataView)
    }

    private fun onNoteDeleted(position: Int, dataView: NoteDataView) {
        _adapter.removeItemByIndex(position, dataView)
        viewModel.deleteNote(dataView)
    }

}
