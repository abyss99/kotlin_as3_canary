package com.abyss.common;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.abyss.KotlinWithAndroid;
import com.abyss.injection.components.ActivityComponent;
import com.abyss.injection.components.ApplicationComponent;
import com.abyss.injection.module.ActivityModule;
import com.abyss.injection.components.DaggerActivityComponent;


/**
 * Created by Lazysoul on 2017. 7. 15..
 */

public abstract class BaseActivity extends AppCompatActivity implements BaseMvpView {

    protected ActivityComponent component;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        component = DaggerActivityComponent
                .builder()
                .applicationComponent(getApplicationComponet())
                .activityModule(new ActivityModule(this))
                .build();

        inject();
        initPresenter();
    }

    public ApplicationComponent getApplicationComponet() {
        return ((KotlinWithAndroid) getApplication()).getComponent();
    }
}
