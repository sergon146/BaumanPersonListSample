package com.bauman.personlistsample.ui.person_details

import com.bauman.personlistsample.data.DataGenerator
import com.bauman.personlistsample.data.ViewTyped.Person

class PersonDetailsPresenter(
    private val view: PersonDetailView,
    private val dataGenerator: DataGenerator
) {

    fun loadGeneratedPerson(position: Int) {
        (dataGenerator.generatedData[position] as? Person)?.let {
            view.showPersonInfo(it)
        } ?: { view.showErrorAndCloseScreen("Error occurred!") }
    }
}
