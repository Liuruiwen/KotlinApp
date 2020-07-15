package com.ruiwenliu.kotlin.moudle

import com.ruiwenliu.kotlin.moudle.base.presenter.BaseView
import com.ruiwenliu.kotlin.moudle.base.presenter.MvpPresenter

/*   
* @Author:      Amuser
* @CreateDate:   2019-12-19 17:05
*@Description: 
*/
class MainPresenter : MvpPresenter<BaseView>(){
    override fun getBaseUrl(): String {
//        return  "https://www.wanandroid.com/"
        return  Common.HTTP_BASE_URL
    }
//https://www.oschina.net/openapi
    //"https://www.wanandroid.com/"
    override fun getToken(): String {
        return ""
    }
}