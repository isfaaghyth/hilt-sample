package app.isfaaghyth.hilt.ui.adapter.viewholder

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import app.isfaaghyth.hilt.R
import app.isfaaghyth.hilt.ui.dataview.NoteDataView

class NoteViewHolder(
    view: View,
    private val onDelete: (Int, NoteDataView) -> Unit
): RecyclerView.ViewHolder(view) {

    private val txtTime: TextView = view.findViewById(R.id.txtTime)
    private val txtTitle: TextView = view.findViewById(R.id.txtTitle)
    private val txtNote: TextView = view.findViewById(R.id.txtNote)
    private val btnDelete: ImageView = view.findViewById(R.id.btnDelete)

    fun bind(note: NoteDataView?) {
        if (note == null) return

        txtTitle.text = note.title
        txtTime.text = note.date
        txtNote.text = note.note

        if (note.title.isEmpty()) {
            txtTitle.visibility = View.GONE
        }

        itemView.setOnClickListener {
            btnDelete.visibility = View.GONE
        }

        itemView.setOnLongClickListener {
            btnDelete.visibility = View.VISIBLE
            true
        }

        btnDelete.setOnClickListener {
            onDelete(adapterPosition, note)
            btnDelete.visibility = View.GONE
        }
    }

    companion object {
        fun create(
            container: ViewGroup,
            onDelete: (Int, NoteDataView) -> Unit
        ): NoteViewHolder {
            val layout = LayoutInflater
                .from(container.context)
                .inflate(R.layout.item_note, container, false)
            return NoteViewHolder(layout, onDelete)
        }
    }

}