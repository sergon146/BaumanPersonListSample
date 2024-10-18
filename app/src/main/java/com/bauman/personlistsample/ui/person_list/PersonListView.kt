package com.bauman.personlistsample.ui.person_list

import com.bauman.personlistsample.data.ViewTyped

interface PersonListView {

    fun showPersonsList(persons: List<ViewTyped>)

    fun openPersonDetails(personPosition: Int)
}
