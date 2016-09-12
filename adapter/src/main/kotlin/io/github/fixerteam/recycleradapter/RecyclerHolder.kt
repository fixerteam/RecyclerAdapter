package io.github.fixerteam.recycleradapter

import android.support.annotation.LayoutRes
import android.support.v7.widget.RecyclerView.ViewHolder
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

abstract class RecyclerHolder<in T>(itemView: View) : ViewHolder(itemView) {

  constructor(parent: ViewGroup, @LayoutRes layout: Int) : this(
      LayoutInflater.from(parent.context).inflate(layout, parent, false))

  abstract fun bind(item: T, position: Int)
}