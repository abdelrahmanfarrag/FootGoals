package com.example.mana.a4321football.utility;

import android.app.Dialog;
import android.content.Context;
import android.support.annotation.LayoutRes;

public class Dialogs {

  public static Dialog transparentDialog(Context context, @LayoutRes int layoutId, int style) {
    final Dialog transDialog = new Dialog(context, style);
    transDialog.setContentView(layoutId);
    transDialog.setCancelable(true);
    transDialog.setCanceledOnTouchOutside(true);
    return transDialog;
  }
}
