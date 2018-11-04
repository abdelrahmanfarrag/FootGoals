package com.app.mana.a4321football.ui.screens.mainscreen.screenContents.favorite.teamdetails.tabs.next;

import android.content.Context;
import com.app.mana.a4321football.data.model.Matches;
import com.app.mana.a4321football.ui.base.BasePresenter;
import com.app.mana.a4321football.utility.Constants;
import com.pnikosis.materialishprogress.ProgressWheel;
import io.reactivex.disposables.CompositeDisposable;

public class NextPresenter extends BasePresenter {

  private NextResponse response;

  public NextPresenter(Context context,
      CompositeDisposable disposable, NextResponse response) {
    super(context, disposable);
    this.response = response;
  }

  public void loadNextMatches(int id, ProgressWheel wheel) {
    services.getTeamPreviousGame(wheel, id, Constants.MATCHES_NEXT);
  }

  @Override public void loadServiceData(Object model) {
    Matches matches = (Matches) model;
    response.nextResp(matches);
  }
}
