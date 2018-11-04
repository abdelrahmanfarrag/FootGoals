package com.app.mana.a4321football.ui.screens.mainscreen.screenContents.favorite.teamdetails;

import android.content.Context;
import com.app.mana.a4321football.data.model.Matches;
import com.app.mana.a4321football.data.network.RetrofitServices;
import com.app.mana.a4321football.ui.base.BasePresenter;
import com.pnikosis.materialishprogress.ProgressWheel;
import io.reactivex.disposables.CompositeDisposable;

public class DetailPresenter extends BasePresenter {

  private DetailResponse resp;

  public DetailPresenter(Context context,
      CompositeDisposable disposable, DetailResponse resp) {
    super(context, disposable);
    this.resp = resp;
  }

  public void loadGames(ProgressWheel wheel, int id, String query) {
    services.getTeamPreviousGame(wheel, id, query);
  }

  @Override public void loadServiceData(Object model) {
    if (model instanceof Matches) {
      Matches matches = (Matches) model;
      resp.getDetaiGames(matches);
    }
  }
}
