package com.ruiwenliu.kotlin.mvvm.model

import androidx.lifecycle.LiveData

/**
 * Created by Amuse
 * Date:2020/2/10.
 * Desc:
 */
 open class MutableLiveData<T> : LiveData<T>() {

    public override fun postValue(value: T) {
        super.postValue(value)
    }

    public override fun setValue(value: T) {
        super.setValue(value)
    }
}