package com.example.spyagent.presentation.view.activity

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.spyagent.domain.StartNavigationInteractor
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MainActivityViewModel @Inject constructor(private val startNavigationInteractor: StartNavigationInteractor) : ViewModel(){

    private var _sawOnBoard = MutableLiveData<Boolean>()
    val sawOnBoard: LiveData<Boolean> = _sawOnBoard

    fun getResultSawOnBoard() {
        _sawOnBoard.value = startNavigationInteractor.getResultSawOnBoard()
    }

}