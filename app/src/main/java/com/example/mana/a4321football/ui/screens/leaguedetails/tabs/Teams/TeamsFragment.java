package com.example.mana.a4321football.ui.screens.leaguedetails.tabs.Teams;

import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import butterknife.BindView;
import com.example.mana.a4321football.R;
import com.example.mana.a4321football.data.eventbus.Details;
import com.example.mana.a4321football.data.model.LeagueTeams;
import com.example.mana.a4321football.data.model.Teams;
import com.example.mana.a4321football.ui.base.BaseFragment;
import com.example.mana.a4321football.utility.RecyclerConfigs;
import com.example.mana.a4321football.utility.ToastMessages;
import com.pnikosis.materialishprogress.ProgressWheel;
import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

public class TeamsFragment extends BaseFragment implements TeamsResponse, TeamDetails {

  @BindView(R.id.teams_wheel) ProgressWheel wheel;
  @BindView(R.id.teams_list) RecyclerView list;

  private String id;

  private TeamsPresenter presenter;
  private Teams team;

  public static TeamsFragment getInstance() {
    return new TeamsFragment();
  }

  @Override public int fragmentLayout() {
    return R.layout.fragment_teams;
  }

  @Override public void init() {
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
  public void subscribeToBus(Details details) {
    instantiatePreseneter(details.getId());
    RecyclerConfigs.setupRecyclerSettings(list, getContext(), LinearLayoutManager.VERTICAL,
        DividerItemDecoration.HORIZONTAL);
  }

  private void instantiatePreseneter(String id) {
    presenter = new TeamsPresenter(getContext(), disposables, this);
    presenter.loadLeagueTeams(id, wheel);
  }

  @Override public void teamResponse(LeagueTeams teams) {
    list.setAdapter(new TeamsAdapter(teams.getTeams(), this));
  }

  @Override public void teamInfo(Teams teams) {
    team = teams;
  }

  @Override public void teamsInfo(int id) {
    presenter.buildTeamInfoDialog(id, team);
  }
}
