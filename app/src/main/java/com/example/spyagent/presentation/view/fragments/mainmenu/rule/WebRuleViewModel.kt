package com.example.spyagent.presentation.view.fragments.mainmenu.rule

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class WebRuleViewModel @Inject constructor():ViewModel(){

    private var _helpUrl = MutableLiveData<String>()
    val helpUrl :LiveData<String> = _helpUrl

    fun getUrlToWebRule(){
        _helpUrl.value = "https://boardgamegeek.com/boardgame/166384/spyfall"
    }

}