package com.example.spyagent.presentation.view.fragments.mainmenu.sets.adapter

import com.example.spyagent.domain.model.SetModel

interface SetsListener {

    fun onOpenSetClicked(setModel: SetModel)

    fun OnDeleteSetClicked(id:Int)

}