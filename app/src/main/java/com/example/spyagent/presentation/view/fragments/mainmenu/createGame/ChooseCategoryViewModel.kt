package com.example.spyagent.presentation.view.fragments.mainmenu.createGame

import android.util.Log
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
class ChooseCategoryViewModel @Inject constructor(private val mainMenuInteractor: MainMenuInteractor) :
    ViewModel() {

    private var _listSets = MutableLiveData<List<SetModel>>()
    val listSets: LiveData<List<SetModel>> = _listSets
    fun showSets() {
        try {
            viewModelScope.launch {
                val temple = mainMenuInteractor.getSets()
                temple.collect {
                    _listSets.value = it
                }
            }
        } catch (e: Exception) {
            Log.w("", e.toString())
        }
    }

    fun addSetToGameDataBase(setModel: SetModel) {
        try {
            viewModelScope.launch {
                mainMenuInteractor.addSetToGameDataBase(setModel)
            }
        } catch (e: Exception) {
            Log.w("", e.toString())
        }
    }

    fun deleteFromSetToGameDataBase(gameSetModelId: Int) {
        try {

            viewModelScope.launch {
                mainMenuInteractor.deleteSetFromGameDataBase(gameSetModelId)
            }
        } catch (e: Exception) {
            Log.w("", e.toString())
        }
    }


}