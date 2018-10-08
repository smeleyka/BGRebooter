package ru.smeleyka.bgrebooter.view;

/**
 * Created by smeleyka on 14.03.18.
 */

public interface LoginView {

    void showError(String message);

    //If yes should return authKey
    //If none, should return authKey = "empty"
    void isLoggedIn();

    void showLoading();

    void hideLoading();

    void startRebootActivity();

    void startMainActivity();

    void setLogin(String login);
}
