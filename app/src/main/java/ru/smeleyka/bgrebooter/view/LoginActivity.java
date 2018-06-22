package ru.smeleyka.bgrebooter.view;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.reactivex.android.schedulers.AndroidSchedulers;
import ru.smeleyka.bgrebooter.App;
import ru.smeleyka.bgrebooter.R;
import ru.smeleyka.bgrebooter.presenter.LoginPresenter;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
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
    @BindView(R.id.sign_in_button)          Button signInButton;
    @BindView(R.id.login_activity_progress) ProgressBar loginProgress;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);
        loginPresenter = new LoginPresenter(this, AndroidSchedulers.mainThread());
        isLoggedIn();
        SharedPreferences preferences = this.
    }

    @OnClick(R.id.sign_in_button)
    public void onSignButton() {
        loginPresenter.login(
                loginEditText.getText().toString(),
                passwordEditText.getText().toString()
        );
    }

    @Override
    public void showError(String message) {
        new AlertDialog
                .Builder(this)
                .setTitle(message)
                .setPositiveButton(R.string.dialog_ok,null)
                //.setMessage(message)
                .show();
    }

    @Override
    public String isLoggedIn() {
        String authKey = loadAuthKey();
        loginPresenter.checkAuthKey(authKey);
        return authKey;
    }

    @Override
    public void onLoginOk(String authKey) {
        saveAuthKey(authKey);
        startRebootActivity (authKey);

    }

    @Override
    public void showLoading() {
        loginProgress.setVisibility(View.VISIBLE);

    }

    @Override
    public void hideLoading() {
        loginProgress.setVisibility(View.INVISIBLE);

    }

    private void saveAuthKey(String authKey){
        SharedPreferences sharedPref = this.getPreferences(Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPref.edit();
        editor.putString(Constants.EXTRA_AUTH, authKey);
        editor.commit();
    }

    private void startRebootActivity (String authKey){
        Intent intent = new Intent(this, RebootActivity.class);
        intent.putExtra(Constants.EXTRA_AUTH, authKey);
        startActivity(intent);
        Log.d(TAG,authKey);
    }

    private String loadAuthKey(){
        SharedPreferences sharedPref = this.getPreferences(Context.MODE_PRIVATE);
        return sharedPref.getString(Constants.EXTRA_AUTH,null);
    }
}
