package com.example.mana.a4321football.ui.screens.mainscreen;

import android.os.Bundle;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.widget.FrameLayout;
import butterknife.BindView;
import com.example.mana.a4321football.R;
import com.example.mana.a4321football.ui.base.BaseActivity;
import com.example.mana.a4321football.ui.screens.mainscreen.screenContents.favorite.FavoriteFragment;
import com.example.mana.a4321football.ui.screens.mainscreen.screenContents.HomeFragment;
import com.example.mana.a4321football.ui.screens.mainscreen.screenContents.Leagues.LeaguesFragment;
import com.example.mana.a4321football.ui.screens.mainscreen.screenContents.matches.MatchesFragment;
import com.example.mana.a4321football.utility.FragmentManagement;

public class MainScreenActivity extends BaseActivity {

  @BindView(R.id.bottom_nav_main_screen) BottomNavigationView bnv;
  @BindView(R.id.screens_container) FrameLayout container;

  public static MainScreenActivity getActivity(){
    return new MainScreenActivity();
  }

  @Override public int activityLayoutId() {
    return R.layout.main_activity_screen;
  }

  @Override public void instantiateView(Bundle bundle) {
    bottomNavigationChangeItems();

  }

  @Override public void activityToolbar() {

  }

  private void bottomNavigationChangeItems() {
    bnv.setSelectedItemId(R.id.bottom_home);
    changeFragment(HomeFragment.getInstance());
    bnv.setOnNavigationItemSelectedListener(
        item -> {
          switch (item.getItemId()) {
            case R.id.bottom_home:
              changeFragment(HomeFragment.getInstance());
              return true;
            case R.id.bottom_match:
              changeFragment(MatchesFragment.getInstance());
              return true;
            case R.id.bottom_favorite:
              changeFragment(FavoriteFragment.getInstance());
              return true;
            case R.id.bottom_leagues:
              changeFragment(LeaguesFragment.getInstance());
              return true;
            default:
              return false;
          }
        });
  }

  private void changeFragment(Fragment fragment) {
    FragmentManagement.showFragmentOnActivity(getContext(), fragment, R.id.screens_container, true);
  }
}
