package com.ruiwenliu.kotlin.moudle.base

import com.ruiwenliu.kotlin.moudle.base.presenter.BaseView
import com.ruiwenliu.kotlin.moudle.base.presenter.MvpPresenter

/**
 * Created by Amuse
 * Date:2020/2/12.
 * Desc:
 */
abstract  class BaseFragment<P:MvpPresenter<BaseView>> :BaseWrapperFragment<P>(){
    override fun onShowLoading() {
        super.onShowLoading()
    }

    override fun lazyData() {
//       showToast("第一次加载哦")
    }


}