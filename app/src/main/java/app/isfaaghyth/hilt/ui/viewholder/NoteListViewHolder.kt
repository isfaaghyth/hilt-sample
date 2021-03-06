package app.isfaaghyth.hilt.ui.viewholder

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.annotation.LayoutRes
import app.isfaaghyth.hilt.R
import app.isfaaghyth.hilt.base.AbstractViewHolder
import app.isfaaghyth.hilt.ui.dataview.NoteDataView
import app.isfaaghyth.hilt.util.DateFormatter.timeAgo
import app.isfaaghyth.hilt.util.RouteManager
import app.isfaaghyth.hilt.util.hide
import app.isfaaghyth.hilt.util.show
import app.isfaaghyth.hilt.util.showWithCondition
import kotlin.LazyThreadSafetyMode.NONE as NONE

internal open class NoteListViewHolder(
    view: View,
    private val onDelete: (Int, NoteDataView) -> Unit
): AbstractViewHolder<NoteDataView>(view) {

    private val txtTime: TextView = view.findViewById(R.id.txtTime)
    private val txtTitle: TextView = view.findViewById(R.id.txtTitle)
    private val txtNote: TextView = view.findViewById(R.id.txtNote)
    private val btnDelete: ImageView = view.findViewById(R.id.btnDelete)

    private val context by lazy(NONE) { itemView.context }

    override fun bind(element: NoteDataView?) {
        if (element == null) return
        txtTitle.text = element.title
        txtTime.text = timeAgo(element.date)
        txtNote.text = element.note

        onTitleState(element)
        onItemAction(element)
    }

    private fun onTitleState(element: NoteDataView) {
        val hasTitle = element.title.isNotEmpty()
        txtTitle.showWithCondition(hasTitle)
    }

    private fun onItemAction(element: NoteDataView) {
        itemView.setOnClickListener {
            RouteManager.route(context, "note://detail/${element.id}")
            btnDelete.hide()
        }

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