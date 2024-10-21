package com.bauman.personlistsample.ui.person_list

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.bauman.personlistsample.data.DataGenerator
import com.bauman.personlistsample.databinding.ActivityPersonListBinding
import com.bauman.personlistsample.ui.PersonViewModelFactory
import com.bauman.personlistsample.ui.person_list.recycler.adapters.ViewTypedAdapter

class PersonListActivity : AppCompatActivity() {

    private lateinit var binding: ActivityPersonListBinding
    private lateinit var viewModel: PersonListViewModel

    private val adapter = ViewTypedAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPersonListBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initViewModel()

        setupRecyclerView()
        setupClicks()
        savedInstanceState?.let { viewModel.showGeneratedData() }
    }

    private fun initViewModel() {
        val factory = PersonViewModelFactory(DataGenerator)
        viewModel = ViewModelProvider(this, factory)[PersonListViewModel::class.java]
        viewModel.persons.observe(this) { adapter.setNewData(it) }
        viewModel.error.observe(this) {
            Toast.makeText(this, it, Toast.LENGTH_SHORT).show()
        }
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
            viewModel.generateData()
        }

        binding.buttonChange.setOnClickListener {
            viewModel.changeRandomPerson()
        }
    }
}
