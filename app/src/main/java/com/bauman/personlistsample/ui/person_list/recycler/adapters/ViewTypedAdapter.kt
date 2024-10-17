package com.bauman.personlistsample.ui.person_list.recycler.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.bauman.personlistsample.R
import com.bauman.personlistsample.data.ViewTyped
import com.bauman.personlistsample.data.ViewTyped.Header
import com.bauman.personlistsample.data.ViewTyped.Person
import com.bauman.personlistsample.databinding.HeaderItemBinding
import com.bauman.personlistsample.databinding.PersonListItemBinding
import com.bauman.personlistsample.ui.person_details.PersonDetailsActivity
import com.bauman.personlistsample.ui.person_list.recycler.holders.HeaderViewHolder
import com.bauman.personlistsample.ui.person_list.recycler.holders.PersonViewHolder
import com.bauman.personlistsample.ui.person_list.recycler.util.PersonNamePayload

class ViewTypedAdapter : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private var data = emptyList<ViewTyped>()

    fun setNewData(newData: List<ViewTyped>) {
        data = newData
        notifyDataSetChanged()
    }

    override fun getItemViewType(position: Int): Int {
        val item = data[position]
        return when (item) { // избыточный when, else не требуется
            is Person -> R.layout.person_list_item
            is Header -> R.layout.header_item
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return when (viewType) {
            R.layout.person_list_item -> {
                val binding = PersonListItemBinding.inflate(inflater)
                PersonViewHolder(binding).apply {
                    binding.root.setOnClickListener {
                        handlePersonClick(parent.context, adapterPosition)
                    }
                }
            }
            R.layout.header_item -> {
                HeaderViewHolder(HeaderItemBinding.inflate(inflater))
            }
            else -> throw IllegalArgumentException("Unknown view type")
        }
    }

    override fun getItemCount() = data.size

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val item = data[position]
        when (holder) {
            is PersonViewHolder -> holder.bind(item as Person)
            is HeaderViewHolder -> holder.bind(item as Header)
        }
    }

    override fun onBindViewHolder(
        holder: RecyclerView.ViewHolder,
        position: Int,
        payloads: MutableList<Any>
    ) {
        val person = data[position]
        if (payloads.isEmpty()) {
            super.onBindViewHolder(holder, position, payloads)
        } else {
            payloads.forEach {
                when (it) {
                    is PersonNamePayload -> (holder as PersonViewHolder)
                        .bindName((person as Person).name)
                }
            }
        }
    }

    private fun handlePersonClick(context: Context, position: Int) {
        if (position != RecyclerView.NO_POSITION) {
            (data[position] as? Person)?.let {
                context.startActivity(PersonDetailsActivity.getIntent(context, position))
                Toast.makeText(context, "Opening ${it.name}", Toast.LENGTH_SHORT).show()
            }
        }
    }
}
