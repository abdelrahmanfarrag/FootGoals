package com.example.mana.a4321football.ui.screens.mainscreen.screenContents.favorite.teamdetails;

import android.support.constraint.ConstraintLayout;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.view.View;
import butterknife.BindView;
import com.example.mana.a4321football.R;
import com.example.mana.a4321football.data.eventbus.Tabs;
import com.example.mana.a4321football.data.eventbus.TeamDetail;
import com.example.mana.a4321football.ui.base.BaseFragment;
import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

public class TeamDetailFragment extends BaseFragment {

  @BindView(R.id.tabs_pages) ViewPager pager;
  @BindView(R.id.tab_layout) TabLayout tabLayout;
  @BindView(R.id.detail_cont) ConstraintLayout layout;

  TeamDetail detail;

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
      this.detail = detail;
      tabsSetup();
      EventBus.getDefault().postSticky(new Tabs(detail));
      //
      layout.setVisibility(View.VISIBLE);
    } else {
      layout.setVisibility(View.INVISIBLE);
    }
  }




  @Override public void init() {
  }

  private void tabsSetup() {
    FavoriteTabAdapter adapter = new FavoriteTabAdapter(getFragmentManager(), getContext());
    pager.setAdapter(adapter);
    tabLayout.setupWithViewPager(pager);
  }
}
