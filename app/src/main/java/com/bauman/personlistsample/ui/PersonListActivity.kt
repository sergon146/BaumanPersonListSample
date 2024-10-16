package com.bauman.personlistsample.ui

import android.os.Bundle
import android.widget.FrameLayout.LayoutParams
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.bauman.personlistsample.data.PersonGenerator
import com.bauman.personlistsample.data.ViewTyped
import com.bauman.personlistsample.data.ViewTyped.Person
import com.bauman.personlistsample.databinding.ActivityPersonLinearListBinding
import com.bauman.personlistsample.databinding.ActivityPersonListBinding
import com.bauman.personlistsample.ui.recycler.adapters.PersonListAdapter
import com.bauman.personlistsample.ui.recycler.adapters.ViewTypedAdapter
import com.bauman.personlistsample.ui.recycler.util.PersonDiffUtilItemCallback
import kotlin.random.Random

class PersonListActivity : AppCompatActivity() {

    private lateinit var binding: ActivityPersonListBinding
    private lateinit var oldBinding: ActivityPersonLinearListBinding

    private val dataGenerator = PersonGenerator()
    private val adapter = ViewTypedAdapter()
    private var generatedData = mutableListOf<ViewTyped>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

//        oldBinding = ActivityPersonLinearListBinding.inflate(layoutInflater)
//        setContentView(oldBinding.root)
//        createLinearLayoutList()

        binding = ActivityPersonListBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setupRecyclerView()
        setupClicks()
    }

    private fun setupRecyclerView() {
        with(binding.recyclerView) {
            adapter = this@PersonListActivity.adapter
            layoutManager = StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL)
        }
    }

    private fun setupClicks() {
        binding.buttonGenerate.setOnClickListener {
            binding.buttonChange.isVisible = true
            generatedData.clear()
            generatedData.addAll(dataGenerator.generateData())
            adapter.setNewData(generatedData)
        }

        binding.buttonChange.setOnClickListener {
            val position = Random.nextInt(2, 9)
            (generatedData[position] as? Person)?.let {
                val newData = buildList {
                    addAll(generatedData.subList(0, position))
                    add(it.copy(name = "Person ${Random.nextInt()}"))
                    addAll(generatedData.subList(position + 1, generatedData.size))
                }
                generatedData.clear()
                generatedData.addAll(newData)
                adapter.setNewData(newData)
            }
        }
    }

    private fun createLinearLayoutList() {
        val data = dataGenerator.generatePersonsData()
        oldBinding.linearList.apply {
            data.forEach { item ->
                val textItem = TextView(context).apply {
                    text = item.name
                    textSize = 20f
                    layoutParams = LayoutParams(
                        LayoutParams.MATCH_PARENT,
                        LayoutParams.WRAP_CONTENT
                    ).apply {
                        setMargins(36, 20, 20, 36)
                    }
                }
                addView(textItem)
            }
        }
    }
}
