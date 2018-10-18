package com.example.mana.a4321football.ui.screens.leaguedetails.tabs.Standings;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.support.annotation.NonNull;
import android.support.constraint.ConstraintLayout;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import butterknife.BindColor;
import butterknife.BindString;
import butterknife.BindView;
import butterknife.OnClick;
import com.ahmadrosid.svgloader.SvgLoader;

import com.bumptech.glide.Glide;
import com.example.mana.a4321football.R;
import com.example.mana.a4321football.data.eventbus.LeagueBus;
import com.example.mana.a4321football.data.model.Standing;
import com.example.mana.a4321football.ui.base.BaseRecyclerAdapter;
import com.example.mana.a4321football.ui.base.BaseRecyclerHolder;
import java.util.List;

public class StandingAdapter extends BaseRecyclerAdapter<Standing.Stand.Table, BaseRecyclerHolder> {

  @SuppressLint("StaticFieldLeak") private static Activity activity;

  ItemDetails details;

  StandingAdapter(
      List<Standing.Stand.Table> recyclerItems, ItemDetails details) {
    super(recyclerItems);
    this.details = details;
  }

  public static Activity getActivity() {
    return activity;
  }

  public static void setActivity(Activity activity) {
    StandingAdapter.activity = activity;
  }

  @NonNull @Override
  public BaseRecyclerHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
    View v = setRecyclerItemLayout(parent.getContext())
        .inflate(R.layout.single_league_match, parent, false);
    return new StandingHolder(v);
  }

  @SuppressLint("SetTextI18n") @Override
  public void onBindViewHolder(@NonNull BaseRecyclerHolder holder, int position) {
    StandingHolder hold = (StandingHolder) holder;
    int itemPosition = hold.getLayoutPosition();
    if (!TextUtils.isEmpty(getItemAtPosition(itemPosition).getTeam().getTeamLogo())) {
      if (getItemAtPosition(itemPosition).getTeam().getTeamLogo().contains(".svg")) {
        if (LeagueBus.getLeagueNAME().equalsIgnoreCase("Ligue 1")) {
          Glide.with(getActivity()).load(getItemAtPosition(itemPosition).getTeam().getTeamLogo())
              .into(hold.emblem);
          Glide.with(getActivity()).load(getItemAtPosition(itemPosition).getTeam().getTeamLogo())
              .into(hold.backDrop);
        } else {
          SvgLoader.pluck()
              .with(getActivity())
              .load(getItemAtPosition(itemPosition).getTeam().getTeamLogo(), hold.emblem);
          SvgLoader.pluck()
              .with(getActivity())
              .load(getItemAtPosition(itemPosition).getTeam().getTeamLogo(), hold.backDrop);
        }
      } else {
        Glide.with(getActivity()).load(getItemAtPosition(itemPosition).getTeam().getTeamLogo())
            .into(hold.emblem);
        Glide.with(getActivity()).load(getItemAtPosition(itemPosition).getTeam().getTeamLogo())
            .into(hold.backDrop);
      }
    }

    hold.name.setText(getItemAtPosition(itemPosition).getTeam().getTeamName());
    hold.position.setText(
        hold.positions + String.valueOf(getItemAtPosition(itemPosition).getPosition()));
    hold.played.setText(hold.play + String.valueOf(getItemAtPosition(itemPosition).getGames()));
    hold.won.setText(hold.win + String.valueOf(getItemAtPosition(itemPosition).getWon()));
    hold.draw.setText(hold.drew + String.valueOf(getItemAtPosition(itemPosition).getDraw()));
    hold.lost.setText(hold.loss + String.valueOf(getItemAtPosition(itemPosition).getLost()));
    hold.goals.setText(
        hold.scored + String.valueOf(getItemAtPosition(itemPosition).getGoalScored()));
    hold.conceded.setText(
        hold.conced + String.valueOf(getItemAtPosition(itemPosition).getGoalsConceded()));
    hold.points.setText(hold.point + String.valueOf(getItemAtPosition(itemPosition).getPoints()));
    hold.goalDifference.setText(
        hold.goalDiff + String.valueOf(getItemAtPosition(itemPosition).getGoalDiff()));
    if (getItemAtPosition(itemPosition).getGoalDiff() > 0) {
      hold.goalDifference.setTextColor(hold.darkGreen);
    } else if (getItemAtPosition(itemPosition).getGoalDiff() == 0) {
      hold.goalDifference.setTextColor(hold.darkWhite);
    } else {
      hold.goalDifference.setTextColor(hold.darkRed);
    }
  }

  class StandingHolder extends BaseRecyclerHolder {

    @BindView(R.id.backdrop_img) ImageView backDrop;
    @BindView(R.id.emblem_img) ImageView emblem;
    @BindView(R.id.team_standing_name) TextView name;
    @BindView(R.id.team_standing_postion) TextView position;
    @BindView(R.id.team_standing_played) TextView played;
    @BindView(R.id.team_standing_won) TextView won;
    @BindView(R.id.team_standing_draw) TextView draw;
    @BindView(R.id.team_standing_lost) TextView lost;
    @BindView(R.id.team_standing_goals) TextView goals;
    @BindView(R.id.team_standing_conceded) TextView conceded;
    @BindView(R.id.team_standing_points) TextView points;
    @BindView(R.id.team_standing_difference) TextView goalDifference;
    @BindView(R.id.item_standing_cont) ConstraintLayout layout;

    @BindString(R.string.position) String positions;
    @BindString(R.string.played) String play;
    @BindString(R.string.won) String win;
    @BindString(R.string.draw) String drew;
    @BindString(R.string.lost) String loss;
    @BindString(R.string.scored) String scored;
    @BindString(R.string.conceded) String conced;
    @BindString(R.string.points) String point;
    @BindString(R.string.goal_diff) String goalDiff;

    @BindColor(R.color.darkGreen) int darkGreen;
    @BindColor(R.color.darkRed) int darkRed;
    @BindColor(R.color.darkWhite) int darkWhite;

    StandingHolder(View itemView) {
      super(itemView);
    }

    @OnClick({ R.id.item_standing_cont })
    public void onItemClicked(View v) {
      switch (v.getId()) {
        case R.id.item_standing_cont:
          details.teamId(getItemAtPosition(getLayoutPosition()).getTeam());
          break;
      }
    }
  }
}
