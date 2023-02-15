package com.example.spyagent.presentation.view.fragments.mainmenu

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.spyagent.R
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MainMenuViewModel @Inject constructor() : ViewModel() {

    private var _helpNav = MutableLiveData<Int>()
    val helpNav : LiveData<Int> = _helpNav

    fun navToRule() {
        _helpNav.value = R.id.action_mainMenuFragment_to_ruleFragment
    }

}