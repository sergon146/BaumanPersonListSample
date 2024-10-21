package com.bauman.personlistsample.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.bauman.personlistsample.data.DataGenerator
import com.bauman.personlistsample.ui.person_details.PersonDetailsViewModel
import com.bauman.personlistsample.ui.person_list.PersonListViewModel

class PersonViewModelFactory(private val dataGenerator: DataGenerator) : ViewModelProvider.Factory {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return when {
            modelClass.isAssignableFrom(PersonListViewModel::class.java) -> {
                PersonListViewModel(dataGenerator) as T
            }

            modelClass.isAssignableFrom(PersonDetailsViewModel::class.java) -> {
                PersonDetailsViewModel(dataGenerator) as T
            }

            else -> throw IllegalArgumentException("Unknown ViewModel class")
        }
    }
}
