package com.example.mana.a4321football.ui.screens.leaguedetails.tabs.Teams.team_info;

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
import com.example.mana.a4321football.data.eventbus.TeamDetail;
import com.example.mana.a4321football.data.model.Teams;
import com.example.mana.a4321football.ui.base.BaseFragment;
import com.example.mana.a4321football.utility.ImageSettings;
import com.example.mana.a4321football.utility.RecyclerConfigs;
import com.pnikosis.materialishprogress.ProgressWheel;
import java.util.List;
import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

public class TeamInfoFragment extends BaseFragment implements InfoResponse {

  @BindView(R.id.team_info_whee) ProgressWheel wheel;
  @BindView(R.id.team_info_squad) RecyclerView squad;
  @BindView(R.id.team_info_img) ImageView teamImg;
  @BindView(R.id.team_info_name) TextView name;
  @BindView(R.id.select_team_tv) TextView select;
  @BindView(R.id.team_info_follow) Button favoriteBtn;

  private InfoPresenter presenter;
  private TeamDetail detail;

  public static TeamInfoFragment newInstance() {
    return new TeamInfoFragment();
  }

  @Override public int fragmentLayout() {
    return R.layout.team_info_dialog;
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
  public void subscribeToIdBus(TeamDetail info) {
    detail = info;
    if (info != null) {
      instantiaatePresenter(info.getId());
      favoriteBtn.setVisibility(View.VISIBLE);
      ImageSettings.settingImage(info.getUrl(), getContext(), teamImg);
      name.setText(info.getName());
    }
  }

  private void instantiaatePresenter(int id) {
    presenter = new InfoPresenter(getContext(), disposables, this);
    presenter.loadTeamsSquad(id, wheel);
  }

  @Override public void init() {
    RecyclerConfigs.setupRecyclerSettings(squad, getContext(), LinearLayoutManager.VERTICAL,
        DividerItemDecoration.HORIZONTAL);
  }

  @Override public void squad(List<Teams.Squad> list) {
    select.setVisibility(View.GONE);
    squad.setAdapter(new TeamInfoAdapter(list));
  }

  @OnClick({ R.id.team_info_follow })
  public void onFavoriteClick() {
    presenter.insertNewTeam(detail.getId(), detail.getName(), detail.getUrl());
  }
}
