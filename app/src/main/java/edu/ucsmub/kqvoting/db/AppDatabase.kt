package edu.ucsmub.kqvoting.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import edu.ucsmub.kqvoting.db.dao.NotificationDao
import edu.ucsmub.kqvoting.db.dao.SelectionDao
import edu.ucsmub.kqvoting.db.model.Notification
import edu.ucsmub.kqvoting.db.model.Selection

@Database(entities = [Selection::class, Notification::class], version = 1, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {

    abstract fun selectionDao(): SelectionDao
    abstract fun notificationDao(): NotificationDao

    companion object {
        private var INSTANCE: AppDatabase? = null

        fun getInstance(context: Context): AppDatabase {
            if (INSTANCE == null) {
                INSTANCE = Room.databaseBuilder(
                    context,
                    AppDatabase::class.java,
                    "kqvoting.db"
                ).build()
            }
            return INSTANCE!!
        }

        fun destoryInstance() {
            INSTANCE = null
        }
    }
}