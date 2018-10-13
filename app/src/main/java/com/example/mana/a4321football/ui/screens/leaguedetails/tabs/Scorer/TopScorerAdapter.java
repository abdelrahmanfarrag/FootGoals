package com.example.mana.a4321football.ui.screens.leaguedetails.tabs.Scorer;

import android.annotation.SuppressLint;
import android.support.annotation.NonNull;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import butterknife.BindColor;
import butterknife.BindString;
import butterknife.BindView;
import com.ahmadrosid.svgloader.SvgLoader;
import com.bumptech.glide.Glide;
import com.example.mana.a4321football.R;
import com.example.mana.a4321football.data.eventbus.EmblemsBus;
import com.example.mana.a4321football.data.eventbus.LeagueBus;
import com.example.mana.a4321football.data.model.Scorer;
import com.example.mana.a4321football.ui.base.BaseRecyclerAdapter;
import com.example.mana.a4321football.ui.base.BaseRecyclerHolder;
import com.example.mana.a4321football.ui.screens.leaguedetails.tabs.Standings.StandingAdapter;
import com.example.mana.a4321football.utility.Constants;
import java.util.List;

public class TopScorerAdapter extends BaseRecyclerAdapter<Scorer.Scorers, BaseRecyclerHolder> {

  TopScorerAdapter(
      List<Scorer.Scorers> recyclerItems) {
    super(recyclerItems);
  }

  @NonNull @Override
  public BaseRecyclerHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
    View v = setRecyclerItemLayout(parent.getContext()).inflate(R.layout.single_scorer_item, parent,
        false);
    return new ScorerHolder(v);
  }

  @SuppressLint("SetTextI18n") @Override
  public void onBindViewHolder(@NonNull BaseRecyclerHolder holder, int position) {
    ScorerHolder hold = (ScorerHolder) holder;
    int pos = hold.getLayoutPosition();
    String teamName = getItemAtPosition(pos).getTeamInfo().getName();
    String emblemUrl = EmblemsBus.getEmblems().get(teamName);

    hold.scorerPosiition.setText(String.valueOf(pos + 1));
    hold.name.setText(getItemAtPosition(pos).getInfo().getName());
    hold.team.setText(getItemAtPosition(pos).getTeamInfo().getName());
    hold.textNumber.setText(String.valueOf(getItemAtPosition(pos).getInfo().getNumber()));
    hold.shirtNumber.setText(String.valueOf(getItemAtPosition(pos).getInfo().getNumber()));
    hold.goalsScored.setText(hold.scored + String.valueOf(getItemAtPosition(pos).getGoals()));
    if (!TextUtils.isEmpty(EmblemsBus.getEmblems().get(teamName))) {
      if (LeagueBus.getLeagueNAME().equalsIgnoreCase("Ligue 1")) {
        Glide.with(hold.itemView.getContext()).load(emblemUrl)
            .placeholder(R.drawable.cup)
            .into(hold.emblem);
      } else {
        SvgLoader.pluck()
            .with(StandingAdapter.getActivity())
            .load(emblemUrl, hold.emblem);
      }
    }

    String playablePosition = getItemAtPosition(pos).getInfo().getPosition();
    hold.position.setText(playablePosition);
    if (playablePosition.equalsIgnoreCase(Constants.ATTACKER)) {
      hold.position.setTextColor(hold.red);
    } else if (playablePosition.equalsIgnoreCase(Constants.MIDFIELDER)) {
      hold.position.setTextColor(hold.green);
    } else {
      hold.position.setTextColor(hold.blue);
    }
  }

  class ScorerHolder extends BaseRecyclerHolder {

    @BindView(R.id.player_emblem) ImageView emblem;
    @BindView(R.id.top_scorer_name) TextView name;
    @BindView(R.id.top_scorer_team) TextView team;
    @BindView(R.id.top_scorer_number) TextView textNumber;
    @BindView(R.id.player_goals_scored) TextView goalsScored;
    @BindView(R.id.player_shirt_num) TextView shirtNumber;
    @BindView(R.id.player_position) TextView position;
    @BindView(R.id.scorer_pos) TextView scorerPosiition;

    @BindString(R.string.scored) String scored;

    @BindColor(R.color.lightRed) int red;
    @BindColor(R.color.darkGreen) int green;
    @BindColor(R.color.darkBlue) int blue;

    ScorerHolder(View itemView) {
      super(itemView);
    }
  }
}
