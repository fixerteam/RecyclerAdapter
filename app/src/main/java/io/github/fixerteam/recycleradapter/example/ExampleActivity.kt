package io.github.fixerteam.recycleradapter.example

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.Menu
import android.view.MenuItem
import android.view.ViewGroup
import io.github.fixerteam.recycleradapter.RecyclerAdapter
import java.lang.Math.random
import java.lang.Math.round

class ExampleActivity : AppCompatActivity() {

  private val adapter = object : RecyclerAdapter<Pair<String, String>, ExampleHolder>() {
    override fun getViewHolder(parent: ViewGroup) = ExampleHolder(parent)
  }

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_example)

    val recyclerView = findViewById(R.id.recycler_view) as RecyclerView
    recyclerView.layoutManager = LinearLayoutManager(this)
    recyclerView.adapter = adapter

    adapter.setItems(generateStubItems())
  }

  override fun onCreateOptionsMenu(menu: Menu): Boolean {
    menuInflater.inflate(R.menu.main, menu)
    return super.onCreateOptionsMenu(menu)
  }

  override fun onOptionsItemSelected(item: MenuItem): Boolean {
    when (item.itemId) {
      R.id.set_items -> adapter.setItems(generateStubItems())
      R.id.add_items -> adapter.addItems(generateStubItems())
      R.id.add_item -> adapter.addItem("Custom Title" to "Custom Description")
      R.id.clear_items -> adapter.clearItems()
    }
    return super.onOptionsItemSelected(item)
  }

  private fun generateStubItems() = listOf<Pair<String, String>>(
      generateStub(),
      generateStub(),
      generateStub(),
      generateStub(),
      generateStub(),
      generateStub(),
      generateStub(),
      generateStub(),
      generateStub(),
      generateStub(),
      generateStub(),
      generateStub(),
      generateStub()
  )

  private fun generateStub(): Pair<String, String> {
    val randomInt = round(1000 * random()).toInt()
    return "Title $randomInt" to "Description $randomInt"
  }
}
