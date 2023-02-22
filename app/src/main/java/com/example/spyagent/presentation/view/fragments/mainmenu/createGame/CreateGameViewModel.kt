package com.example.spyagent.presentation.view.fragments.mainmenu.createGame

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.spyagent.R
import com.example.spyagent.domain.MainMenuInteractor
import com.example.spyagent.domain.model.SetModel
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


}