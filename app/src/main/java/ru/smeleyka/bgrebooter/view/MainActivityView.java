package ru.smeleyka.bgrebooter.view;

import ru.smeleyka.bgrebooter.model.entity.HostgroupGetResponse;

public interface MainActivityView {

    void showResult(String result);

    void showError(String message);

    void showLoading();

    void hideLoading();

    void addMenuItem(String name);

    void addMenuItem(HostgroupGetResponse.Hostgroup hostgroup);

}
