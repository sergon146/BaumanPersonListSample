package com.bauman.personlistsample.ui.recycler.holders

import androidx.recyclerview.widget.RecyclerView
import com.bauman.personlistsample.data.ViewTyped
import com.bauman.personlistsample.databinding.PersonListItemBinding

class PersonViewHolder(private val binding: PersonListItemBinding) :
    RecyclerView.ViewHolder(binding.root) {

    fun bind(person: ViewTyped.Person) {
        bindName(person.name)

        person.image?.let { imageRes ->
            binding.personImage.setImageResource(imageRes)
            binding.personImage.background = null
        }
    }

    fun bindName(name: String) {
        binding.personName.text = name
    }
}
