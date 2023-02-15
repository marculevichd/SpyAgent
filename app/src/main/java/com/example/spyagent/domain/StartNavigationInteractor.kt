package com.example.spyagent.domain

import javax.inject.Inject

class StartNavigationInteractor @Inject constructor(private val startNavigationRepository: StartNavigationRepository) {


    fun saveResultOnBoard(){
        startNavigationRepository.saveResultOnBoard()
    }

    fun getResultSawOnBoard():Boolean{
        return startNavigationRepository.getResultSawOnBoard()
    }
}