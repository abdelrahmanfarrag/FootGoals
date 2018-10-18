package com.example.mana.a4321football.utility;

import android.content.Context;
import android.text.TextUtils;
import android.widget.ImageView;
import com.ahmadrosid.svgloader.SvgLoader;
import com.bumptech.glide.Glide;
import com.example.mana.a4321football.data.eventbus.LeagueBus;
import com.example.mana.a4321football.ui.screens.leaguedetails.tabs.Standings.StandingAdapter;

public class ImageSettings {

  public static void settingImage(String team, Context context, ImageView target) {
    if (!TextUtils.isEmpty(team)) {
      if (team.contains(".svg")) {
        if (LeagueBus.getLeagueNAME().equalsIgnoreCase("Ligue 1")) {
          Glide.with(StandingAdapter.getActivity())
              .load(team)
              .into(target);
          Glide.with(StandingAdapter.getActivity())
              .load(team)
              .into(target);
        } else {
          SvgLoader.pluck()
              .with(StandingAdapter.getActivity())
              .load(team, target);
          SvgLoader.pluck()
              .with(StandingAdapter.getActivity())
              .load(team, target);
        }
      } else {
        Glide.with(StandingAdapter.getActivity())
            .load(team)
            .into(target);
        Glide.with(StandingAdapter.getActivity())
            .load(team)
            .into(target);
      }
    }
  }
}
