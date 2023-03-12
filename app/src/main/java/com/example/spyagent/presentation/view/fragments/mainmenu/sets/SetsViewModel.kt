package com.example.spyagent.presentation.view.fragments.mainmenu.sets

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
class SetsViewModel @Inject constructor(private val mainMenuInteractor: MainMenuInteractor) :
    ViewModel() {

    private val _foundSets = MutableLiveData<List<SetModel>>()
    val foundSets: LiveData<List<SetModel>> = _foundSets
    fun searchSets(searchText: String) {
        try {
            viewModelScope.launch {
                val temple = mainMenuInteractor.searchSets(searchText)
                temple.collect {
                    _foundSets.value = it
                }
            }
        } catch (e: Exception) {
            Log.w("", e.toString())
        }
    }

    private var _deleteAndNavigate = MutableLiveData<Unit>()

    fun deleteSet(id: Int) {
        try {
            viewModelScope.launch {
                mainMenuInteractor.deleteSet(id)
                _deleteAndNavigate.value = Unit
            }
        } catch (e: Exception) {
            Log.w("", e.toString())
        }
    }

    private var _helpNavToAddNewSet = MutableLiveData<Int?>()
    val helpNavToAddNewSet: LiveData<Int?> = _helpNavToAddNewSet

    fun openAddNewSet() {
        _helpNavToAddNewSet.value = R.id.action_setsFragment_to_addNewSetFragment
    }

    fun userNavigatedToAddNewSet() {
        _helpNavToAddNewSet.value = null
    }

    private val _helpNavToSet = MutableLiveData<HelpNavToSet?>()
    val helpNavToSet: LiveData<HelpNavToSet?> = _helpNavToSet

    fun navToSet(setModel: SetModel) {
        _helpNavToSet.value = HelpNavToSet(R.id.action_setsFragment_to_setDetailFragment, setModel)
    }

    fun userNavigatedToSet() {
        _helpNavToSet.value = null
    }


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
}

data class HelpNavToSet(val destination: Int, val setModel: SetModel)

