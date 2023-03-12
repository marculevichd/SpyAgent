package com.example.spyagent.presentation.view.fragments.mainmenu.sets.adapter

import androidx.core.view.isVisible
import androidx.recyclerview.widget.RecyclerView
import com.example.spyagent.databinding.ItemNewSetBinding

class NewSetViewHolder(
    private val binding: ItemNewSetBinding,
    private val newSetListener: NewSetListener
) : RecyclerView.ViewHolder(binding.root) {

    fun bind(word: String) {

//        if(word.isEmpty()){
//            binding.itemsNewSetDelete.isVisible = false
//            binding.itemsNewSetUpdate.isVisible = false
//            binding.animationView.isVisible = true
//        }else{
//            binding.itemsNewSetTitle.text = word
//            binding.animationView.isVisible = false
//        }
        binding.itemsNewSetTitle.text = word


        binding.itemsNewSetDelete.setOnClickListener {
            newSetListener.deleteWord(word)
        }

        binding.itemsNewSetUpdate.setOnClickListener {
            newSetListener.updateWord(word)
        }

    }

}