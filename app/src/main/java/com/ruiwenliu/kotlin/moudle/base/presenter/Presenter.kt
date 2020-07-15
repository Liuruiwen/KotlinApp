package com.ruiwenliu.kotlin.moudle.base.presenter

interface Presenter<V> {
    fun  attachView(mvpView:V);
    fun  detachView();
}