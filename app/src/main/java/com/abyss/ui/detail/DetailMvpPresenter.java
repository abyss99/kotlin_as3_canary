package com.abyss.ui.detail;


import android.content.Intent;

import com.abyss.common.BaseMvpPresenter;
import com.abyss.common.BaseMvpView;

/**
 * Created by Lazysoul on 2017. 7. 15..
 */

interface DetailMvpPresenter<MvpView extends BaseMvpView> extends BaseMvpPresenter<MvpView> {

    void loadTodo(Intent intent);

    void onTextChanged(String s);

    boolean isFixed();

    void saveTodo(String text);
}
