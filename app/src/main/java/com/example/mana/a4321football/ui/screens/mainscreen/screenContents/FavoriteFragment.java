package com.example.mana.a4321football.ui.screens.mainscreen.screenContents;

import com.example.mana.a4321football.R;
import com.example.mana.a4321football.ui.base.BaseFragment;

public class FavoriteFragment extends BaseFragment {

  public static FavoriteFragment getInstance() {
    return new FavoriteFragment();
  }

  @Override public int fragmentLayout() {
    return R.layout.favorite_fragment;
  }

  @Override public void init() {

  }
}
