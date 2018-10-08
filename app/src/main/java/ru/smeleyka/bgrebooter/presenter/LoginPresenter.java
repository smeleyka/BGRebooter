package ru.smeleyka.bgrebooter.presenter;

import android.util.Log;


import javax.inject.Inject;

import io.reactivex.Scheduler;
import ru.smeleyka.bgrebooter.App;
import ru.smeleyka.bgrebooter.model.api.ZabbixRequest;
import ru.smeleyka.bgrebooter.model.data.DataManager;
import ru.smeleyka.bgrebooter.model.data.GsonHelper;
import ru.smeleyka.bgrebooter.model.entity.UserGetRequest;
import ru.smeleyka.bgrebooter.model.entity.UserGetResponse;
import ru.smeleyka.bgrebooter.model.entity.UserLoginRequest;
import ru.smeleyka.bgrebooter.model.entity.UserLoginResponse;
import ru.smeleyka.bgrebooter.view.LoginView;

/**
 * Created by smeleyka on 14.03.18.
 */

public class LoginPresenter {
    final static String TAG = "LoginPresenter.class";

    private LoginView loginView;
    private Scheduler mainThread;

    @Inject
    protected DataManager dataManager;
    @Inject
    protected GsonHelper gsonHelper;
    @Inject
    protected ZabbixRequest zabbixRequest;

    public LoginPresenter(LoginView loginView, Scheduler mainThread) {
        this.loginView = loginView;
        this.mainThread = mainThread;
        App.getInstance().getAppComponent().inject(this);
        checkAuthKey();
    }

    public void login(String login, String password) {
        loginView.showLoading();
        String loginRequest = gsonHelper.toJson(new UserLoginRequest(login, password));
        Log.d(TAG, loginRequest);
        zabbixRequest
                .senRequestToZabbixServer(loginRequest)
                .observeOn(mainThread)
                .subscribe(
                        s -> {
                            Log.d(TAG, s);
                            UserLoginResponse userLoginResponse = gsonHelper.fromJson(s, UserLoginResponse.class);
                            if (userLoginResponse.getResult() != null) {
                                dataManager.saveAuthKey(userLoginResponse.getResult());
                                dataManager.saveLogin(login);
                                loginView.hideLoading();
                                loginView.startMainActivity();
                            } else {
                                loginView.showError(userLoginResponse.getError().getData());
                                loginView.hideLoading();

                            }
                        },
                        throwable -> {
                            Log.d(TAG, throwable.getMessage());
                            loginView.showError(throwable.getMessage());
                            loginView.hideLoading();
                        }
                );
    }

    public void checkAuthKey() {
        loginView.setLogin(dataManager.getLogin());
        loginView.showLoading();
        if (dataManager.getAuthKey() != null) {
            String authCheckRequest = gsonHelper.toJson(new UserGetRequest(dataManager.getAuthKey()));
            Log.d(TAG, authCheckRequest);
            zabbixRequest
                    .senRequestToZabbixServer(authCheckRequest)
                    .observeOn(mainThread)
                    .subscribe(
                            s -> {
                                Log.d(TAG, s);
                                UserGetResponse userGetResponse = gsonHelper.fromJson(s, UserGetResponse.class);
                                if (userGetResponse.getResult() != null) {
                                    loginView.startMainActivity();
                                    loginView.hideLoading();

                                } else {
                                    loginView.showError(userGetResponse.getError().getData());
                                    loginView.hideLoading();

                                }
                            },
                            throwable -> {
                                Log.d(TAG, throwable.getMessage());
                                loginView.showError(throwable.getMessage());
                                loginView.hideLoading();
                            }
                    );
        }
        loginView.hideLoading();
    }
}