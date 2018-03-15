package ru.smeleyka.bgrebooter.model.api;

import java.util.List;

import io.reactivex.Observable;
import io.reactivex.schedulers.Schedulers;
import ru.smeleyka.bgrebooter.model.entity.LoginResponse;
import ru.smeleyka.bgrebooter.model.entity.ZabbixObject;

/**
 * Created by smeleyka on 14.03.18.
 */

public class ZabbixRequest extends Repo{
    public Observable<LoginResponse> getLoginAnswer(String loginRequest)
    {
        return getApi().makeRequest(loginRequest).subscribeOn(Schedulers.io());
    }

    public Observable<String> getTestAnswer(String loginRequest)
    {
        return getTestApi().makeTestRequest(loginRequest).subscribeOn(Schedulers.io());
    }
}
