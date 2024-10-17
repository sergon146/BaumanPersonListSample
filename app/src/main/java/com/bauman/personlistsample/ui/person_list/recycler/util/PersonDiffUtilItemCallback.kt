package com.bauman.personlistsample.ui.person_list.recycler.util

import androidx.recyclerview.widget.DiffUtil
import com.bauman.personlistsample.data.ViewTyped.Person

class PersonDiffUtilItemCallback : DiffUtil.ItemCallback<Person>() {
    override fun areItemsTheSame(oldItem: Person, newItem: Person): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: Person, newItem: Person): Boolean {
        return oldItem == newItem
    }
}
