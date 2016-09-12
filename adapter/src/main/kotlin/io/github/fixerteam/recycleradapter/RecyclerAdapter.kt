package io.github.fixerteam.recycleradapter

import android.support.v7.widget.RecyclerView.Adapter
import android.view.ViewGroup
import java.util.*

abstract class RecyclerAdapter<in E, VH : RecyclerHolder<E>> : Adapter<VH>() {

  private val items: MutableList<E> = ArrayList()

  abstract fun getViewHolder(parent: ViewGroup): VH

  override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = getViewHolder(parent)

  override fun getItemCount() = items.size

  override fun onBindViewHolder(holder: VH, position: Int) {
    holder.bind(items[position], position)
  }

  fun setItems(items: List<E>) {
    this.items.clear()
    this.items.addAll(items)
    notifyDataSetChanged()
  }

  fun addItem(item: E) {
    this.items.add(item)
    notifyItemInserted(itemCount)
  }

  fun addItems(items: List<E>) {
    val oldSize = itemCount
    this.items.addAll(items)
    notifyItemRangeInserted(oldSize, itemCount)
  }

  fun clearItems() {
    this.items.clear()
    notifyDataSetChanged()
  }
}