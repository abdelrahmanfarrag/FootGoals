package com.example.mana.a4321football.data.model;

import com.google.gson.annotations.SerializedName;
import java.util.List;

public class LeagueTeams {

  @SerializedName("teams") private List<Teams> teams;

  public List<Teams> getTeams() {
    return teams;
  }

  public class Teams{
    @SerializedName("id") private int id;
    @SerializedName("name") private String name;
    @SerializedName("crestUrl") private String url;
    @SerializedName("venue") private String stadium;
    @SerializedName("shortName") private String shortName;

    public int getId() {
      return id;
    }

    public String getName() {
      return name;
    }

    public String getUrl() {
      return url;
    }

    public String getStadium() {
      return stadium;
    }

    public String getShortName() {
      return shortName;
    }
  }
}
