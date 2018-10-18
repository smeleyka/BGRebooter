package ru.smeleyka.bgrebooter.view;

import io.reactivex.Observable;
import ru.smeleyka.bgrebooter.model.entity.HostgroupGetResponse;
import ru.smeleyka.bgrebooter.model.entity.HostsOfGroupGetResponse;
import ru.smeleyka.bgrebooter.presenter.MainActivityPresenter;

public interface MainActivityView {

    void showResult(String result);

    void showError(String message);

    void showLoading();

    void hideLoading();

    void addMenuItem(String name);

    void showHostgroupFragment(Observable<HostgroupGetResponse.Hostgroup> hostgroupObservable);

    void showHostsFragment(Observable<HostsOfGroupGetResponse.Host> hostObservable);

    MainActivityPresenter getPresenter();

    Observable<HostsOfGroupGetResponse.Host> getHostOfGroup(int groupId);

    void goBack();

}
