package com.example.mana.a4321football.ui.screens.mainscreen.screenContents.favorite.teamdetails.tabs.next;

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
import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

public class Next extends BaseFragment implements NextResponse, OnFavorClicked {
  @BindView(R.id.next_wheel) ProgressWheel wheel;
  @BindView(R.id.next_match_list) RecyclerView list;

  public static Next getInstance() {
    return new Next();
  }

  @Override public int fragmentLayout() {
    return R.layout.fragment_next;
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
  public void subscribeToTabsBus(Tabs details) {
    instantiatePresenter(details.getDetails().getId());
  }

  private void instantiatePresenter(int id) {
    NextPresenter presenter = new NextPresenter(getContext(), disposables, this);
    presenter.loadNextMatches(id, wheel);
  }

  @Override public void nextResp(Matches matches) {
    list.setAdapter(new MatchesAdapter(matches.getDetails(),this,true));
  }

  @Override public void onFavoriteMatchClicked(String date) {

  }
}
