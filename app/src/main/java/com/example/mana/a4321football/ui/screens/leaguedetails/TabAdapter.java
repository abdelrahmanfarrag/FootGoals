package com.example.mana.a4321football.ui.screens.leaguedetails;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import com.example.mana.a4321football.R;
import com.example.mana.a4321football.ui.screens.leaguedetails.tabs.Fixutres.Fixtures;
import com.example.mana.a4321football.ui.screens.leaguedetails.tabs.Standings.Standings;
import com.example.mana.a4321football.ui.screens.leaguedetails.tabs.Scorer.TopScorers;
import com.example.mana.a4321football.ui.screens.leaguedetails.tabs.Teams.TeamsFragment;

public class TabAdapter extends FragmentPagerAdapter {

  private Context context;

  TabAdapter(FragmentManager fm, Context context) {
    super(fm);
    this.context = context;
  }

  @Override public Fragment getItem(int position) {
    switch (position) {
      case 0:
        return new Fixtures();
      case 1:
        return new Standings();
      case 2:
        return new TeamsFragment();
      case 3:
        return new TopScorers();
      default:
        return null;
    }
  }

  @Override public int getCount() {
    return 4;
  }

  @Nullable @Override public CharSequence getPageTitle(int position) {
    switch (position) {
      case 0:
        return context.getResources().getString(R.string.details_fix);
      case 1:
        return context.getResources().getString(R.string.details_table);
      case 2:
        return context.getResources().getString(R.string.details_teams);
      case 3:
        return context.getResources().getString(R.string.details_score);
      default:
        return "null";
    }
  }
}
