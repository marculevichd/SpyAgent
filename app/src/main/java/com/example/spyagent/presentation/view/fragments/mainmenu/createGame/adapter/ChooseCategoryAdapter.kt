package com.example.spyagent.presentation.view.fragments.mainmenu.createGame.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.spyagent.databinding.ItemChooseCategoryBinding
import com.example.spyagent.domain.model.SetModel

class ChooseCategoryAdapter(private val chooseCategoryListener: ChooseCategoryListener) :
    RecyclerView.Adapter<ChooseCategoryViewHolder>() {

    private var listSets = mutableListOf<SetModel>()

    fun submitList(list: List<SetModel>) {
        this.listSets = list.toMutableList()
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ChooseCategoryViewHolder {
        val binding =
            ItemChooseCategoryBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ChooseCategoryViewHolder(binding, chooseCategoryListener)
    }

    override fun onBindViewHolder(holder: ChooseCategoryViewHolder, position: Int) {
        holder.bind(listSets[position])
    }

    override fun getItemCount(): Int {
        return listSets.size
    }
}