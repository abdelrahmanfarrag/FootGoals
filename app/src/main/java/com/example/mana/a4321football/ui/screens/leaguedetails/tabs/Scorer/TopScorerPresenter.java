package com.example.mana.a4321football.ui.screens.leaguedetails.tabs.Scorer;

import android.content.Context;
import com.example.mana.a4321football.data.model.Scorer;
import com.example.mana.a4321football.ui.base.BasePresenter;
import com.pnikosis.materialishprogress.ProgressWheel;
import io.reactivex.disposables.CompositeDisposable;

public class TopScorerPresenter extends BasePresenter {

  private ScorerResponse response;

  TopScorerPresenter(Context context, CompositeDisposable disposable,
      ScorerResponse response) {
    super(context, disposable);
    this.response = response;
  }

  public void loadLeagueScorers(String id, ProgressWheel wheel) {

      services.getLeagueScorers(wheel, id);

  }

  @Override public void loadServiceData(Object model) {
    Scorer scorer = (Scorer) model;
    response.scorerData(scorer);
  }
}
