package edu.ucsmub.kqvoting.viewModel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import edu.ucsmub.kqvoting.db.AppDatabase
import edu.ucsmub.kqvoting.db.model.Notification

class NotificationViewModel(application: Application) : AndroidViewModel(application) {
    fun getAllNotification(): LiveData<List<Notification>> {
        val db = AppDatabase.getInstance(this.getApplication())
        return db.notificationDao().selectNotification()
    }
}