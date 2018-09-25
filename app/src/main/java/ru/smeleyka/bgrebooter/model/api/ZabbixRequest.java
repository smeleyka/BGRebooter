package ru.smeleyka.bgrebooter.model.api;

import javax.inject.Inject;
import javax.inject.Singleton;

import io.reactivex.Observable;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by smeleyka on 14.03.18.
 */

public class ZabbixRequest extends Repo{

    @Inject
    public ZabbixRequest() {
    }

    public Observable<String> senRequestToZabbixServer(String loginRequest)
    {
        return getApi().makeRequest(loginRequest).subscribeOn(Schedulers.io());
    }

}
