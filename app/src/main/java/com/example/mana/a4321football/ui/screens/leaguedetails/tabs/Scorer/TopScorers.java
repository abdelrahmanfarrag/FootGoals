package com.example.mana.a4321football.ui.screens.leaguedetails.tabs.Scorer;

import android.media.Image;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import butterknife.BindView;
import com.example.mana.a4321football.R;
import com.example.mana.a4321football.data.eventbus.LeagueBus;
import com.example.mana.a4321football.data.model.Scorer;
import com.example.mana.a4321football.ui.base.BaseFragment;
import com.example.mana.a4321football.utility.RecyclerConfigs;
import com.pnikosis.materialishprogress.ProgressWheel;

public class TopScorers extends BaseFragment implements ScorerResponse {

  @BindView(R.id.scorers_list) RecyclerView scorerList;
  @BindView(R.id.scorers_loader) ProgressWheel loader;
  @BindView(R.id.technical_error_btn) Button errorBtn;
  @BindView(R.id.technical_error) ImageView errorImg;

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
    setupConnectionToPreseneter();
  }

  private void setupConnectionToPreseneter() {
    View[] views = { errorBtn, errorImg };
    presenter = new TopScorerPresenter(getContext(), disposables, this);
    presenter.loadLeagueScorers(loader, views);
  }

  @Override public void scorerData(Scorer data) {
    scorerList.setAdapter(new TopScorerAdapter(data.getPlayers()));
  }
}
