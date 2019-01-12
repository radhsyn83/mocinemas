package com.example.fathurradhy.mocinemas.domain.view;

public interface DefaultView<T> {
    void onSuccess(T data);
    void onFailed(String msg);
}
