package io.github.fixerteam.recycleradapter.example

import android.view.ViewGroup
import android.widget.TextView
import io.github.fixerteam.recycleradapter.RecyclerHolder

class ExampleHolder(parent: ViewGroup) : RecyclerHolder<Pair<String, String>>(parent, R.layout.item_example) {

  val titleView = itemView.findViewById(R.id.item_title) as TextView
  val descriptionView = itemView.findViewById(R.id.item_description) as TextView

  override fun bind(item: Pair<String, String>, position: Int) {
    titleView.text = item.first
    descriptionView.text = item.second
  }
}