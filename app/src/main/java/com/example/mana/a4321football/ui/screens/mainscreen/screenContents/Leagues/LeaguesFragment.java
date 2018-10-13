package com.example.mana.a4321football.ui.screens.mainscreen.screenContents.Leagues;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import butterknife.BindDrawable;
import butterknife.BindString;
import butterknife.BindView;
import com.example.mana.a4321football.R;
import com.example.mana.a4321football.ui.base.BaseFragment;
import com.example.mana.a4321football.ui.screens.leaguedetails.LeagueDetailsActivity;
import com.example.mana.a4321football.utility.AppUtils;
import com.example.mana.a4321football.utility.Constants;
import com.example.mana.a4321football.utility.Intents;
import com.example.mana.a4321football.utility.ToastMessages;
import java.text.ParseException;
import java.util.Arrays;
import java.util.List;

public class LeaguesFragment extends BaseFragment implements LeagueId {

  @BindView(R.id.league_list) RecyclerView leaguesList;

  @BindString(R.string.champions_league) String championsLeague;
  @BindString(R.string.euro_champion) String euroChampionship;
  @BindString(R.string.premier_league) String premierLeague;
  @BindString(R.string.championship) String eflChampionship;
  @BindString(R.string.la_liga) String laLeague;
  @BindString(R.string.ligue_1) String league1;
  @BindString(R.string.serie_a) String italySeriaA;
  @BindString(R.string.portogues) String potogues;
  @BindString(R.string.bundes_league) String bundesLeague;
  @BindString(R.string.dutch_league) String dutch;
  @BindString(R.string.brazil_league) String brazilLeague;
  @BindString(R.string.language_change) String language;

  @BindDrawable(R.drawable.euro) Drawable euro;
  @BindDrawable(R.drawable.england) Drawable england;
  @BindDrawable(R.drawable.spain) Drawable spain;
  @BindDrawable(R.drawable.france) Drawable france;
  @BindDrawable(R.drawable.italy) Drawable italy;
  @BindDrawable(R.drawable.portugal) Drawable portugal;
  @BindDrawable(R.drawable.germany) Drawable germany;
  @BindDrawable(R.drawable.netherlands) Drawable holland;
  @BindDrawable(R.drawable.brazil) Drawable brazil;
  @BindDrawable(R.drawable.world) Drawable world;

  public static LeaguesFragment getInstance() {
    return new LeaguesFragment();
  }

  @Override public int fragmentLayout() {
    return R.layout.leagues_fragment;
  }

  @SuppressLint("NewApi") @Override public void init() {
    recyclerSetup();
  }

  private void recyclerSetup() {
    leaguesList.setLayoutManager(new LinearLayoutManager(getContext()));
    leaguesList.setHasFixedSize(true);
    leaguesList.setAdapter(
        new LeaguesAdapter(Arrays.asList(leagueName()), Arrays.asList(leagueIcons()), this));
    leaguesList.addItemDecoration(
        new DividerItemDecoration(getContext(), DividerItemDecoration.VERTICAL));
  }

  private String[] leagueName() {
    return new String[] {
        championsLeague,/* euroChampionship,*/ null, premierLeague, eflChampionship, null, laLeague,
        league1, italySeriaA, null, potogues, bundesLeague, dutch, brazilLeague, null, language
    };
  }

  private Drawable[] leagueIcons() {
    return new Drawable[] {
        euro, /*euro, */null, england, england, null, spain, france, italy, null, portugal, germany,
        holland, brazil, null, world
    };
  }

  @Override public void leagueId(String id) {
    Intent in = new Intent(getContext(), LeagueDetailsActivity.class);
    in.putExtra(Constants.LEAGUE_ID, id);
    Intents.dataIntent(getContext(), in);
  }
}
