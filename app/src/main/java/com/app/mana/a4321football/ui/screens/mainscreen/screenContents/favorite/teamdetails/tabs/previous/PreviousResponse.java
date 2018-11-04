package com.app.mana.a4321football.ui.screens.mainscreen.screenContents.favorite.teamdetails.tabs.previous;

import com.app.mana.a4321football.data.model.Matches;
import java.util.List;

public interface PreviousResponse {

  void getMatchesDetails(List<Matches.MatchDetails> matches);
}
