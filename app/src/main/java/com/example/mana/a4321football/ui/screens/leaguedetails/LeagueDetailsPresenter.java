package com.example.mana.a4321football.ui.screens.leaguedetails;

import android.content.Context;
import com.example.mana.a4321football.data.eventbus.LeagueBus;
import com.example.mana.a4321football.data.eventbus.MatchDay;
import com.example.mana.a4321football.data.model.League;
import com.example.mana.a4321football.ui.base.BasePresenter;
import com.pnikosis.materialishprogress.ProgressWheel;
import io.reactivex.disposables.CompositeDisposable;
import org.greenrobot.eventbus.EventBus;

public class LeagueDetailsPresenter extends BasePresenter {

  private PresenterResponse response;

  LeagueDetailsPresenter(Context context, CompositeDisposable disposable,
      PresenterResponse response) {
    super(context, disposable);
    this.response = response;
  }

  public void loadDetails(String id, ProgressWheel wheel) {
    if (id != null) {
      services.getLeagueDetails(wheel, id);
    }
  }

  @Override public void loadServiceData(Object model) {
    League league = (League) model;
    response.leageDetails(league);
    EventBus.getDefault().post(new MatchDay(league.getSeason().getCurrentMatch()));
    LeagueBus.setLeagueNAME(league.getName());
  }
}
