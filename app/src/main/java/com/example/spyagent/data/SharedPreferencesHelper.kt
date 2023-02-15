package com.example.spyagent.data

import android.content.SharedPreferences
import javax.inject.Inject

class SharedPreferencesHelper @Inject constructor(
    private val sharedPreferences: SharedPreferences
) {

    fun saveResultOnBoard (){
        sharedPreferences.edit().putBoolean(ON_BOARD, true).apply()
    }

    fun getResultSawOnBoard(): Boolean{
    return sharedPreferences.getBoolean(ON_BOARD, false)
    }

    companion object {
        private const val ON_BOARD = "ON_BOARD"
    }


}