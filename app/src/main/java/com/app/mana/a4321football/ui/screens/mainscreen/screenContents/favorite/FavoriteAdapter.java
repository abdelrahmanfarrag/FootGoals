package com.app.mana.a4321football.ui.screens.mainscreen.screenContents.favorite;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.support.annotation.NonNull;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import butterknife.BindView;
import butterknife.OnClick;
import butterknife.OnLongClick;
import com.app.mana.a4321football.R;
import com.app.mana.a4321football.data.database.Favorite;
import com.app.mana.a4321football.data.eventbus.TeamDetail;
import com.app.mana.a4321football.ui.base.BaseRecyclerAdapter;
import com.app.mana.a4321football.ui.base.BaseRecyclerHolder;
import com.app.mana.a4321football.utility.ImageSettings;
import java.util.List;
import org.greenrobot.eventbus.EventBus;

public class FavoriteAdapter extends BaseRecyclerAdapter<Favorite, BaseRecyclerHolder> {

  private presenterResponse response;
  @SuppressLint("StaticFieldLeak") private static Activity activity;

  FavoriteAdapter(
      List<Favorite> recyclerItems, presenterResponse response) {
    super(recyclerItems);
    this.response = response;
  }

  public presenterResponse getResponse() {
    return response;
  }

  public void setResponse(
      presenterResponse response) {
    this.response = response;
  }

  private static Activity getActivity() {
    return activity;
  }

  public static void setActivity(Activity activity) {
    FavoriteAdapter.activity = activity;
  }

  @NonNull @Override
  public BaseRecyclerHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
    View v =
        setRecyclerItemLayout(parent.getContext()).inflate(R.layout.single_fav_team, parent, false);
    return new FavoriteHolder(v);
  }

  @Override public void onBindViewHolder(@NonNull BaseRecyclerHolder holder, int position) {
    FavoriteHolder hold = (FavoriteHolder) holder;
    int pos = hold.getLayoutPosition();
    String imgUrl = getItemAtPosition(pos).getCrestUrl();
    hold.favoriteName.setText(getItemAtPosition(pos).getTeamName());
    ImageSettings.favoriteImage(hold.itemView.getContext(), hold.teamCrest, getActivity(), imgUrl);
  }

  class FavoriteHolder extends BaseRecyclerHolder {

    @BindView(R.id.fav_team_crest) ImageView teamCrest;
    @BindView(R.id.fav_team_name) TextView favoriteName;

    FavoriteHolder(View itemView) {
      super(itemView);
    }

    @OnClick()
    public void OnClick() {
      EventBus.getDefault()
          .post(new TeamDetail(getItemAtPosition(getLayoutPosition()).getTeamId(),
              getItemAtPosition(getLayoutPosition()).getTeamName(),
              getItemAtPosition(getLayoutPosition()).getCrestUrl()));
    }

    @OnLongClick()
    public boolean onLongClick() {
      response.teamData(getItemAtPosition(getLayoutPosition()).getTeamId());

      return true;
    }
  }
}
