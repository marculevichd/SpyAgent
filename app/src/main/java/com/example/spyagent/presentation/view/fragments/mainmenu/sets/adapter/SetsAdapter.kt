package com.example.spyagent.presentation.view.fragments.mainmenu.sets.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.spyagent.databinding.ItemSetBinding
import com.example.spyagent.domain.model.SetModel

class SetsAdapter(private val setsListener: SetsListener) : RecyclerView.Adapter<SetsViewHolder>() {

    private var listSets = mutableListOf<SetModel>()

    fun submitList(list: List<SetModel>) {
        this.listSets = list.toMutableList()
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SetsViewHolder {
        val binding = ItemSetBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return SetsViewHolder(binding, setsListener)
    }

    override fun onBindViewHolder(holder: SetsViewHolder, position: Int) {
        holder.bind(listSets[position])
    }

    override fun getItemCount(): Int {
        return listSets.size
    }
}