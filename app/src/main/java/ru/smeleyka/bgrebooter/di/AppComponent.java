package ru.smeleyka.bgrebooter.di;


import android.content.Context;

import javax.inject.Singleton;

import dagger.BindsInstance;
import dagger.Component;
import ru.smeleyka.bgrebooter.di.modules.ContextModule;
import ru.smeleyka.bgrebooter.di.modules.SharedPreferencesModule;
import ru.smeleyka.bgrebooter.presenter.LoginPresenter;
import ru.smeleyka.bgrebooter.view.LoginActivity;

@Singleton
@Component (modules =  SharedPreferencesModule.class)

public interface AppComponent {
    void inject(LoginActivity loginActivityctivity);
    void inject(LoginPresenter loginPresenter);
    }
