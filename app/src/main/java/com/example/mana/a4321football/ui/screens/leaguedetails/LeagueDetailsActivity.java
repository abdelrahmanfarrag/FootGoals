package com.example.mana.a4321football.ui.screens.leaguedetails;

import android.app.Activity;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.TextView;
import butterknife.BindView;
import butterknife.OnClick;
import com.example.mana.a4321football.R;
import com.example.mana.a4321football.data.eventbus.LeagueBus;
import com.example.mana.a4321football.data.eventbus.testBus;
import com.example.mana.a4321football.data.model.League;
import com.example.mana.a4321football.data.network.RetrofitServices;
import com.example.mana.a4321football.ui.base.BaseActivity;
import com.example.mana.a4321football.ui.screens.leaguedetails.tabs.Standings.StandingAdapter;
import com.example.mana.a4321football.utility.AppUtils;
import com.example.mana.a4321football.utility.Constants;
import com.example.mana.a4321football.utility.ToastMessages;
import com.pnikosis.materialishprogress.ProgressWheel;
import java.util.Objects;
import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

public class LeagueDetailsActivity extends BaseActivity implements PresenterResponse {

  @BindView(R.id.tabs_pages) ViewPager pager;
  @BindView(R.id.tab_layout) TabLayout tabLayout;
  @BindView(R.id.details_wheel) ProgressWheel wheel;
  @BindView(R.id.league_name) TextView leagueName;

  public static Activity getActivityInstance() {
    return new LeagueDetailsActivity();
  }

  private String id;

  @Override public int activityLayoutId() {
    return R.layout.activity_competition_layout;
  }

  @Override public void instantiateView(Bundle bundle) {
    id = Objects.requireNonNull(getIntent().getExtras()).getString(Constants.LEAGUE_ID);
    ToastMessages.ShortToastMessage(getContext(),id);
    tabsSetup();
    getPresenterData();
    StandingAdapter.setActivity(this);
  }

  @Override public void activityToolbar() {

  }

  @OnClick({ R.id.league_name })
  public void onClick(View v) {
    switch (v.getId()) {
      case R.id.league_name:
        getPresenterData();
        break;
    }
  }

  @Override protected void onStart() {
    super.onStart();
    EventBus.getDefault().register(this);
  }

  @Override protected void onStop() {
    super.onStop();
    EventBus.getDefault().unregister(this);
  }

  @Subscribe(threadMode = ThreadMode.MAIN)
  public void getName(testBus bus) {
    if (bus.getName() == null) {
      ToastMessages.ShortToastMessage(getContext(), "NULL");
    } else {
      leagueName.setText(bus.getName());
    }
  }

  private void getPresenterData() {
    if (!AppUtils.isOnline(getContext())) {
    } else {
      LeagueDetailsPresenter presenter =
          new LeagueDetailsPresenter(getContext(), disposables, this);
      presenter.loadDetails(id, wheel);
    }
  }

  private void tabsSetup() {
    TabAdapter adapter = new TabAdapter(getSupportFragmentManager());
    pager.setAdapter(adapter);
    tabLayout.setupWithViewPager(pager);
  }

  @Override public void leageDetails(League league) {
    if (league !=null) {
      leagueName.setText(league.getName());
      EventBus.getDefault().post(new testBus(league.getName()));
    }
  }
}
