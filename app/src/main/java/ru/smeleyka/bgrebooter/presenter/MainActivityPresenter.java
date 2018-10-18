package ru.smeleyka.bgrebooter.presenter;

import android.util.Log;

import javax.inject.Inject;

import io.reactivex.Observable;
import io.reactivex.Scheduler;
import io.reactivex.schedulers.Schedulers;
import ru.smeleyka.bgrebooter.App;
import ru.smeleyka.bgrebooter.model.api.ZabbixRequest;
import ru.smeleyka.bgrebooter.model.data.DataManager;
import ru.smeleyka.bgrebooter.model.data.GsonHelper;
import ru.smeleyka.bgrebooter.model.entity.HostgroupGetRequest;
import ru.smeleyka.bgrebooter.model.entity.HostgroupGetResponse;
import ru.smeleyka.bgrebooter.model.entity.HostsOfGroupGetResponse;
import ru.smeleyka.bgrebooter.model.entity.ScriptExecuteRequest;
import ru.smeleyka.bgrebooter.model.entity.ScriptExecuteResponse;
import ru.smeleyka.bgrebooter.view.MainActivityView;
import ru.smeleyka.bgrebooter.view.MainActivityView;

public class MainActivityPresenter {
    final static String TAG = "MainActivityPres.class";

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
        Log.d(TAG, "constructor " + auth);
    }

    public void onStart(){
        Observable<HostgroupGetResponse.Hostgroup> hostgroupObservable = getHostGroupeObseravable();
        mainActivityView.showHostgroupFragment(hostgroupObservable);
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
                            mainActivityView.hideLoading();
                        },
                        throwable -> {
                            Log.d(TAG, throwable.getMessage());
                            mainActivityView.showError(throwable.getMessage());
                            mainActivityView.hideLoading();
                        }
                );
    }

    public Observable<HostgroupGetResponse.Hostgroup> getHostGroupeObseravable() {
        return dataManager.getHostGroupList(auth);
    }

    public Observable<HostsOfGroupGetResponse.Host> getHostsOfGroupeObseravable(int groupeId) {
        return dataManager.getHostsOfGroupList(auth,groupeId);
    }


    public void onGroupeFragmentClick(int groupId){
        mainActivityView.showHostsFragment(getHostsOfGroupeObseravable(groupId));
    }

    public void cleanAuth() {
        mainActivityView.showResult("Clear Auth");
        mainActivityView.goBack();
        dataManager.deleteAuthKey();
    }


    public void addMenuItem(String name) {
        mainActivityView.addMenuItem(name);
    }

     public void testFunc() {
        Log.d(TAG, "TEST");
    }


}
