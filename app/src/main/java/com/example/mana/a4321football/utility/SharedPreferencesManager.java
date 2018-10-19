package com.example.mana.a4321football.utility;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.SharedPreferences;

import static com.example.mana.a4321football.utility.Constants.SHARED_KEY;
import static com.example.mana.a4321football.utility.Constants.SHARED_PREF;
import static com.example.mana.a4321football.utility.Constants.SHARED_TEAM_ID;
import static com.example.mana.a4321football.utility.Constants.TEAM_ID_PREF;

public class SharedPreferencesManager {

  public static void setSharedPreferences(Context context, String teamId) {
    SharedPreferences.Editor preferences =
        context.getSharedPreferences(SHARED_TEAM_ID, Context.MODE_PRIVATE).edit();
    preferences.putString(TEAM_ID_PREF, teamId);
    preferences.apply();
  }

  private static String retrievePref(Context context) {
    SharedPreferences pref = context.getSharedPreferences(SHARED_PREF, Context.MODE_PRIVATE);
    return pref.getString(SHARED_KEY, "no data inserted");
  }


  static void languageSharedPref(Context context, String value) {
    @SuppressLint("CommitPrefEdits") SharedPreferences.Editor pref =
        context.getSharedPreferences(SHARED_PREF, Context.MODE_PRIVATE).edit();
    pref.putString(SHARED_KEY, value);
    pref.apply();
  }

  static String retrieveLanguagePref(Context context) {
    SharedPreferences pref = context.getSharedPreferences(SHARED_PREF, Context.MODE_PRIVATE);
    return pref.getString(SHARED_KEY, "no data inserted");
  }
}
