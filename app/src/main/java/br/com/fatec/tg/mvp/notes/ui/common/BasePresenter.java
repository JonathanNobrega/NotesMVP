package br.com.fatec.tg.mvp.notes.ui.common;

import android.support.annotation.NonNull;

public interface BasePresenter<T> {

    void attachView(@NonNull T view);

    void detachView();
}
