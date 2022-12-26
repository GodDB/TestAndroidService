package com.example.testservice

import android.app.Service
import android.content.Intent
import android.os.IBinder
import android.util.Log
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class TestService : Service() {

    lateinit var job : Job

    companion object {
        private const val ID = 1000
    }

    override fun onCreate() {
        Log.e("godgod", "onCreate")
        super.onCreate()
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        Log.e("godgod", "onStartCommand")
        startForeground(ID, createNotification(intent))
        return START_NOT_STICKY
    }

    override fun onRebind(intent: Intent?) {
        Log.e("godgod", "onRebind")
        super.onRebind(intent)
    }

    override fun onTaskRemoved(rootIntent: Intent?) {
        Log.e("godgod", "onTaskRemoved")
        //job.cancel()
        stopSelf()
        super.onTaskRemoved(rootIntent)
    }

    override fun onDestroy() {
        Log.e("godgod", "onDestory")

        super.onDestroy()
    }

    override fun onBind(intent: Intent?): IBinder? {
        Log.e("godgod", "onBind")
        return null
    }
}
