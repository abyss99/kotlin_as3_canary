package com.abyss.ui.main;


import com.abyss.common.BaseMvpView;
import com.abyss.datas.Todo;

import java.util.List;

/**
 * Created by Lazysoul on 2017. 7. 9..
 */

interface MainMvpView extends BaseMvpView {

    void onUpdateTodoList(List<Todo> todoList);

    void onCreatedTodo(Todo todo);

    void showEmtpyView();

    void onSuccessCreateSampes();
}
