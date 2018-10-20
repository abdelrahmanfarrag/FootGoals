package com.example.mana.a4321football.ui.screens.splashscreen;

import android.os.Bundle;
import com.example.mana.a4321football.R;
import com.example.mana.a4321football.ui.base.BaseActivity;
import com.example.mana.a4321football.ui.screens.mainscreen.MainScreenActivity;
import com.example.mana.a4321football.utility.Intents;

public class SplashScreen extends BaseActivity {

  @Override public int activityLayoutId() {
    return R.layout.activity_main;
  }

  @Override public void instantiateView(Bundle bundle) {
    Intents.delayedIntent(getContext(),MainScreenActivity.class,1000);

  }


  @Override public void activityToolbar() {

  }


}
