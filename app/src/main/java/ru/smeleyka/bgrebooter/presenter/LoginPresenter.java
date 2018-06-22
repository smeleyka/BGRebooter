package ru.smeleyka.bgrebooter.presenter;

import android.util.Log;

import com.google.gson.GsonBuilder;


import io.reactivex.Scheduler;
import ru.smeleyka.bgrebooter.model.api.ZabbixRequest;
import ru.smeleyka.bgrebooter.model.entity.AuthKeyCheckRequest;
import ru.smeleyka.bgrebooter.model.entity.LoginRequest;
import ru.smeleyka.bgrebooter.model.entity.LoginResponse;
import ru.smeleyka.bgrebooter.view.LoginView;

/**
 * Created by smeleyka on 14.03.18.
 */

public class LoginPresenter {
    final static String TAG = "LoginPresenter.class";
    private ZabbixRequest zabbixRequest;

    private Scheduler mainThread;
    private LoginView loginView;

    public LoginPresenter(LoginView loginView, Scheduler mainThread){
        this.loginView=loginView;
        this.mainThread = mainThread;
        this.zabbixRequest=new ZabbixRequest();
    }

    public void login(String login, String password){
        loginView.showLoading();
        String loginRequest = new GsonBuilder().serializeNulls().create().toJson(new LoginRequest(login,password));
        Log.d(TAG,loginRequest);
        zabbixRequest
                .senRequestToZabbixServer(loginRequest)
                .observeOn(mainThread)
                .subscribe(
                        s -> {
                            Log.d(TAG,  s);
                            LoginResponse loginResponse = new GsonBuilder().create().fromJson(s,LoginResponse.class);
                            if(loginResponse.getResult()!=null){
                                loginView.onLoginOk(loginResponse.getResult());
                                loginView.hideLoading();

                            }
                            else {
                                loginView.showError(loginResponse.getError().getData());
                                loginView.hideLoading();

                            }},
                        throwable -> {
                            Log.d(TAG,throwable.getMessage());
                            loginView.showError(throwable.getMessage());
                            loginView.hideLoading();
                        }
                );
    }
    public void checkAuthKey(String authKey){
        loginView.showLoading();
        String authCheckRequest = new GsonBuilder().serializeNulls().create().toJson(new AuthKeyCheckRequest(authKey));
        Log.d(TAG,authCheckRequest);
        zabbixRequest
                .senRequestToZabbixServer(authCheckRequest)
                .observeOn(mainThread)
                .subscribe(
                        s -> {
                            Log.d(TAG,  s);
                            LoginResponse loginResponse = new GsonBuilder().create().fromJson(s,LoginResponse.class);
                            if(loginResponse.getResult()!=null){
                                loginView.onLoginOk(loginResponse.getResult());
                                loginView.hideLoading();

                            }
                            else {
                                loginView.showError(loginResponse.getError().getData());
                                loginView.hideLoading();

                            }},
                        throwable -> {
                            Log.d(TAG,throwable.getMessage());
                            loginView.showError(throwable.getMessage());
                            loginView.hideLoading();
                        }
                );


    }
}