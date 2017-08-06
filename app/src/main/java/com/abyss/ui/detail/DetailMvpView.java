package com.abyss.ui.detail;

import com.abyss.common.BaseMvpView;
import com.abyss.datas.Todo;

/**
 * Created by Lazysoul on 2017. 7. 15..
 */

public interface DetailMvpView extends BaseMvpView {

    void onUpdated(Todo todo, boolean editable);

    void onChagedSaveBt();

    void onSaved(int requestType, int todoId);
}
