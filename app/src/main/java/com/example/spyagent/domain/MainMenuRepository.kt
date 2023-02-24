package com.example.spyagent.domain

import com.example.spyagent.domain.model.GameSetModel
import com.example.spyagent.domain.model.SetModel
import kotlinx.coroutines.flow.Flow

interface MainMenuRepository {

    suspend fun selectCategoryToPlay(): GameSetModel

    suspend fun checkDoesGameSetExist() : Boolean

    suspend fun getSetsWhichSelected(): List<GameSetModel>

    suspend fun addSetToGameDataBase(setModel: SetModel)

    suspend fun deleteSetFromGameDataBase(gameSetModelId:Int)

    suspend fun getWordsNewSet(): Flow<SetModel>

    suspend fun updateNewSetName(newSetName: String)

    suspend fun removeWordNewSet(word: String)

    suspend fun addNewWordNewSet(newWord:String)

    suspend fun  createNewSet()

    suspend fun updateWord(id: Int, word: String, newWord:String)

    suspend fun updateWordNewSet(word: String, newWord:String)

    suspend fun addStartSet()

    suspend fun getSets(): Flow<List<SetModel>>

    suspend fun getWords(id: Int): Flow<SetModel>

    suspend fun deleteSet(id: Int)

    suspend fun searchSets(searchText: String):  Flow<List<SetModel>>

    suspend fun removeWord(id: Int, word: String)

    suspend fun updateSetName(id:Int, newSetName:String)

    suspend fun addNewWord(id:Int, newWord:String)

}