package com.app.mana.a4321football.ui.screens.mainscreen.screenContents.matches;

import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import butterknife.BindView;
import com.app.mana.a4321football.R;
import com.app.mana.a4321football.data.Notification.Notify;
import com.app.mana.a4321football.data.model.Matches;
import com.app.mana.a4321football.ui.base.BaseFragment;
import com.app.mana.a4321football.utility.RecyclerConfigs;
import com.pnikosis.materialishprogress.ProgressWheel;
import java.util.List;

@SuppressWarnings("ALL") public class MatchesFragment extends BaseFragment
    implements TodayMatches, OnFavorClicked {
  @BindView(R.id.matches_wheel) ProgressWheel matchesWheel;
  @BindView(R.id.today_matches_list) RecyclerView todayMatches;
  @BindView(R.id.no_match_img) ImageView noMatches;

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
    if (matches.size() == 0) {
      todayMatches.setVisibility(View.INVISIBLE);
      noMatches.setVisibility(View.VISIBLE);
    } else {
      todayMatches.setVisibility(View.VISIBLE);
      noMatches.setVisibility(View.GONE);
      Notify.setDailyNotification(getContext(), matches.size());
    }
    todayMatches.setAdapter(new MatchesAdapter(matches, this,false));
  }

  @Override public void onFavoriteMatchClicked(String date) {
    //TODO ADD NOTIFICATION CREATION HERE !!
  }
}
