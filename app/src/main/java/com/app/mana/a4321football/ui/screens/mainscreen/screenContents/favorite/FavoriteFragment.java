package com.app.mana.a4321football.ui.screens.mainscreen.screenContents.favorite;

import android.app.AlertDialog;
import android.os.Handler;
import android.support.constraint.ConstraintLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.TextView;
import butterknife.BindColor;
import butterknife.BindString;
import butterknife.BindView;
import com.app.mana.a4321football.R;
import com.app.mana.a4321football.data.database.Favorite;
import com.app.mana.a4321football.ui.base.BaseFragment;
import com.app.mana.a4321football.ui.screens.mainscreen.screenContents.favorite.teamdetails.TeamDetailFragment;
import com.app.mana.a4321football.utility.FragmentManagement;
import com.app.mana.a4321football.utility.RecyclerConfigs;
import java.util.List;
import java.util.Objects;

public class FavoriteFragment extends BaseFragment implements presenterResponse {

  @BindView(R.id.no_fav_cont) ConstraintLayout noFavLayout;
  @BindView(R.id.list_fav_teams) RecyclerView favTeamsList;
  @BindView(R.id.no_fav_found_tv) TextView noFavTv;
  @BindView(R.id.fav_cont_container) FrameLayout container;

  @BindString(R.string.delete_team_msg) String msg;
  @BindString(R.string.delete_team_title) String title;
  @BindString(R.string.approve_msg) String yes;
  @BindString(R.string.no_msg) String no;

  @BindColor(R.color.black_b) int lightBlack;

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
    FavoriteAdapter.setActivity(this.getActivity());
    RecyclerConfigs.setupRecyclerSettings(favTeamsList, getContext(),
        LinearLayoutManager.HORIZONTAL, 0);
    FragmentManagement.showFragmentOnFragment(FavoriteFragment.this,
        TeamDetailFragment.getInstance(),
        R.id.fav_cont_container, false);
  }

  @Override public void teamData(int id) {
    AlertDialog dialog = new AlertDialog.Builder(getContext())
        .setTitle(title)
        .setMessage(msg)
        .setPositiveButton(yes, (dialog1, which) -> {
          presenter.deleteItem(id);
          Handler h = new Handler();
          h.postDelayed(() -> {
            presenter.getFavoriteList();
            Objects.requireNonNull(getContext()).getSupportFragmentManager().beginTransaction()
                .replace(R.id.no_fav_cont, FavoriteFragment.getInstance()).commit();
          }, 1000);
        })
        .setNegativeButton(no, (dialog12, which) -> dialog12.dismiss()).create();
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
