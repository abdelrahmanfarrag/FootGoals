package com.example.mana.a4321football.ui.screens.leaguedetails.tabs.Teams;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import butterknife.BindView;
import butterknife.OnClick;
import com.example.mana.a4321football.R;
import com.example.mana.a4321football.data.eventbus.EmblemsBus;
import com.example.mana.a4321football.data.model.LeagueTeams;
import com.example.mana.a4321football.ui.base.BaseRecyclerAdapter;
import com.example.mana.a4321football.ui.base.BaseRecyclerHolder;
import com.example.mana.a4321football.utility.ImageSettings;
import java.util.List;

public class TeamsAdapter extends BaseRecyclerAdapter<LeagueTeams.Teams, BaseRecyclerHolder> {

  private TeamDetails details;

  public TeamsAdapter(
      List<LeagueTeams.Teams> recyclerItems, TeamDetails details) {
    super(recyclerItems);
    this.details = details;
  }

  @NonNull @Override
  public BaseRecyclerHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
    View v = setRecyclerItemLayout(parent.getContext()).inflate(R.layout.single_league_team, parent,
        false);
    return new TeamsHolder(v);
  }

  @Override public void onBindViewHolder(@NonNull BaseRecyclerHolder holder, int position) {
    TeamsHolder hold = (TeamsHolder) holder;
    int pos = hold.getLayoutPosition();
    Context context = hold.itemView.getContext();
    String imgUrl = EmblemsBus.getEmblems().get(getItemAtPosition(pos).getName());
    ImageSettings.settingImage(imgUrl, context, hold.emblem);
    hold.name.setText(getItemAtPosition(pos).getName());
  }

  class TeamsHolder extends BaseRecyclerHolder {
    @BindView(R.id.team_emblem) ImageView emblem;
    @BindView(R.id.league_team_name) TextView name;

    public TeamsHolder(View itemView) {
      super(itemView);
    }

    @OnClick()
    public void onTeamClicked() {
      details.teamsInfo(getItemAtPosition(getLayoutPosition()).getId(),
          getItemAtPosition(getLayoutPosition()).getName(),getItemAtPosition(getLayoutPosition()).getUrl());
    }
  }
}
