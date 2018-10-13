package com.example.mana.a4321football.utility;

import android.annotation.SuppressLint;
import android.app.AlarmManager;
import android.app.Notification;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.BitmapDrawable;
import android.media.RingtoneManager;
import android.os.Handler;
import android.os.SystemClock;
import android.support.v4.app.NotificationCompat;
import com.example.mana.a4321football.R;
import com.example.mana.a4321football.ui.screens.mainscreen.MainScreenActivity;
import java.util.Calendar;

public class Intents {

  public static void normalIntent(Context context, Class<?> destination) {
    Intent in = new Intent(context, destination);
    context.startActivity(in);
  }

  public static void dataIntent(Context context, Intent in) {
    context.startActivity(in);
  }

  public static void delayedIntent(Context context, Class<?> destination, long millis) {
    Handler handler = new Handler();
    handler.postDelayed(() -> normalIntent(context, destination), millis);
  }


}
