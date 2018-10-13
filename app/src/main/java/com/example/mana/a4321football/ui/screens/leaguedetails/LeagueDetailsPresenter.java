package com.example.mana.a4321football.ui.screens.leaguedetails;

import android.content.Context;
import com.example.mana.a4321football.data.eventbus.LeagueBus;
import com.example.mana.a4321football.data.model.League;
import com.example.mana.a4321football.ui.base.BasePresenter;
import com.example.mana.a4321football.utility.ToastMessages;
import com.pnikosis.materialishprogress.ProgressWheel;
import io.reactivex.disposables.CompositeDisposable;

public class LeagueDetailsPresenter extends BasePresenter {

  private PresenterResponse response;

  LeagueDetailsPresenter(Context context, CompositeDisposable disposable,
      PresenterResponse response) {
    super(context, disposable);
    this.response = response;
  }

  public void loadDetails(String id, ProgressWheel wheel) {
    if (id !=null) {
      services.getLeagueDetails(wheel, id);
    }else {
      ToastMessages.ShortToastMessage(context,"id value is "+id
      );
    }
  }

  @Override public void loadServiceData(Object model) {
    League league = (League) model;
    response.leageDetails(league);
    LeagueBus.setLeagueID(league.getLeagueCode());
    LeagueBus.setLeagueNAME(league.getName());
    LeagueBus.setCurrentMatch(league.getSeason().getCurrentMatch());
  }
}
