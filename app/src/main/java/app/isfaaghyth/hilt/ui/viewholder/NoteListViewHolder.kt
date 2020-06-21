package app.isfaaghyth.hilt.ui.viewholder

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.annotation.LayoutRes
import app.isfaaghyth.hilt.R
import app.isfaaghyth.hilt.base.AbstractViewHolder
import app.isfaaghyth.hilt.ui.dataview.NoteDataView
import app.isfaaghyth.hilt.util.hide
import app.isfaaghyth.hilt.util.show

internal open class NoteListViewHolder(
    view: View,
    private val onDelete: (Int, NoteDataView) -> Unit
): AbstractViewHolder<NoteDataView>(view) {

    private val txtTime: TextView = view.findViewById(R.id.txtTime)
    private val txtTitle: TextView = view.findViewById(R.id.txtTitle)
    private val txtNote: TextView = view.findViewById(R.id.txtNote)
    private val btnDelete: ImageView = view.findViewById(R.id.btnDelete)

    override fun bind(element: NoteDataView?) {
        if (element == null) return

        txtTitle.text = element.title
        txtTime.text = element.date
        txtNote.text = element.note

        if (element.title.isEmpty()) { txtTitle.hide() }
        itemView.setOnClickListener { btnDelete.hide() }

        itemView.setOnLongClickListener {
            btnDelete.show()
            true
        }

        btnDelete.setOnClickListener {
            onDelete(adapterPosition, element)
            btnDelete.hide()
        }
    }

    companion object {
        @LayoutRes val LAYOUT = R.layout.item_note
    }

}