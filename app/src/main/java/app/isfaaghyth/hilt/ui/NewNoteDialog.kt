package app.isfaaghyth.hilt.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import app.isfaaghyth.hilt.R
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import kotlinx.android.synthetic.main.dialog_new_note.*

internal open class NewNoteDialog(
    val onSave: (title: String, note: String) -> Unit
): BottomSheetDialogFragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.dialog_new_note, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        addNote()
    }

    private fun addNote() {
        btnSave.setOnClickListener {
            val title = edtTitle.text.toString()
            val note = edtNote.text.toString()

            onSave(title, note)
            dismiss()
        }
    }

    companion object {
        const val TAG = "NewNoteDialog"
    }

}