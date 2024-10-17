package com.bauman.personlistsample.data

import androidx.annotation.DrawableRes

sealed interface ViewTyped {

    data class Person(
        val id: Int,
        val name: String,
        @DrawableRes val image: Int?,
        val age: Int,
        val phoneNumber: String,
        val address: String
    ) : ViewTyped

    data class Header(val title: String) : ViewTyped
}
