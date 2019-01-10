package edu.ucsmub.kqvoting.service

import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage
import edu.ucsmub.kqvoting.R
import android.app.PendingIntent
import android.content.Intent
import edu.ucsmub.kqvoting.Activity.MainActivity
import edu.ucsmub.kqvoting.Activity.NotificationActivity
import edu.ucsmub.kqvoting.extra.UZawString


class MyFirebaseMessagingService : FirebaseMessagingService() {
    override fun onCreate() {
        super.onCreate()
    }

    override fun onNewToken(p0: String?) {
        Log.d("This is KQ", "This is KQ")
        super.onNewToken(p0)
    }

    override fun onMessageReceived(remoteMessage: RemoteMessage?) {

        val APP_PACKAGE = "edu.ucsmub.kqvoting"
        val CHANNEL_ID = "$APP_PACKAGE.APP_CHANNEL"

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            createChannel(CHANNEL_ID, "KQVoting", "Notification", NotificationManager.IMPORTANCE_HIGH)
        }
        val intent = Intent(this, NotificationActivity::class.java)
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
        val pendingIntent = PendingIntent.getActivity(this, 0, intent, PendingIntent.FLAG_ONE_SHOT)

        val mBuilder = NotificationCompat.Builder(this, CHANNEL_ID)
            .setSmallIcon(R.drawable.king_icon)
            .setContentTitle(remoteMessage!!.notification!!.title!!.UZawString())
            .setContentText(remoteMessage!!.notification!!.body!!.UZawString())
            .setPriority(NotificationCompat.PRIORITY_HIGH)
            .setContentIntent(pendingIntent)
            .setAutoCancel(true)

        NotificationManagerCompat.from(this).apply {
            notify(1, mBuilder.build())
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    private fun createChannel(channel_id: String, name: String, description: String, importance: Int) {
        val mChannel = NotificationChannel(channel_id, name, importance)
        mChannel.description = description
        val notificationManager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        notificationManager.createNotificationChannel(mChannel)
    }
}