package com.example.mana.a4321football.ui.screens.leaguedetails.tabs.Teams.team_info;

import android.annotation.SuppressLint;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.annotation.RequiresApi;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import butterknife.BindColor;
import butterknife.BindDrawable;
import butterknife.BindView;
import com.example.mana.a4321football.R;
import com.example.mana.a4321football.data.model.Teams;
import com.example.mana.a4321football.ui.base.BaseRecyclerAdapter;
import com.example.mana.a4321football.ui.base.BaseRecyclerHolder;
import com.example.mana.a4321football.utility.AppUtils;
import java.util.List;

public class TeamInfoAdapter extends BaseRecyclerAdapter<Teams.Squad, BaseRecyclerHolder> {
  TeamInfoAdapter(
      List<Teams.Squad> recyclerItems) {
    super(recyclerItems);
  }

  @NonNull @Override
  public BaseRecyclerHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
    View v = setRecyclerItemLayout(parent.getContext()).inflate(R.layout.single_team_info, parent,
        false);
    return new TeamHolder(v);
  }

  @RequiresApi(api = Build.VERSION_CODES.N) @SuppressLint("SetTextI18n") @Override
  public void onBindViewHolder(@NonNull BaseRecyclerHolder holder, int position) {
    TeamHolder hold = (TeamHolder) holder;
    int pos = hold.getLayoutPosition();
    hold.secondaryName.setText(getItemAtPosition(pos).getName());
    hold.playerName.setText(getItemAtPosition(pos).getName());
    if (getItemAtPosition(pos).getDob() != null) {
      hold.dob.setText(AppUtils.transformDate(getItemAtPosition(pos).getDob()));
    }
    hold.nation.setText(getItemAtPosition(pos).getNationality());
    if (getItemAtPosition(pos).getNumber() > 0) {
      hold.number.setText(String.valueOf(getItemAtPosition(pos).getNumber()));
    } else {
      hold.number.setText("");
    }
    if (getItemAtPosition(pos).getRole().equalsIgnoreCase("PLAYER")) {
      hold.positionImg.setBackground(hold.player);
      settingViews(hold.position, hold.posImg, getItemAtPosition(pos).getPosition(), hold);
    } else {
      hold.positionImg.setBackground(hold.coach);
      hold.posImg.setBackgroundColor(hold.red);
      hold.position.setTextColor(hold.white);
      hold.position.setText("CO");
    }

    if (getItemAtPosition(pos).getPosition() != null) {
      hold.position.setText(getItemAtPosition(pos).getPosition());
      hold.position.setText(
          playerPosition(getItemAtPosition(pos).getPosition(), hold.positionImg, hold.gk));
    } else {
      hold.positionImg.setBackground(hold.coach);
      hold.posImg.setBackgroundColor(hold.red);
      hold.position.setTextColor(hold.white);
      hold.position.setText("CO");
    }
  }

  private void settingViews(TextView positionColor, ImageView background, String position,
      TeamHolder holder) {
    switch (position) {
      case "Defender":
        positionColor.setTextColor(holder.white);
        background.setBackgroundColor(holder.blue);
        break;
      case "Attacker":
        positionColor.setTextColor(holder.black);
        background.setBackgroundColor(holder.red);
        break;
      case "Goalkeeper":
        positionColor.setTextColor(holder.white);
        background.setBackgroundColor(holder.orange);
        break;
      case "Midfielder":
        positionColor.setTextColor(holder.white);
        background.setBackgroundColor(holder.green);
        break;
    }
  }

  private String playerPosition(String position, ImageView gkImg, Drawable img) {
    switch (position) {
      case "Defender":
        return "DF";
      case "Attacker":
        return "FW";
      case "Goalkeeper":
        gkImg.setBackground(img);
        return "GK";
      case "Midfielder":
        return "MF";
      default:
        return "CW";
    }
  }

  class TeamHolder extends BaseRecyclerHolder {

    @BindView(R.id.second_player_name_info) TextView secondaryName;
    @BindView(R.id.player_info_name) TextView playerName;
    @BindView(R.id.player_info_dob) TextView dob;
    @BindView(R.id.player_info_nation) TextView nation;
    @BindView(R.id.player_info_pos) TextView position;
    @BindView(R.id.imageView2) ImageView positionImg;
    @BindView(R.id.info_player_num) TextView number;
    @BindView(R.id.imageView4) ImageView posImg;

    @BindColor(R.color.blackColor) int black;
    @BindColor(R.color.darkRed) int red;
    @BindColor(R.color.green) int green;
    @BindColor(R.color.colorPrimary) int blue;
    @BindColor(R.color.orangeColor) int orange;
    @BindColor(R.color.whiteColor) int white;

    @BindDrawable(R.drawable.coach) Drawable coach;
    @BindDrawable(R.drawable.player) Drawable player;
    @BindDrawable(R.drawable.gk) Drawable gk;

    TeamHolder(View itemView) {
      super(itemView);
    }
  }
}
