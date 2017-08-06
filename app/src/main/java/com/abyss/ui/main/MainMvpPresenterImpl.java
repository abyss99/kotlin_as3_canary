package com.abyss.ui.main;


import com.abyss.common.BaseMvpView;
import com.abyss.common.RxPresenter;
import com.abyss.datas.Todo;
import com.abyss.singletons.TodoManager;

import java.util.concurrent.TimeUnit;

import io.reactivex.Single;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Action;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Predicate;
import io.reactivex.subjects.PublishSubject;
import io.realm.Realm;
import io.realm.RealmResults;

/**
 * Created by Lazysoul on 2017. 7. 9..
 */

class MainMvpPresenterImpl<MvpView extends BaseMvpView> extends RxPresenter
        implements MainMvpPresenter<MvpView> {

    private MainMvpView view;

    private Realm realm;

    private PublishSubject<String> searchTextChangeSubject = PublishSubject.create();

    MainMvpPresenterImpl(final Realm realm) {
        this.realm = realm;

        add(searchTextChangeSubject
                .throttleLast(1, TimeUnit.SECONDS)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        new Consumer<String>() {
                            @Override
                            public void accept(String text) throws Exception {
                                RealmResults<Todo> todoList = TodoManager.search(realm, text);
                                if (todoList.isEmpty()) {
                                    view.showEmtpyView();
                                } else {
                                    view.onUpdateTodoList(todoList);
                                }
                            }
                        }));
    }

    @Override
    public void attachView(MvpView view) {
        this.view = (MainMvpView) view;
    }

    @Override
    public void destroy() {
        dispose();
        realm.close();
    }

    @Override
    public void loadTotoList(final boolean isFirst) {
        add(Single.just(isFirst)
                .filter(new Predicate<Boolean>() {
                    @Override
                    public boolean test(Boolean isFirst) throws Exception {
                        return !isFirst;
                    }
                })
                .doOnSuccess(new Consumer<Boolean>() {
                    @Override
                    public void accept(Boolean isFirst) throws Exception {
                        view.onSuccessCreateSampes();
                    }
                })
                .observeOn(AndroidSchedulers.mainThread())
                .doFinally(new Action() {
                    @Override
                    public void run() throws Exception {
                        loadTodoList();
                    }
                })
                .subscribe(new Consumer<Boolean>() {
                    @Override
                    public void accept(Boolean isfirst) throws Exception {
                        createdSample();
                    }
                }));
    }

    private void loadTodoList() {
        RealmResults<Todo> todoList = TodoManager.getTodoList(realm);
        if (null != todoList && todoList.isEmpty()) {
            view.showEmtpyView();
        } else {
            view.onUpdateTodoList(todoList);
        }
    }

    private void createdSample() {
        TodoManager.createSamleTodo(realm);
    }

    @Override
    public void insert(int id) {
        Todo todo = TodoManager.load(realm, id);
        view.onCreatedTodo(todo);
    }

    @Override
    public void checked(int id, boolean isChecked) {
        realm.beginTransaction();
        TodoManager.load(realm, id).setChecked(isChecked);
        realm.commitTransaction();
    }

    @Override
    public void searchQuery(String text) {
        searchTextChangeSubject.onNext(text);
    }

    @Override
    public void searchFinish() {
        loadTodoList();
    }
}
