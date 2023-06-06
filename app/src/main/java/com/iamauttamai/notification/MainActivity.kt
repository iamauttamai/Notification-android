package com.iamauttamai.notification

import android.annotation.SuppressLint
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.os.Build
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.NotificationCompat
import com.iamauttamai.notification.databinding.ActivityMainBinding
import java.util.*

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.button.setOnClickListener {
            val intent = Intent(this, SecondActivity::class.java)
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
            createNotificationChannel("Notification Custom", binding.txt.text.toString(), intent)
        }

    }

    private fun createNotificationChannel(title: String, msg: String, intent: Intent) {
        showNotification(title, msg, intent, this)
    }

    @SuppressLint("UnspecifiedImmutableFlag")
    private fun showNotification(strtitle: String, msg: String, intent: Intent?, context: Context) {
        val channel = NotificationChannel("my_channel_01", "notification", NotificationManager.IMPORTANCE_HIGH)
        val random = Random().nextInt(1000)
        val inboxStyle = NotificationCompat.InboxStyle()
        var pendingIntent: PendingIntent? = null
        if (intent != null) {
            intent.action = "actionstring" + System.currentTimeMillis()
            pendingIntent = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S) {
                PendingIntent.getActivity(
                    this,
                    0, intent,
                    PendingIntent.FLAG_MUTABLE
                )
            } else {
                PendingIntent.getActivity(
                    this,
                    0, intent,
                    PendingIntent.FLAG_UPDATE_CURRENT
                )
            }
        }
        val notification: NotificationCompat.Builder =
            NotificationCompat.Builder(context, channel.id)
                .setContentTitle(strtitle)
                .setContentText(msg)
                .setStyle(inboxStyle.addLine(msg))
                .setAutoCancel(true)
                .setColor(Color.parseColor("#78b52e"))
                .setSmallIcon(R.drawable.ic_launcher_background)

        if (pendingIntent != null) {
            notification.setContentIntent(pendingIntent)
        }
        val mNotificationManager =
            context.getSystemService(NOTIFICATION_SERVICE) as NotificationManager
        mNotificationManager.createNotificationChannel(channel)
        mNotificationManager.notify(random, notification.build())
    }

}