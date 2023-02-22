package com.example.spyagent.presentation.view.fragments.mainmenu.createGame.adapter

import com.example.spyagent.domain.model.SetModel

interface ChooseCategoryListener {

    fun setSelected(setModel: SetModel, isSelected: Boolean)
}