package com.example.mana.a4321football.data.network;

import android.content.Context;
import retrofit2.Response;

public class CheckServiceResponse {
  private RetrofitServices.transformServiceResponse reponse;
  Context context;

  public CheckServiceResponse(RetrofitServices.transformServiceResponse reponse, Context context) {
    this.context = context;
    this.reponse = reponse;
  }

  public void getServiceResponse(Response<?> serviceResponse) {
    Object model = serviceResponse.body();
    reponse.responseReceived(model);
  }
}
