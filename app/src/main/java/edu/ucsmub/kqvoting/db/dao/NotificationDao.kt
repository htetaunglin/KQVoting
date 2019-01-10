package edu.ucsmub.kqvoting.db.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import edu.ucsmub.kqvoting.db.model.Notification

@Dao
interface NotificationDao {
    @Insert
    fun insertNotification(notification: Notification)

    @Query("select * from notification order by id desc")
    fun selectNotification(): LiveData<List<Notification>>
}