package ru.smeleyka.bgrebooter.model.api;

import io.reactivex.Observable;
import io.reactivex.schedulers.Schedulers;
import ru.smeleyka.bgrebooter.model.entity.Zobject;


public class UserRepo extends Repo
{
    public Observable<String> getUser(String loginRequest)
    {
        return getApi().getLoginAnswer(loginRequest).subscribeOn(Schedulers.io());
    }

//    public Observable <List<Zobject.Repos>> getUserRepos(String username)
//    {
//        return getApi().getUserRepos(username).subscribeOn(Schedulers.io());
//    }
}
