package com.bauman.personlistsample.ui.person_details

import com.bauman.personlistsample.data.ViewTyped

interface PersonDetailView {

    fun showPersonInfo(person: ViewTyped.Person)

    fun showErrorAndCloseScreen(errorMessage: String)
}
