package com.example.mana.a4321football.ui.screens.leaguedetails.tabs.Standings;

import android.content.Context;
import android.view.View;
import android.widget.TextView;
import com.example.mana.a4321football.data.eventbus.EmblemsBus;
import com.example.mana.a4321football.data.model.Standing;
import com.example.mana.a4321football.ui.base.BasePresenter;
import com.example.mana.a4321football.utility.AppUtils;
import com.example.mana.a4321football.utility.Constants;
import com.example.mana.a4321football.utility.ToastMessages;
import com.pnikosis.materialishprogress.ProgressWheel;
import io.reactivex.disposables.CompositeDisposable;
import java.util.HashMap;
import java.util.Map;

public class StandingsPresenter extends BasePresenter {

  private StandingsResponse response;

  StandingsPresenter(Context context, CompositeDisposable disposable, StandingsResponse response) {
    super(context, disposable);
    this.response = response;
  }

  public void loadStandings(ProgressWheel wheel, View[] views, String id) {

    if (!AppUtils.isOnline(context)// || LeagueBus.getLeagueID() == null
        ) {
      views[0].setVisibility(View.VISIBLE);
      views[1].setVisibility(View.VISIBLE);
    } else {
      services.getLeagueStandings(wheel, id, standingMap());
      views[0].setVisibility(View.GONE);
      views[1].setVisibility(View.GONE);
    }
  }

  private Map<String, String> standingMap() {
    Map<String, String> data = new HashMap<>();
    data.put(Constants.STANDING_TYPE, Constants.STANDING_TYPE_KEY);
    return data;
  }

  private String groupName(int position) {
    switch (position) {
      case 0:
        return "GROUP A";
      case 1:
        return "GROUP B";
      case 2:
        return "GROUP C";
      case 3:
        return "GROUP D";
      case 4:
        return "GROUP E";
      case 5:
        return "GROUP F";
      case 6:
        return "GROUP G";
      case 7:
        return "GROUP H";
      default:
        return "NULL";
    }
  }

  public void selectGroup(int listLength, View[] toggles, TextView group, int position) {
    if (listLength > 1) {
      group.setText(groupName(position));
      toggles[2].setVisibility(View.VISIBLE);
      if (position == 0) {
        toggles[1].setVisibility(View.INVISIBLE);
        toggles[0].setVisibility(View.VISIBLE);
      } else if (position == 7) {
        toggles[1].setVisibility(View.VISIBLE);
        toggles[0].setVisibility(View.INVISIBLE);
      } else {
        toggles[1].setVisibility(View.VISIBLE);
        toggles[0].setVisibility(View.VISIBLE);
      }
    } else {
      toggles[2].setVisibility(View.GONE);
    }
  }

  @Override public void loadServiceData(Object model) {
    Standing standing = (Standing) model;
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
