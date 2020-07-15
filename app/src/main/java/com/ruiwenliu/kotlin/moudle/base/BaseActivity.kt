package com.ruiwenliu.kotlin.moudle.base

import android.app.ProgressDialog
import android.os.Bundle
import android.util.Log
import android.view.Window
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.ruiwenliu.kotlin.R
import com.ruiwenliu.kotlin.moudle.base.bean.BaseBean
import com.ruiwenliu.kotlin.moudle.base.dialog.LoadingDialog
import com.ruiwenliu.kotlin.moudle.base.presenter.BaseView
import com.ruiwenliu.kotlin.moudle.base.presenter.MvpPresenter
import com.ruiwenliu.kotlin.moudle.base.presenter.WrapperPresenter
import java.lang.reflect.ParameterizedType

/*
* @Author:      Amuser
* @CreateDate:   2019-12-18 9:19
*@Description: 
*/
abstract class BaseActivity<P : MvpPresenter<BaseView>> : BaseWrapperActivity(), BaseView {


    protected var mPresenter: P? = null
    private var mLoadingDialog: LoadingDialog? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        setContentView(getLayout())
//        intiView()
        val type = this.javaClass.getGenericSuperclass() as ParameterizedType
        // Class<? extends WrapperPresenter> presenterClass = (Class<? extends WrapperPresenter>) type.getActualTypeArguments()[0];
        val presenterClass = type.actualTypeArguments[0] as Class<out WrapperPresenter<BaseView>>
        try {
            this.mPresenter = presenterClass.newInstance() as P
        } catch (e: IllegalAccessException) {
            e.printStackTrace()
        } catch (e: java.lang.InstantiationException) {
            e.printStackTrace()
        }


        mPresenter?.attachView(this)
        mLoadingDialog = createLoadingDialog();
    }

    override fun onDestroy() {
        super.onDestroy()
        onHideLoading()
        mLoadingDialog = null
        mPresenter?.detachView()
        mPresenter = null
    }

    override fun onBeforeSuccess() {
    }

    override fun onShowLoading() {
        Log.d("是否进来了弹窗", "========${mLoadingDialog?.isShowing}")
        if(mLoadingDialog!=null && !mLoadingDialog?.isShowing!!){
          mLoadingDialog?.show()
        }

    }

    override fun onHideLoading() {
        if (!isFinishing && mLoadingDialog?.isShowing()!!) {
            runOnUiThread { mLoadingDialog?.dismiss() }
        }
    }

    override fun onShowError(errorMsg: String?, errorType: Int?, position: Int?) {
    }


    protected fun createLoadingDialog(): LoadingDialog {
        if (mLoadingDialog == null) {
            mLoadingDialog = LoadingDialog(this, R.style.Alert_Dialog_Style)
            mLoadingDialog?.requestWindowFeature(Window.FEATURE_NO_TITLE)
            mLoadingDialog?.setCanceledOnTouchOutside(false)
            mLoadingDialog?.setProgressStyle(ProgressDialog.STYLE_SPINNER)
        }
        return mLoadingDialog as LoadingDialog
    }


//    fun  showToast(message:String){
//        Toast.makeText(this,message, Toast.LENGTH_SHORT).show()
//    }
}