package ru.smeleyka.bgrebooter.presenter;

import android.util.Log;

import com.google.gson.GsonBuilder;


import javax.inject.Inject;

import io.reactivex.Scheduler;
import ru.smeleyka.bgrebooter.App;
import ru.smeleyka.bgrebooter.model.api.ZabbixRequest;
import ru.smeleyka.bgrebooter.model.data.DataManager;
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

    @Inject
    protected DataManager dataManager;

    @Inject
    public LoginPresenter(LoginView loginView, Scheduler mainThread){
        this.loginView=loginView;
        this.mainThread = mainThread;
        this.zabbixRequest=new ZabbixRequest();
        App.getInstance().getAppComponent().inject(this);
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
//                                loginView.onLoginOk(loginResponse.getResult());
                                dataManager.saveAuthKey(loginResponse.getResult());
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
    public void checkAuthKey(){
        loginView.showLoading();
        String authCheckRequest = new GsonBuilder().serializeNulls().create().toJson(new AuthKeyCheckRequest(dataManager.getAuthKey()));
        Log.d(TAG,authCheckRequest);
        zabbixRequest
                .senRequestToZabbixServer(authCheckRequest)
                .observeOn(mainThread)
                .subscribe(
                        s -> {
                            Log.d(TAG,  s);
                            LoginResponse loginResponse = new GsonBuilder().create().fromJson(s,LoginResponse.class);
                            if(loginResponse.getResult()!=null){
                                loginView.onLoginOk(dataManager.getAuthKey());
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