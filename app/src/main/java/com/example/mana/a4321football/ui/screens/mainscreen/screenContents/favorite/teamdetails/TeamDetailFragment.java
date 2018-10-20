package com.example.mana.a4321football.ui.screens.mainscreen.screenContents.favorite.teamdetails;

import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.widget.ImageView;
import android.widget.TextView;
import butterknife.BindView;
import com.example.mana.a4321football.R;
import com.example.mana.a4321football.data.eventbus.TeamDetail;
import com.example.mana.a4321football.ui.base.BaseFragment;
import com.example.mana.a4321football.ui.screens.leaguedetails.TabAdapter;
import com.example.mana.a4321football.ui.screens.mainscreen.screenContents.favorite.FavoriteAdapter;
import com.example.mana.a4321football.utility.ImageSettings;
import com.example.mana.a4321football.utility.ToastMessages;
import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

public class TeamDetailFragment extends BaseFragment {

  @BindView(R.id.tabs_pages) ViewPager pager;
  @BindView(R.id.tab_layout) TabLayout tabLayout;

  public static TeamDetailFragment getInstance() {
    return new TeamDetailFragment();
  }

  @Override public int fragmentLayout() {
    return R.layout.fragment_fav_team_details;
  }

  @Override public void onStart() {
    super.onStart();
    EventBus.getDefault().register(this);
  }

  @Override public void onStop() {
    super.onStop();
    EventBus.getDefault().unregister(this);
  }

  @Subscribe(threadMode = ThreadMode.MAIN)
  public void subscribeToTeamDetailBus(TeamDetail detail) {
    if (detail != null) {
      //hAN3ML 7AGA 5WAGATI ISA 1111
    }
  }

  @Override public void init() {
    tabsSetup();
  }

  private void tabsSetup() {
    FavoriteTabAdapter adapter = new FavoriteTabAdapter(getFragmentManager());
    pager.setAdapter(adapter);
    tabLayout.setupWithViewPager(pager);
  }
}
