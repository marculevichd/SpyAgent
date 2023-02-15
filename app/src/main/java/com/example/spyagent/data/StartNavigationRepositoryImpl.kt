package com.example.spyagent.data

import com.example.spyagent.domain.StartNavigationRepository
import javax.inject.Inject

class StartNavigationRepositoryImpl @Inject constructor(private val sharedPreferencesHelper: SharedPreferencesHelper) :
    StartNavigationRepository {


    override fun saveResultOnBoard() {
        sharedPreferencesHelper.saveResultOnBoard()
    }

    override fun getResultSawOnBoard(): Boolean {
        return sharedPreferencesHelper.getResultSawOnBoard()
    }
}