package com.bauman.personlistsample.ui.person_list

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.bauman.personlistsample.data.DataGenerator
import com.bauman.personlistsample.data.ViewTyped
import kotlin.random.Random

class PersonListViewModel(private val dataGenerator: DataGenerator) : ViewModel() {

    private val _persons = MutableLiveData<List<ViewTyped>>()
    val persons: LiveData<List<ViewTyped>> = _persons

    private val _error = MutableLiveData<String>()
    val error: LiveData<String> = _error

    fun generateData() {
        _persons.value = dataGenerator.generateNewData()
    }

    fun showGeneratedData() {
        _persons.value = dataGenerator.generatedData
    }

    fun changeRandomPerson() {
        val position = Random.nextInt(2, 9)
        val data = _persons.value
        val item: ViewTyped? = data?.get(position)
        (item as? ViewTyped.Person)?.let {
            val newData = buildList {
                addAll(data.subList(0, position))
                add(it.copy(name = "Person ${Random.nextInt()}"))
                addAll(data.subList(position + 1, data.size))
            }
            _persons.value = newData
        }
    }
}
