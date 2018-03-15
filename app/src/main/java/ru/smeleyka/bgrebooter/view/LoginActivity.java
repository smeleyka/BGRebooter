package ru.smeleyka.bgrebooter.view;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.reactivex.android.schedulers.AndroidSchedulers;
import ru.smeleyka.bgrebooter.R;
import ru.smeleyka.bgrebooter.presenter.LoginPresenter;

import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;

public class LoginActivity extends AppCompatActivity implements LoginView {
    public static final String TAG = "LoginActivity.class";


    private LoginPresenter loginPresenter;

    @BindView(R.id.login)                   EditText loginEditText;
    @BindView(R.id.password)                EditText passwordEditText;
    @BindView(R.id.email_sign_in_button)    Button signInButton;
    @BindView(R.id.login_progress)          ProgressBar loginProgress;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);
        loginPresenter = new LoginPresenter(this, AndroidSchedulers.mainThread());
    }

    @OnClick(R.id.email_sign_in_button)
    public void onSignButton() {
        loginProgress.setVisibility(View.VISIBLE);

        loginPresenter.testLogin(
                    loginEditText.getText().toString(),
                    passwordEditText.getText().toString());

        loginPresenter.login(
                loginEditText.getText().toString(),
                passwordEditText.getText().toString()
        );
    }

    @Override
    public void showError(String message) {
        loginProgress.setVisibility(View.INVISIBLE);
        new AlertDialog
                .Builder(this)
                .setTitle(message)
                .setPositiveButton(R.string.dialog_ok,null)
                //.setMessage(message)
                .show();
    }

    @Override
    public void onLoginOk(String auth) {
        loginProgress.setVisibility(View.INVISIBLE);
        Intent intent = new Intent(this, RebootActivity.class);
        intent.putExtra(Constants.EXTRA_MESSAGE, auth);
        startActivity(intent);
        Log.d(TAG,auth);
    }
}
