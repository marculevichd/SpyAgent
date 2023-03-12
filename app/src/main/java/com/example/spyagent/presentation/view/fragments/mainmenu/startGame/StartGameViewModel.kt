package com.example.spyagent.presentation.view.fragments.mainmenu.startGame

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavDestination
import com.example.spyagent.R
import com.example.spyagent.domain.MainMenuInteractor
import com.example.spyagent.domain.model.GameSetModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject
import kotlin.random.Random

@HiltViewModel
class StartGameViewModel @Inject constructor(private val mainMenuInteractor: MainMenuInteractor) :
    ViewModel() {

    private var _gamePreparation = MutableLiveData<GamePreparation>()
    val gamePreparation: LiveData<GamePreparation> = _gamePreparation

    fun doGamePreparation(players: Int, spies: Int) {
        viewModelScope.launch {
            try {
                val gameSet = mainMenuInteractor.selectCategoryToPlay()
                val word = Random.nextInt(1, gameSet.listWords.size)
                val spy = Random.nextInt(1, players + spies)

                _gamePreparation.value = GamePreparation(gameSet, gameSet.listWords[word], players + spies, spy)
            } catch (e: Exception) {
                Log.w("", e.toString())
            }
        }
    }

    private var _navHelperTimer = MutableLiveData<HelperNavToTimer?>()
    val navHelperTimer: LiveData<HelperNavToTimer?> = _navHelperTimer

    fun navToTimer() {
        _navHelperTimer.value = HelperNavToTimer(
            R.id.action_startGameFragment_to_timerFragment,
            R.id.startGameFragment
        )
    }

    fun userNavigatedToTimer() {
        _navHelperTimer.value = null
    }


    private var _helpNavUserToMainMenu = MutableLiveData<HelperNavMainMenu?>()
    val helpNavUserToMainMenu: LiveData<HelperNavMainMenu?> = _helpNavUserToMainMenu

    fun userNavToMainMenu() {
        _helpNavUserToMainMenu.value = HelperNavMainMenu(
            R.id.action_startGameFragment_to_mainMenuFragment,
            R.id.startGameFragment
        )
    }

    fun userNavigatedToMainMenu() {
        _helpNavUserToMainMenu.value = null
    }


}

data class HelperNavToTimer(val destination: Int, val fragmentToDelete: Int)
data class HelperNavMainMenu(val destination: Int, val fragmentToDelete: Int)
data class GamePreparation(val gameSetModel: GameSetModel, val word: String, val amountPlayers: Int, val spy: Int)
