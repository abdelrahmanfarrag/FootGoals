package com.example.mana.a4321football.ui.screens.leaguedetails;

import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import com.example.mana.a4321football.ui.screens.leaguedetails.tabs.Fixutres.Fixtures;
import com.example.mana.a4321football.ui.screens.leaguedetails.tabs.Standings.Standings;
import com.example.mana.a4321football.ui.screens.leaguedetails.tabs.Scorer.TopScorers;
import com.example.mana.a4321football.ui.screens.leaguedetails.tabs.Teams.TeamsFragment;

public class TabAdapter extends FragmentPagerAdapter {

  TabAdapter(FragmentManager fm) {
    super(fm);
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
        return "FIXTURES";
      case 1:
        return "STANDINGS";
      case 2:
        return "TEAMS";
      case 3:
        return "TOP SCORERS";
      default:
        return "null";
    }
  }
}
