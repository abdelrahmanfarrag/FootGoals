package com.example.mana.a4321football.ui.screens.leaguedetails.tabs.Scorer;

import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import butterknife.BindView;
import com.example.mana.a4321football.R;
import com.example.mana.a4321football.data.eventbus.Details;
import com.example.mana.a4321football.data.model.Scorer;
import com.example.mana.a4321football.ui.base.BaseFragment;
import com.example.mana.a4321football.utility.RecyclerConfigs;
import com.pnikosis.materialishprogress.ProgressWheel;
import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

public class TopScorers extends BaseFragment implements ScorerResponse {

  @BindView(R.id.scorers_list) RecyclerView scorerList;
  @BindView(R.id.scorers_loader) ProgressWheel loader;

  TopScorerPresenter presenter;

  public static TopScorers getInstance() {
    return new TopScorers();
  }

  @Override public int fragmentLayout() {
    return R.layout.fragment_scorers;
  }

  @Override public void init() {
    RecyclerConfigs.setupRecyclerSettings(scorerList, getContext(), LinearLayoutManager.VERTICAL,
        DividerItemDecoration.HORIZONTAL);
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
  public void getBusData(Details details) {
    setupConnectionToPreseneter(details.getId());
  }

  private void setupConnectionToPreseneter(String id) {
    presenter = new TopScorerPresenter(getContext(), disposables, this);
    presenter.loadLeagueScorers(id, loader);
  }

  @Override public void scorerData(Scorer data) {
    scorerList.setAdapter(new TopScorerAdapter(data.getPlayers()));
  }
}
