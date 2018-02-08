package com.example.x5.a20180208_alvinjean_nyschools.utils;


public interface BasePresenter <V extends BaseView> {
    void attachView(V view);
    void detachView();
}