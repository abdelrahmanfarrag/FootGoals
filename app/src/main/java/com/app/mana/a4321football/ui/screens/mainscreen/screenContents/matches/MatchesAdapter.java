package com.app.mana.a4321football.ui.screens.mainscreen.screenContents.matches;

import android.annotation.SuppressLint;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.annotation.RequiresApi;
import android.support.constraint.ConstraintLayout;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import butterknife.BindColor;
import butterknife.BindDrawable;
import butterknife.BindView;
import butterknife.OnClick;
import com.app.mana.a4321football.R;
import com.app.mana.a4321football.data.model.Matches;
import com.app.mana.a4321football.ui.base.BaseRecyclerAdapter;
import com.app.mana.a4321football.ui.base.BaseRecyclerHolder;
import com.app.mana.a4321football.utility.AppUtils;
import com.app.mana.a4321football.utility.RecyclerConfigs;
import java.util.List;

/*
This Adapter is used in two positions [MATCHES - NEXT MATCHES =PREVOUS MATCHES ]
IS NEED MODIFICATION THEN SEND TO IT (bool isMatches ) to differ between prev/matches/next :D
 */

public class MatchesAdapter extends BaseRecyclerAdapter<Matches.MatchDetails, BaseRecyclerHolder> {

  OnFavorClicked clicked;
  private boolean isFavorite;

  public MatchesAdapter(
      List<Matches.MatchDetails> recyclerItems, OnFavorClicked clicked, boolean isFavorite) {
    super(recyclerItems);
    this.clicked = clicked;
    this.isFavorite = isFavorite;
  }

  @NonNull @Override
  public BaseRecyclerHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
    View v =
        setRecyclerItemLayout(parent.getContext()).inflate(R.layout.single_match, parent, false);
    return new MatchHolder(v);
  }

  @SuppressLint("SetTextI18n") @RequiresApi(api = Build.VERSION_CODES.N) @Override
  public void onBindViewHolder(@NonNull BaseRecyclerHolder holder, int position) {
    MatchHolder hold = (MatchHolder) holder;
    int[] colors = { hold.darkGreen, hold.darkGrey, hold.darkBlue, hold.darkRed };

    if (isFavorite) {
      hold.matchNotify.setVisibility(View.GONE);
    } else {
      hold.matchNotify.setVisibility(View.VISIBLE);
    }
    RecyclerConfigs.positionColoringFourItems(hold.matchLayout, colors, position);
    RecyclerConfigs.slidingAdapterAnimation(hold.itemView.getContext(), hold.itemView,
        position, -1);
    hold.competitionName.setText(getItemAtPosition(position).getCompetition().getCompetitonName());
    hold.matchDate.setText(AppUtils.transformTime(getItemAtPosition(position).getTime()) + " CLT");
    hold.matchDay.setText(
        AppUtils.matchType(hold.itemView.getContext(), getItemAtPosition(position).getStage()) +
            getItemAtPosition(position).getMatchDay());
    hold.tournamentFlag.setBackgroundResource(AppUtils.getChampionshipFlag(
        getItemAtPosition(position).getCompetition().getCompetitonName()));
    hold.awayTeamName.setText(
        getItemAtPosition(position).getAwayTeam().getName());

    hold.homeTeamName.setText(
        getItemAtPosition(position).getHomeTeam().getName());
    if (getItemAtPosition(position).getStatus().equalsIgnoreCase("SCHEDULED")) {
      hold.homeTeanScore.setText("--");
      hold.awayTeamScore.setText("--");
      hold.postponed.setVisibility(View.INVISIBLE);
    } else if (getItemAtPosition(position).getStatus().equalsIgnoreCase("POSTPONED")) {
      hold.postponed.setVisibility(View.VISIBLE);
      hold.homeTeanScore.setText("--");
      hold.awayTeamScore.setText("--");
    } else {
      hold.awayTeamScore.setText(String.valueOf(getItemAtPosition(position)
          .getScore()
          .getFinalScore()
          .getAwayTeam()));
      hold.homeTeanScore.setText(
          String.valueOf(getItemAtPosition(position).getScore().getFinalScore().getHomeScore()));
      hold.postponed.setVisibility(View.INVISIBLE);
    }
  }

  class MatchHolder extends BaseRecyclerHolder {

    @BindView(R.id.match_date) TextView matchDate;
    @BindView(R.id.competiton_name) TextView competitionName;
    @BindView(R.id.match_day) TextView matchDay;
    @BindView(R.id.away_team_name) TextView awayTeamName;
    @BindView(R.id.away_team_score) TextView awayTeamScore;
    @BindView(R.id.home_team_name) TextView homeTeamName;
    @BindView(R.id.home_team_score) TextView homeTeanScore;
    @BindView(R.id.tournament_flag) ImageView tournamentFlag;
    @BindView(R.id.match_container) ConstraintLayout matchLayout;
    @BindView(R.id.match_notify) ImageView matchNotify;
    @BindView(R.id.post_match) TextView postponed;

    @BindColor(R.color.darkGrey) int darkGrey;
    @BindColor(R.color.darkRed) int darkRed;
    @BindColor(R.color.darkBlue) int darkBlue;
    @BindColor(R.color.darkGreen) int darkGreen;

    @BindDrawable(R.drawable.white_bell) Drawable whiteBell;
    @BindDrawable(R.drawable.gold_bell) Drawable goldBell;

    MatchHolder(View itemView) {
      super(itemView);
    }

    @RequiresApi(api = Build.VERSION_CODES.N) @OnClick({ R.id.match_notify })
    public void onClick(View view) {
      switch (view.getId()) {
        case R.id.match_notify:
          clicked.onFavoriteMatchClicked(
              AppUtils.transformTime(getItemAtPosition(getLayoutPosition()).getTime()));
          break;
      }
    }
  }
}
