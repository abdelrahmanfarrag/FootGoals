package com.example.mana.a4321football.ui.screens.leaguedetails.tabs.Standings;

import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.constraint.ConstraintLayout;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import butterknife.BindView;
import butterknife.OnClick;
import com.example.mana.a4321football.R;
import com.example.mana.a4321football.data.eventbus.Details;
import com.example.mana.a4321football.data.model.Standing;
import com.example.mana.a4321football.ui.base.BaseFragment;
import com.example.mana.a4321football.utility.RecyclerConfigs;
import com.pnikosis.materialishprogress.ProgressWheel;
import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

public class Standings extends BaseFragment implements StandingsResponse {

  @BindView(R.id.standing_wheel) ProgressWheel wheel;
  @BindView(R.id.league_table) RecyclerView table;
  @BindView(R.id.technical_error_btn) Button errorBtn;
  @BindView(R.id.technical_error) ImageView errorImg;
  @BindView(R.id.toggle_container) ConstraintLayout container;
  @BindView(R.id.next_group) Button next;
  @BindView(R.id.prev_group) Button prev;
  @BindView(R.id.group_name) TextView name;
  StandingsPresenter presenter;
  String id;
  private int groupPosition = 0;
  private Standing standingObject;

  @NonNull public static Standings getInstance() {
    return new Standings();
  }

  @Override public int fragmentLayout() {
    return R.layout.fragment_standing;
  }

  @Override public void init() {

    RecyclerConfigs.setupRecyclerSettings(table, getContext(), LinearLayoutManager.VERTICAL,
        DividerItemDecoration.HORIZONTAL);
  }

  @Override public void onStop() {
    super.onStop();
    EventBus.getDefault().unregister(this);
  }

  @Override public void onStart() {
    super.onStart();
    EventBus.getDefault().register(this);
  }

  @Subscribe(sticky = true, threadMode = ThreadMode.MAIN)
  public void getBusDetails(Details details) {
    instantiatePresenter(details.getId());
  }

  @OnClick({ R.id.technical_error_btn, R.id.next_group, R.id.prev_group })
  public void onViewClicked(View v) {
    switch (v.getId()) {
      case R.id.technical_error_btn:
        instantiatePresenter(id);
        break;
      case R.id.next_group:
        if (groupPosition != 7) {
          groupPosition++;
        }
        settingToggles(standingObject);
        break;
      case R.id.prev_group:
        if (groupPosition != 0) {
          groupPosition--;
        }
        settingToggles(standingObject);
        break;
    }
  }

  private void instantiatePresenter(String id) {
    presenter = new StandingsPresenter(getContext(), disposables, this);
    View[] views = { errorImg, errorBtn };
    Handler h = new Handler();
    h.postDelayed(() -> presenter.loadStandings(wheel, views, id), 600);
  }

  @Override public void standingResponse(Standing standing) {
    standingObject = standing;
    settingToggles(standingObject);
  }

  private void settingToggles(Standing standing) {
    View[] views = { next, prev, container };
    presenter.selectGroup(standing.getStandings().size(), views,name ,groupPosition);
    table.setAdapter(new StandingAdapter(standing.getStandings().get(groupPosition).getTables()));
  }
}
