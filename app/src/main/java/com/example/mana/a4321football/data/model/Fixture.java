package com.example.mana.a4321football.data.model;

import com.example.mana.a4321football.ui.screens.mainscreen.screenContents.HomeFragment;
import com.google.gson.annotations.SerializedName;
import java.util.List;

public class Fixture {
  @SerializedName("matches") private List<Matches> matches;

  public List<Matches> getMatches() {
    return matches;
  }

  public class Matches {

    @SerializedName("id") private long id;
    @SerializedName("status") private String status;
    @SerializedName("matchday") private int matchDay;
    @SerializedName("utcDate") private String matchTime;
    @SerializedName("homeTeam") private HomeTeam home;
    @SerializedName("awayTeam") private AwayTeam away;
    @SerializedName("score") private Score score;

    public String getStatus() {
      return status;
    }

    public int getMatchDay() {
      return matchDay;
    }

    public String getMatchTime() {
      return matchTime;
    }

    public HomeTeam getHome() {
      return home;
    }

    public AwayTeam getAway() {
      return away;
    }

    public Score getScore() {
      return score;
    }

    public class HomeTeam {
      @SerializedName("id") private long id;
      @SerializedName("name") private String name;

      public long getId() {
        return id;
      }

      public String getName() {
        return name;
      }
    }

    public class AwayTeam {
      @SerializedName("id") private long id;
      @SerializedName("name") private String name;

      public long getId() {
        return id;
      }

      public String getName() {
        return name;
      }
    }

    public class Score {

      @SerializedName("fullTime") private FullTime score;

      public FullTime getScore() {
        return score;
      }

      public class FullTime {
        @SerializedName("homeTeam") private int homeScore;
        @SerializedName("awayTeam") private int awayScore;

        public int getHomeScore() {
          return homeScore;
        }

        public int getAwayScore() {
          return awayScore;
        }
      }
    }

    public long getId() {
      return id;
    }
  }
}
