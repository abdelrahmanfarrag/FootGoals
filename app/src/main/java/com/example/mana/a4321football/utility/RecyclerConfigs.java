package com.example.mana.a4321football.utility;

import android.content.Context;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;

public class RecyclerConfigs {
  public static void setupRecyclerSettings(RecyclerView view, Context context, int orientation,
      int dividerDecoration) {
    view.setHasFixedSize(true);
    view.setLayoutManager(new LinearLayoutManager(context, orientation, false));
    view.addItemDecoration(
        new DividerItemDecoration(context, dividerDecoration));
  }

  public static void slidingAdapterAnimation(Context context, View animated, int index, int layoutPos) {
    if (index > layoutPos) {
      Animation animation = AnimationUtils.loadAnimation(context, android.R.anim.slide_in_left);
      animated.startAnimation(animation);
      index = layoutPos;
    }
  }

  public static void fadeAdapterAnimation(Context context, View animated, int index,
      int layoutPos) {
    if (index > layoutPos) {
      Animation animation = AnimationUtils.loadAnimation(context, android.R.anim.fade_in);
      animated.startAnimation(animation);
      index = layoutPos;
    }
  }
  public static void positionColoringFourItems(View view,int[] colors,int position){
    if (position % 4 == 0) {
      view.setBackgroundColor(colors[0]);
    } else if (position % 4 == 1) {
      view.setBackgroundColor(colors[1]);
    } else if (position % 4 == 2) {
      view.setBackgroundColor(colors[2]);
    } else {
      view.setBackgroundColor(colors[3]);
    }
  }
}
