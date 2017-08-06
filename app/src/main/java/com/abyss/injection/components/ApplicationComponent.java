package com.abyss.injection.components;


import android.content.SharedPreferences;

import com.abyss.KotlinWithAndroid;
import com.abyss.injection.module.ApplicationModule;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by Lazysoul on 2017. 7. 17..
 */

@Singleton
@Component(modules = {ApplicationModule.class})
public interface ApplicationComponent {

    void injet(KotlinWithAndroid kotlinWithAndroid);

    SharedPreferences getSharedPreferences();

    SharedPreferences.Editor getEditor();
}
