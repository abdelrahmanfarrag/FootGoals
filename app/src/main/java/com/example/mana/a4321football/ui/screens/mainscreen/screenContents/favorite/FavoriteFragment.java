package com.example.mana.a4321football.ui.screens.mainscreen.screenContents.favorite;

import android.app.AlertDialog;
import android.os.Handler;
import android.support.constraint.ConstraintLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;
import butterknife.BindView;
import com.example.mana.a4321football.R;
import com.example.mana.a4321football.data.database.Favorite;
import com.example.mana.a4321football.ui.base.BaseFragment;
import com.example.mana.a4321football.ui.screens.mainscreen.screenContents.favorite.teamdetails.TeamDetailFragment;
import com.example.mana.a4321football.utility.FragmentManagement;
import com.example.mana.a4321football.utility.RecyclerConfigs;
import java.util.List;

public class FavoriteFragment extends BaseFragment implements presenterResponse {

  @BindView(R.id.no_fav_cont) ConstraintLayout noFavLayout;
  @BindView(R.id.list_fav_teams) RecyclerView favTeamsList;
  @BindView(R.id.no_fav_found_tv) TextView noFavTv;

  FavoritePresenter presenter;
  FavoriteAdapter adapter;

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
    AlertDialog dialog = new AlertDialog.Builder(getContext())
        .setTitle("Delete a team ")
        .setMessage("Are your sure you want to remove this item ?")
        .setPositiveButton("Yes", (dialog1, which) -> {
          presenter.deleteItem(id);
          Handler h = new Handler();
          h.postDelayed(() -> presenter.getFavoriteList(), 1000);
        })
        .setNegativeButton("No", (dialog12, which) -> dialog12.dismiss()).create();
    dialog.show();
  }

  @Override public void favoritedTeams(List<Favorite> favorites) {
    if (favorites.size() > 0) {
      noFavTv.setVisibility(View.GONE);
      favTeamsList.setAdapter(new FavoriteAdapter(favorites, this));
    } else {
      noFavTv.setVisibility(View.VISIBLE);
    }
  }
}
