package ru.smeleyka.bgrebooter.di;


import javax.inject.Singleton;

import dagger.Component;
import ru.smeleyka.bgrebooter.di.modules.AndroidResourcesModule;
import ru.smeleyka.bgrebooter.presenter.LoginPresenter;
import ru.smeleyka.bgrebooter.view.LoginActivity;

@Singleton
@Component (
        modules = {
                AndroidResourcesModule.class
        }
        )

public interface AppComponent {
    void inject(LoginActivity loginActivityctivity);
    void inject(LoginPresenter loginPresenter);

}
