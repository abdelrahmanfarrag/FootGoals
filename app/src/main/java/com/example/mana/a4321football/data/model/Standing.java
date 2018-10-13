package com.example.mana.a4321football.data.model;

import com.google.gson.annotations.SerializedName;
import java.util.List;

public class Standing {
  @SerializedName("competition") private Competit competitions;
  @SerializedName("standings") private List<Stand> standings;

  public Competit getCompetitions() {
    return competitions;
  }

  public List<Stand> getStandings() {
    return standings;
  }

  public class Competit {
    @SerializedName("name") private String name;

    public String getName() {
      return name;
    }
  }

  public class Stand {
    @SerializedName("table") private List<Table> tables;

    public List<Table> getTables() {
      return tables;
    }

    public class Table {
      @SerializedName("position") private int position;
      @SerializedName("team") private Team team;
      @SerializedName("playedGames") private int games;
      @SerializedName("won") private int won;
      @SerializedName("draw") private int draw;
      @SerializedName("lost") private int lost;
      @SerializedName("points") private int points;
      @SerializedName("goalsFor") private int goalScored;
      @SerializedName("goalsAgainst") private int goalsConceded;
      @SerializedName("goalDifference") private int goalDiff;

      public int getPosition() {
        return position;
      }

      public Team getTeam() {
        return team;
      }

      public int getGames() {
        return games;
      }

      public int getWon() {
        return won;
      }

      public int getDraw() {
        return draw;
      }

      public int getLost() {
        return lost;
      }

      public int getPoints() {
        return points;
      }

      public int getGoalScored() {
        return goalScored;
      }

      public int getGoalsConceded() {
        return goalsConceded;
      }

      public int getGoalDiff() {
        return goalDiff;
      }

      public class Team {
        @SerializedName("name") private String teamName;
        @SerializedName("crestUrl") private String teamLogo;
        @SerializedName("id") private int id;

        public String getTeamName() {
          return teamName;
        }

        public String getTeamLogo() {
          return teamLogo;
        }

        public int getId() {
          return id;
        }
      }
    }
  }
}
