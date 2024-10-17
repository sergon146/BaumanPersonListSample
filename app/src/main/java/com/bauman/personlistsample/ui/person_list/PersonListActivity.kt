package com.bauman.personlistsample.ui.person_list

import android.os.Bundle
import android.widget.FrameLayout.LayoutParams
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.bauman.personlistsample.data.DataGenerator
import com.bauman.personlistsample.data.ViewTyped
import com.bauman.personlistsample.data.ViewTyped.Person
import com.bauman.personlistsample.databinding.ActivityPersonLinearListBinding
import com.bauman.personlistsample.databinding.ActivityPersonListBinding
import com.bauman.personlistsample.ui.person_list.recycler.adapters.ViewTypedAdapter
import kotlin.random.Random

class PersonListActivity : AppCompatActivity() {

    private lateinit var binding: ActivityPersonListBinding
    private lateinit var oldBinding: ActivityPersonLinearListBinding

    private val dataGenerator = DataGenerator
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
        savedInstanceState?.let { setupData(dataGenerator.generatedData) }
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
            setupData(dataGenerator.generateNewData())
        }

        binding.buttonChange.setOnClickListener {
            val position = Random.nextInt(2, 9)
            (generatedData[position] as? Person)?.let {
                val newData = buildList {
                    addAll(generatedData.subList(0, position))
                    add(it.copy(name = "Person ${Random.nextInt()}"))
                    addAll(generatedData.subList(position + 1, generatedData.size))
                }
                setupData(newData)
            }
        }
    }

    private fun setupData(newData: List<ViewTyped>) {
        generatedData.clear()
        generatedData.addAll(newData)
        adapter.setNewData(generatedData)
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
