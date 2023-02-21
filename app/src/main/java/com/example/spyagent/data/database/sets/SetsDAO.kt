package com.example.spyagent.data.database.sets

import androidx.room.*
import kotlinx.coroutines.flow.Flow

@Dao
interface SetsDAO {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun addSet(setEntity: SetEntity)

    @Query("SELECT * FROM sets_table")
    fun getSets(): Flow<List<SetEntity>>

    @Query("SELECT * FROM sets_table WHERE id LIKE :searchId")
    fun searchInSetsTable(searchId: Int): Flow<SetEntity>

    @Query("DELETE FROM sets_table WHERE id =:idForDelete")
    fun deleteSetFromSetsTableById(idForDelete: Int)

    @Query("SELECT * FROM sets_table WHERE setName LIKE :searchText")
    fun searchInSetsDataBase(searchText: String): Flow<List<SetEntity>>

    @Query("SELECT * FROM sets_table WHERE id LIKE :searchId")
    fun searchInSetsTableWithoutFlow(searchId: Int): SetEntity

    @Query("UPDATE sets_table SET listWords = :newList WHERE id =:id")
    fun updateListInSet(newList: String, id: Int)

    fun updateList(possibleList: ArrayList<String>, id: Int) {
        val listToString = possibleList.joinToString(separator = "№")
        updateListInSet(listToString, id)
    }

    @Query("UPDATE sets_table SET setName = :newSetName WHERE id =:id")
    fun updateSetName(newSetName: String, id:Int)


    @Query("SELECT (SELECT COUNT (*) FROM sets_table)!=0")
    fun doesStartSetExist(): Boolean






    @Query("SELECT COUNT (*) FROM sets_table")
    fun getSizeTable(): Int

}