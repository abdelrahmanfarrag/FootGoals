package com.example.mana.a4321football.ui.screens.leaguedetails.tabs.Standings;

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
import com.example.mana.a4321football.data.model.Standing;
import com.example.mana.a4321football.ui.base.BaseFragment;
import com.example.mana.a4321football.utility.RecyclerConfigs;
import com.pnikosis.materialishprogress.ProgressWheel;

public class Standings extends BaseFragment implements StandingsResponse {

  @BindView(R.id.standing_wheel) ProgressWheel wheel;
  @BindView(R.id.league_table) RecyclerView table;
  @BindView(R.id.technical_error_btn) Button errorBtn;
  @BindView(R.id.technical_error) ImageView errorImg;
  @BindView(R.id.next_group) Button next;
  @BindView(R.id.prev_group) Button prev;

  StandingsPresenter presenter;

  public static Standings getInstance() {
    return new Standings();
  }

  @Override public int fragmentLayout() {
    return R.layout.fragment_standing;
  }

  @Override public void init() {

    instantiatePresenter();
    RecyclerConfigs.setupRecyclerSettings(table, getContext(), LinearLayoutManager.VERTICAL,
        DividerItemDecoration.HORIZONTAL);
  }

  @OnClick({ R.id.technical_error_btn })
  public void onViewClicked(View v) {
    switch (v.getId()) {
      case R.id.technical_error_btn:
        instantiatePresenter();
        break;
    }
  }

  private void instantiatePresenter() {
    presenter = new StandingsPresenter(getContext(), disposables, this);
    View[] views = { errorImg, errorBtn };
    Handler h = new Handler();
    h.postDelayed(() -> presenter.loadStandings(wheel, views), 600);
  }

  @Override public void standingResponse(Standing standing) {
    View[] views = { next, prev };
    presenter.selectGroup(standing.getStandings().size(), views);
    for (int i = 0; i < standing.getStandings().size(); i++) {
      table.setAdapter(new StandingAdapter(standing.getStandings().get(i).getTables()));
    }
  }
}
