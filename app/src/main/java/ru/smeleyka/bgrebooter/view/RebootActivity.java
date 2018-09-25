package ru.smeleyka.bgrebooter.view;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.reactivex.android.schedulers.AndroidSchedulers;
import ru.smeleyka.bgrebooter.R;
import ru.smeleyka.bgrebooter.presenter.RebootPresenter;

/**
 * Created by smeleyka on 14.03.18.
 */

public class RebootActivity extends AppCompatActivity implements RebootView {
    RebootPresenter rebootPresenter;


    @BindView(R.id.reboot_button)
    Button rebootButton;
    @BindView(R.id.clean_button)
    Button cleanButton;
    @BindView(R.id.test_button)
    Button testButton;
    @BindView(R.id.result_tv)
    TextView resultTextView;
    @BindView(R.id.reboot_activity_progress)
    ProgressBar rebootProgress;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reboot);
        ButterKnife.bind(this);
        rebootPresenter = new RebootPresenter(this, AndroidSchedulers.mainThread());
    }

    @OnClick(R.id.reboot_button)
    protected void onRebootButton() {
        rebootPresenter.ping();
    }

    @OnClick(R.id.clean_button)
    protected void onCleanButton() {
        rebootPresenter.cleanAuth();
    }


    @Override
    public void showResult(String result) {
        resultTextView.setText(result);
    }

    @Override
    public void showError(String message) {
        new AlertDialog
                .Builder(this)
                .setTitle(message)
                .setPositiveButton(R.string.dialog_ok, null)
                //.setMessage(message)
                .show();
    }

    @Override
    public void showLoading() {
        rebootProgress.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideLoading() {
        rebootProgress.setVisibility(View.INVISIBLE);
    }

    @Override
    public void onBackPressed() {
        System.out.println("BACK");
        rebootPresenter.cleanAuth();
        super.onBackPressed();
    }

    @OnClick(R.id.test_button)
    public void onTestButton(){
        rebootPresenter.getHostsGroup();
    }


}
