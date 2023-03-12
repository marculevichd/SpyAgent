package com.example.spyagent.presentation.view.fragments.mainmenu.sets

import android.util.Log
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
        try {
            viewModelScope.launch {
                val flow = mainMenuInteractor.getWords(id)
                flow.collect {
                    _listWords.value = it.listWords
                }
            }
        } catch (e: Exception) {
            Log.w("", e.toString())
        }
    }

    fun updateWord(id: Int, word: String, newWord: String) {
        try {
            viewModelScope.launch {
                mainMenuInteractor.updateWord(id, word, newWord)
            }
        } catch (e: Exception) {
            Log.w("", e.toString())
        }
    }

    fun removeWord(id: Int, word: String) {
        try {
            viewModelScope.launch {
                mainMenuInteractor.removeWord(id, word)
            }
        } catch (e: Exception) {
            Log.w("", e.toString())
        }
    }

    fun updateSetName(id: Int, newSetName: String) {
        try {
            viewModelScope.launch {
                mainMenuInteractor.updateSetName(id, newSetName)
            }
        } catch (e: Exception) {
            Log.w("", e.toString())
        }
    }

    fun addNewWord(id: Int, newWord: String) {
        try {
            viewModelScope.launch {
                mainMenuInteractor.addNewWord(id, newWord)
            }
        } catch (e: Exception) {
            Log.w("", e.toString())
        }
    }
}