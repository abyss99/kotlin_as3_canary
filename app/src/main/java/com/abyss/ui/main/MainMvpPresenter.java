package com.abyss.ui.main;


import com.abyss.common.BaseMvpPresenter;
import com.abyss.common.BaseMvpView;

interface
MainMvpPresenter<MvpView extends BaseMvpView> extends BaseMvpPresenter<MvpView> {

    void loadTotoList(boolean isFirst);

    void insert(int id);

    void checked(int id, boolean isChecked);

    void searchQuery(String text);

    void searchFinish();
}
