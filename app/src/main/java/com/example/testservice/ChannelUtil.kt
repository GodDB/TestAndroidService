package com.example.testservice

import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationChannelGroup
import android.app.NotificationManager
import android.app.PendingIntent
import android.app.Service
import android.content.Context
import android.content.Intent
import android.graphics.BitmapFactory
import android.media.AudioAttributes
import android.media.AudioManager
import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationCompat.FOREGROUND_SERVICE_IMMEDIATE
import androidx.core.app.NotificationCompat.ServiceNotificationBehavior

const val CHANNEL_ID = "2000"

@RequiresApi(Build.VERSION_CODES.O)
fun createNotiChannel(context: Context){
    val manager = (context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager)

    val channel = NotificationChannel(
        CHANNEL_ID,
        "포그라운 서비스",
        NotificationManager.IMPORTANCE_HIGH
    ).apply {
        lockscreenVisibility = Notification.VISIBILITY_PUBLIC
    }

    manager.createNotificationChannel(channel)
}


fun Service.createNotification(intent : Intent?) : Notification {
    Log.e("godgod", "createNotification() START")
    val title = "foreground service title"
    val content = "foreground service description"

    val resultIntent = Intent(this, MainActivity::class.java)
    resultIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
    resultIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
    resultIntent.addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP)

    val pendingIntent = PendingIntent.getActivity(
        this,
        2,
        resultIntent,
        PendingIntent.FLAG_CANCEL_CURRENT or PendingIntent.FLAG_IMMUTABLE
    )
    val icon = BitmapFactory.decodeResource(resources, R.mipmap.ic_launcher)

    // TargetSDK 27 대응. Channel ID 적용된 notification 생성.
    val notification = NotificationCompat.Builder(this, CHANNEL_ID)
        //.setForegroundServiceBehavior(FOREGROUND_SERVICE_IMMEDIATE)
        .setContentTitle(title)
        .setTicker(title)
        .setContentText(content)
        .setSmallIcon(androidx.core.R.drawable.notification_bg)
        .setLargeIcon(icon)
        .setContentIntent(pendingIntent)
        .setOngoing(true)
        .build()

    Log.e("godgod","createNotification() END")
    return notification
}
