package com.example.spyagent.presentation.view.fragments.mainmenu.sets.adapter

import androidx.recyclerview.widget.RecyclerView
import com.example.spyagent.R
import com.example.spyagent.databinding.ItemSetBinding
import com.example.spyagent.domain.model.SetModel

class SetsViewHolder(
    private val binding: ItemSetBinding,
    private val setsListener: SetsListener
) :  RecyclerView.ViewHolder(binding.root) {

    fun bind(setModel: SetModel) {
        binding.itemsSetTitle.text = setModel.setName

        binding.openSet.setOnClickListener {
            setsListener.onOpenSetClicked(setModel)
        }

        if(setModel.id!=1) {
            binding.deleteSet.setBackgroundResource(R.drawable.delete_set)
            binding.deleteSet.setOnClickListener {
                setsListener.OnDeleteSetClicked(setModel.id)
            }
        }
    }



}