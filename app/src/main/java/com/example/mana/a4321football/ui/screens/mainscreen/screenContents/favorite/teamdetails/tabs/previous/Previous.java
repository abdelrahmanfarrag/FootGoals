package com.example.mana.a4321football.ui.screens.mainscreen.screenContents.favorite.teamdetails.tabs.previous;

import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import butterknife.BindView;
import com.example.mana.a4321football.R;
import com.example.mana.a4321football.data.eventbus.Tabs;
import com.example.mana.a4321football.data.model.Matches;
import com.example.mana.a4321football.ui.base.BaseFragment;
import com.example.mana.a4321football.ui.screens.mainscreen.screenContents.matches.MatchesAdapter;
import com.example.mana.a4321football.ui.screens.mainscreen.screenContents.matches.OnFavorClicked;
import com.example.mana.a4321football.utility.RecyclerConfigs;
import com.pnikosis.materialishprogress.ProgressWheel;
import java.util.List;
import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

public class Previous extends BaseFragment implements PreviousResponse, OnFavorClicked {

  @BindView(R.id.prev_wheel) ProgressWheel wheel;
  @BindView(R.id.finished_match_list) RecyclerView list;

  public static Previous getInstance() {
    return new Previous();
  }

  @Override public int fragmentLayout() {
    return R.layout.fragment_prev;
  }

  @Override public void init() {
    RecyclerConfigs.setupRecyclerSettings(list, getContext(), LinearLayoutManager.VERTICAL,
        DividerItemDecoration.HORIZONTAL);
  }

  @Override public void onStart() {
    super.onStart();
    EventBus.getDefault().register(this);
  }

  @Override public void onStop() {
    super.onStop();
    EventBus.getDefault().unregister(this);
  }

  @Subscribe(sticky = true, threadMode = ThreadMode.MAIN)
  public void subscribeToBus(Tabs detail) {

    instantiatePresenter(detail.getDetails().getId());
  }

  private void instantiatePresenter(int id) {
    PreviousPresenter previousPresenter = new PreviousPresenter(getContext(), disposables, this);
    previousPresenter.getPreviousGames(id, wheel);
  }

  @Override public void getMatchesDetails(List<Matches.MatchDetails> matches) {
    list.setAdapter(new MatchesAdapter(matches, this,true));
  }

  @Override public void onFavoriteMatchClicked(String date) {

  }
}
