package com.droids.ffs.smd_project.Notifications;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.TaskStackBuilder;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.media.AudioAttributes;
import android.net.Uri;
import android.support.v4.app.NotificationCompat;
import android.util.Log;

import com.droids.ffs.smd_project.R;
import com.droids.ffs.smd_project.ViewWeeklySchedule.ViewScheduleActivity;

public class AlarmReceiver extends BroadcastReceiver {
    public static final String NOTIFICATION_CHANNEL_ID = "4565";
    public static final String NOTIFICATION_CHANNEL_NAME = "Class Reminders";


    @Override
    public void onReceive(Context context, Intent intent) {
        Log.v("Alarmreceiver", "onReceive");

        SharedPreferences pref = context.getSharedPreferences("MyPref", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = pref.edit();
        boolean reminderSetting = pref.getBoolean("runAlarm", true);

        Log.v("Alarmreceiver", String.valueOf(reminderSetting));

        if (reminderSetting == false) {
            return;
        }

        Uri sound = Uri.parse("android.resource://" + context.getPackageName() + "/raw/notification_sound");

        NotificationManager notificationManager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);


        NotificationChannel notificationChannel = null;

        //Since Notification channels are included in Oreo onwards, sdk condition is required
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
            notificationChannel = new NotificationChannel(NOTIFICATION_CHANNEL_ID, NOTIFICATION_CHANNEL_NAME, NotificationManager.IMPORTANCE_HIGH);
            notificationChannel.enableLights(true);
            notificationChannel.setImportance(NotificationManager.IMPORTANCE_HIGH);
            notificationChannel.setLightColor(Color.CYAN);
            notificationChannel.enableVibration(true);

            AudioAttributes att = new AudioAttributes.Builder()
                    .setUsage(AudioAttributes.USAGE_NOTIFICATION)
                    .setContentType(AudioAttributes.CONTENT_TYPE_MUSIC)
                    .build();

            notificationChannel.setSound(sound, att);
            notificationChannel.setVibrationPattern(new long[]{100, 200, 300, 100, 200, 300, 100, 200, 300, 500});

            notificationManager.createNotificationChannel(notificationChannel);

        }

        final NotificationCompat.Builder builder = new NotificationCompat.Builder(context, NOTIFICATION_CHANNEL_ID)
                .setSound(sound)
                .setSmallIcon(R.drawable.logo2)
                .setVibrate(new long[]{100, 200, 300, 100, 200, 300, 100, 200, 300, 500})
                .setChannelId(NOTIFICATION_CHANNEL_ID)
                .setPriority(NotificationCompat.PRIORITY_HIGH)
                .setAutoCancel(false);


        Notification notification = builder.setSound(sound)
                .setContentTitle("Class Reminder: " + intent.getStringExtra("className"))
                .setContentText(intent.getStringExtra("classroom"))
                .setTicker("Wheres my class: Class Reminder")
                .setSmallIcon(R.drawable.logo2)
                .setLargeIcon(BitmapFactory.decodeResource(context.getResources(),
                        R.drawable.logo2))
                .setPriority(NotificationCompat.PRIORITY_HIGH)
                .build();


        notificationManager.notify(Integer.valueOf(NOTIFICATION_CHANNEL_ID), notification);


    }

}
