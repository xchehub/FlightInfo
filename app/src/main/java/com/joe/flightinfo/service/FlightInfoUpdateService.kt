package com.joe.flightinfo.service

import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.Service
import android.content.Context
import android.content.Intent
import android.os.Build
import android.os.IBinder
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.core.app.NotificationCompat
import com.joe.flightinfo.R
import com.joe.flightinfo.data.FlightInfoRepository
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class FlightInfoUpdateService : Service() {

    lateinit var job: Job
    var runRepeat = true
    private var timer = DURATION_SECONDS
    private suspend fun run() = coroutineScope {
        job = launch {
            while (runRepeat) {
                ///update UI
                getFlightInfo()
                delay(timer * 1000L)
            }
        }
    }

    override fun onBind(intent: Intent?): IBinder? {
        return null
    }

    override fun onDestroy() {
        super.onDestroy()
        //remove runnable when service closed
        if (::job.isInitialized && job.isActive) {
            job.cancel()
            runRepeat = false
        }
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        val timeString = intent?.getStringExtra("timer")
        timer = timeString?.toLong() ?: DURATION_SECONDS
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) setForeGroundService()
        GlobalScope.launch {
            run()
        }
        //Process.setThreadPriority(Thread.currentThread().id.toInt(),Process.THREAD_PRIORITY_BACKGROUND)
        if (intent == null) {
            return START_NOT_STICKY
        }
        return START_REDELIVER_INTENT
    }

    @RequiresApi(Build.VERSION_CODES.O)
    private fun setForeGroundService() {

        //to keep service alive
        val channelId = "flight_info"
        val chan = NotificationChannel(
            channelId, "ForegroundService", NotificationManager.IMPORTANCE_NONE
        )
        val ss = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        ss.createNotificationChannel(chan)
        val builder = NotificationCompat.Builder(this, channelId)
        val notification =
            builder.setOngoing(true).setSmallIcon(R.mipmap.ic_launcher_round).setPriority(10)
                .setCategory(Notification.CATEGORY_SERVICE).build()
        startForeground(1, notification)
    }

    private suspend fun getFlightInfo() {
        Log.i(TAG, "Update flight information")
        FlightInfoRepository().getAllArrivalFlightRepository("TPE")
        FlightInfoRepository().getAllDepartureFlightRepository("TPE")
    }

    companion object {
        private const val DURATION_SECONDS = 180L
        private val TAG: String? = FlightInfoUpdateService::class.java.simpleName
    }
}