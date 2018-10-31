package com.example.mana.a4321football.ui.screens.mainscreen.screenContents.favorite.teamdetails.tabs.previous;

import android.content.Context;
import com.example.mana.a4321football.data.model.Matches;
import com.example.mana.a4321football.ui.base.BasePresenter;
import com.pnikosis.materialishprogress.ProgressWheel;
import io.reactivex.disposables.CompositeDisposable;

public class PreviousPresenter extends BasePresenter {

  private PreviousResponse response;

  public PreviousPresenter(Context context,
      CompositeDisposable disposable, PreviousResponse response) {
    super(context, disposable);
    this.response = response;
  }

  public void getPreviousGames(int id, String query, ProgressWheel wheel) {
    services.getTeamPreviousGame(wheel, id, query);
  }

  @Override public void loadServiceData(Object model) {
    Matches matches = (Matches) model;
    response.getMatchesDetails(matches);
  }
}
