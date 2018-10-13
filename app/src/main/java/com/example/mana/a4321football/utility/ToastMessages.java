package com.example.mana.a4321football.utility;

import android.content.Context;
import android.widget.Toast;

public class ToastMessages {
  public static void LongToastMessage(Context context,String message){
    Toast.makeText(context,message,Toast.LENGTH_LONG).show();
  }
  public static void ShortToastMessage(Context context,String message){
    Toast.makeText(context,message,Toast.LENGTH_SHORT).show();
  }

}
