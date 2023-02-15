package com.example.spyagent.presentation.view.fragments.onboarding


import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.navigation.NavDestination
import com.example.spyagent.R
import com.example.spyagent.domain.StartNavigationInteractor
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class OnBoardingViewModel @Inject constructor(private val startNavigationInteractor: StartNavigationInteractor) :
    ViewModel() {


    private var _saveResultOnBoard = MutableLiveData<Unit>()

    fun saveResultSawOnBoard(): Unit {
        startNavigationInteractor.saveResultOnBoard()
        _saveResultOnBoard.value = Unit
    }

    private var _helpNav = MutableLiveData<HelpNavOnBoard>()
    val helpNav: LiveData<HelpNavOnBoard> = _helpNav

    fun navToNextFragment(){
        _helpNav.value = HelpNavOnBoard(R.id.action_onBoardingFragment_to_mainMenuFragment, R.id.onBoardingFragment)
    }

}

data class HelpNavOnBoard(val destination: Int, val toDelete:Int)