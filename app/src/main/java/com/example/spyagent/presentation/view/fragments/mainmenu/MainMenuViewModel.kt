package com.example.spyagent.presentation.view.fragments.mainmenu

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.spyagent.R
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MainMenuViewModel @Inject constructor() : ViewModel() {

    private var _helpNavRule = MutableLiveData<Int?>()
    val helpNavRule : LiveData<Int?> = _helpNavRule

    fun navToRule() {
        _helpNavRule.value = R.id.action_mainMenuFragment_to_ruleFragment
    }

    fun userNavigatedToRule(){
        _helpNavRule.value = null
    }

    private var _helpNavSets = MutableLiveData<Int?>()
    val helpNavSets : LiveData<Int?> = _helpNavSets

    fun navToSets() {
        _helpNavSets.value = R.id.action_mainMenuFragment_to_setsFragment
    }
    fun userNavigatedToSets(){
        _helpNavSets.value = null
    }

}