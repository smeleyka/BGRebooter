package ru.smeleyka.bgrebooter.model.data;

import android.content.SharedPreferences;
import android.util.Log;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;

import dagger.Provides;
import io.reactivex.Observable;
import io.reactivex.Scheduler;
import io.reactivex.internal.operators.observable.ObservableAll;
import io.reactivex.schedulers.Schedulers;
import ru.smeleyka.bgrebooter.model.api.ZabbixRequest;
import ru.smeleyka.bgrebooter.model.entity.HostgroupGetRequest;
import ru.smeleyka.bgrebooter.model.entity.HostgroupGetResponse;
import ru.smeleyka.bgrebooter.model.entity.HostsOfGroupGetRequest;
import ru.smeleyka.bgrebooter.model.entity.HostsOfGroupGetResponse;

@Singleton
public class DataManager {
    private String TAG = "DataManager";
    private SharedPrefsHelper sharedPrefsHelper;
    private GsonHelper gsonHelper;
    private ZabbixRequest zabbixRequest;


    @Inject
    public DataManager(SharedPrefsHelper sharedPrefsHelper, GsonHelper gsonHelper, ZabbixRequest zabbixRequest) {
        this.sharedPrefsHelper = sharedPrefsHelper;
        this.gsonHelper = gsonHelper;
        this.zabbixRequest = zabbixRequest;
    }

    public void saveAuthKey(String authKey) {
        sharedPrefsHelper.put(sharedPrefsHelper.PREF_AUTH_KEY, authKey);
    }

    public String getAuthKey() {
        return sharedPrefsHelper.get(sharedPrefsHelper.PREF_AUTH_KEY, null);
    }

    public void deleteAuthKey() {
        sharedPrefsHelper.deleteSavedData(sharedPrefsHelper.PREF_AUTH_KEY);
    }

    public void saveLogin(String authKey) {
        sharedPrefsHelper.put(sharedPrefsHelper.PREF_LOGIN, authKey);
    }

    public String getLogin() {
        return sharedPrefsHelper.get(sharedPrefsHelper.PREF_LOGIN, null);
    }

    public void deleteLogin() {
        sharedPrefsHelper.deleteSavedData(sharedPrefsHelper.PREF_LOGIN);
    }

    public Observable<HostgroupGetResponse.Hostgroup> getHostGroupList(String auth) {
        String scriptRequest = gsonHelper.toJson(new HostgroupGetRequest(auth));
        Log.d(TAG, scriptRequest);
        Observable<HostgroupGetResponse.Hostgroup> observableGroupe =
                zabbixRequest.senRequestToZabbixServer(scriptRequest)
                        .flatMap(s -> {
                            Log.d(TAG, s);
                            HostgroupGetResponse hostgroupGetResponse = gsonHelper.fromJson(s, HostgroupGetResponse.class);
                            HostgroupGetResponse.Hostgroup[] hostGroupeList = hostgroupGetResponse.getResult();
                            return Observable.fromArray(hostGroupeList);
                        });
        return observableGroupe;

    }
    public Observable<HostsOfGroupGetResponse.Host> getHostsOfGroupList(String auth, int groupId) {
        String scriptRequest = gsonHelper.toJson(new HostsOfGroupGetRequest(auth,groupId));
        Log.d(TAG, scriptRequest);
        Observable<HostsOfGroupGetResponse.Host> observableHost =
                zabbixRequest.senRequestToZabbixServer(scriptRequest).subscribeOn(Schedulers.computation())
                        .flatMap(s -> {
                            Log.d(TAG, s);
                            HostsOfGroupGetResponse hostsOfGroupGetResponse = gsonHelper.fromJson(s, HostsOfGroupGetResponse.class);
                            return Observable.fromArray(hostsOfGroupGetResponse.getHosts());
                        });
        return observableHost;

    }

}

