package com.example.mana.a4321football.ui.screens.leaguedetails.tabs.Teams;

import android.app.Dialog;
import android.content.Context;
import android.os.Handler;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import com.example.mana.a4321football.R;
import com.example.mana.a4321football.data.database.DatabaseConstruct;
import com.example.mana.a4321football.data.database.Favorite;
import com.example.mana.a4321football.data.model.LeagueTeams;
import com.example.mana.a4321football.data.model.Teams;
import com.example.mana.a4321football.data.network.RetrofitServices;
import com.example.mana.a4321football.ui.base.BasePresenter;
import com.example.mana.a4321football.utility.Dialogs;
import com.example.mana.a4321football.utility.ImageSettings;
import com.example.mana.a4321football.utility.RecyclerConfigs;
import com.example.mana.a4321football.utility.ToastMessages;
import com.pnikosis.materialishprogress.ProgressWheel;
import io.reactivex.Single;
import io.reactivex.SingleObserver;
import io.reactivex.SingleOnSubscribe;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import java.util.Objects;

public class TeamsPresenter extends BasePresenter {

  private TeamsResponse response;
  private RetrofitServices services;
  private Teams teams;
  private CompositeDisposable disposables;

  TeamsPresenter(Context context,
      CompositeDisposable disposable, TeamsResponse response) {
    super(context, disposable);
    this.response = response;
    this.disposables = disposable;
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

  private void retryButtonAction(Button button, int id, ProgressWheel wheel) {
    button.setVisibility(View.VISIBLE);
    loadTeamInfo(id, wheel);
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
    Button retryBtn = dialog.findViewById(R.id.team_info_retry);
    Button favBtn = dialog.findViewById(R.id.team_info_follow);
    loadTeamInfo(id, wheel);
    RecyclerConfigs.setupRecyclerSettings(list, context, LinearLayoutManager.VERTICAL,
        DividerItemDecoration.VERTICAL);
    Handler h = new Handler();
    h.postDelayed(() -> {
      if (team != null) {
        list.setAdapter(new TeamInfoAdapter(teams.getSquads()));
        name.setText(teams.getName());
        ImageSettings.settingImage(teams.getImgUrl(), context, teamImg);
      } else {
        retryButtonAction(retryBtn, id, wheel);
      }
    }, 2000);

    favBtn.setOnClickListener(v -> insertNewTeam(team.getId(), team.getName(), team.getImgUrl()));
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

  @SuppressWarnings("unchecked")
  private Single addToFavoriteObservable(int id, String name, String path) {
    DatabaseConstruct db = DatabaseConstruct.getInstance(context);

    return Single.create((SingleOnSubscribe) emitter -> {
      if (!emitter.isDisposed()) {
        Favorite favorite = new Favorite(id, path
            , name);
        db.accessPoint().insertMovieToFavorite(favorite);
        emitter.onSuccess(favorite);
      }
    });
  }

  private SingleObserver addToFavoriteObserver() {
    return new SingleObserver() {
      @Override public void onSubscribe(Disposable d) {
        disposables.add(d);
      }

      @Override public void onSuccess(Object o) {
        ToastMessages.ShortToastMessage(context, "ADDED SUCCESSFULLY TO FAVORITES !");
      }

      @Override public void onError(Throwable e) {

      }
    };
  }

  @SuppressWarnings("unchecked") private void insertNewTeam(int id, String name, String path) {
    addToFavoriteObservable(id, name, path)
        .subscribeOn(Schedulers.io())
        .observeOn(AndroidSchedulers.mainThread())
        .subscribe(addToFavoriteObserver());
  }
}
