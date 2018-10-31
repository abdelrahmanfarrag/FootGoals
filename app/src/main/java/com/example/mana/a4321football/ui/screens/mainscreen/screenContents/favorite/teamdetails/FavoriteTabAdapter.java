package com.example.mana.a4321football.ui.screens.mainscreen.screenContents.favorite.teamdetails;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import com.example.mana.a4321football.R;
import com.example.mana.a4321football.ui.screens.mainscreen.screenContents.favorite.teamdetails.tabs.Next;
import com.example.mana.a4321football.ui.screens.mainscreen.screenContents.favorite.teamdetails.tabs.info.Info;
import com.example.mana.a4321football.ui.screens.mainscreen.screenContents.favorite.teamdetails.tabs.previous.Previous;

public class FavoriteTabAdapter extends FragmentPagerAdapter {
  private Context context;

  public FavoriteTabAdapter(FragmentManager fm, Context context) {
    super(fm);
    this.context = context;
  }

  @Override public Fragment getItem(int position) {
    switch (position) {
      case 0:
        return new Info();
      case 1:
        return new Next();
      case 2:
        return new Previous();
      default:
        return null;
    }
  }

  @Nullable @Override public CharSequence getPageTitle(int position) {
    switch (position) {
      case 0:
        return context.getResources().getString(R.string.app_name);
      case 1:
        return "NEXT GAMES";
      case 2:
        return "PREVIOUS GAMES";
      default:
        return "";
    }
  }

  @Override public int getCount() {
    return 3;
  }
}
