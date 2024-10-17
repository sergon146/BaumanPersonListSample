package com.bauman.personlistsample.ui.person_list.recycler.util

import androidx.recyclerview.widget.DiffUtil
import com.bauman.personlistsample.data.ViewTyped.Person

class PersonDiffUtilCallback(
    private val oldList: List<Person>,
    private val newList: List<Person>
) : DiffUtil.Callback() {

    override fun getOldListSize() = oldList.size

    override fun getNewListSize() = newList.size

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldList[oldItemPosition].id == newList[newItemPosition].id
    }

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        val oldItem = oldList[oldItemPosition]
        val newItem = newList[newItemPosition]

        return oldItem == newItem
    }

    override fun getChangePayload(oldItemPosition: Int, newItemPosition: Int): Any? {
        val oldItem = oldList[oldItemPosition]
        val newItem = newList[newItemPosition]

        return when {
            oldItem.name != newItem.name -> PersonNamePayload(newItem.name)
            else -> null
        }
    }
}

data class PersonNamePayload(val name: String)
