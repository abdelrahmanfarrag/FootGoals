package com.example.mana.a4321football.utility;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.icu.text.DateFormat;
import android.icu.text.SimpleDateFormat;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.util.Log;
import com.example.mana.a4321football.R;
import com.example.mana.a4321football.ui.screens.mainscreen.MainScreenActivity;
import java.io.File;
import java.io.IOException;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.text.ParseException;
import java.util.Locale;
import okhttp3.Cache;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Response;

import static com.example.mana.a4321football.utility.Constants.CACHE_SIZE;
import static com.example.mana.a4321football.utility.Constants.SHARED_KEY;
import static com.example.mana.a4321football.utility.Constants.SHARED_PREF;

public class AppUtils {
  @SuppressWarnings("ConstantConditions") public static boolean isOnline(Context context) {
    ConnectivityManager cm =
        (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
    NetworkInfo netInfo = cm.getActiveNetworkInfo();
    return netInfo != null && netInfo.isConnectedOrConnecting();
  }

  public static void changeLanguage(Context context, Class<?> destination) {
    if (SharedPreferencesManager.retrieveLanguagePref(context).equals(Constants.ENGLISH_LANG)) {
      SharedPreferencesManager.languageSharedPref(context, Constants.ARABIC_LANG);
      AppUtils.languageToLoad(Constants.ARABIC_LANG, context, MainScreenActivity.class);
    } else {
      SharedPreferencesManager.languageSharedPref(context, Constants.ENGLISH_LANG);
      AppUtils.languageToLoad(Constants.ENGLISH_LANG, context, destination);
    }
  }

  private static void languageToLoad(String lang, Context context, Class<?> destination) {
    Locale locale = new Locale(lang);
    Locale.setDefault(locale);
    Configuration config = new Configuration();
    config.locale = locale;
    context.getResources()
        .updateConfiguration(config, context.getResources().getDisplayMetrics());
    Intents.normalIntent(context, destination);
  }

  @RequiresApi(api = Build.VERSION_CODES.N) public static String transformTime(String input) {
    DateFormat outputFormat = new SimpleDateFormat("hh:mm a", Locale.US);
    DateFormat inputFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssZ", Locale.US);

    Date date = null;
    try {
      date = inputFormat.parse(input);
    } catch (ParseException e) {
      e.printStackTrace();
    }
    return outputFormat.format(date);
  }

  @TargetApi(Build.VERSION_CODES.O) @RequiresApi(api = Build.VERSION_CODES.N)
  public static String getCurrentTim() {
    LocalTime localTime = LocalTime.now();
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm");
    String formattedTime = localTime.format(formatter);
    return formattedTime;
  }

  @RequiresApi(api = Build.VERSION_CODES.N) public static String transformDate(String input) {
    DateFormat outputFormat = new SimpleDateFormat("yyyy/MM/dd", Locale.US);
    DateFormat inputFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssZ", Locale.US);

    Date date = null;
    try {
      date = inputFormat.parse(input);
    } catch (ParseException e) {
      e.printStackTrace();
    }
    return outputFormat.format(date);
  }

  public static int getChampionshipFlag(String championshipName) {
    switch (championshipName) {
      case "Série A":
        return R.drawable.brazil;
      case "Championship":
        return R.drawable.england;
      case "Premier League":
        return R.drawable.england;
      case "UEFA Champions League":
        return R.drawable.euro;
      case "European Championship":
        return R.drawable.euro;
      case "Ligue 1":
        return R.drawable.france;
      case "Bundesliga":
        return R.drawable.germany;
      case "Serie A":
        return R.drawable.italy;
      case "Eredivisie":
        return R.drawable.netherlands;
      case "Primeira Liga":
        return R.drawable.portugal;
      case "Primera Division":
        return R.drawable.spain;
      default:
        return R.drawable.euro;
    }
  }

  public static String matchType(Context context, String type) {
    switch (type) {
      case "REGULAR_SEASON":
        return context.getString(R.string.league_week);
      case "GROUP_STAGE":
        return context.getString(R.string.group_stage);
      default:
        return "0";
    }
  }

  @SuppressLint("NewApi")
  public static int dateDiff(String date) {
    SimpleDateFormat simpleDateFormat = new SimpleDateFormat("hh:mm a");
    try {
      Date startDate = simpleDateFormat.parse(date);
      Date endDate = simpleDateFormat.parse(getCurrentTimeWithTimeZone());
      long difference = startDate.getTime() - endDate.getTime();
      if (difference < 0) {
        Date dateMax = simpleDateFormat.parse(date);
        Date dateMin = simpleDateFormat.parse(getCurrentTimeWithTimeZone());
        difference =
            (dateMax.getTime() - startDate.getTime()) + (endDate.getTime() - dateMin.getTime());
      }
      int days = (int) (difference / (1000 * 60 * 60 * 24));
      int hours = (int) ((difference - (1000 * 60 * 60 * 24 * days)) / (1000 * 60 * 60));
      int min =
          (int) (difference - (1000 * 60 * 60 * 24 * days) - (1000 * 60 * 60 * hours)) / (1000
              * 60);
      Log.i("log_tag", "Hours: " + hours + ", Mins: " + min);
      if (hours <= 1) {
        return min;
      } else {
        return hours;
      }
    } catch (ParseException e) {
      e.printStackTrace();
      return 0;
    }
  }

  @RequiresApi(api = Build.VERSION_CODES.O) public static String getCurrentTimeWithTimeZone() {
    Date date = new Date(System.currentTimeMillis());
    SimpleDateFormat dateFormat = new SimpleDateFormat("hh:mm a",
        Locale.ENGLISH);
    String var = dateFormat.format(date);
    return var;
  }


}
/*
  public static void clearPref(Context context){
    @SuppressLint("CommitPrefEdits") SharedPreferences.Editor pref =
        context.getSharedPreferences(SHARED_PREF, Context.MODE_PRIVATE).edit();
    pref.clear().apply();
  }*/



