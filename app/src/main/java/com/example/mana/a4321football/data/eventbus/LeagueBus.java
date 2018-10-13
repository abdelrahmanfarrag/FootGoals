package com.example.mana.a4321football.data.eventbus;


public class LeagueBus {
  private static String leagueID;
  private static String leagueNAME;
  private static int currentMatch;

  public static String getLeagueID() {
    return leagueID;
  }

  public static void setLeagueID(String leagueID) {
    LeagueBus.leagueID = leagueID;
  }

  public static String getLeagueNAME() {
    return leagueNAME;
  }

  public static void setLeagueNAME(String leagueNAME) {
    LeagueBus.leagueNAME = leagueNAME;
  }

  public static int getCurrentMatch() {
    return currentMatch;
  }

  public static void setCurrentMatch(int currentMatch) {
    LeagueBus.currentMatch = currentMatch;
  }
}

