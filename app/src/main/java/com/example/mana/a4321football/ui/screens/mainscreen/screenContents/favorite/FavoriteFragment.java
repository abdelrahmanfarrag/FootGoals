package com.example.mana.a4321football.ui.screens.mainscreen.screenContents.favorite;

import android.support.constraint.ConstraintLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import butterknife.BindView;
import com.example.mana.a4321football.R;
import com.example.mana.a4321football.data.database.Favorite;
import com.example.mana.a4321football.data.eventbus.TeamDetail;
import com.example.mana.a4321football.ui.base.BaseFragment;
import com.example.mana.a4321football.ui.screens.mainscreen.screenContents.favorite.teamdetails.TeamDetailFragment;
import com.example.mana.a4321football.utility.FragmentManagement;
import com.example.mana.a4321football.utility.RecyclerConfigs;
import com.example.mana.a4321football.utility.ToastMessages;
import java.util.List;
import org.greenrobot.eventbus.EventBus;

public class FavoriteFragment extends BaseFragment implements presenterResponse {

  @BindView(R.id.no_fav_cont) ConstraintLayout noFavLayout;
  @BindView(R.id.list_fav_teams) RecyclerView favTeamsList;

  FavoritePresenter presenter;

  public static FavoriteFragment getInstance() {
    return new FavoriteFragment();
  }

  @Override public int fragmentLayout() {
    return R.layout.favorite_fragment;
  }

  @Override public void init() {
    presenter = new FavoritePresenter(getContext(), disposables, this);
    presenter.getFavoriteList();
    presenter.getShared(noFavLayout);
    FavoriteAdapter.setActivity(this.getActivity());
    RecyclerConfigs.setupRecyclerSettings(favTeamsList, getContext(),
        LinearLayoutManager.HORIZONTAL, 0);
    FragmentManagement.showFragmentOnFragment(FavoriteFragment.this,
        TeamDetailFragment.getInstance(),
        R.id.fav_cont_container, false);
  }

  @Override public void teamData(int id) {
  }

  @Override public void favoritedTeams(List<Favorite> favorites) {
    favTeamsList.setAdapter(new FavoriteAdapter(favorites, this));
  }
}
