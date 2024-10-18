package com.bauman.personlistsample.ui.person_list

import com.bauman.personlistsample.data.DataGenerator
import com.bauman.personlistsample.data.ViewTyped
import kotlin.random.Random

class PersonListPresenter(
    private val view: PersonListView,
    private val generator: DataGenerator
) {

    private val generatedData = mutableListOf<ViewTyped>()

    fun generatePersonsList() {
        val generateNewData = generator.generateNewData()
        generatedData.clear()
        generatedData.addAll(generateNewData)
        view.showPersonsList(generateNewData)
    }

    fun showGeneratedData() {
        view.showPersonsList(generatedData)
    }

    fun changeRandomPerson() {
        val position = Random.nextInt(2, 9)
        (generatedData[position] as? ViewTyped.Person)?.let {
            val newData = buildList {
                addAll(generatedData.subList(0, position))
                add(it.copy(name = "Person ${Random.nextInt()}"))
                addAll(generatedData.subList(position + 1, generatedData.size))
            }
            view.showPersonsList(newData)
        }
    }
}
