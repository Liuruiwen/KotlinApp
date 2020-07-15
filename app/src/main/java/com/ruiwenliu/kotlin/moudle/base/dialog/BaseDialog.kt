package com.ruiwenliu.kotlin.moudle.base.dialog

import android.app.Dialog
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.ruiwenliu.kotlin.R
import com.ruiwenliu.kotlin.moudle.base.until.OnViewHolder
import com.ruiwenliu.kotlin.moudle.base.until.ViewHolder
import java.lang.ref.WeakReference

/**
 * Created by Amuse
 * Date:2020/3/29.
 * Desc:
 */
abstract class BaseDialog : Dialog {
    var viewReference:WeakReference<Context>?=null
    constructor(context: Context,viewHelper: OnViewHolder) : super(context, R.style.AlertTipsDialogTheme){
        viewReference = WeakReference(context)
        setContentView(getHelperView(null, getViewLayout(), viewHelper)!!)
    }

    abstract fun getViewLayout(): Int

    /**
     * 实例化对应layoutId的view同时生成ViewHelper
     *
     * @param group    可为null
     * @param layoutId
     * @param listener
     * @return
     */
    protected  fun getHelperView(
        group: ViewGroup?,
        layoutId: Int,
        listener: OnViewHolder
    ): View? {
        var viewHolder = ViewHolder(LayoutInflater.from(getDialogContext()).inflate(layoutId, group, false))
        listener.helper(viewHolder)
        return viewHolder.getItemView()
    }


    /**
     * 设置参数的参考实现
     *
     * @param width
     * @param height
     * @param gravity
     */
    protected  fun setDialogParams(width: Int, height: Int, gravity: Int) {
        val window = this.window
        val params = window!!.attributes
        params.width = width
        params.height = height
        window.setGravity(gravity)
        window.attributes = params
    }



     fun getDialogContext(): Context? {
        return if (viewReference == null) null else viewReference?.get()
    }

    override fun onStop() {
        super.onStop()
            viewReference?.clear()
            viewReference = null

    }

}