package com.example.fathurradhy.mocinemas.utils.reminder;

import android.app.AlarmManager;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.media.RingtoneManager;

import com.example.fathurradhy.mocinemas.R;
import com.example.fathurradhy.mocinemas.activity.MainActivity;

import java.util.Calendar;

import androidx.core.app.NotificationCompat;

import static com.example.fathurradhy.mocinemas.utils.SupportVariable.ALARM_REQUEST_CODE;

public class AlarmReceiver extends BroadcastReceiver {

    private static final int NOTIFICATION_ID = 101;

    public static final String NOTIFICATION_CHANNEL_ID = "10001";

    @Override
    public void onReceive(Context context, Intent intent) {
        String appName = context.getString(R.string.app_name);
        String message = context.getString(R.string.reminder_mesage);
        showAlarmNotification(context, appName, message, NOTIFICATION_ID);
    }

    private void showAlarmNotification(Context context, String title, String message, int notifId) {
        NotificationManager notificationManager = (NotificationManager) context.getSystemService(
                Context.NOTIFICATION_SERVICE);

        Intent intent = new Intent(context, MainActivity.class);
        PendingIntent pendingIntent = PendingIntent.getActivity(context, notifId, intent, PendingIntent.FLAG_UPDATE_CURRENT);

        NotificationCompat.Builder builder = new NotificationCompat.Builder(context)
                .setSmallIcon(R.drawable.ic_reminder)
                .setContentTitle(title)
                .setContentText(message)
                .setContentIntent(pendingIntent)
                .setPriority(NotificationCompat.PRIORITY_HIGH)
                .setAutoCancel(true)
                .setSound(RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION));

        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
            int importance = NotificationManager.IMPORTANCE_HIGH;
            NotificationChannel notificationChannel = new NotificationChannel(NOTIFICATION_CHANNEL_ID, "NOTIFICATION_CHANNEL_NAME", importance);

            builder.setChannelId(NOTIFICATION_CHANNEL_ID);
            notificationManager.createNotificationChannel(notificationChannel);
        }

        notificationManager.notify(notifId, builder.build());
    }

    public void setRepeatingAlarm(Context context) {
        cancelAlarm(context);
        AlarmManager alarmManager = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);

        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.HOUR_OF_DAY, 7);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);

        if (alarmManager != null) {
            alarmManager.setInexactRepeating(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(), AlarmManager.INTERVAL_DAY, getPendingIntent(context));
        }
    }

    public void cancelAlarm(Context context) {
        AlarmManager alarmManager = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);
        alarmManager.cancel(getPendingIntent(context));
    }

    private static PendingIntent getPendingIntent(Context context) {
        Intent alarmIntent = new Intent(context, AlarmReceiver.class);
        return PendingIntent.getBroadcast(context, ALARM_REQUEST_CODE, alarmIntent,
                PendingIntent.FLAG_CANCEL_CURRENT);
    }

}
