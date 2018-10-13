package com.example.mana.a4321football.utility.Notification;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.support.v4.app.NotificationCompat;
import android.util.Log;
import com.example.mana.a4321football.R;

import static android.app.NotificationManager.IMPORTANCE_DEFAULT;

public class NotifyReceiver extends BroadcastReceiver {
  private static final String CHANNEL_ID = "com.mana.footgoal.sendnoty";

  @Override public void onReceive(Context context, Intent intent) {
   /* NotificationManager manager =
        (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
    NotificationCompat.Builder notificationBuild = new NotificationCompat.Builder(context)
        .setSmallIcon(android.support.v4.R.drawable.notification_icon_background)
        .setContentTitle("HELLOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOO")
        .setContentText("MANA IS WRITING")
        .setAutoCancel(true);
    if (intent.getAction().equals("MY_NOTIFICATION_MESSAGE")) {
      manager.notify(1000, notificationBuild.build());
      Log.i("Notify", "Alarm");
    }*/
    Notification.Builder builder = new Notification.Builder(context);

    Notification notification = builder
        .setContentTitle("Open the app and see what is the incoming games")
        .setContentText(intent.getExtras().getString("Matches Schedule"))
        .setTicker("")
        .setSmallIcon(R.drawable.app_icon)
        .build();
    notification.sound = Uri.parse("android.resource://"
        + context.getPackageName() + "/" + R.raw.stadium_voice);

    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
      builder.setChannelId(CHANNEL_ID);
    }

    NotificationManager notificationManager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);

    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
      NotificationChannel channel = new NotificationChannel(
          CHANNEL_ID,
          "NotificationDemo",
          IMPORTANCE_DEFAULT
      );
      notificationManager.createNotificationChannel(channel);
    }

    notificationManager.notify(0, notification);
  }
}
