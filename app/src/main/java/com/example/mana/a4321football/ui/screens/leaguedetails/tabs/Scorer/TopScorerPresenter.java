package com.example.mana.a4321football.ui.screens.leaguedetails.tabs.Scorer;

import android.content.Context;
import android.view.View;
import com.example.mana.a4321football.data.eventbus.LeagueBus;
import com.example.mana.a4321football.data.model.Scorer;
import com.example.mana.a4321football.data.network.RetrofitServices;
import com.example.mana.a4321football.ui.base.BasePresenter;
import com.example.mana.a4321football.utility.AppUtils;
import com.pnikosis.materialishprogress.ProgressWheel;
import io.reactivex.disposables.CompositeDisposable;

public class TopScorerPresenter extends BasePresenter {

  private ScorerResponse response;

  public TopScorerPresenter(Context context, CompositeDisposable disposable,
      ScorerResponse response) {
    super(context, disposable);
    this.response = response;
  }

  public void loadLeagueScorers(String id, ProgressWheel wheel, View[] views) {
    if (!AppUtils.isOnline(context) || id == null) {
      views[0].setVisibility(View.VISIBLE);
      views[1].setVisibility(View.VISIBLE);
    } else {
      services.getLeagueScorers(wheel, id
      );
      views[0].setVisibility(View.GONE);
      views[1].setVisibility(View.GONE);
    }
  }

  @Override public void loadServiceData(Object model) {
    Scorer scorer = (Scorer) model;
    response.scorerData(scorer);
  }
}
