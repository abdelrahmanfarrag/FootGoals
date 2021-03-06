package com.app.mana.a4321football.ui.screens.mainscreen.screenContents.favorite.teamdetails.tabs.info;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.net.Uri;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ScrollView;
import android.widget.TextView;
import butterknife.BindString;
import butterknife.BindView;
import butterknife.OnClick;
import com.app.mana.a4321football.R;
import com.app.mana.a4321football.data.database.Favorite;
import com.app.mana.a4321football.data.eventbus.Tabs;
import com.app.mana.a4321football.data.eventbus.TeamDetail;
import com.app.mana.a4321football.data.model.LeagueTeams;
import com.app.mana.a4321football.data.model.Teams;
import com.app.mana.a4321football.ui.base.BaseFragment;
import com.app.mana.a4321football.ui.screens.leaguedetails.tabs.Teams.TeamsPresenter;
import com.app.mana.a4321football.ui.screens.leaguedetails.tabs.Teams.TeamsResponse;
import com.app.mana.a4321football.ui.screens.leaguedetails.tabs.Teams.team_info.TeamInfoFragment;
import com.app.mana.a4321football.utility.FragmentManagement;
import com.app.mana.a4321football.utility.ImageSettings;
import com.app.mana.a4321football.utility.ToastMessages;
import com.pnikosis.materialishprogress.ProgressWheel;
import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

public class Info extends BaseFragment implements TeamsResponse {

  @BindView(R.id.fav_wheel) ProgressWheel wheel;
  @BindView(R.id.fav_team_crest) ImageView teamImg;
  @BindView(R.id.fav_team_name) TextView name;
  @BindView(R.id.fav_team_area) TextView area;
  @BindView(R.id.fav_team_founded) TextView founded;
  @BindView(R.id.fav_team_stadium) TextView stadium;
  @BindView(R.id.fav_team_web_btn) Button webBtn;
  @BindView(R.id.fragment_info_container) ScrollView container;

  @BindString(R.string.found) String found;
  @BindString(R.string.stad_name) String stad;
  TeamsPresenter presenter;
  String team, url;
  String urlToWebSite;
  int id;

  public static Info getInstance() {
    return new Info();
  }

  @Override public int fragmentLayout() {
    return R.layout.fragment_info;
  }

  @Override public void init() {

  }

  @Override public void teamResponse(LeagueTeams teams) {

  }

  @Override public void teamInfo(Teams info) {
    settingViews(info);
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
  public void subscribtToBus(Tabs details) {
    id = details.getDetails().getId();
    team = details.getDetails().getName();
    url = details.getDetails().getUrl();
    instantiatePresenter(details.getDetails().getId());
  }

  @OnClick({ R.id.fav_team_web_btn })
  public void onClick(View v) {
    switch (v.getId()) {
      case R.id.fav_team_web_btn:
        Intent i = new Intent(Intent.ACTION_VIEW);
        i.setData(Uri.parse(urlToWebSite));
        startActivity(i);

        break;
    }
  }

  private void instantiatePresenter(int id) {
    if (id != 0) {
      container.setVisibility(View.VISIBLE);
      presenter = new TeamsPresenter(getContext(), disposables, this);
      presenter.loadTeamInfo(id, wheel);
      webBtn.setVisibility(View.VISIBLE);
    } else {
      container.setVisibility(View.INVISIBLE);
    }
  }

  @SuppressLint("SetTextI18n") private void settingViews(Teams teams) {
    ImageSettings.favoriteImage(getContext(), teamImg, this.getActivity(), teams.getImgUrl());
    name.setText(teams.getName());
    area.setText(teams.getArea().getName());
    urlToWebSite = teams.getWeb();
    founded.setText(found + " : " + teams.getFounded());
    stadium.setText(stad + " : " + teams.getStadium());
  }
}
