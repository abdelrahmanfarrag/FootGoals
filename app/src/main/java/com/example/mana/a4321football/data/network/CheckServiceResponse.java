package com.example.mana.a4321football.data.network;

import android.content.Context;
import com.example.mana.a4321football.utility.ToastMessages;
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
      ToastMessages.ShortToastMessage(context,"Error ya3mna");
    }
  }
}
