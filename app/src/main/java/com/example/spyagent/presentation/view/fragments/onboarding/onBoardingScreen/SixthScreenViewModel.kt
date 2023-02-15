package com.example.spyagent.presentation.view.fragments.onboarding.onBoardingScreen

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.spyagent.R
import com.example.spyagent.domain.StartNavigationInteractor
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class SixthScreenViewModel @Inject constructor(private val startNavigationInteractor: StartNavigationInteractor) :ViewModel() {

    // сохранения результата просмотра онбординга
    private var _saveResultOnBoard = MutableLiveData<Unit>()

    fun saveResultSawOnBoard(): Unit {
        startNavigationInteractor.saveResultOnBoard()
        _saveResultOnBoard.value = Unit
    }


    private var _helpNav = MutableLiveData<NavHelperSixthScreen?>()
    val helpNav: LiveData<NavHelperSixthScreen?> = _helpNav

    fun navToNextFragment(){
        _helpNav.value = NavHelperSixthScreen(R.id.action_onBoardingFragment_to_mainMenuFragment, R.id.onBoardingFragment)
    }

    fun userNavigated(){
        _helpNav.value = null
    }

}


data class NavHelperSixthScreen(val destination: Int, val toDelete:Int)