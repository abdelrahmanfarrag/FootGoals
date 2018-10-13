package com.example.mana.a4321football.ui.screens.leaguedetails.tabs.Standings;

import android.content.Context;
import android.view.View;
import com.example.mana.a4321football.data.eventbus.EmblemsBus;
import com.example.mana.a4321football.data.eventbus.LeagueBus;
import com.example.mana.a4321football.data.eventbus.testBus;
import com.example.mana.a4321football.data.model.Standing;
import com.example.mana.a4321football.ui.base.BasePresenter;
import com.example.mana.a4321football.utility.AppUtils;
import com.example.mana.a4321football.utility.Constants;
import com.pnikosis.materialishprogress.ProgressWheel;
import io.reactivex.disposables.CompositeDisposable;
import java.util.HashMap;
import java.util.Map;
import org.greenrobot.eventbus.EventBus;

public class StandingsPresenter extends BasePresenter {

  private StandingsResponse response;

  StandingsPresenter(Context context, CompositeDisposable disposable, StandingsResponse response) {
    super(context, disposable);
    this.response = response;
  }

  public void loadStandings(ProgressWheel wheel, View[] views) {

    if (!AppUtils.isOnline(context)// || LeagueBus.getLeagueID() == null
        ) {
      views[0].setVisibility(View.VISIBLE);
      views[1].setVisibility(View.VISIBLE);
    } else {
      services.getLeagueStandings(wheel, LeagueBus.getLeagueID(), standingMap());
      views[0].setVisibility(View.GONE);
      views[1].setVisibility(View.GONE);
    }
  }

  private Map<String, String> standingMap() {
    Map<String, String> data = new HashMap<>();
    data.put(Constants.STANDING_TYPE, Constants.STANDING_TYPE_KEY);
    return data;
  }

  public void selectGroup(int listLength, View[] toggles) {
    if (listLength > 1) {
      toggles[0].setVisibility(View.VISIBLE);
      toggles[1].setVisibility(View.VISIBLE);
    }
  }

  @Override public void loadServiceData(Object model) {
    Standing standing = (Standing) model;
    EventBus.getDefault().post(new testBus("MANA"));
    int groupsCount = standing.getStandings().size();
    Map<String, String> emblems = new HashMap<>();
    for (int i = 0; i < groupsCount; i++) {
      for (int x = 0; x < standing.getStandings().get(i).getTables().size(); x++) {
        emblems.put(standing.getStandings().get(i).getTables().get(x).getTeam().getTeamName(),
            standing.getStandings().get(i).getTables().get(x).getTeam().getTeamLogo());
      }
    }
    EmblemsBus.setEmblems(emblems);
    response.standingResponse(standing);
  }
}
