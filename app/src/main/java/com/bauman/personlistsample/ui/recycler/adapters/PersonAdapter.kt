package com.bauman.personlistsample.ui.recycler.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bauman.personlistsample.data.ViewTyped.Person
import com.bauman.personlistsample.databinding.PersonListItemBinding
import com.bauman.personlistsample.ui.recycler.holders.PersonViewHolder
import com.bauman.personlistsample.ui.recycler.util.PersonDiffUtilCallback

class PersonAdapter : RecyclerView.Adapter<PersonViewHolder>() {

    private var data = emptyList<Person>()

    fun setNewData(newData: List<Person>) {
        val diffUtilCallback = PersonDiffUtilCallback(data, newData)
        val calculateDiff = DiffUtil.calculateDiff(diffUtilCallback)
        data = newData
        notifyDataSetChanged()
        calculateDiff.dispatchUpdatesTo(this)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PersonViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = PersonListItemBinding.inflate(inflater)
        return PersonViewHolder(binding).apply {
            binding.root.setOnClickListener {
                handlePersonClick(parent.context, adapterPosition)
            }
        }
    }

    override fun getItemCount() = data.size

    override fun onBindViewHolder(holder: PersonViewHolder, position: Int) {
        val person = data[position]
        holder.bind(person)
    }

    private fun handlePersonClick(context: Context, position: Int) {
        if (position != RecyclerView.NO_POSITION) {
            val item = data[position]
            Toast.makeText(context, item.name, Toast.LENGTH_SHORT).show()
        }
    }
}
