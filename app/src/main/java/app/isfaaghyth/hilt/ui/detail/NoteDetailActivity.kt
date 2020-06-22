package app.isfaaghyth.hilt.ui.detail

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import app.isfaaghyth.hilt.R
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.activity_detail.*

@AndroidEntryPoint
class NoteDetailActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        btnSave.setOnClickListener {
            val title = edtTitle.text.toString()
            val note = edtNote.text.toString()

            setResult(Activity.RESULT_OK, Intent().apply {
                putExtra("title", title)
                putExtra("note", note)
            })

            finish()
        }
    }

}