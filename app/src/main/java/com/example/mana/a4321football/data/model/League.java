package com.example.mana.a4321football.data.model;

import com.google.gson.annotations.SerializedName;

public class League {

  @SerializedName("name") private String name;
  @SerializedName("code") private String leagueCode;
  @SerializedName("currentSeason") private CurrentSeason season;

  public CurrentSeason getSeason() {
    return season;
  }

  public String getName() {
    return name;
  }

  public String getLeagueCode() {
    return leagueCode;
  }

  public class CurrentSeason {
    @SerializedName("currentMatchday") private int currentMatch;

    public int getCurrentMatch() {
      return currentMatch;
    }
  }
}
