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
import ru.smeleyka.bgrebooter.view.MainActivityView;
import ru.smeleyka.bgrebooter.view.MainActivityView;

public class MainActivityPresenter {
    final static String TAG = "MainActivityPresenter.class";

    private String auth;
    private Scheduler mainThread;
    private MainActivityView mainActivityView;

    @Inject
    protected ZabbixRequest zabbixRequest;

    @Inject
    protected DataManager dataManager;

    @Inject
    protected GsonHelper gsonHelper;

    public MainActivityPresenter(MainActivityView mainActivityView, Scheduler mainThread) {
        App.getInstance().getAppComponent().inject(this);
        this.mainActivityView = mainActivityView;
        this.mainThread = mainThread;
        this.auth = dataManager.getAuthKey();
    }

    public void rebootSwitch() {

    }

    public void showAuth() {
        Log.d(TAG, auth);
    }

    public void ping() {
        mainActivityView.showLoading();
        Log.d(TAG, auth);
        String scriptRequest = gsonHelper.toJson(new ScriptExecuteRequest(auth, 1, 10271));
        Log.d(TAG, scriptRequest);
        zabbixRequest.senRequestToZabbixServer(scriptRequest)
                .observeOn(mainThread)
                .subscribe(
                        s -> {
                            Log.d(TAG, s);
                            ScriptExecuteResponse scriptExecuteResponse = gsonHelper.fromJson(s, ScriptExecuteResponse.class);
                            if (scriptExecuteResponse.getResult() != null) {
                                mainActivityView.showResult(scriptExecuteResponse.getResult().getValue());
                                mainActivityView.hideLoading();

                            } else {
                                mainActivityView.showError(scriptExecuteResponse.getError().getData());
                                mainActivityView.hideLoading();

                            }
                        },
                        throwable -> {
                            Log.d(TAG, throwable.getMessage());
                            mainActivityView.showError(throwable.getMessage());
                            mainActivityView.hideLoading();
                        }
                );
    }

    public void getHostsGroup() {
        mainActivityView.showLoading();
        Log.d(TAG, auth);
        dataManager.getHostGroupList(auth)
                .observeOn(mainThread)
                .subscribe(
                        s -> {
                            addMenuItem(s.getName());
                        },
                        throwable -> {
                            Log.d(TAG, throwable.getMessage());
                            mainActivityView.showError(throwable.getMessage());
                            mainActivityView.hideLoading();
                        }
                );
    }

    public void cleanAuth() {
        mainActivityView.showResult("TEST");
        dataManager.deleteAuthKey();
    }

    public void addMenuItem(String name){
        mainActivityView.addMenuItem(name);
    }


}