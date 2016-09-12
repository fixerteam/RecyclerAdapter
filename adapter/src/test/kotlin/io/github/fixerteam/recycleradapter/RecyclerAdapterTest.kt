package io.github.fixerteam.recycleradapter

import android.support.v7.widget.RecyclerView.AdapterDataObserver
import android.view.ViewGroup
import com.nhaarman.mockito_kotlin.mock
import com.nhaarman.mockito_kotlin.verify
import com.nhaarman.mockito_kotlin.verifyNoMoreInteractions
import org.assertj.core.api.Assertions.assertThat
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.internal.verification.Times

@RunWith(TestRunner::class)
class RecyclerAdapterTest {

  private val adapter = object : RecyclerAdapter<String, RecyclerHolder<String>>() {
    override fun getViewHolder(parent: ViewGroup) = mock<RecyclerHolder<String>>()
  }

  @Test
  fun getItemCount_itemCountShouldBeZeroByDefault() {
    assertThat(adapter.itemCount).isEqualTo(0)
  }

  @Test
  fun onBindViewHolder() {
    val holder = mock<RecyclerHolder<String>>()

    adapter.addItem("1")
    adapter.onBindViewHolder(holder, 0)
    verify(holder).bind("1", 0)
  }

  @Test
  fun setItems() {
    val items = listOf("1", "2", "3")
    val observer = mock<AdapterDataObserver>()

    adapter.registerAdapterDataObserver(observer)
    adapter.setItems(items)

    assertThat(adapter.itemCount).isEqualTo(items.size)
    verify(observer).onChanged()
    verifyNoMoreInteractions(observer)
  }

  @Test
  fun addItem() {
    val observer = mock<AdapterDataObserver>()

    adapter.registerAdapterDataObserver(observer)
    adapter.addItem("4")

    assertThat(adapter.itemCount).isEqualTo(1)
    verify(observer).onItemRangeInserted(adapter.itemCount, 1)
    verifyNoMoreInteractions(observer)
  }

  @Test
  fun addItems() {
    val items = listOf("1", "2", "3")
    val observer = mock<AdapterDataObserver>()

    adapter.registerAdapterDataObserver(observer)
    adapter.addItems(items)

    assertThat(adapter.itemCount).isEqualTo(items.size)
    verify(observer).onItemRangeInserted(0, items.size)
    verifyNoMoreInteractions(observer)
  }

  @Test
  fun clearItems() {
    val items = listOf("1", "2", "3")
    val observer = mock<AdapterDataObserver>()

    adapter.registerAdapterDataObserver(observer)
    adapter.setItems(items)

    assertThat(adapter.itemCount).isEqualTo(items.size)

    adapter.clearItems()

    assertThat(adapter.itemCount).isEqualTo(0)
    verify(observer, Times(2)).onChanged()
    verifyNoMoreInteractions(observer)
  }
}