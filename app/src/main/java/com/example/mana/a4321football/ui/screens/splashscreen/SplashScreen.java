package com.example.mana.a4321football.ui.screens.splashscreen;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;
import butterknife.BindView;
import com.example.mana.a4321football.R;
import com.example.mana.a4321football.data.model.Matches;
import com.example.mana.a4321football.data.model.Test;
import com.example.mana.a4321football.data.network.RetrofitServices;
import com.example.mana.a4321football.ui.base.BaseActivity;
import com.example.mana.a4321football.ui.screens.mainscreen.MainScreenActivity;
import com.example.mana.a4321football.utility.Constants;
import com.example.mana.a4321football.utility.Intents;
import com.example.mana.a4321football.utility.ToastMessages;

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
