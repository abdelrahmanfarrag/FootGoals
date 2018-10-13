package com.example.mana.a4321football.ui.screens.mainscreen.screenContents.matches;

import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import butterknife.BindView;
import com.example.mana.a4321football.R;
import com.example.mana.a4321football.data.model.Matches;
import com.example.mana.a4321football.ui.base.BaseFragment;
import com.example.mana.a4321football.utility.RecyclerConfigs;
import com.pnikosis.materialishprogress.ProgressWheel;
import java.util.List;

@SuppressWarnings("ALL") public class MatchesFragment extends BaseFragment
    implements TodayMatches, OnFavorClicked {
  @BindView(R.id.matches_wheel) ProgressWheel matchesWheel;
  @BindView(R.id.today_matches_list) RecyclerView todayMatches;

  public static MatchesFragment getInstance() {
    return new MatchesFragment();
  }

  @Override public int fragmentLayout() {
    return R.layout.matches_fragment;
  }

  @Override public void init() {
    RecyclerConfigs.setupRecyclerSettings(todayMatches, getContext(), LinearLayoutManager.VERTICAL,
        DividerItemDecoration.VERTICAL);
    MatchesPresenter presenter =
        new MatchesPresenter(getContext(), disposables, this, matchesWheel);
  }

  @Override public void todayMatchesList(List<Matches.MatchDetails> matches) {
    todayMatches.setAdapter(new MatchesAdapter(matches, this));
  }

  @Override public void onFavoriteMatchClicked(String date) {
    //TODO ADD NOTIFICATION CREATION HERE !!
  }
}
