package app.isfaaghyth.hilt.ui.detail

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import app.isfaaghyth.hilt.R
import app.isfaaghyth.hilt.ui.dataview.NewNoteDataView
import app.isfaaghyth.hilt.ui.dataview.NoteDataView
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.activity_detail.*

@AndroidEntryPoint
class NoteDetailActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        intent?.let {
            it.getParcelableExtra(KEY_NOTE) as? NoteDataView?
        }.also {
            it.let {
                edtTitle.setText(it?.title)
                edtNote.setText(it?.note)
            }
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        onSaveNote()
        return super.onSupportNavigateUp()
    }

    private fun onSaveNote() {
        val title = edtTitle.text.toString()
        val note = edtNote.text.toString()

        setResult(Activity.RESULT_OK, Intent().apply {
            putExtra(KEY_NOTE, NewNoteDataView(
                title = title,
                note = note
            ))
        })

        finish()
    }

    companion object {
        const val REQUEST_CODE = 100
        const val KEY_NOTE = "note"

        fun route(activity: Activity) {
            val intent = Intent(activity, NoteDetailActivity::class.java)
            activity.startActivityForResult(intent, REQUEST_CODE)
        }

        fun route(activity: Activity, note: NoteDataView) {
            activity.startActivity(Intent(activity, NoteDetailActivity::class.java).apply {
                putExtra(KEY_NOTE, note)
            })
        }
    }

}