package com.bauman.personlistsample.ui.person_details

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.bauman.personlistsample.data.DataGenerator
import com.bauman.personlistsample.data.ViewTyped.Person

class PersonDetailsViewModel(private val dataGenerator: DataGenerator) : ViewModel() {

    private val _person = MutableLiveData<Person>()
    val person: MutableLiveData<Person> = _person

    private val _error = MutableLiveData<String>()
    val error: LiveData<String> = _error

    fun loadPersonDetails(personPosition: Int) {
        if (personPosition == -1) {
            _error.value = "Incorrect position"
        } else {
            (dataGenerator.generatedData[personPosition] as? Person)?.let {
                _person.value = it
            } ?: {
                _error.value = "Person not found"
            }
        }
    }
}
