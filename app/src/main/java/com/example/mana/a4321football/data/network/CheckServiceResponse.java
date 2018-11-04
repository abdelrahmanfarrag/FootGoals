package com.example.mana.a4321football.data.network;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
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
    if (model != null) {
      reponse.responseReceived(model);
    } else {
      checkNetworkAvailability();
    }
  }

  private void checkNetworkAvailability() {
    Dialog dialog = Dialogs.transparentDialog(context, R.layout.no_response_api,
        R.style.Theme_Design_NoActionBar);
    Objects.requireNonNull(dialog.getWindow())
        .setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
    Button b = dialog.findViewById(R.id.no_con_retry_btn);
    b.setOnClickListener(v -> dialog.dismiss());
    dialog.show();
  }
}
