package com.example.spyagent.domain

import com.example.spyagent.domain.model.SetModel
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject


class MainMenuInteractor @Inject constructor(private val mainMenuRepository: MainMenuRepository) {


    suspend fun getWordsNewSet(): Flow<SetModel> {
        return mainMenuRepository.getWordsNewSet()
    }

    suspend fun updateNewSetName(newSetName: String) {
        mainMenuRepository.updateNewSetName(newSetName)
    }

    suspend fun removeWordNewSet(word: String) {
        mainMenuRepository.removeWordNewSet(word)
    }

    suspend fun createNewSet() {
        mainMenuRepository.createNewSet()
    }

    suspend fun addNewWord(id: Int, newWord: String) {
        mainMenuRepository.addNewWord(id, newWord)
    }

    suspend fun addNewWordNewSet(newWord: String) {
        mainMenuRepository.addNewWordNewSet(newWord)
    }

    suspend fun removeWord(id: Int, word: String) {
        mainMenuRepository.removeWord(id, word)
    }

    suspend fun updateWord(id: Int, word: String, newWord: String) {
        mainMenuRepository.updateWord(id, word, newWord)
    }

    suspend fun updateWordNewSet(word: String, newWord: String) {
        mainMenuRepository.updateWordNewSet(word, newWord)
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