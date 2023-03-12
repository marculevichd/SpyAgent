package com.example.spyagent.domain

import com.example.spyagent.domain.model.GameSetModel
import com.example.spyagent.domain.model.SetModel
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject


class MainMenuInteractor @Inject constructor(private val mainMenuRepository: MainMenuRepository) {



    suspend fun createNewSet():  Flow<SetModel> {
        return mainMenuRepository.createNewSet()
    }

    suspend fun selectCategoryToPlay(): GameSetModel {
        return mainMenuRepository.selectCategoryToPlay()
    }

    suspend fun checkDoesGameSetExist() : Boolean  {
        return mainMenuRepository.checkDoesGameSetExist()
    }

    suspend fun addSetToGameDataBase(setModel: SetModel){
        mainMenuRepository.addSetToGameDataBase(setModel)
    }

    suspend fun deleteSetFromGameDataBase(gameSetModelId:Int){
        mainMenuRepository.deleteSetFromGameDataBase(gameSetModelId)
    }

    suspend fun getSetsWhichSelected(): List<GameSetModel> {
        return mainMenuRepository.getSetsWhichSelected()
    }


    suspend fun addNewWord(id: Int, newWord: String) {
        mainMenuRepository.addNewWord(id, newWord)
    }

    suspend fun removeWord(id: Int, word: String) {
        mainMenuRepository.removeWord(id, word)
    }

    suspend fun updateWord(id: Int, word: String, newWord: String) {
        mainMenuRepository.updateWord(id, word, newWord)
    }


    suspend fun addStartSet() {
        mainMenuRepository.addStartSet()
    }

    suspend fun getSets(): Flow<List<SetModel>> {
        return mainMenuRepository.getSets()
    }

    suspend fun getWords(id: Int): Flow<SetModel> {
        return mainMenuRepository.getWords(id)
    }

    suspend fun deleteSet(id: Int) {
        mainMenuRepository.deleteSet(id)
    }

    suspend fun searchSets(searchText: String): Flow<List<SetModel>> {
        return mainMenuRepository.searchSets(searchText)
    }


    suspend fun updateSetName(id: Int, newSetName: String) {
        mainMenuRepository.updateSetName(id, newSetName)
    }

}