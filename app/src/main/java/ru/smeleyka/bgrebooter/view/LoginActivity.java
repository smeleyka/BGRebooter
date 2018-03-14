package ru.smeleyka.bgrebooter.view;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.reactivex.android.schedulers.AndroidSchedulers;
import ru.smeleyka.bgrebooter.R;
import ru.smeleyka.bgrebooter.presenter.LoginPresenter;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

public class LoginActivity extends AppCompatActivity implements LoginView {

    private LoginPresenter loginPresenter;

    @BindView(R.id.login)    EditText loginEditText;
    @BindView(R.id.password)    EditText passwordEditText;
    @BindView(R.id.email_sign_in_button) Button signInButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);
        loginPresenter = new LoginPresenter(this, AndroidSchedulers.mainThread());
    }

    @OnClick (R.id.email_sign_in_button)
    public void onSignButton() {
        loginPresenter.login(
                                loginEditText.getText().toString(),
                                passwordEditText.getText().toString()
                                                                        );
    }

}
