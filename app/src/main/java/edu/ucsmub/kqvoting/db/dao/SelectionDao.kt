package edu.ucsmub.kqvoting.db.dao

import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import edu.ucsmub.kqvoting.db.model.Selection

@Dao
interface SelectionDao {
    @Query("select * from selection")
    fun getSelections(): LiveData<List<Selection>>

    @Insert
    fun insertSelection(selection: Selection)

    @Query("select * from selection where isBoy = 1")
    fun getSelectionBoys(): LiveData<List<Selection>>

    @Query("select * from selection where isBoy = 0")
    fun getSelectionGirls(): LiveData<List<Selection>>

    @Query("select * from selection where isBoy = 1 AND id != :kingID")
    fun getPopularList(kingID: String): LiveData<List<Selection>>

    @Query("select * from selection where isBoy = 0 AND id  != :queenID")
    fun getInnocenceList(queenID: String): LiveData<List<Selection>>

    @Query("update selection set isVote = 1 where id = :id")
    fun voted(id: Int)

    @Query("update selection set isVote = 0 where id = :id")
    fun unVoted(id: Int)

    @Query("select * from selection where id = :ID")
    fun getSelectionByID(ID: String): Selection

    @Query("update selection set firstCount = :firstCount, secondCount = :secondCount where id = :ID")
    fun updateCountsByID(firstCount: Int, secondCount: Int, ID: String)

    //for Boy
    @Query("select * from selection where isBoy = 1 and firstCount = (select max(firstCount) from selection where isBoy = 1) limit 1")
    fun getCurrentKing(): LiveData<List<Selection>>

    @Query("select * from selection where isBoy = 1 order by secondCount desc")
    fun getPopularList(): LiveData<List<Selection>>

    //for Girl
    @Query("select * from selection where isBoy = 0 and firstCount = (select max(firstCount) from selection where isBoy = 0) limit 1")
    fun getCurrentQueen(): LiveData<List<Selection>>

    @Query("select * from selection where isBoy = 0 order by secondCount desc")
    fun getInnocenceList(): LiveData<List<Selection>>

    //delete in table
    @Query("delete from selection")
    fun deleteAllData()
}