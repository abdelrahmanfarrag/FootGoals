package com.example.mana.a4321football.ui.screens.mainscreen.screenContents.matches;

import android.content.Context;
import com.example.mana.a4321football.data.model.Matches;
import com.example.mana.a4321football.data.network.RetrofitServices;
import com.pnikosis.materialishprogress.ProgressWheel;
import io.reactivex.disposables.CompositeDisposable;

public class MatchesPresenter implements RetrofitServices.transformServiceResponse {
  private Context context;
  private TodayMatches matches;
  private CompositeDisposable disposables;

  MatchesPresenter(Context context, CompositeDisposable disposables, TodayMatches matches,
      ProgressWheel wheel) {
    this.context = context;
    this.disposables = disposables;
    this.matches = matches;
    loadMatches(wheel);
  }

  private void loadMatches(ProgressWheel wheel) {
    RetrofitServices services = new RetrofitServices(context, disposables, this);
    services.matches(wheel);
  }

  @Override public void responseReceived(Object model) {
    Matches todayMatches = (Matches) model;
    matches.todayMatchesList(todayMatches.getDetails());
  }
}
