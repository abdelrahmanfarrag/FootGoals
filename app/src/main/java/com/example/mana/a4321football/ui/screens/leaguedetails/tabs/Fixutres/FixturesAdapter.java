package com.example.mana.a4321football.ui.screens.leaguedetails.tabs.Fixutres;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.annotation.RequiresApi;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import butterknife.BindString;
import butterknife.BindView;
import com.ahmadrosid.svgloader.SvgLoader;
import com.bumptech.glide.Glide;
import com.example.mana.a4321football.R;
import com.example.mana.a4321football.data.eventbus.EmblemsBus;
import com.example.mana.a4321football.data.eventbus.LeagueBus;
import com.example.mana.a4321football.data.model.Fixture;
import com.example.mana.a4321football.ui.base.BaseRecyclerAdapter;
import com.example.mana.a4321football.ui.base.BaseRecyclerHolder;
import com.example.mana.a4321football.ui.screens.leaguedetails.tabs.Standings.StandingAdapter;
import com.example.mana.a4321football.utility.AppUtils;
import com.example.mana.a4321football.utility.ImageSettings;
import com.example.mana.a4321football.utility.ToastMessages;
import java.util.List;

public class FixturesAdapter extends BaseRecyclerAdapter<Fixture.Matches, BaseRecyclerHolder> {
  public FixturesAdapter(
      List<Fixture.Matches> recyclerItems) {
    super(recyclerItems);
  }

  @NonNull @Override
  public BaseRecyclerHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
    View v =
        setRecyclerItemLayout(parent.getContext()).inflate(R.layout.single_fixture_match, parent,
            false);
    return new FixtureHolder(v);
  }

  @SuppressLint("SetTextI18n") @RequiresApi(api = Build.VERSION_CODES.N) @Override
  public void onBindViewHolder(@NonNull BaseRecyclerHolder holder, int position) {
    FixtureHolder fixture = (FixtureHolder) holder;
    int layoutPos = fixture.getLayoutPosition();

    String homeTeamName = getItemAtPosition(layoutPos).getHome().getName();
    String awayTeamName = getItemAtPosition(layoutPos).getAway().getName();
    if (EmblemsBus.getEmblems() == null && EmblemsBus.getEmblems() == null) {
      ToastMessages.ShortToastMessage(fixture.itemView.getContext(), "URL IS NULL !!!!");
    } else {
      String homeUrl = EmblemsBus.getEmblems().get(homeTeamName);
      String awayUrl = EmblemsBus.getEmblems().get(awayTeamName);
      ImageSettings.settingImage(homeUrl, fixture.itemView.getContext(), fixture.homeImg);
      ImageSettings.settingImage(awayUrl, fixture.itemView.getContext(), fixture.awayImg);
    }

    if (getItemAtPosition(layoutPos).getStatus().equalsIgnoreCase("SCHEDULED")) {
      fixture.homeScore.setText("?");
      fixture.awayScre.setText("?");
    } else {
      fixture.homeScore.setText(
          String.valueOf(getItemAtPosition(layoutPos).getScore().getScore().getHomeScore()));
      fixture.awayScre.setText(
          String.valueOf(getItemAtPosition(layoutPos).getScore().getScore().getAwayScore()));
    }

    fixture.homeName.setText(homeTeamName);
    fixture.awayName.setText(awayTeamName);

    fixture.fixtureDate.setText(
        AppUtils.transformDate(getItemAtPosition(layoutPos).getMatchTime()));
    fixture.fixtureTime.setText(
        AppUtils.transformTime(getItemAtPosition(layoutPos).getMatchTime()));

    fixture.day.setText(fixture.gameweek + " " + getItemAtPosition(layoutPos).getMatchDay());
  }

  class FixtureHolder extends BaseRecyclerHolder {

    @BindView(R.id.fixture_home_name) TextView homeName;
    @BindView(R.id.fixture_home_img) ImageView homeImg;
    @BindView(R.id.fixture_home_score) TextView homeScore;
    @BindView(R.id.fixture_away_name) TextView awayName;
    @BindView(R.id.fixture_away_score) TextView awayScre;
    @BindView(R.id.fixture_away_img) ImageView awayImg;
    @BindView(R.id.fixture_date) TextView fixtureDate;
    @BindView(R.id.fixture_time) TextView fixtureTime;
    @BindView(R.id.fixure_day) TextView day;

    @BindString(R.string.gameweek) String gameweek;

    FixtureHolder(View itemView) {
      super(itemView);
    }
  }
}
