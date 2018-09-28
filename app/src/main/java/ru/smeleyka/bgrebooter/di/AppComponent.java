package ru.smeleyka.bgrebooter.di;


import javax.inject.Singleton;

import dagger.Component;
import ru.smeleyka.bgrebooter.di.modules.SharedPreferencesModule;
import ru.smeleyka.bgrebooter.presenter.HostgroupActivityPresenter;
import ru.smeleyka.bgrebooter.presenter.LoginPresenter;
import ru.smeleyka.bgrebooter.presenter.RebootPresenter;
import ru.smeleyka.bgrebooter.view.LoginActivity;
import ru.smeleyka.bgrebooter.view.hostsView.HostgroupActivity;

@Singleton
@Component (modules =  {SharedPreferencesModule.class})

public interface AppComponent {
    void inject(LoginActivity loginActivityctivity);
    void inject(LoginPresenter loginPresenter);
    void inject(RebootPresenter rebootPresenter);
    void inject(HostgroupActivity hostgroupActivity);
    void inject(HostgroupActivityPresenter hostgroupActivityPresenter);
    }
