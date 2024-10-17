package com.bauman.personlistsample.data

import androidx.annotation.DrawableRes
import com.bauman.personlistsample.R
import com.bauman.personlistsample.data.ViewTyped.Header
import com.bauman.personlistsample.data.ViewTyped.Person
import kotlin.random.Random

object DataGenerator {

    var generatedData = mutableListOf<ViewTyped>()
        private set

    private val avatarSet = mutableSetOf(
        null,
        R.drawable.person_1,
        R.drawable.person_2,
        R.drawable.person_3,
        R.drawable.person_4,
        R.drawable.person_5,
        R.drawable.person_6,
        R.drawable.person_7,
    )

    fun generateNewData(): List<ViewTyped> {
        generatedData.clear()
        generatedData.addAll(buildList {
            for (i in 0..100) {
                if (i % 10 == 0) {
                    add(Header("Header $i"))
                } else {
                    add(generateRandomPerson(i))
                }
            }
        })
        return generatedData
    }

    fun generatePersonsData(): List<Person> {
        return buildList {
            for (i in 0..100) {
                add(generateRandomPerson(i))
            }
        }
    }

    private fun generateRandomPerson(index: Int): Person {
        return Person(
            id = Random.nextInt(),
            "Person $index",
            image = getRandomAvatar(),
            age = Random.nextInt(1, 100),
            address = "Some default address",
            phoneNumber = "+7 999 999 99 99"
        )
    }

    @DrawableRes
    private fun getRandomAvatar(): Int? {
        return avatarSet.random()
    }
}
