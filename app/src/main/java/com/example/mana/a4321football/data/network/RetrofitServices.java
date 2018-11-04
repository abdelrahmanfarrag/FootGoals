package com.example.mana.a4321football.data.network;

import android.app.Dialog;
import android.content.Context;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import com.example.mana.a4321football.R;
import com.example.mana.a4321football.utility.AppUtils;
import com.example.mana.a4321football.utility.Dialogs;
import com.example.mana.a4321football.utility.ToastMessages;
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
    if (checkNetwork()) {
      Disposable disposable = api.getLeagueData(id)
          .subscribeOn(Schedulers.io())
          .observeOn(AndroidSchedulers.mainThread())
          .doOnSubscribe(dis -> wheel.setVisibility(View.VISIBLE))
          .doFinally(() -> wheel.setVisibility(View.INVISIBLE))
          .subscribe(this::subscribeToResponse, this::onFail);
      disposables.add(disposable);
    } else {
      buildNoNetWorkDialog().setOnDismissListener(dialog -> getLeagueDetails(wheel, id));
    }
  }

  public void matches(ProgressWheel wheel) {
    if (checkNetwork()) {
      Disposable disposable = api.todayMatches()
          .subscribeOn(Schedulers.io())
          .observeOn(AndroidSchedulers.mainThread())
          .doOnSubscribe(dis -> wheel.setVisibility(View.VISIBLE))
          .doFinally(() -> wheel.setVisibility(View.INVISIBLE))
          .subscribe(this::subscribeToResponse, this::onFail);
      disposables.add(disposable);
    } else {
      buildNoNetWorkDialog().setOnDismissListener(dialog -> matches(wheel));
    }
  }

  public void getLeagueStandings(ProgressWheel wheel, String id, Map<String, String> query) {
    if (checkNetwork()) {
      Disposable disposable = api.getLeagueStandings(id, query)
          .subscribeOn(Schedulers.io())
          .observeOn(AndroidSchedulers.mainThread())
          .doOnSubscribe(dis -> wheel.setVisibility(View.VISIBLE))
          .doFinally(() -> wheel.setVisibility(View.INVISIBLE))
          .subscribe(this::subscribeToResponse, this::onFail);
      disposables.add(disposable);
    } else {
      buildNoNetWorkDialog().setOnDismissListener(dialog -> getLeagueStandings(wheel, id, query));
    }
  }

  public void getLeagueScorers(ProgressWheel wheel, String id) {
    if (checkNetwork()) {
      Disposable disposable = api.getLeagueScorers(id)
          .subscribeOn(Schedulers.io())
          .observeOn(AndroidSchedulers.mainThread())
          .doOnSubscribe(dis -> wheel.setVisibility(View.VISIBLE))
          .doFinally(() -> wheel.setVisibility(View.INVISIBLE))
          .subscribe(this::subscribeToResponse, this::onFail);
      disposables.add(disposable);
    } else {
      buildNoNetWorkDialog().setOnDismissListener(dialog -> getLeagueScorers(wheel, id));
    }
  }

  public void getLeagueTeams(ProgressWheel wheel, String leagueId) {
    if (checkNetwork()) {
      Disposable disposable = api.getLeagueTeams(leagueId)
          .subscribeOn(Schedulers.io())
          .observeOn(AndroidSchedulers.mainThread())
          .doOnSubscribe(dis -> wheel.setVisibility(View.VISIBLE))
          .doFinally(() -> wheel.setVisibility(View.INVISIBLE))
          .subscribe(this::subscribeToResponse, this::onFail);
      disposables.add(disposable);
    } else {
      buildNoNetWorkDialog().setOnDismissListener(dialog -> getLeagueTeams(wheel, leagueId));
    }
  }

  public void getTeamInfo(ProgressWheel wheel, int teamId) {
    if (checkNetwork()) {
      Disposable disposable = api.getSingleTeamInfo(teamId)
          .subscribeOn(Schedulers.io())
          .observeOn(AndroidSchedulers.mainThread())
          .doOnSubscribe(dis -> wheel.setVisibility(View.VISIBLE))
          .doFinally(() -> wheel.setVisibility(View.INVISIBLE))
          .subscribe(this::subscribeToResponse, this::onFail);
      disposables.add(disposable);
    } else {
      buildNoNetWorkDialog().setOnDismissListener(dialog -> getTeamInfo(wheel, teamId));
    }
  }

  public void getLeagueNews(ProgressWheel wheel, Map<String, String> params) {
    if (checkNetwork()) {
      Disposable disposable = newsApi.getLeagueNews(params)
          .subscribeOn(Schedulers.io())
          .observeOn(AndroidSchedulers.mainThread())
          .doOnSubscribe(dis -> wheel.setVisibility(View.VISIBLE))
          .doFinally(() -> wheel.setVisibility(View.INVISIBLE))
          .subscribe(this::subscribeToResponse, this::onFail);
      disposables.add(disposable);
    } else {
      buildNoNetWorkDialog().setOnDismissListener(dialog -> getLeagueNews(wheel, params));
    }
  }

  public void getLeagueFixtures(ProgressWheel wheel, String id, Map<String, Integer> params) {
    if (checkNetwork()) {
      Disposable disposable = api.getLeagueFixtures(id, params)
          .subscribeOn(Schedulers.io())
          .observeOn(AndroidSchedulers.mainThread())
          .doOnSubscribe(dis -> wheel.setVisibility(View.VISIBLE))
          .doFinally(() -> wheel.setVisibility(View.INVISIBLE))
          .subscribe(this::subscribeToResponse, this::onFail);
      disposables.add(disposable);
    } else {
      buildNoNetWorkDialog().setOnDismissListener(dialog -> getLeagueFixtures(wheel, id, params));
    }
  }

  public void getTeamPreviousGame(ProgressWheel wheel, int id, String query) {
    if (checkNetwork()) {
      Disposable disposable = api.getGames(id, query)
          .subscribeOn(Schedulers.io())
          .observeOn(AndroidSchedulers.mainThread())
          .doOnSubscribe(dis -> wheel.setVisibility(View.VISIBLE))
          .doFinally(() -> wheel.setVisibility(View.INVISIBLE))
          .subscribe(this::subscribeToResponse, this::onFail);
      disposables.add(disposable);
    } else {
      buildNoNetWorkDialog().setOnDismissListener(dialog -> getTeamPreviousGame(wheel, id, query));
    }
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

  private void onFail(Throwable t) {
    Log.d("errorMsg", "onFail:" + t.getMessage());
  }

  private Dialog buildNoNetWorkDialog() {
    Dialog dialog =
        Dialogs.transparentDialog(context, R.layout.no_connection_layout,
            R.style.Theme_Design_NoActionBar);
    Objects.requireNonNull(dialog.getWindow())
        .setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
    Button b = dialog.findViewById(R.id.no_con_retry_btn);
    b.setOnClickListener(v -> {
      if (!checkNetwork()) {
        ToastMessages.ShortToastMessage(context,
            context.getResources().getString(R.string.no_connect));
      } else {
        dialog.dismiss();
      }
    });
    dialog.show();
    return dialog;
  }
}
