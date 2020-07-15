package com.ruiwenliu.kotlin.moudle.base

import android.app.Activity
import android.app.ProgressDialog
import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.Window
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.ruiwenliu.kotlin.R
import com.ruiwenliu.kotlin.moudle.base.dialog.LoadingDialog
import com.ruiwenliu.kotlin.moudle.base.presenter.BaseView
import com.ruiwenliu.kotlin.moudle.base.presenter.MvpPresenter
import com.ruiwenliu.kotlin.moudle.base.presenter.WrapperPresenter
import java.lang.reflect.ParameterizedType






/**
 * Created by Amuse
 * Date:2020/2/11.
 * Desc:
 */
abstract class BaseWrapperFragment<P : MvpPresenter<BaseView>>: Fragment(),BaseView  {

    protected var mPresenter: P? = null
    private var mLoadingDialog: LoadingDialog? = null
    protected var contentView: View? = null
    /**
     * 是否初始化过布局
     */
    protected var isViewInitiated: Boolean = false
    /**
     * 当前界面是否可见
     */
    protected var isVisibleToUser: Boolean = false
    /**
     * 是否加载过数据
     */
    protected var isDataInitiated: Boolean = false
    protected var mContext:Activity?=null;
    var mToast: Toast? = null
    override fun onAttach(context: Context) {
        super.onAttach(context)
        mContext=context as Activity;
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
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


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        contentView = inflater.inflate(getViewLayout(), container, false)
        return contentView
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initView()
        loadData()
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        isViewInitiated=true;
        //加载数据
        prepareFetchData(false);
    }

    override fun onDestroyView() {
        super.onDestroyView()
        onHideLoading()
        mLoadingDialog = null
        mPresenter?.detachView()
        mPresenter = null
    }

    override fun setUserVisibleHint(isVisibleToUser: Boolean) {
        super.setUserVisibleHint(isVisibleToUser)
        this.isVisibleToUser = isVisibleToUser;
        if (isVisibleToUser) {
            prepareFetchData(false);
        }
    }

    override fun onShowLoading() {
        Log.d("是否进来了弹窗", "========${mLoadingDialog?.isShowing}")
        if(mLoadingDialog!=null && !mLoadingDialog?.isShowing!!){
            mLoadingDialog?.show()
        }

    }

    override fun onHideLoading() {

            if (mLoadingDialog?.isShowing()?:false) {
                mLoadingDialog?.dismiss()
            }


    }

    override fun onShowError(errorMsg: String?, errorType: Int?, position: Int?) {
    }
    override fun onBeforeSuccess() {
    }

    protected fun createLoadingDialog(): LoadingDialog {
        if (mLoadingDialog == null) {
            mLoadingDialog = LoadingDialog(mContext, R.style.Alert_Dialog_Style)
            mLoadingDialog?.requestWindowFeature(Window.FEATURE_NO_TITLE)
            mLoadingDialog?.setCanceledOnTouchOutside(false)
            mLoadingDialog?.setProgressStyle(ProgressDialog.STYLE_SPINNER)
        }
        return mLoadingDialog as LoadingDialog
    }



    /**
     * @return 布局resourceId
     */
    abstract fun getViewLayout(): Int

    /**
     * 初始化View。或者其他view级第三方控件的初始化,及相关点击事件的绑定
     *
     * @param savedInstanceState
     */
    protected abstract fun initView()

    /**
     * 从intent中获取请求参数，初始化vo对象，并发送请求
     *
     * @param savedInstanceState
     */
    protected abstract fun loadData()
    /**
     * 判断懒加载条件
     *
     * @param forceUpdate 强制更新，好像没什么用？
     */
    fun prepareFetchData(forceUpdate: Boolean) {
        if (isVisibleToUser && isViewInitiated && (!isDataInitiated || forceUpdate)) {
            lazyData()
            isDataInitiated = true
        }
    }

    /**
     * 数据懒加载
     *
     * @param savedInstanceState
     */
    protected abstract fun lazyData()

    protected fun  showToast(msg:String){
        if (mToast == null) {
            mToast = Toast.makeText(mContext?.getApplicationContext(), "", Toast.LENGTH_SHORT);
        }
        mToast?.setText(msg);
        mToast?.show();
    }
}