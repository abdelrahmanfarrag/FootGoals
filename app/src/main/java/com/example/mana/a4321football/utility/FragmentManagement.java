package com.example.mana.a4321football.utility;

import android.app.Activity;
import android.support.annotation.IdRes;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;

public class FragmentManagement {
  public static void showFragmentOnActivity(Activity hostActivity, Fragment showingFragment,
      @IdRes int container, boolean replace) {
    FragmentManager fragmentManager = ((FragmentActivity) hostActivity).getSupportFragmentManager();
    FragmentTransaction transaction = fragmentManager.beginTransaction();
    transaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
    if (replace) {
      transaction.replace(container, showingFragment).commit();
    } else {
      transaction.addToBackStack(null);
      transaction.add(container, showingFragment).commit();
    }
  }

  public static void showFragmentOnFragment(Fragment hostFragment, Fragment guestFragment,
      @IdRes int container, boolean replace) {
    FragmentManager manager = hostFragment.getChildFragmentManager();
    FragmentTransaction transaction = manager.beginTransaction();
    transaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
    if (replace) {
      transaction.replace(container, guestFragment).commit();
    } else {
      transaction.addToBackStack(null);
      transaction.add(container, guestFragment).commit();
    }
  }
}
