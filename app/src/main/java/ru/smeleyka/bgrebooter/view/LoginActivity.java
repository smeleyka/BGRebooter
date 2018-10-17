package ru.smeleyka.bgrebooter.view;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.reactivex.android.schedulers.AndroidSchedulers;
import ru.smeleyka.bgrebooter.App;
import ru.smeleyka.bgrebooter.R;
import ru.smeleyka.bgrebooter.presenter.LoginPresenter;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;


public class LoginActivity extends AppCompatActivity implements LoginView {
    public static final String TAG = "LoginActivity.class";
    private LoginPresenter loginPresenter;
    private LoginView loginView;

    @BindView(R.id.login)
    EditText loginEditText;
    @BindView(R.id.password)
    EditText passwordEditText;
    @BindView(R.id.sign_in_button)
    Button signInButton;
    @BindView(R.id.login_activity_progress)
    ProgressBar loginProgress;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        init();

    }

    private void init() {
        App.getInstance().getAppComponent().inject(this);
        ButterKnife.bind(this);
        loginView = this;
        loginPresenter = new LoginPresenter(this, AndroidSchedulers.mainThread());
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
                .setPositiveButton(R.string.dialog_ok, null)
                //.setMessage(message)
                .show();
    }

    @Override
    public void isLoggedIn() {
        loginPresenter.checkAuthKey();
    }

    @Override
    public void showLoading() {
        loginProgress.setVisibility(View.VISIBLE);

    }

    @Override
    public void hideLoading() {
        loginProgress.setVisibility(View.INVISIBLE);

    }


      @Override
    public void startMainActivity() {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

    @Override
    public void setLogin(String login){
        loginEditText.setText(login);
    }

//    private void saveAuthKey(String authKey) {
//        SharedPreferences sharedPref = this.getPreferences(Context.MODE_PRIVATE);
//        SharedPreferences.Editor editor = sharedPref.edit();
//        editor.putString(Constants.EXTRA_AUTH, authKey);
//        editor.apply();
//        dataManager.saveAuthKey(authKey);
//    }
//
//    private String loadAuthKey() {
//        SharedPreferences sharedPref = this.getPreferences(Context.MODE_PRIVATE);
//        System.out.println("TEST" + dataManager.getAuthKey());
//        return sharedPref.getString(Constants.EXTRA_AUTH, null);
//    }
}
