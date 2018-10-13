package com.example.mana.a4321football.data.model;

import com.google.gson.annotations.SerializedName;
import java.util.List;

public class Scorer {
  @SerializedName("scorers") private List<Scorers> players;

  public List<Scorers> getPlayers() {
    return players;
  }

  public class Scorers {
    @SerializedName("numberOfGoals") private int goals;
    @SerializedName("player") private Player info;
    @SerializedName("team") private Team teamInfo;

    public int getGoals() {
      return goals;
    }

    public Player getInfo() {
      return info;
    }

    public Team getTeamInfo() {
      return teamInfo;
    }

    public class Player {
      @SerializedName("name") private String name;
      @SerializedName("id") private long id;
      @SerializedName("position") private String position;
      @SerializedName("shirtNumber") private int number;

      public String getName() {
        return name;
      }

      public long getId() {
        return id;
      }

      public String getPosition() {
        return position;
      }

      public int getNumber() {
        return number;
      }
    }

    public class Team {
      @SerializedName("id") private long id;
      @SerializedName("name") private String name;

      public long getId() {
        return id;
      }

      public String getName() {
        return name;
      }
    }
  }
}
