package ru.smeleyka.bgrebooter.view;

import io.reactivex.Observable;
import ru.smeleyka.bgrebooter.model.entity.HostgroupGetResponse;

public interface MainActivityView {

    void showResult(String result);

    void showError(String message);

    void showLoading();

    void hideLoading();

    void addMenuItem(String name);

    void showHostgroupFragment(Observable<HostgroupGetResponse.Hostgroup> hostgroupObservable);

    void goBack();

}
