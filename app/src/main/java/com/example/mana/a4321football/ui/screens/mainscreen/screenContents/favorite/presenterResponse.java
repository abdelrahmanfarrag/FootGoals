package com.example.mana.a4321football.ui.screens.mainscreen.screenContents.favorite;

import com.example.mana.a4321football.data.database.Favorite;
import java.util.List;

public interface presenterResponse {

  void teamData(int id);

  void favoritedTeams(List<Favorite> favorites);

}
