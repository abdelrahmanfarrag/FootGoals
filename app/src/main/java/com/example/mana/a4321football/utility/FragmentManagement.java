package com.example.mana.a4321football.utility;

import android.app.Activity;
import android.support.annotation.IdRes;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;

public class FragmentManagement {
  public static void showFragmentOnActivity(Activity activity, Fragment fragment,
      @IdRes int container, boolean replace) {
    if (activity == null || fragment == null) return;
    //animateContainer(activity, null, container, true);
    FragmentManager manager = ((FragmentActivity) activity).getSupportFragmentManager();
    FragmentTransaction transaction = manager.beginTransaction();
    transaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
    if (replace) {
      transaction.replace(container, fragment).commit();
    } else {
      transaction.addToBackStack(null);
      manager.executePendingTransactions();
    }
  }

  public static void showFragmentOnFragment(Fragment host, Fragment guest,
      @IdRes int container, boolean replace, boolean animate) {
    if (host == null || guest == null) return;
    //animateContainer(null, host, container, animate);
    if (host.getActivity() == null) return;
    FragmentManager manager = host.getChildFragmentManager();
    FragmentTransaction transaction = manager.beginTransaction();
    transaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
    if (replace) {
      transaction.replace(container, guest).commit();
    } else {
      transaction.addToBackStack(null);
      transaction.add(container, guest).commit();
    }
  }
}
