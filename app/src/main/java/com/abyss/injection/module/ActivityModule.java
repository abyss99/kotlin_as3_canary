package com.abyss.injection.module;


import android.app.Activity;

import com.abyss.injection.scopes.ActivityScope;

import dagger.Module;
import dagger.Provides;
import io.realm.Realm;

/**
 * Created by Lazysoul on 2017. 7. 17..
 */

@Module
public class ActivityModule {

    private Activity activity;

    public ActivityModule(Activity activity) {
        this.activity = activity;
    }

    @Provides
    @ActivityScope
    Activity provideActivity() {
        return activity;
    }

    @Provides
    @ActivityScope
    Realm provideRealm() {
        return Realm.getDefaultInstance();
    }
}
