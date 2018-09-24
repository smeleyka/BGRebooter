package ru.smeleyka.bgrebooter.di.modules;

import dagger.Module;
import dagger.Provides;
import ru.smeleyka.bgrebooter.view.LoginView;

@Module
public class LoginViewModule {

    LoginView loginView;

    LoginViewModule(LoginView loginView){
        this.loginView=loginView;
    }

    @Provides
    public LoginView getLoginView() {
        return loginView;
    }
}
