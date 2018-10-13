package com.example.mana.a4321football.ui.screens.leaguedetails.tabs.Fixutres;

import android.os.Handler;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import butterknife.BindView;
import butterknife.OnClick;
import com.example.mana.a4321football.R;
import com.example.mana.a4321football.data.eventbus.Details;
import com.example.mana.a4321football.data.eventbus.MatchDay;
import com.example.mana.a4321football.data.model.Fixture;
import com.example.mana.a4321football.ui.base.BaseFragment;
import com.example.mana.a4321football.utility.RecyclerConfigs;
import com.pnikosis.materialishprogress.ProgressWheel;
import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

public class Fixtures extends BaseFragment implements FixtureResponse {

  @BindView(R.id.technical_error) ImageView technicalImage;
  @BindView(R.id.technical_error_btn) Button error;
  @BindView(R.id.fixture_wheel) ProgressWheel fixtureLoader;
  @BindView(R.id.fixture_list) RecyclerView list;
  @BindView(R.id.next_fixture) Button next;
  @BindView(R.id.prev_fixture) Button prev;

  FixturesPresenter presenter;
  private String id;
  private int matchDay,currentMatch;

  public static Fixtures getInstance() {
    return new Fixtures();
  }

  @Override public int fragmentLayout() {
    return R.layout.fragment_fixtures;
  }

  @Override public void init() {
    RecyclerConfigs.setupRecyclerSettings(list, getContext(), LinearLayoutManager.VERTICAL,
        DividerItemDecoration.VERTICAL);
  }

  @Override public void onStart() {
    super.onStart();
    EventBus.getDefault().register(this);
  }

  @Override public void onStop() {
    super.onStop();
    EventBus.getDefault().unregister(this);
  }

  @Subscribe(sticky = true, threadMode = ThreadMode.MAIN)
  public void getData(Details details) {
    id = details.getId();
  }
  @Subscribe(threadMode = ThreadMode.MAIN)
  public void getMatchDay(MatchDay day){
    matchDay=day.getDay();
    Handler h = new Handler();
    h.postDelayed(() -> instantiatePresenter(0), 1500);

  }

  @OnClick({ R.id.technical_error_btn, R.id.prev_fixture, R.id.next_fixture })
  public void onViewClicked(View v) {
    switch (v.getId()) {
      case R.id.technical_error_btn:
        instantiatePresenter(0);
        break;
      case R.id.next_fixture:
        instantiatePresenter(1);
        break;
      case R.id.prev_fixture:
        instantiatePresenter(-1);
        break;
    }
  }

  private void instantiatePresenter(int incrementDecrement) {
    View[] views = { error, technicalImage };
    presenter = new FixturesPresenter(getContext(), disposables, this);
     currentMatch = matchDay + incrementDecrement;
    if (currentMatch > 0) {
      presenter.loadFixtures(fixtureLoader, currentMatch
          , id,
          views);
    }
  }

  @Override public void fixtureResponse(Fixture fixture) {
    if (fixture != null) {
      View[] buttons = { prev, next };
      presenter.settingsViews(buttons,currentMatch);
      list.setAdapter(new FixturesAdapter(fixture.getMatches()));
    }
  }
}
