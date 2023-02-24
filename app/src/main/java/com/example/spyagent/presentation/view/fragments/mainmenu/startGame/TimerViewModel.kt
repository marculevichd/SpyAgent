package com.example.spyagent.presentation.view.fragments.mainmenu.startGame

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.spyagent.R
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class TimerViewModel @Inject constructor() : ViewModel() {

    private val _helpNavToMainMenu = MutableLiveData<HelperNavToMainMenu?>()
    val helpNavToMainMenu: LiveData<HelperNavToMainMenu?> = _helpNavToMainMenu

    fun navToMainMenu() {
        _helpNavToMainMenu.value = HelperNavToMainMenu(
            R.id.action_timerFragment_to_mainMenuFragment,
            R.id.timerFragment
        )
    }

    fun userNavigatedToStartGame() {
        _helpNavToMainMenu.value = null
    }

    private var _helpNavUserToMainMenu = MutableLiveData<HelperNavMainMenuFromTimer?>()
    val helpNavUserToMainMenu: LiveData<HelperNavMainMenuFromTimer?> = _helpNavUserToMainMenu

    fun userNavToMainMenu() {
        _helpNavUserToMainMenu.value = HelperNavMainMenuFromTimer(
            R.id.action_timerFragment_to_mainMenuFragment,
            R.id.timerFragment
        )
    }

    fun userNavigatedToMainMenu() {
        _helpNavUserToMainMenu.value = null
    }


}

data class HelperNavToMainMenu(val destination: Int, val deleteFromBackStack: Int)
data class HelperNavMainMenuFromTimer(val destination: Int, val fragmentToDelete: Int)
