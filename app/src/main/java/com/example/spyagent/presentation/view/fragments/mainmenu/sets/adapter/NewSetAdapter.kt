package com.example.spyagent.presentation.view.fragments.mainmenu.sets.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.spyagent.databinding.ItemNewSetBinding

class NewSetAdapter(private val newSetListener: NewSetListener) :
    RecyclerView.Adapter<NewSetViewHolder>() {

    private var listWords = mutableListOf<String>()

    fun submitList(list: List<String>) {
        this.listWords = list.toMutableList()
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewSetViewHolder {
        val binding =
            ItemNewSetBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return NewSetViewHolder(binding, newSetListener)
    }

    override fun onBindViewHolder(holder: NewSetViewHolder, position: Int) {
        holder.bind(listWords[position])
    }

    override fun getItemCount(): Int {
        return listWords.size
    }
}