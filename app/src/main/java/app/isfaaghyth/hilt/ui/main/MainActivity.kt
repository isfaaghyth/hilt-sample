package app.isfaaghyth.hilt.ui.main

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import app.isfaaghyth.hilt.R
import app.isfaaghyth.hilt.base.BaseListAdapter
import app.isfaaghyth.hilt.ui.dataview.NoteDataView
import app.isfaaghyth.hilt.ui.factory.NoteItemTypeFactory
import app.isfaaghyth.hilt.util.DarkModeUtil.darkModeDelegate
import app.isfaaghyth.hilt.util.RouteManager
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.activity_main.*

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private val viewModel: MainViewModel by viewModels()

    private val _adapter by lazy {
        BaseListAdapter(NoteItemTypeFactory(
            ::onNoteDeleted
        ))
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initObservable()
        initView()
    }

    private fun initView() {
        lstNote.adapter = _adapter

        btnAddNote.setOnClickListener {
            RouteManager.route(this, "note://add")
        }
    }

    private fun initObservable() {
        viewModel.notes.observe(this, Observer {
            layoutManager(it.isNotEmpty())
            _adapter.addItem(it)
        })
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.mnDarkMode -> {
                darkModeDelegate(resources)
            }
        }
        return super.onOptionsItemSelected(item)
    }

    private fun layoutManager(state: Boolean) {
        // use to switch layoutManager for empty state
        applicationContext?.let {
            with(lstNote) {
                layoutManager = if (state) {
                    GridLayoutManager(it, 2)
                } else {
                    LinearLayoutManager(it)
                }
            }
        }
    }

    private fun onNoteDeleted(position: Int, dataView: NoteDataView) {
        _adapter.removeItemByIndex(position, dataView)
        viewModel.deleteNote(dataView.id)
    }

}
