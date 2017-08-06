package com.abyss.injection.components;


import com.abyss.injection.module.ActivityModule;
import com.abyss.injection.scopes.ActivityScope;
import com.abyss.ui.detail.DetailActivity;
import com.abyss.ui.main.MainActivity;

import dagger.Component;
import io.realm.Realm;

/**
 * Created by Lazysoul on 2017. 7. 17..
 */
@Component(dependencies = {ApplicationComponent.class}, modules = {ActivityModule.class})
@ActivityScope
public interface ActivityComponent extends ApplicationComponent {

    Realm provideRealm();

    void inject(MainActivity activity);

    void inject(DetailActivity activity);
}
