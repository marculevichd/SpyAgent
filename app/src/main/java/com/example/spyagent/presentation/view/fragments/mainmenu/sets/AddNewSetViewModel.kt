package com.example.spyagent.presentation.view.fragments.mainmenu.sets

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.spyagent.domain.MainMenuInteractor
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AddNewSetViewModel @Inject constructor(private val mainMenuInteractor: MainMenuInteractor) :
    ViewModel() {


    private var _listWordsNewSet = MutableLiveData<List<String>>()
    val listWordsNewSet: LiveData<List<String>> = _listWordsNewSet

    fun getWordsNewSet() {
        viewModelScope.launch {
            val flow = mainMenuInteractor.getWordsNewSet()
            flow.collect {
                _listWordsNewSet.value = it.listWords
            }
        }
    }


    fun updateNewSetName(newSetName: String) {
        viewModelScope.launch {
            mainMenuInteractor.updateNewSetName(newSetName)
        }
    }

    fun createNewSet() {
        viewModelScope.launch {
            mainMenuInteractor.createNewSet()
        }
    }

    fun addNewWordNewSet(word: String) {
        viewModelScope.launch {
            mainMenuInteractor.addNewWordNewSet(word)
        }
    }

    fun updateWordNewSet(word: String, newWord: String) {
        viewModelScope.launch {
            mainMenuInteractor.updateWordNewSet(word, newWord)
        }
    }

    fun removeWordNewSet(word: String) {
        viewModelScope.launch {
            mainMenuInteractor.removeWordNewSet(word)
        }
    }

}