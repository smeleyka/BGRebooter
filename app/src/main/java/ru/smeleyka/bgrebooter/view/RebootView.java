package ru.smeleyka.bgrebooter.view;

/**
 * Created by smeleyka on 14.03.18.
 */

public interface RebootView {
    void showResult(String result);

    void showError(String message);

    void showLoading();

    void hideLoading();
}
