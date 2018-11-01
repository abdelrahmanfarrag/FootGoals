package com.example.mana.a4321football.ui.screens.leaguedetails.tabs.Fixutres;

import android.content.Context;
import android.view.View;
import com.example.mana.a4321football.data.eventbus.LeagueBus;
import com.example.mana.a4321football.data.model.Fixture;
import com.example.mana.a4321football.ui.base.BasePresenter;
import com.example.mana.a4321football.utility.AppUtils;
import com.example.mana.a4321football.utility.Constants;
import com.example.mana.a4321football.utility.ToastMessages;
import com.pnikosis.materialishprogress.ProgressWheel;
import io.reactivex.disposables.CompositeDisposable;
import java.util.HashMap;
import java.util.Map;

public class FixturesPresenter extends BasePresenter {

  private FixtureResponse response;

  FixturesPresenter(Context context, CompositeDisposable disposable,
      FixtureResponse response) {
    super(context, disposable);
    this.response = response;
  }
  public void settingsViews(View[] views,int matchday){
    if (matchday == 1){
      views[0].setVisibility(View.GONE);
      }else if (matchday ==38){
      views[1].setVisibility(View.INVISIBLE);
    }else {
      views[0].setVisibility(View.VISIBLE);
      views[1].setVisibility(View.VISIBLE); }

  }

  private Map<String, Integer> fixtureMap(int day) {
    Map<String, Integer> data = new HashMap<>();
    data.put(Constants.MATCH_DAY, day);
    return data;
  }


  public void loadFixtures(ProgressWheel wheel, int day, String id, View[] views) {

    if (!AppUtils.isOnline(context) || id == null) {
      views[0].setVisibility(View.VISIBLE);
      views[1].setVisibility(View.VISIBLE);
    } else {
      if (day > 0) {
        services.getLeagueFixtures(wheel, id, fixtureMap(day));
        ToastMessages.ShortToastMessage(context,String.valueOf(day));
        views[0].setVisibility(View.GONE);
        views[1].setVisibility(View.GONE);
      }
    }
  }

  @Override public void loadServiceData(Object model) {
    Fixture fixture = (Fixture) model;
    response.fixtureResponse(fixture);
  }
}
