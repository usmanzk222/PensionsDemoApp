package com.lbg.pensionsdemo.notifications

import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.core.app.NotificationCompat
import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage
import com.lbg.pensionsdemo.MainActivity
import com.lbg.pensionsdemo.R

class PensionsNotificationsService : FirebaseMessagingService() {

    override fun onMessageReceived(remoteMessage: RemoteMessage) {
        Log.d("MyFirebaseMsgService", "From: ${remoteMessage.from}")

        // Check if message contains a data payload.
        val navigationTarget =  remoteMessage.data["navigation_target"]
        if (remoteMessage.data.isNotEmpty()) {
            Log.d("MyFirebaseMsgService", "Message data payload: ${remoteMessage.data}")
        }else{
            Log.d("MyFirebaseMsgService", "Message data payload is empty")
        }

        // Check if message contains a notification payload.
        remoteMessage.notification?.let {
            Log.d("MyFirebaseMsgService", "Message Notification Body: ${it.body}")
            sendNotification(it.title, it.body, navigationTarget)
        }
    }

    private fun sendNotification(title: String?, messageBody: String?, navigationTarget: String?) {
        val intent = Intent(this, MainActivity::class.java).apply {
            navigationTarget?.let {
                putExtra("NAVIGATION_TARGET", navigationTarget)
            }
            addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_NEW_TASK)
        }

        val pendingIntent = PendingIntent.getActivity(this, 0 /* Request code */, intent,
            PendingIntent.FLAG_UPDATE_CURRENT or PendingIntent.FLAG_IMMUTABLE)

        val data = Bundle().apply {
            putString("NAVIGATION_TARGET", navigationTarget)
        }

        val channelId = getString(R.string.default_notification_channel_id)
        val notificationBuilder = NotificationCompat.Builder(this, channelId)
            .setSmallIcon(R.drawable.ic_launcher_background) // Replace with your notification icon
            .setContentTitle(title ?: "Scottish Widows")
            .setContentText(messageBody ?: "This is a test message")
            .setAutoCancel(true)
            .setContentIntent(pendingIntent)
            .setExtras(data)

        val notificationManager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager

        // Since android Oreo notification channel is needed.
        val channel = NotificationChannel(channelId,
            getString(R.string.default_notification_channel_name),
            NotificationManager.IMPORTANCE_HIGH)
        notificationManager.createNotificationChannel(channel)



        notificationManager.notify(0 /* ID of notification */, notificationBuilder.build())
    }

    override fun onNewToken(token: String) {
        Log.d(TAG, "Refreshed token: $token")
        super.onNewToken(token)
    }

    companion object {
        private const val TAG = "MyFirebaseMsgService"
    }
}