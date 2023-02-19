package com.example.spyagent.presentation.view.fragments.mainmenu.sets.adapter

import androidx.recyclerview.widget.RecyclerView
import com.example.spyagent.databinding.ItemDetailSetBinding

class SetDetailViewHolder(
    private val binding: ItemDetailSetBinding,
    private val setDetailListener: SetDetailListener
) :  RecyclerView.ViewHolder(binding.root) {

    fun bind(word: String) {
        binding.itemsDetailSetTitle.text = word

        binding.itemsDetailSetDelete.setOnClickListener {
            setDetailListener.deleteWord(word)
        }

        binding.itemsDetailSetUpdate.setOnClickListener {
            setDetailListener.updateWord(word)
        }

    }

}