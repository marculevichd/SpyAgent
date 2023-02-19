package com.example.spyagent.presentation.view.fragments.mainmenu.sets.adapter

import androidx.recyclerview.widget.RecyclerView
import com.example.spyagent.databinding.ItemNewSetBinding

class NewSetViewHolder(
    private val binding: ItemNewSetBinding,
    private val newSetListener: NewSetListener
) : RecyclerView.ViewHolder(binding.root) {

    fun bind(word: String) {
        binding.itemsNewSetTitle.text = word

        binding.itemsNewSetDelete.setOnClickListener {
            newSetListener.deleteWord(word)
        }

        binding.itemsNewSetUpdate.setOnClickListener {
            newSetListener.updateWord(word)
        }

    }

}