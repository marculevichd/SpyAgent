package com.example.spyagent.presentation.view.fragments.mainmenu.startGame

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

    private var _gameSetToPlay = MutableLiveData<GameSetAndWord>()
    val gameSetToPlay: LiveData<GameSetAndWord> = _gameSetToPlay

    fun selectCategoryToPlay() {
        viewModelScope.launch {
            val gameSet = mainMenuInteractor.selectCategoryToPlay()
            val word = Random.nextInt(1, gameSet.listWords.size)
            _gameSetToPlay.value = GameSetAndWord(gameSet, gameSet.listWords[word])
        }
    }

    private var _amountPlayersAndWhoWillBeSpy =
        MutableLiveData<HelperAmountPlayersAndWhoWillBeSpy>()
    val amountPlayersAndWhoWillBeSpy: LiveData<HelperAmountPlayersAndWhoWillBeSpy> =
        _amountPlayersAndWhoWillBeSpy

    fun countAmountPlayersAndWhoWillBeSpy(players: Int, spies: Int) {
        val spy = Random.nextInt(1, players + spies)
        _amountPlayersAndWhoWillBeSpy.value =
            HelperAmountPlayersAndWhoWillBeSpy(players + spies, spy)
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

data class HelperAmountPlayersAndWhoWillBeSpy(val amountPlayers: Int, val spy: Int)
data class HelperNavToTimer(val destination: Int, val fragmentToDelete: Int)
data class HelperNavMainMenu(val destination: Int, val fragmentToDelete: Int)
data class GameSetAndWord(val gameSetModel: GameSetModel, val word: String)
