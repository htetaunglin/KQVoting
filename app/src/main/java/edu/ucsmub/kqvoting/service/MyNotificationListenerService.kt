package edu.ucsmub.kqvoting.service

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Build
import android.os.IBinder
import android.service.notification.NotificationListenerService
import android.service.notification.StatusBarNotification
import android.util.Log
import android.content.ComponentName
import android.annotation.TargetApi
import edu.ucsmub.kqvoting.db.AppDatabase
import edu.ucsmub.kqvoting.db.model.Notification
import org.jetbrains.anko.doAsync
import java.util.*


@SuppressLint("NewApi")
class MyNotificationListenerService : NotificationListenerService() {


    override fun onCreate() {
        super.onCreate()
    }

    override fun onDestroy() {
        super.onDestroy()
    }

    override fun onNotificationRemoved(sbn: StatusBarNotification?) {
        super.onNotificationRemoved(sbn)
    }

    override fun onBind(intent: Intent?): IBinder? {
        return super.onBind(intent)
    }

    val APP_PACKAGE = "edu.ucsmub.kqvoting"

    override fun onNotificationPosted(sbn: StatusBarNotification?) {
        activeNotifications.forEach {
            Log.d("this is ", "${it.packageName}")
            if (it.packageName == APP_PACKAGE) {
                val title = it.notification.extras.getString("android.title")
                val text = it.notification.extras.getString("android.text")
                Log.d("this is ", "$title and $text")
                doAsync {
                    val db = AppDatabase.getInstance(this@MyNotificationListenerService)
                    val notification = Notification(null, title, text, Date())
                    db.notificationDao().insertNotification(notification)
                }
            }
        }
        super.onNotificationPosted(sbn)
    }

    @TargetApi(Build.VERSION_CODES.N)
    override fun onListenerDisconnected() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            // Notification listener disconnected - requesting rebind
            NotificationListenerService.requestRebind(ComponentName(this, NotificationListenerService::class.java))
        }
    }

    @TargetApi(Build.VERSION_CODES.N)
    override fun onListenerConnected() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            activeNotifications
        }
        Log.d("This", "Listener connected")
    }
}
