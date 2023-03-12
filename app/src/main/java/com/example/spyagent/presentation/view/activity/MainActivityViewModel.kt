package com.example.spyagent.presentation.view.activity

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.spyagent.R
import com.example.spyagent.domain.StartNavigationInteractor
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MainActivityViewModel @Inject constructor(private val startNavigationInteractor: StartNavigationInteractor) :
    ViewModel() {

    private var _sawOnBoard = MutableLiveData<MainActivityNavHelper>()
    val sawOnBoard: LiveData<MainActivityNavHelper> = _sawOnBoard

    fun getResultSawOnBoard() {
        val result = startNavigationInteractor.getResultSawOnBoard()
        if (result == false) {
            _sawOnBoard.value = MainActivityNavHelper(R.navigation.main_graph, R.id.onBoardingFragment)
        } else {
            _sawOnBoard.value = MainActivityNavHelper(R.navigation.main_graph,R.id.mainMenuFragment)
        }
    }
}

data class MainActivityNavHelper(val graph: Int, val startDestination: Int)