package com.example.spyagent.domain

interface StartNavigationRepository {

    fun saveResultOnBoard()

    fun getResultSawOnBoard(): Boolean
}