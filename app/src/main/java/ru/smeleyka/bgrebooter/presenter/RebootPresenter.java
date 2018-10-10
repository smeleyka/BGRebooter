package ru.smeleyka.bgrebooter.presenter;

import android.util.Log;

import javax.inject.Inject;

import io.reactivex.Scheduler;
import ru.smeleyka.bgrebooter.App;
import ru.smeleyka.bgrebooter.model.api.ZabbixRequest;
import ru.smeleyka.bgrebooter.model.data.DataManager;
import ru.smeleyka.bgrebooter.model.data.GsonHelper;
import ru.smeleyka.bgrebooter.model.entity.HostgroupGetRequest;
import ru.smeleyka.bgrebooter.model.entity.HostgroupGetResponse;
import ru.smeleyka.bgrebooter.model.entity.ScriptExecuteRequest;
import ru.smeleyka.bgrebooter.model.entity.ScriptExecuteResponse;
import ru.smeleyka.bgrebooter.view.RebootView;

/**
 * Created by smeleyka on 14.03.18.
 */

public class RebootPresenter {

    final static String TAG = "RebootPresenter.class";

    private String auth;
    private Scheduler mainThread;
    private RebootView rebootView;

    @Inject
    protected ZabbixRequest zabbixRequest;

    @Inject
    protected DataManager dataManager;

    @Inject
    protected GsonHelper gsonHelper;

    public RebootPresenter(RebootView rebootView, Scheduler mainThread){
        App.getInstance().getAppComponent().inject(this);
        this.rebootView=rebootView;
        this.mainThread = mainThread;
        this.auth = dataManager.getAuthKey();
    }

    public void rebootSwitch(){

    }

    public void showAuth(){
        Log.d(TAG,auth);
    }

    public void ping(){
        rebootView.showLoading();
        Log.d(TAG,auth);
        String scriptRequest = gsonHelper.toJson(new ScriptExecuteRequest(auth,1,10271));
        Log.d(TAG,scriptRequest);
        zabbixRequest.senRequestToZabbixServer(scriptRequest)
                .observeOn(mainThread)
                .subscribe(
                        s -> {Log.d(TAG,  s);
                            ScriptExecuteResponse scriptExecuteResponse = gsonHelper.fromJson(s,ScriptExecuteResponse.class);
                            if(scriptExecuteResponse.getResult()!=null){
                                rebootView.showResult(scriptExecuteResponse.getResult().getValue());
                                rebootView.hideLoading();

                            }
                            else {
                                rebootView.showError(scriptExecuteResponse.getError().getData());
                                rebootView.hideLoading();

                            }},
                        throwable -> {Log.d(TAG,throwable.getMessage());
                            rebootView.showError(throwable.getMessage());
                            rebootView.hideLoading();
                        }
                );
    }

    public void getHostsGroup(){
        rebootView.showLoading();
        Log.d(TAG,auth);
        String scriptRequest = gsonHelper.toJson(new HostgroupGetRequest(auth));
        Log.d(TAG,scriptRequest);
        zabbixRequest.senRequestToZabbixServer(scriptRequest)
                .observeOn(mainThread)
                .subscribe(
                        s -> {Log.d(TAG,  s);
                            HostgroupGetResponse hostgroupGetResponse = gsonHelper.fromJson(s,HostgroupGetResponse.class);
                            if(hostgroupGetResponse.getResult()!=null){
                                //rebootView.showResult(""+hostgroupGetResponse.getResult().getGroupid());
                                rebootView.hideLoading();

                            }
                            else {
                                rebootView.showError(hostgroupGetResponse.getError().getData());
                                rebootView.hideLoading();

                            }},
                        throwable -> {Log.d(TAG,throwable.getMessage());
                            rebootView.showError(throwable.getMessage());
                            rebootView.hideLoading();
                        }
                );
    }

    public void cleanAuth(){
        rebootView.showResult("TEST");
        dataManager.deleteAuthKey();
    }




}