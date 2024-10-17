package com.bauman.personlistsample.ui.person_list.recycler.holders

import androidx.recyclerview.widget.RecyclerView
import com.bauman.personlistsample.data.ViewTyped
import com.bauman.personlistsample.databinding.HeaderItemBinding

class HeaderViewHolder(private val binding: HeaderItemBinding) :
    RecyclerView.ViewHolder(binding.root) {

    fun bind(header: ViewTyped.Header) {
        binding.headerTitle.text = header.title
    }
}
