package com.app.mana.a4321football.ui.screens.mainscreen.screenContents.favorite.teamdetails.tabs.previous;

import android.content.Context;
import com.app.mana.a4321football.data.model.Matches;
import com.app.mana.a4321football.ui.base.BasePresenter;
import com.app.mana.a4321football.utility.Constants;
import com.app.mana.a4321football.utility.ToastMessages;
import com.pnikosis.materialishprogress.ProgressWheel;
import io.reactivex.disposables.CompositeDisposable;
import java.util.ArrayList;
import java.util.List;

public class PreviousPresenter extends BasePresenter {

  private PreviousResponse response;
  private List<Matches.MatchDetails> list = new ArrayList<>();

  public PreviousPresenter(Context context,
      CompositeDisposable disposable, PreviousResponse response) {
    super(context, disposable);
    this.response = response;
  }

  public void getPreviousGames(int id, ProgressWheel wheel) {
    services.getTeamPreviousGame(wheel, id, Constants.MATCHES_PLAYED);
  }

  @Override public void loadServiceData(Object model) {
    Matches matches = (Matches) model;
    for (int i =matches.getDetails().size();i>0;i--){
      list.add(matches.getDetails().get(i-1));
      response.getMatchesDetails(list);
    }
  }
}
