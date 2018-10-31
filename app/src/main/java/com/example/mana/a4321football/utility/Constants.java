package com.example.mana.a4321football.utility;

public class Constants {

  //Instantiate Retrofit Header and baseUrl (Football-Api)
  public static final String BASE_URL = "http://api.football-data.org/v2/";
  public static final String AUTH_HEADER = "X-Auth-Token";
  public static final String API_KEEY = "0002bf548fd946109f0b9379c420ab1d";

  //NewsTab Api
  public static final String NEWS_API_BASE_URL = "https://newsapi.org/v2/";
  public static final String NEWS_API_KEEY = "8790a66266f6458eb99f6e01101e8442";

  //Retrofit EndPointes
  public static final String MATCHES = "matches";
  public static final String DETAILS = "competitions/{id}";
  public static final String STANDINGS = "competitions/{id}/standings";
  public static final String SCORERS = "competitions/{id}/scorers";
  public static final String TOP_HEADLINES = "top-headlines";
  public static final String FIXTURES = "competitions/{id}/matches";
  public static final String LEAGUE_TEAMS = "competitions/{id}/teams";
  public static final String TEAM_INFO = "teams/{id}";
  public static final String PREVIOUS_GAMES = "teams/{id}/matches";

  //Hashmap Keys
  public static final String MATCH_STATUS = "status";
  public static final String MATCHES_LIMIT = "limit";
  public static final String MATCHES_PLAYED = "FINISHED";

  //News API KEYS
  public static final String NEWS_KEY = "apiKey";
  public static final String LANGUAGE = "language";

  //SHARED PREF KEY && LANGUAGE KEYS
  public static final String SHARED_PREF = "pref";
  public static final String SHARED_KEY = "key";
  public static final String SHARED_TEAM_ID = "shMap";
  public static final String TEAM_ID_PREF = "_id_pref";
  public static final String ARABIC_LANG = "ar";
  public static final String ENGLISH_LANG = "en";

  //QUERY MAP KEY-VALUES
  public static final String STANDING_TYPE = "standingType";
  public static final String STANDING_TYPE_KEY = "TOTAL";
  public static final String MATCH_DAY = "matchday";

  //Player postions
  public static final String ATTACKER = "Attacker";
  public static final String MIDFIELDER = "Midfielder";

  //LEAGUE CODE
  public static final String PREMIER_LEAGUE = "PL";
  public static final String SKYPET_CHAMPIONSHIP = "ELC";
  public static final String SERIA_A = "SA";
  public static final String LA_LEAGUE = "PD";
  public static final String PORTUGAL_LEAGUE = "PPL";
  public static final String HOLLAND_LEAGUE = "DED";
  public static final String BUNDES_LEAGUE = "BL1";
  public static final String LIGUE_1 = "FL1";
  public static final String CHAMPIONS_LEAGUE = "CL";
  public static final String BRAZIL_LEAGUE = "BSA";
  public static final String NEWS_LIST = "_news_";
  public static final String EUROPEAN_CHAMPIONSHIP = "EC";

  //INTENT KEYS
  public static final String LEAGUE_ID = "_id";

  //Caching
  public static final long CACHE_SIZE = (5 * 1024 * 1024);
}

