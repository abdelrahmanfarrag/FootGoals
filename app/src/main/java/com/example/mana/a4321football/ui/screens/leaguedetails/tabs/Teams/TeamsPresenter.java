package com.example.mana.a4321football.ui.screens.leaguedetails.tabs.Teams;

import android.app.Dialog;
import android.content.Context;
import android.os.Handler;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import com.example.mana.a4321football.R;
import com.example.mana.a4321football.data.eventbus.EmblemsBus;
import com.example.mana.a4321football.data.model.LeagueTeams;
import com.example.mana.a4321football.data.model.Teams;
import com.example.mana.a4321football.data.network.RetrofitServices;
import com.example.mana.a4321football.ui.base.BasePresenter;
import com.example.mana.a4321football.utility.Dialogs;
import com.example.mana.a4321football.utility.ImageSettings;
import com.example.mana.a4321football.utility.RecyclerConfigs;
import com.example.mana.a4321football.utility.ToastMessages;
import com.pnikosis.materialishprogress.ProgressWheel;
import io.reactivex.disposables.CompositeDisposable;
import java.util.Objects;

public class TeamsPresenter extends BasePresenter {

  private TeamsResponse response;
  private RetrofitServices services;

  Teams teams;

  public TeamsPresenter(Context context,
      CompositeDisposable disposable, TeamsResponse response) {
    super(context, disposable);
    this.response = response;
    services = new RetrofitServices(context, disposable, this);
  }

  public void loadLeagueTeams(String id, ProgressWheel wheel) {
    if (id != null) {
      services.getLeagueTeams(wheel, id);
    }
  }

  private void loadTeamInfo(int id, ProgressWheel wheel) {
    if (id != 0) {
      services.getTeamInfo(wheel, id);
    }
  }

  public void buildTeamInfoDialog(int id, Teams team) {
    Dialog dialog =
        Dialogs.transparentDialog(context, R.layout.team_info_dialog, R.style.wide_dialog);
    Objects.requireNonNull(dialog.getWindow())
        .setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
    TextView name = dialog.findViewById(R.id.team_info_name);
    ProgressWheel wheel = dialog.findViewById(R.id.team_info_whee);
    ImageView teamImg = dialog.findViewById(R.id.team_info_img);
    RecyclerView list = dialog.findViewById(R.id.team_info_squad);
    loadTeamInfo(id, wheel);
    RecyclerConfigs.setupRecyclerSettings(list, context, LinearLayoutManager.VERTICAL,
        DividerItemDecoration.VERTICAL);
    Handler h = new Handler();
    h.postDelayed(new Runnable() {
      @Override public void run() {
        list.setAdapter(new TeamInfoAdapter(teams.getSquads()));
        name.setText(teams.getName());
        ImageSettings.settingImage(teams.getImgUrl(),context,teamImg);
        //ToastMessages.ShortToastMessage(context,String.valueOf(teams.getSquads().size()));
      }
    },2000);
   // list.setAdapter(new TeamInfoAdapter(teams.getSquads()));
    dialog.show();
  }

  @Override public void loadServiceData(Object model) {
    if (model instanceof LeagueTeams) {
      LeagueTeams teams = (LeagueTeams) model;
      response.teamResponse(teams);
    } else if (model instanceof Teams) {
       teams = (Teams) model;
      response.teamInfo(teams);
    }
  }
}
