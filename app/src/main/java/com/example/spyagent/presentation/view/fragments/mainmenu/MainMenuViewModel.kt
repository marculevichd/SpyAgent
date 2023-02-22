package com.example.spyagent.presentation.view.fragments.mainmenu

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.spyagent.R
import com.example.spyagent.domain.MainMenuInteractor
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainMenuViewModel @Inject constructor(private val mainMenuInteractor: MainMenuInteractor) : ViewModel() {

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

    private var _helpNavCreateGame = MutableLiveData<Int?>()
    val helpNavCreateGame : LiveData<Int?> = _helpNavCreateGame

    fun navToCreateGame() {
        _helpNavCreateGame.value = R.id.action_mainMenuFragment_to_createGameFragment
    }
    fun userNavigatedToCreateGame(){
        _helpNavCreateGame.value = null
    }



    private var _firstSet = MutableLiveData<Unit>()
    fun addStartSet() {
        viewModelScope.launch {
            mainMenuInteractor.addStartSet()
            _firstSet.value = Unit
        }
    }

}