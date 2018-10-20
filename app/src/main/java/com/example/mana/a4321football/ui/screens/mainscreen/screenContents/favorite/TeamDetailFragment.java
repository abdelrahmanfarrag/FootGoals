package com.example.mana.a4321football.ui.screens.mainscreen.screenContents.favorite;

import android.widget.TextView;
import butterknife.BindView;
import com.example.mana.a4321football.R;
import com.example.mana.a4321football.data.eventbus.TeamDetail;
import com.example.mana.a4321football.ui.base.BaseFragment;
import com.example.mana.a4321football.utility.ToastMessages;
import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

public class TeamDetailFragment extends BaseFragment {

  @BindView(R.id.rest_psp) TextView resp;

  public static TeamDetailFragment getInstance() {
    return new TeamDetailFragment();
  }

  @Override public int fragmentLayout() {
    return R.layout.fragment_fav_team_details;
  }

  @Override public void onStart() {
    super.onStart();
    EventBus.getDefault().register(this);
  }

  @Override public void onStop() {
    super.onStop();
    EventBus.getDefault().unregister(this);
  }

  @Subscribe(threadMode = ThreadMode.MAIN)
  public void subscribeToTeamDetailBus(TeamDetail detail) {
    ToastMessages.ShortToastMessage(getContext(), detail
        .getName());
    resp.setText(detail.getName());
  }

  @Override public void init() {

  }
}
