package com.app.mana.a4321football.ui.screens.mainscreen.screenContents.favorite.teamdetails;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import com.app.mana.a4321football.R;
import com.app.mana.a4321football.ui.screens.leaguedetails.tabs.Teams.team_info.TeamInfoFragment;
import com.app.mana.a4321football.ui.screens.mainscreen.screenContents.favorite.teamdetails.tabs.next.Next;
import com.app.mana.a4321football.ui.screens.mainscreen.screenContents.favorite.teamdetails.tabs.info.Info;
import com.app.mana.a4321football.ui.screens.mainscreen.screenContents.favorite.teamdetails.tabs.previous.Previous;

public class FavoriteTabAdapter extends FragmentPagerAdapter {
  private Context context;

  FavoriteTabAdapter(FragmentManager fm, Context context) {
    super(fm);
    this.context = context;
  }

  @Override public Fragment getItem(int position) {
    switch (position) {
      case 0:
        return new Info();

      case 1:
        return new TeamInfoFragment();
      case 2:
        return new Next();
      case 3:
        return new Previous();
      default:
        return null;
    }
  }

  @Nullable @Override public CharSequence getPageTitle(int position) {
    switch (position) {
      case 0:
        return context.getResources().getString(R.string.info);
      case 1:
        return context.getResources().getString(R.string.squad_tab);
      case 2:
        return context.getResources().getString(R.string.next_game);
      case 3:
        return context.getResources().getString(R.string.prev_game);
      default:
        return "";
    }
  }

  @Override public int getCount() {
    return 4;
  }
}
