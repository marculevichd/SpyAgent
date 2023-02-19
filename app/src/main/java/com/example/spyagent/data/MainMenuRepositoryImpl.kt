package com.example.spyagent.data

import com.example.spyagent.data.database.sets.SetEntity
import com.example.spyagent.data.database.sets.SetsDAO
import com.example.spyagent.domain.MainMenuRepository
import com.example.spyagent.domain.model.SetModel
import com.example.spyagent.utils.Const.SET_NAME
import com.example.spyagent.utils.Const.START_SET_NAME
import com.example.spyagent.utils.Const.START_SET_WORD1
import com.example.spyagent.utils.Const.START_SET_WORD10
import com.example.spyagent.utils.Const.START_SET_WORD2
import com.example.spyagent.utils.Const.START_SET_WORD3
import com.example.spyagent.utils.Const.START_SET_WORD4
import com.example.spyagent.utils.Const.START_SET_WORD5
import com.example.spyagent.utils.Const.START_SET_WORD6
import com.example.spyagent.utils.Const.START_SET_WORD7
import com.example.spyagent.utils.Const.START_SET_WORD8
import com.example.spyagent.utils.Const.START_SET_WORD9
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.withContext
import javax.inject.Inject

class MainMenuRepositoryImpl @Inject constructor(private val setsDAO: SetsDAO) :
    MainMenuRepository {


    override suspend fun updateSetName(id: Int, newSetName: String) {
        withContext(Dispatchers.IO) {
            setsDAO.updateSetName(newSetName, id)
        }
    }

    override suspend fun addNewWord(id: Int, newWord: String) {
        withContext(Dispatchers.IO) {
            val entity = setsDAO.searchInSetsTableWithoutFlow(id)
            val newList = entity.listWords
            newList.add(newWord)
            setsDAO.updateList(newList, id)
        }
    }

    override suspend fun updateWord(id: Int, word: String, newWord: String) {
        withContext(Dispatchers.IO) {
            val entity = setsDAO.searchInSetsTableWithoutFlow(id)
            val newList = entity.listWords
            val index = newList.indexOf(word)
            newList[index] = newWord

            setsDAO.updateList(newList, id)
        }
    }


    override suspend fun removeWord(id: Int, word: String) {
        withContext(Dispatchers.IO) {
            val entity = setsDAO.searchInSetsTableWithoutFlow(id)

            val newList = entity.listWords
            newList.remove(word)

            setsDAO.updateList(newList, id)
        }
    }

    override suspend fun addStartSet() {
        return withContext(Dispatchers.IO) {
            setsDAO.addSet(
                SetEntity(
                    1,
                    START_SET_NAME,
                    arrayListOf(
                        START_SET_WORD1,
                        START_SET_WORD2,
                        START_SET_WORD3,
                        START_SET_WORD4,
                        START_SET_WORD5,
                        START_SET_WORD6,
                        START_SET_WORD7,
                        START_SET_WORD8,
                        START_SET_WORD9,
                        START_SET_WORD10
                    )
                )
            )
        }
    }


    override suspend fun addNewWordNewSet(newWord: String) {
        withContext(Dispatchers.IO) {
            val id = setsDAO.getSizeTable()
            val entity = setsDAO.searchInSetsTableWithoutFlow(id)
            val newList = entity.listWords
            newList.add(newWord)
            setsDAO.updateList(newList, id)
        }
    }

    override suspend fun createNewSet() {
        withContext(Dispatchers.IO) {
            val id = setsDAO.getSizeTable() + 1
            setsDAO.addSet(
                SetEntity(
                    id,
                    SET_NAME,
                    arrayListOf()
                )
            )
        }
    }


    override suspend fun updateWordNewSet(word: String, newWord: String) {
        withContext(Dispatchers.IO) {
            val id = setsDAO.getSizeTable()
            val entity = setsDAO.searchInSetsTableWithoutFlow(id)
            val newList = entity.listWords
            val index = newList.indexOf(word)
            newList[index] = newWord
            setsDAO.updateList(newList, id)
        }
    }


    override suspend fun updateNewSetName(newSetName: String) {
        withContext(Dispatchers.IO) {
            val id = setsDAO.getSizeTable()
            setsDAO.updateSetName(newSetName, id)
        }
    }


    override suspend fun removeWordNewSet(word: String) {
        withContext(Dispatchers.IO) {
            val id = setsDAO.getSizeTable()
            val entity = setsDAO.searchInSetsTableWithoutFlow(id)
            val newList = entity.listWords
            newList.remove(word)
            setsDAO.updateList(newList, id)
        }
    }

    override suspend fun getSets(): Flow<List<SetModel>> {
        return withContext(Dispatchers.IO) {
            val entity = setsDAO.getSets()
            entity.map {
                it.map {
                    SetModel(
                        it.id,
                        it.setName,
                        it.listWords
                    )
                }
            }
        }
    }

    override suspend fun getWords(id: Int): Flow<SetModel> {
        return withContext(Dispatchers.IO) {
            val entity: Flow<SetEntity> = setsDAO.searchInSetsTable(id)
            entity.map {
                SetModel(
                    it.id,
                    it.setName,
                    it.listWords
                )
            }

        }
    }

    override suspend fun getWordsNewSet(): Flow<SetModel> {
        return withContext(Dispatchers.IO) {
            val id = setsDAO.getSizeTable()
            val entity: Flow<SetEntity> = setsDAO.searchInSetsTable(id)
            entity.map {
                SetModel(
                    it.id,
                    it.setName,
                    it.listWords
                )
            }
        }
    }


    override suspend fun deleteSet(id: Int) {
        withContext(Dispatchers.IO) {
            setsDAO.deleteSetFromSetsTableById(id)
        }
    }

    override suspend fun searchSets(searchText: String): Flow<List<SetModel>> {
        return withContext(Dispatchers.IO) {
            val entity = setsDAO.searchInSetsDataBase(searchText)
            entity.map { list ->
                list.map { entity ->
                    SetModel(
                        entity.id,
                        entity.setName,
                        entity.listWords
                    )
                }
            }
        }

    }
}