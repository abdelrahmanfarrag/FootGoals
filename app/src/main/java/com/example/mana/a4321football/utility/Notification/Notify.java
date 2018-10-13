package com.example.mana.a4321football.utility.Notification;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import java.sql.Date;
import java.util.Calendar;

public class Notify {
  public static void setDailyNotification(Context context) {
    Calendar calendar = Calendar.getInstance();
    calendar.set(Calendar.HOUR_OF_DAY, 12);
    calendar.set(Calendar.MINUTE, 30);
    if (calendar.getTime().compareTo(new Date(0)) < 0)
      calendar.add(Calendar.DAY_OF_MONTH, 1);

    Intent intent = new Intent(context, NotifyReceiver.class);
    intent.setAction("MY_NOTIFICATION_MESSAGE");
    PendingIntent pendingIntent =
        PendingIntent.getBroadcast(context, 1000, intent, PendingIntent.FLAG_UPDATE_CURRENT);
    AlarmManager manager = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);
    assert manager != null;
    manager.setRepeating(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(),
        AlarmManager.INTERVAL_DAY, pendingIntent);
  }
}
