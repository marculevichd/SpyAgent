package com.example.spyagent.presentation.view.fragments.mainmenu.sets

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.spyagent.domain.MainMenuInteractor
import com.example.spyagent.domain.model.SetModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AddNewSetViewModel @Inject constructor(private val mainMenuInteractor: MainMenuInteractor) :
    ViewModel() {


    private var _listWordsNewSet = MutableLiveData<SetModel>()
    val listWordsNewSet: LiveData<SetModel> = _listWordsNewSet

    fun createNewSet() {
        viewModelScope.launch {
            try {
                val flow = mainMenuInteractor.createNewSet()
                flow.collect {
                    _listWordsNewSet.value = it
                }
            } catch (e: Exception) {
                Log.w("", e.toString())
            }
        }
    }

    fun updateNewSetName(newSetName: String) {
        try {
            viewModelScope.launch {
                mainMenuInteractor.updateSetName(_listWordsNewSet.value!!.id, newSetName)
            }
        } catch (e: Exception) {
            Log.w("", e.toString())
        }
    }


    fun addNewWordNewSet(word: String) {
        try {
            viewModelScope.launch {
                mainMenuInteractor.addNewWord(_listWordsNewSet.value!!.id, word)
            }
        } catch (e: Exception) {
            Log.w("", e.toString())
        }
    }

    fun updateWordNewSet(word: String, newWord: String) {
        try {
            viewModelScope.launch {
                mainMenuInteractor.updateWord(_listWordsNewSet.value!!.id, word, newWord)
            }
        } catch (e: Exception) {
            Log.w("", e.toString())
        }
    }

    fun removeWordNewSet(word: String) {
        try {
            viewModelScope.launch {
                mainMenuInteractor.removeWord(_listWordsNewSet.value!!.id, word)
            }
        } catch (e: Exception) {
            Log.w("", e.toString())
        }
    }
}