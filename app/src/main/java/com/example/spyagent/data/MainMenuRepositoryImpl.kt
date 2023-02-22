package com.example.spyagent.data

import com.example.spyagent.data.database.sets.GameSetEntity
import com.example.spyagent.data.database.sets.SetEntity
import com.example.spyagent.data.database.sets.SetsDAO
import com.example.spyagent.domain.MainMenuRepository
import com.example.spyagent.domain.model.GameSetModel
import com.example.spyagent.domain.model.SetModel
import com.example.spyagent.utils.Const.SET_NAME
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.withContext
import javax.inject.Inject

class MainMenuRepositoryImpl @Inject constructor(
    private val setsDAO: SetsDAO,
    private val apiService: ApiService
) : MainMenuRepository {


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
        withContext(Dispatchers.IO) {
            if (!setsDAO.doesStartSetExist()) {

                val response = apiService.getSetDataFromJson()

                response.body()?.let {

                    setsDAO.addSet(
                        SetEntity(
                            it.setId, it.title,
                            arrayListOf(
                                it.listWords[0].word1,
                                it.listWords[1].word2,
                                it.listWords[2].word3,
                                it.listWords[3].word4,
                                it.listWords[4].word5,
                                it.listWords[5].word6,
                                it.listWords[6].word7,
                                it.listWords[7].word8,
                                it.listWords[8].word9,
                                it.listWords[9].word10
                            ),
                            false
                        )
                    )
                }
            }
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
                    arrayListOf(),
                    false
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
                        it.listWords,
                        it.isSelected
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
                    it.listWords,
                    it.isSelected
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
                    it.listWords,
                    it.isSelected
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
                        entity.listWords,
                        entity.isSelected
                    )
                }
            }
        }
    }

    override suspend fun addSetToGameDataBase(setModel: SetModel) {
        withContext(Dispatchers.IO) {
            setsDAO.updateSetIsSelected(true, setModel.id)

            setsDAO.addGameSet(
                GameSetEntity(
                    setModel.id,
                    setModel.setName,
                    setModel.listWords
                )
            )
        }
    }

    override suspend fun deleteSetFromGameDataBase(setModelId: Int) {
        withContext(Dispatchers.IO) {
            setsDAO.updateSetIsSelected(false, setModelId)
            setsDAO.deleteSetFromGameDataBase(setModelId)
        }
    }


    override suspend fun getSetsWhichSelected() : List<GameSetModel> {
        return withContext(Dispatchers.IO){
            val entityList = setsDAO.getSetsWhichSelected()
            entityList.map{
                    GameSetModel(
                        it.id,
                        it.setName,
                        it.listWords
                    )
                }

            }

        }
    }
