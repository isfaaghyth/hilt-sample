package app.isfaaghyth.hilt.base

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import app.isfaaghyth.hilt.ui.factory.ItemTypeFactory
import java.util.*

class BaseListAdapter(
    private val adapterTypeFactory: ItemTypeFactory,
    private val items: ArrayList<BaseDataView> = arrayListOf()
): RecyclerView.Adapter<AbstractViewHolder<BaseDataView>>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AbstractViewHolder<BaseDataView> {
        val view = inflateItem(parent, viewType)
        return adapterTypeFactory.createViewHolder(view, viewType) as AbstractViewHolder<BaseDataView>
    }

    override fun getItemCount(): Int = items.size

    override fun onBindViewHolder(holder: AbstractViewHolder<BaseDataView>, position: Int) {
        holder.bind(items[position])
    }

    private fun inflateItem(viewGroup: ViewGroup, viewType: Int): View {
        return LayoutInflater.from(viewGroup.context).inflate(viewType, viewGroup, false)
    }

    override fun getItemViewType(position: Int): Int {
        return items[position].type(adapterTypeFactory)
    }

    fun addItem(items: List<BaseDataView>) {
        this.items.addAll(items)
        notifyDataSetChanged()
    }

    fun removeItem(item: BaseDataView) {
        this.items.remove(item)
        notifyDataSetChanged()
    }

    fun removeItemByIndex(position: Int, item: BaseDataView) {
        this.items.remove(item)
        notifyItemRemoved(position)
    }

}
