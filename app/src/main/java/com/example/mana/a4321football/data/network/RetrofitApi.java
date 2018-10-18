package com.example.mana.a4321football.data.network;

import com.example.mana.a4321football.data.model.Fixture;
import com.example.mana.a4321football.data.model.League;
import com.example.mana.a4321football.data.model.LeagueTeams;
import com.example.mana.a4321football.data.model.Matches;
import com.example.mana.a4321football.data.model.News;
import com.example.mana.a4321football.data.model.Scorer;
import com.example.mana.a4321football.data.model.Standing;
import com.example.mana.a4321football.data.model.Teams;
import io.reactivex.Observable;
import java.util.List;
import java.util.Map;
import retrofit2.Response;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.QueryMap;

import static com.example.mana.a4321football.utility.Constants.DETAILS;
import static com.example.mana.a4321football.utility.Constants.FIXTURES;
import static com.example.mana.a4321football.utility.Constants.LEAGUE_TEAMS;
import static com.example.mana.a4321football.utility.Constants.TEAM_INFO;
import static com.example.mana.a4321football.utility.Constants.TOP_HEADLINES;
import static com.example.mana.a4321football.utility.Constants.MATCHES;
import static com.example.mana.a4321football.utility.Constants.SCORERS;
import static com.example.mana.a4321football.utility.Constants.STANDINGS;

public interface RetrofitApi {

  @GET(MATCHES) Observable<Response<Matches>> todayMatches();

  @GET(DETAILS) Observable<Response<League>> getLeagueData(@Path("id") String id);

  @GET(STANDINGS) Observable<Response<Standing>> getLeagueStandings(@Path("id") String id,
      @QueryMap
          Map<String, String> params);

  @GET(SCORERS) Observable<Response<Scorer>> getLeagueScorers(@Path("id") String id);

  @GET(TOP_HEADLINES) Observable<Response<News>> getLeagueNews(
      @QueryMap Map<String, String> params);

  @GET(FIXTURES) Observable<Response<Fixture>> getLeagueFixtures(@Path("id") String id,
      @QueryMap Map<String, Integer> params);

  @GET(LEAGUE_TEAMS) Observable<Response<LeagueTeams>> getLeagueTeams(
      @Path("id") String id);

  @GET(TEAM_INFO) Observable<Response<Teams>> getSingleTeamInfo(@Path("id")
      int id);
}
