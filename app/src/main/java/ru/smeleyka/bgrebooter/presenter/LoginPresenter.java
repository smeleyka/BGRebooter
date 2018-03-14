package ru.smeleyka.bgrebooter.presenter;

import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.Arrays;

import io.reactivex.Scheduler;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import retrofit2.Response;
import ru.smeleyka.bgrebooter.model.api.ApiHolder;
import ru.smeleyka.bgrebooter.model.api.ZabbixRequest;
import ru.smeleyka.bgrebooter.model.entity.LoginRequest;
import ru.smeleyka.bgrebooter.view.LoginView;

/**
 * Created by smeleyka on 14.03.18.
 */

public class LoginPresenter {
    final static String TAG = "LoginPresenter";
    private ZabbixRequest zabbixRequest;

    private Scheduler mainThread;
    private LoginView loginView;

    public LoginPresenter(LoginView loginView, Scheduler mainThread){
        this.loginView=loginView;
        this.mainThread = mainThread;
        this.zabbixRequest=new ZabbixRequest();
    }

    public void login(String login, String password){
        String loginRequest = new GsonBuilder().serializeNulls().create().toJson(new LoginRequest(login,password));
        Log.d(TAG,loginRequest);
        zabbixRequest
                .getLoginAnswer(loginRequest)
                .observeOn(mainThread)
                .subscribe(s -> Log.d(TAG, s),throwable -> Log.d(TAG,throwable.getMessage()));
    }


}