package com.example.fathurradhy.mocinemas.utils.reminder;

import android.app.AlarmManager;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.media.RingtoneManager;
import android.net.Uri;
import android.util.Log;

import com.example.fathurradhy.mocinemas.R;
import com.example.fathurradhy.mocinemas.domain.model.movies.MoviesModelResult;

import java.util.ArrayList;
import java.util.Calendar;

import androidx.core.app.NotificationCompat;

public class MovieAlarmReceiver extends BroadcastReceiver {

    private static final int NOTIFICATION_ID = 101;

    public static final String NOTIFICATION_CHANNEL_ID = "10001";

    private static int notifId = 1000;

    public MovieAlarmReceiver() {

    }

    @Override
    public void onReceive(Context context, Intent intent) {
        int notifId = intent.getIntExtra("id", 0);
        String title = intent.getStringExtra("title");

        Log.e("id", String.valueOf(notifId));

        showAlarmNotification(context, title, notifId);
    }

    private void showAlarmNotification(Context context, String title, int notifId) {
        NotificationManager notificationManager = (NotificationManager) context.getSystemService(
                Context.NOTIFICATION_SERVICE);

        Uri alarmRingtone = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
        NotificationCompat.Builder builder = new NotificationCompat.Builder(context)
                .setSmallIcon(R.drawable.ic_reminder)
                .setContentTitle(title)
                .setContentText(context.getString(R.string.released_now))
                .setPriority(NotificationCompat.PRIORITY_HIGH)
                .setAutoCancel(true)
                .setSound(alarmRingtone);

        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
            int importance = NotificationManager.IMPORTANCE_HIGH;
            NotificationChannel notificationChannel = new NotificationChannel(NOTIFICATION_CHANNEL_ID, "NOTIFICATION_CHANNEL_NAME", importance);

            builder.setChannelId(NOTIFICATION_CHANNEL_ID);
            notificationManager.createNotificationChannel(notificationChannel);
        }

        notificationManager.notify(notifId, builder.build());
    }

    public void setRepeatingAlarm(Context context, ArrayList<MoviesModelResult> movies) {

        int delay = 0;

        for (MoviesModelResult movie : movies) {
            cancelAlarm(context);
            AlarmManager alarmManager = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);
            Intent intent = new Intent(context, MovieAlarmReceiver.class);
            intent.putExtra("title", movie.getTitle());
            intent.putExtra("id", notifId);

            Calendar calendar = Calendar.getInstance();
            calendar.set(Calendar.HOUR_OF_DAY, 8);
            calendar.set(Calendar.MINUTE, 0);
            calendar.set(Calendar.SECOND, 0);

            alarmManager.setRepeating(AlarmManager.RTC_WAKEUP,
                    calendar.getTimeInMillis() + delay, AlarmManager.INTERVAL_DAY, getPendingIntent(context));

            notifId += 1;
            delay += 5000;
        }
    }

    public void cancelAlarm(Context context) {
        AlarmManager alarmManager = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);
        alarmManager.cancel(getPendingIntent(context));
    }

    private static PendingIntent getPendingIntent(Context context) {
        Intent alarmIntent = new Intent(context, MovieAlarmReceiver.class);

        return PendingIntent.getBroadcast(context, NOTIFICATION_ID, alarmIntent,
                PendingIntent.FLAG_UPDATE_CURRENT);
    }

}
