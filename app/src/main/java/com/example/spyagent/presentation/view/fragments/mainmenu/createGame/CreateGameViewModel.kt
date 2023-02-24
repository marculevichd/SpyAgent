package com.example.spyagent.presentation.view.fragments.mainmenu.createGame

import android.util.ArraySet
import androidx.collection.arraySetOf
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.spyagent.R
import com.example.spyagent.domain.MainMenuInteractor
import com.example.spyagent.domain.model.SetModel
import com.example.spyagent.presentation.view.fragments.mainmenu.sets.HelpNavToSet
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CreateGameViewModel @Inject constructor(private val mainMenuInteractor: MainMenuInteractor) :
    ViewModel() {

    private var _helpNavChooseCategory = MutableLiveData<Int?>()
    val helpNavChooseCategory: LiveData<Int?> = _helpNavChooseCategory

    fun navToChooseCategory() {
        _helpNavChooseCategory.value = R.id.action_createGameFragment_to_chooseCategoryFragment
    }

    fun userNavigatedToChooseCategory() {
        _helpNavChooseCategory.value = null
    }


    private var _listSetsWhichSelected = MutableLiveData<List<String>>()
    val listSetsWhichSelected: LiveData<List<String>> = _listSetsWhichSelected

    fun showSetsWhichSelected() {
        viewModelScope.launch {
            val list = mainMenuInteractor.getSetsWhichSelected()
            val listTitle: ArrayList<String> = arrayListOf()
            list.map {
                listTitle.add(it.setName)
            }
            _listSetsWhichSelected.value = listTitle
        }
    }


    private var _numberOfPlayers = MutableLiveData<Int>()
    val numberOfPlayers: LiveData<Int> = _numberOfPlayers

    fun numberOfPlayersPlusOne(value: Int) {
        _numberOfPlayers.value = value + 1
    }

    fun numberOfPlayersMinusOne(value: Int) {
        _numberOfPlayers.value = value - 1
    }

    private var _numberOfSpies = MutableLiveData<Int>()
    val numberOfSpies: LiveData<Int> = _numberOfSpies

    fun numberOfSpiesPlusOne(value: Int) {
        _numberOfSpies.value = value + 1
    }

    fun numberOfSpiesMinusOne(value: Int) {
        _numberOfSpies.value = value - 1
    }

    private var _timerValue = MutableLiveData<Int>()
    val timerValue: LiveData<Int> = _timerValue

    fun timerPlusOne(value: Int) {
        _timerValue.value = value + 1
    }

    fun timerMinusOne(value: Int) {
        _timerValue.value = value - 1
    }

    private val _doesGameSetExist = MutableLiveData<Boolean>()
    val doesGameSetExist: LiveData<Boolean> = _doesGameSetExist

    fun checkDoesGameSetExist() {
        viewModelScope.launch {
            _doesGameSetExist.value = mainMenuInteractor.checkDoesGameSetExist()
        }
    }


    private val _helpNavToStartGame = MutableLiveData<HelpNavToStartGame?>()
    val helpNavToSet: LiveData<HelpNavToStartGame?> = _helpNavToStartGame

    fun navToStartGame(
        numberOfPlayerValue: Int,
        numberOfSpiesValue: Int,
        timerValue: Int,
        showCategory: Boolean
    ) {
        _helpNavToStartGame.value = HelpNavToStartGame(
            R.id.action_createGameFragment_to_startGameFragment,
            R.id.createGameFragment,
            numberOfPlayerValue,
            numberOfSpiesValue,
            timerValue,
            showCategory
        )
    }

    fun userNavigatedToStartGame() {
        _helpNavToStartGame.value = null
    }


}

data class HelpNavToStartGame(
    val destination: Int,
    val fragmentToDelete: Int,
    val numberOfPlayerValue: Int,
    val numberOfSpiesValue: Int,
    val timerValue: Int,
    val showCategory: Boolean
)