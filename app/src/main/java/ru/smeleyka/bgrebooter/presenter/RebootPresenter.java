package ru.smeleyka.bgrebooter.presenter;

import android.util.Log;

import com.google.gson.GsonBuilder;

import io.reactivex.Scheduler;
import ru.smeleyka.bgrebooter.model.api.ZabbixRequest;
import ru.smeleyka.bgrebooter.model.entity.ScriptExecuteRequest;
import ru.smeleyka.bgrebooter.model.entity.ScriptResponse;
import ru.smeleyka.bgrebooter.view.RebootView;

/**
 * Created by smeleyka on 14.03.18.
 */

public class RebootPresenter {

    final static String TAG = "RebootPresenter.class";

    private String auth;
    private Scheduler mainThread;
    private RebootView rebootView;
    private ZabbixRequest zabbixRequest;

    public RebootPresenter(RebootView rebootView, Scheduler mainThread,String auth){
        this.rebootView=rebootView;
        this.mainThread = mainThread;
        this.auth = auth;
        this.zabbixRequest = new ZabbixRequest();
    }

    public void rebootSwitch(){

    }

    public void showAuth(){
        Log.d(TAG,auth);
    }

    public void ping(){
        rebootView.showLoading();
        Log.d(TAG,auth);
        String scriptRequest = new GsonBuilder().create().toJson(new ScriptExecuteRequest(auth,1,12112));
        Log.d(TAG,scriptRequest);
        zabbixRequest.getLoginAnswer(scriptRequest)
                .observeOn(mainThread)
                .subscribe(
                        s -> {Log.d(TAG,  s);
                            ScriptResponse scriptResponse = new GsonBuilder().create().fromJson(s,ScriptResponse.class);
                            if(scriptResponse.getResult()!=null){
                                rebootView.showResult(scriptResponse.getResult().getValue());
                                rebootView.hideLoading();

                            }
                            else {
                                rebootView.showError(scriptResponse.getError().getData());
                                rebootView.hideLoading();

                            }},
                        throwable -> {Log.d(TAG,throwable.getMessage());
                            rebootView.showError(throwable.getMessage());
                            rebootView.hideLoading();
                        }
                );
    }

}