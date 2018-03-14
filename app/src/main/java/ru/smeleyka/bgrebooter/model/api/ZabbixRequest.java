package ru.smeleyka.bgrebooter.model.api;

import io.reactivex.Observable;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by smeleyka on 14.03.18.
 */

public class ZabbixRequest extends Repo{
    public Observable<String> getLoginAnswer(String loginRequest)
    {
        return getApi().getLoginAnswer(loginRequest).subscribeOn(Schedulers.io());
    }
}
