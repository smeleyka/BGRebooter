package ru.smeleyka.bgrebooter.view;

public interface MainActivityView {

    void showResult(String result);

    void showError(String message);

    void showLoading();

    void hideLoading();

    void addMenuItem(String name);

}
