package com.bauman.personlistsample.ui.person_list.recycler.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bauman.personlistsample.data.ViewTyped.Person
import com.bauman.personlistsample.databinding.PersonListItemBinding
import com.bauman.personlistsample.ui.person_list.recycler.holders.PersonViewHolder
import com.bauman.personlistsample.ui.person_list.recycler.util.PersonDiffUtilItemCallback

class PersonListAdapter(itemCallback: PersonDiffUtilItemCallback) :
    ListAdapter<Person, PersonViewHolder>(itemCallback) {

    //add items with adapter.submitList(List<T>)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PersonViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = PersonListItemBinding.inflate(inflater)
        return PersonViewHolder(binding).apply {
            binding.root.setOnClickListener {
                handlePersonClick(parent.context, adapterPosition)
            }
        }
    }

    override fun onBindViewHolder(holder: PersonViewHolder, position: Int) {
        val person = currentList[position]
        holder.bind(person)
    }

    private fun handlePersonClick(context: Context, position: Int) {
        if (position != RecyclerView.NO_POSITION) {
            val item = currentList[position]
            Toast.makeText(context, item.name, Toast.LENGTH_SHORT).show()
        }
    }
}