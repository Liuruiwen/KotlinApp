package com.ruiwenliu.kotlin.moudle.base.http

import android.util.Log
import com.google.gson.Gson
import com.ruiwenliu.kotlin.moudle.base.bean.BaseBean
import com.ruiwenliu.kotlin.moudle.base.presenter.BaseView
import io.reactivex.observers.DisposableObserver
import okhttp3.ResponseBody
import retrofit2.HttpException
import retrofit2.Response
import java.lang.Exception
import java.net.ConnectException
import java.net.SocketTimeoutException
import java.net.UnknownHostException
import javax.net.ssl.SSLHandshakeException

/*   
* @Author:      Amuser
* @CreateDate:   2019-12-19 13:49
*@Description: 
*/
class RxRequestResult<T : BaseBean, V : BaseView> constructor(p: Int, bean: Class<T>, view: V) :
    DisposableObserver<Response<ResponseBody>>() {

    var position: Int? = 0
    var baseBean: Class<T>? = null
    var baseView: V? = null

    init {
        this.position = p
        this.baseBean = bean
        this.baseView = view
    }

    override fun onComplete() {
        baseView?.onHideLoading()
    }

    override fun onNext(response: Response<ResponseBody>) {
        baseView?.onHideLoading()
        if (response.isSuccessful) {
            try {
                val result=response.body()?.string()
                val bean = Gson().fromJson(result, baseBean)
//                when(bean.errorCode){
//                    0-> baseView?.onShowResult(position,bean)
//                    else -> baseView?.onShowError(bean.errorMsg,bean.errorCode,position)
//                }

                when(bean.error){
                    false-> baseView?.onShowResult(position,bean)
                    else -> baseView?.onShowError("请求异常",1001,position)
                }

            } catch (e: Exception) {
                baseView?.onShowError("数据解析异常", 303)
                e.printStackTrace()
            }
        }
    }

    override fun onError(e: Throwable) {
        try {
            baseView?.onHideLoading()
            if (e is SocketTimeoutException) {//请求超时
            } else if (e is ConnectException) {//网络连接超时
                //                ToastManager.showShortToast("网络连接超时");
                baseView?.onShowError("服务器地址不正确", position)
            } else if (e is SSLHandshakeException) {//安全证书异常
                //                ToastManager.showShortToast("安全证书异常");
                baseView?.onShowError("安全证书异常", position)
            } else if (e is HttpException) {//请求的地址不存在
                val code = e.code()
                if (code == 504) {
                    //                    ToastManager.showShortToast("网络异常，请检查您的网络状态");
                    baseView?.onShowError("网络异常，请检查您的网络状态", position)
                } else if (code == 404) {
                    //                    ToastManager.showShortToast("请求的地址不存在");
                    baseView?.onShowError("请求的地址不存在", position)
                } else {
                    //                    ToastManager.showShortToast("请求失败");
                    baseView?.onShowError("请求失败", position)
                }
            } else if (e is UnknownHostException) {//域名解析失败
                //                ToastManager.showShortToast("域名解析失败");
                baseView?.onShowError("域名解析失败", position)
            } else {
                //                ToastManager.showShortToast("error:" + e.getMessage());
                baseView?.onShowError("error:" + e.message, position)
            }
        } catch (e2: Exception) {
            e2.printStackTrace()
        } finally {
            Log.e("OnSuccessAndFaultSub", "error:" + e.message)
            //            mOnSuccessAndFaultListener.onFault("error:" + e.getMessage());
            //            dismissProgressDialog();
        }
    }
}