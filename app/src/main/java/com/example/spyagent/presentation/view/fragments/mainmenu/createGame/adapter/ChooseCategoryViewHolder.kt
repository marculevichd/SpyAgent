package com.example.spyagent.presentation.view.fragments.mainmenu.createGame.adapter

import androidx.recyclerview.widget.RecyclerView
import com.example.spyagent.databinding.ItemChooseCategoryBinding
import com.example.spyagent.domain.model.SetModel

class ChooseCategoryViewHolder(
    private val binding: ItemChooseCategoryBinding,
    private val chooseCategoryListener: ChooseCategoryListener
) : RecyclerView.ViewHolder(binding.root) {

    fun bind(setModel: SetModel) {
        binding.chooseCategoryTitle.text = setModel.setName

        binding.checkBox.isChecked = setModel.isSelected


        binding.checkBox.setOnClickListener {
            if (binding.checkBox.isChecked) {
                chooseCategoryListener.setSelected(setModel, binding.checkBox.isChecked)
                binding.checkBox.isChecked = !it.isSelected
            } else {
                chooseCategoryListener.setSelected(setModel, false)
                binding.checkBox.isChecked = !it.isSelected

            }
        }

    }


}


