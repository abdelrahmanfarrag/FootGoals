package com.example.mana.a4321football.data.network;

import android.app.Dialog;
import android.content.Context;
import android.view.ViewGroup;
import com.example.mana.a4321football.R;
import com.example.mana.a4321football.utility.Dialogs;
import com.example.mana.a4321football.utility.ToastMessages;
import java.util.Objects;
import retrofit2.Response;

public class CheckServiceResponse {
  private RetrofitServices.transformServiceResponse reponse;
  private Context context;

  CheckServiceResponse(RetrofitServices.transformServiceResponse reponse, Context context) {
    this.context = context;
    this.reponse = reponse;
  }

  public void getServiceResponse(Response<?> serviceResponse) {
    Object model = serviceResponse.body();
    if (model !=null){
      reponse.responseReceived(model);}
    else {
      checkNetworkAvailability();
    }
  }

  private void checkNetworkAvailability() {
    Dialog dialog = Dialogs.transparentDialog(context, R.layout.dialog, R.style.wide_dialog);
    Objects.requireNonNull(dialog.getWindow())
        .setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
    dialog.show();
  }
}
