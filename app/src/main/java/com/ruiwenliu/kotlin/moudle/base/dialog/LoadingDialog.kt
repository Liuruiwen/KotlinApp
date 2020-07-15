@file:Suppress("DEPRECATION")

package com.ruiwenliu.kotlin.moudle.base.dialog

import android.app.ProgressDialog
import android.content.Context
import android.os.Bundle
import android.view.WindowManager
import com.ruiwenliu.kotlin.R


/*   
* @Author:      Amuser
* @CreateDate:   2019-12-20 15:22
*@Description: 
*/
class LoadingDialog(context: Context?,style:Int?) : ProgressDialog(context) {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initView()
    }
    fun initView(){
      setCancelable(true)
        setCanceledOnTouchOutside(false)
        setContentView(R.layout.dialog_loading_progressly)//loading的xml文件
        val params = window!!.attributes
        params.width = WindowManager.LayoutParams.WRAP_CONTENT
        params.height = WindowManager.LayoutParams.WRAP_CONTENT
        window!!.attributes = params
    }

}