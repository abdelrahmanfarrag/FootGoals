package com.example.mana.a4321football.ui.screens.mainscreen.screenContents.Leagues;

import android.graphics.drawable.Drawable;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import butterknife.BindView;
import butterknife.OnClick;
import com.example.mana.a4321football.R;
import com.example.mana.a4321football.ui.base.BaseRecyclerAdapter;
import com.example.mana.a4321football.ui.base.BaseRecyclerHolder;
import com.example.mana.a4321football.ui.screens.leaguedetails.news.NewsTab;
import com.example.mana.a4321football.ui.screens.mainscreen.MainScreenActivity;
import com.example.mana.a4321football.utility.AppUtils;
import com.example.mana.a4321football.utility.Constants;
import com.example.mana.a4321football.utility.FragmentManagement;
import com.example.mana.a4321football.utility.ToastMessages;
import java.util.List;

public class LeaguesAdapter extends BaseRecyclerAdapter<Object, BaseRecyclerHolder> {

  private static final int LEAGUE_TITLE = 0;
  private static final int LEAGUE_SPACE = 1;

  private List<Drawable> icons;
  private LeagueId id;

  LeaguesAdapter(List<Object> recyclerItems, List<Drawable> icons, LeagueId id) {
    super(recyclerItems);
    this.icons = icons;
    this.id = id;
  }

  @NonNull @Override
  public BaseRecyclerHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
    LayoutInflater inflate = setRecyclerItemLayout(parent.getContext());
    View item;
    switch (viewType) {
      default:
        item = inflate.inflate(R.layout.more_leagues_item, parent, false);
        return new NoTitleHolder(item);
      case LEAGUE_TITLE:
        item = inflate.inflate(R.layout.single_league_adapter_item, parent, false);
        return new LeagueTitleHolder(item);
    }
  }

  @Override public void onBindViewHolder(@NonNull BaseRecyclerHolder holder, int position) {
    if (getItemViewType(position) == LEAGUE_TITLE) {
      LeagueTitleHolder hold = (LeagueTitleHolder) holder;
      hold.leagueIcon.setImageDrawable(icons.get(position));
      hold.leagueName.setText((String) getItemAtPosition(position));
      if (position == 15) {
        hold.nextArrow.setVisibility(View.INVISIBLE);
      }
    }
  }

  @Override public int getItemViewType(int position) {
    if (getItemAtPosition(position) instanceof String) {
      return LEAGUE_TITLE;
    } else {
      return LEAGUE_SPACE;
    }
  }

  public class LeagueTitleHolder extends BaseRecyclerHolder {

    @BindView(R.id.league_name) TextView leagueName;
    @BindView(R.id.league_icon) ImageView leagueIcon;
    @BindView(R.id.next_league_arrow) ImageView nextArrow;

    private LeagueTitleHolder(View itemView) {
      super(itemView);
    }

    @OnClick
    public void onItemClicked() {
      if (getLayoutPosition() == 15) {
        AppUtils.changeLanguage(itemView.getContext(), MainScreenActivity.class);
      } else if (getLayoutPosition() == 14) {
        FragmentManagement.showFragmentOnFragment(LeaguesFragment.getInstance(),
            NewsTab.getInstance(), R.id.leagues_container, false);
      } else {
        id.leagueId(returnLeagueId(), (String) getItemAtPosition(getLayoutPosition()));
      }
    }

    private String returnLeagueId() {
      switch (getLayoutPosition()) {
        case 0:
          return Constants.CHAMPIONS_LEAGUE;
        case 2:
          return Constants.PREMIER_LEAGUE;
        case 3:
          return Constants.SKYPET_CHAMPIONSHIP;
        case 5:
          return Constants.LA_LEAGUE;
        case 6:
          return Constants.LIGUE_1;
        case 7:
          return Constants.SERIA_A;
        case 9:
          return Constants.PORTUGAL_LEAGUE;
        case 10:
          return Constants.BUNDES_LEAGUE;
        case 11:
          return Constants.HOLLAND_LEAGUE;
        case 12:
          return Constants.BRAZIL_LEAGUE;
        case 15:
          return Constants.NEWS_LIST;
        default:
          return Constants.MATCHES;
      }
    }
  }

  private class NoTitleHolder extends BaseRecyclerHolder {

    private NoTitleHolder(View itemView) {
      super(itemView);
    }
  }
}
