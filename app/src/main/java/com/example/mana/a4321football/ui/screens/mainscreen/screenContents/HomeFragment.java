package com.example.mana.a4321football.ui.screens.mainscreen.screenContents;

import android.util.Log;
import android.widget.ImageView;
import butterknife.BindView;
import com.example.mana.a4321football.R;
import com.example.mana.a4321football.ui.base.BaseFragment;
import com.example.mana.a4321football.utility.PicassoConfigs;

public class HomeFragment extends BaseFragment {
  @BindView(R.id.test_img) ImageView testImage;

  public static HomeFragment getInstance() {
    return new HomeFragment();
  }

  @Override public int fragmentLayout() {
    return R.layout.home_fragment;
  }

  @Override public void init() {

    try {
      try {
        PicassoConfigs.picassoInstance(getContext())
            .load("https://upload.wikimedia.org/wikipedia/de/5/56/Newcastle_United_Logo.svg")
            .fit()
            .into(testImage);
      } catch (Exception e) {
        e.printStackTrace();
      }
    } catch (Exception e) {
      Log.i("picasso_msg", e.toString());
    }
  }
}
