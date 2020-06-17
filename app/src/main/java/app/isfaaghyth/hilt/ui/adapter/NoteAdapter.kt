package app.isfaaghyth.hilt.ui.adapter

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import app.isfaaghyth.hilt.ui.adapter.viewholder.NoteViewHolder
import app.isfaaghyth.hilt.ui.dataview.NoteDataView

internal open class NoteAdapter(
    private val notes: List<NoteDataView>,
    private val onDelete: (Int, NoteDataView) -> Unit
) : RecyclerView.Adapter<NoteViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NoteViewHolder {
        return NoteViewHolder.create(parent, onDelete)
    }

    override fun onBindViewHolder(holder: NoteViewHolder, position: Int) {
        holder.bind(notes[position])
    }

    override fun getItemCount(): Int = notes.size

}