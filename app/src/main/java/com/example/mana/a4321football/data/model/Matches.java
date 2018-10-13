package com.example.mana.a4321football.data.model;

import com.google.gson.annotations.SerializedName;
import java.util.List;

public class Matches {
  @SerializedName("count") private int count;
  @SerializedName("matches") private List<MatchDetails> details;

  public List<MatchDetails> getDetails() {
    return details;
  }

  public int getCount() {
    return count;
  }

  public class MatchDetails {
    @SerializedName("id") private long matchId;
    @SerializedName("utcDate") private String time;
    @SerializedName("status") private String status;
    @SerializedName("group") private String group;
    @SerializedName("stage") private String stage;
    @SerializedName("matchday") private String matchDay;
    @SerializedName("homeTeam") private HomeTeam homeTeam;
    @SerializedName("awayTeam") private AwayTeam awayTeam;
    @SerializedName("score") private Score score;
    @SerializedName("competition") private Competition competition;

    public long getMatchId() {
      return matchId;
    }

    public String getTime() {
      return time;
    }

    public Competition getCompetition() {
      return competition;
    }

    public String getStatus() {
      return status;
    }

    public String getStage() {
      return stage;
    }

    public String getGroup() {
      return group;
    }

    public String getMatchDay() {
      return matchDay;
    }

    public HomeTeam getHomeTeam() {
      return homeTeam;
    }

    public AwayTeam getAwayTeam() {
      return awayTeam;
    }

    public Score getScore() {
      return score;
    }

    public class Competition {
      @SerializedName("name") private String competitonName;
      @SerializedName("id") private long competitionID;

      public String getCompetitonName() {
        return competitonName;
      }

      public long getCompetitionID() {
        return competitionID;
      }
    }

    public class HomeTeam {
      @SerializedName("name") private String name;
      @SerializedName("id") private long id;

      public String getName() {
        return name;
      }

      public long getId() {
        return id;
      }
    }

    public class AwayTeam {
      @SerializedName("name") private String name;
      @SerializedName("id") private long id;

      public String getName() {
        return name;
      }

      public long getId() {
        return id;
      }
    }

    public class Score {
      @SerializedName("fullTime") private FinalScore finalScore;
      @SerializedName("winner") private String winnerTeam;

      public String getWinnerTeam() {
        return winnerTeam;
      }

      public FinalScore getFinalScore() {
        return finalScore;
      }

      public class FinalScore {
        @SerializedName("homeTeam") private int homeScore;
        @SerializedName("awayTeam") private int awayTeam;

        public int getHomeScore() {
          return homeScore;
        }

        public int getAwayTeam() {
          return awayTeam;
        }
      }
    }
  }
}
