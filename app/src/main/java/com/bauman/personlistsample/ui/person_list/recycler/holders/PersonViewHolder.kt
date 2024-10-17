package com.bauman.personlistsample.ui.person_list.recycler.holders

import androidx.recyclerview.widget.RecyclerView
import com.bauman.personlistsample.R
import com.bauman.personlistsample.data.ViewTyped
import com.bauman.personlistsample.databinding.PersonListItemBinding

class PersonViewHolder(private val binding: PersonListItemBinding) :
    RecyclerView.ViewHolder(binding.root) {

    fun bind(person: ViewTyped.Person) {
        bindName(person.name)

        val avatar = itemView.context.getDrawable(person.image ?: R.drawable.person_default)
        binding.personImage.setImageDrawable(avatar)
    }

    fun bindName(name: String) {
        binding.personName.text = name
    }
}
