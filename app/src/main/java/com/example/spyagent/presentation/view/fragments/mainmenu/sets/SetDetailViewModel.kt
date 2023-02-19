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
class SetDetailViewModel @Inject constructor(private val mainMenuInteractor: MainMenuInteractor) :
    ViewModel() {

    private var _listWords = MutableLiveData<List<String>>()
    val listWords: LiveData<List<String>> = _listWords

    fun getWords(id: Int) {
        viewModelScope.launch {
            val flow = mainMenuInteractor.getWords(id)
            flow.collect {
                _listWords.value = it.listWords
            }
        }
    }

    fun updateWord(id: Int, word: String, newWord: String) {
        viewModelScope.launch {
            mainMenuInteractor.updateWord(id, word, newWord)
        }
    }

    fun removeWord(id: Int, word: String) {
        viewModelScope.launch {
            mainMenuInteractor.removeWord(id, word)
        }
    }

    fun updateSetName(id: Int, newSetName: String) {
        viewModelScope.launch {
            mainMenuInteractor.updateSetName(id, newSetName)
        }
    }

    fun addNewWord(id: Int, newWord: String) {
        viewModelScope.launch {
            mainMenuInteractor.addNewWord(id, newWord)
        }
    }

}