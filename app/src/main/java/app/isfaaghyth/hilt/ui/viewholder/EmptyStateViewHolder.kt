package app.isfaaghyth.hilt.ui.viewholder

import android.view.View
import androidx.annotation.LayoutRes
import app.isfaaghyth.hilt.R
import app.isfaaghyth.hilt.base.AbstractViewHolder
import app.isfaaghyth.hilt.ui.dataview.EmptyStateDataView

class EmptyStateViewHolder(view: View): AbstractViewHolder<EmptyStateDataView>(view) {

    override fun bind(element: EmptyStateDataView?) {}

    companion object {
        @LayoutRes val LAYOUT = R.layout.layout_empty_state
    }

}