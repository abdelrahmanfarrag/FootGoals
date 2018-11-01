package com.example.mana.a4321football.data.network;

import android.app.Dialog;
import android.content.Context;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import com.example.mana.a4321football.R;
import com.example.mana.a4321football.utility.AppUtils;
import com.example.mana.a4321football.utility.Dialogs;
import com.pnikosis.materialishprogress.ProgressWheel;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import java.util.Map;
import java.util.Objects;
import retrofit2.Response;

public class RetrofitServices {
  private Context context;
  private CompositeDisposable disposables;
  private CheckServiceResponse validator;
  private RetrofitApi api;
  private RetrofitApi newsApi = new RetrofitBuilder().getNewsApi();

  public RetrofitServices(Context context, CompositeDisposable disposables,
      transformServiceResponse reponse) {
    this.context = context;
    this.disposables = disposables;
    validator = new CheckServiceResponse(reponse, context);
    api = new RetrofitBuilder().getApi(context);
  }

  public void getLeagueDetails(ProgressWheel wheel, String id) {
    Disposable disposable = api.getLeagueData(id)
        .subscribeOn(Schedulers.io())
        .observeOn(AndroidSchedulers.mainThread())
        .doOnSubscribe(dis -> wheel.setVisibility(View.VISIBLE))
        .doFinally(() -> wheel.setVisibility(View.INVISIBLE))
        .subscribe(this::subscribeToResponse,this::onFail);
    disposables.add(disposable);
  }

  public void matches(ProgressWheel wheel) {
    Disposable disposable = api.todayMatches()
        .subscribeOn(Schedulers.io())
        .observeOn(AndroidSchedulers.mainThread())
        .doOnSubscribe(dis -> wheel.setVisibility(View.VISIBLE))
        .doFinally(() -> wheel.setVisibility(View.INVISIBLE))
        .subscribe(this::subscribeToResponse,this::onFail);
    disposables.add(disposable);
  }

  public void getLeagueStandings(ProgressWheel wheel, String id, Map<String, String> query) {
    Disposable disposable = api.getLeagueStandings(id, query)
        .subscribeOn(Schedulers.io())
        .observeOn(AndroidSchedulers.mainThread())
        .doOnSubscribe(dis -> wheel.setVisibility(View.VISIBLE))
        .doFinally(() -> wheel.setVisibility(View.INVISIBLE))
        .subscribe(this::subscribeToResponse,this::onFail);
    disposables.add(disposable);
  }

  public void getLeagueScorers(ProgressWheel wheel, String id) {
    Disposable disposable = api.getLeagueScorers(id)
        .subscribeOn(Schedulers.io())
        .observeOn(AndroidSchedulers.mainThread())
        .doOnSubscribe(dis -> wheel.setVisibility(View.VISIBLE))
        .doFinally(() -> wheel.setVisibility(View.INVISIBLE))
        .subscribe(this::subscribeToResponse,this::onFail);
    disposables.add(disposable);
  }

  public void getLeagueTeams(ProgressWheel wheel, String leagueId) {
    Disposable disposable = api.getLeagueTeams(leagueId)
        .subscribeOn(Schedulers.io())
        .observeOn(AndroidSchedulers.mainThread())
        .doOnSubscribe(dis -> wheel.setVisibility(View.VISIBLE))
        .doFinally(() -> wheel.setVisibility(View.INVISIBLE))
        .subscribe(this::subscribeToResponse,this::onFail);
    disposables.add(disposable);
  }

  public void getTeamInfo(ProgressWheel wheel, int teamId) {
    Disposable disposable = api.getSingleTeamInfo(teamId)
        .subscribeOn(Schedulers.io())
        .observeOn(AndroidSchedulers.mainThread())
        .doOnSubscribe(dis -> wheel.setVisibility(View.VISIBLE))
        .doFinally(() -> wheel.setVisibility(View.INVISIBLE))
        .subscribe(this::subscribeToResponse,this::onFail);
    disposables.add(disposable);
  }

  public void getLeagueNews(ProgressWheel wheel, Map<String, String> params) {
    Disposable disposable = newsApi.getLeagueNews(params)
        .subscribeOn(Schedulers.io())
        .observeOn(AndroidSchedulers.mainThread())
        .doOnSubscribe(dis -> wheel.setVisibility(View.VISIBLE))
        .doFinally(() -> wheel.setVisibility(View.INVISIBLE))
        .subscribe(this::subscribeToResponse,this::onFail);
    disposables.add(disposable);
  }


  public void getLeagueFixtures(ProgressWheel wheel, String id, Map<String, Integer> params) {
    Disposable disposable = api.getLeagueFixtures(id, params)
        .subscribeOn(Schedulers.io())
        .observeOn(AndroidSchedulers.mainThread())
        .doOnSubscribe(dis -> wheel.setVisibility(View.VISIBLE))
        .doFinally(() -> wheel.setVisibility(View.INVISIBLE))
        .subscribe(this::subscribeToResponse,this::onFail);
    disposables.add(disposable);
  }

  public void getTeamPreviousGame(ProgressWheel wheel, int id, String query) {
    Disposable disposable = api.getGames(id, query)
        .subscribeOn(Schedulers.io())
        .observeOn(AndroidSchedulers.mainThread())
        .doOnSubscribe(dis -> wheel.setVisibility(View.VISIBLE))
        .doFinally(() -> wheel.setVisibility(View.INVISIBLE))
        .subscribe(this::subscribeToResponse,this::onFail);
    disposables.add(disposable);
  }


  private void subscribeToResponse(Response<?> response) {
    validator.getServiceResponse(response);
  }

  private boolean checkNetwork() {
    return AppUtils.isOnline(context);
  }

  public interface transformServiceResponse {
    void responseReceived(Object model);
  }
  private void onFail(Throwable t){
    Log.d("errorMsg", "onFail:"+t.getMessage());
  }

}
