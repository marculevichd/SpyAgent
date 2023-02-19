package com.example.spyagent.presentation.view.fragments.mainmenu.sets.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.spyagent.databinding.ItemDetailSetBinding

class SetDetailAdapter(private val setDetailListener: SetDetailListener) :
    RecyclerView.Adapter<SetDetailViewHolder>() {


    private var listWords = mutableListOf<String>()

    fun submitList(list: List<String>) {
        this.listWords = list.toMutableList()
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SetDetailViewHolder {
        val binding =
            ItemDetailSetBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return SetDetailViewHolder(binding, setDetailListener)
    }

    override fun onBindViewHolder(holder: SetDetailViewHolder, position: Int) {
        holder.bind(listWords[position])
    }

    override fun getItemCount(): Int {
        return listWords.size
    }


}