package com.bauman.personlistsample.ui.person_list

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.bauman.personlistsample.data.DataGenerator
import com.bauman.personlistsample.data.ViewTyped
import com.bauman.personlistsample.databinding.ActivityPersonListBinding
import com.bauman.personlistsample.ui.person_details.PersonDetailsActivity
import com.bauman.personlistsample.ui.person_list.recycler.adapters.ViewTypedAdapter

class PersonListActivity : AppCompatActivity(), PersonListView {

    private lateinit var presenter: PersonListPresenter
    private lateinit var binding: ActivityPersonListBinding

    private val adapter = ViewTypedAdapter { position -> openPersonDetails(position) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityPersonListBinding.inflate(layoutInflater)
        setContentView(binding.root)

        presenter = PersonListPresenter(this, DataGenerator)

        setupRecyclerView()
        setupClicks()
        savedInstanceState?.let { presenter.showGeneratedData() }
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
            presenter.generatePersonsList()
        }

        binding.buttonChange.setOnClickListener {
            presenter.changeRandomPerson()
        }
    }

    override fun showPersonsList(persons: List<ViewTyped>) {
        adapter.setNewData(persons)
    }

    override fun openPersonDetails(personPosition: Int) {
        startActivity(PersonDetailsActivity.getIntent(this, personPosition))
    }
}
