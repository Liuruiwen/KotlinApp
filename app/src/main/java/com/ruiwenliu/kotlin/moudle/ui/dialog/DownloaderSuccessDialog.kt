package com.ruiwenliu.kotlin.moudle.ui.dialog

import android.content.Context
import android.view.Gravity
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.ruiwenliu.kotlin.R
import com.ruiwenliu.kotlin.moudle.base.dialog.BaseDialog
import com.ruiwenliu.kotlin.moudle.base.until.OnViewHolder
import com.ruiwenliu.kotlin.moudle.base.until.UtilsManager
import com.ruiwenliu.kotlin.moudle.base.until.ViewHolder

/**
 * Created by Amuse
 * Date:2020/3/29.
 * Desc:
 */
class DownloaderSuccessDialog :BaseDialog {
    constructor(context: Context, holder: OnViewHolder) : super(context,holder){
        setDialogParams(UtilsManager.getInstance(getDialogContext()!!).getWindowWidth()/4*3, ViewGroup.LayoutParams.WRAP_CONTENT, Gravity.CENTER)
    }


    override fun getViewLayout(): Int {
        return  R.layout.dialog_downloader_success
    }



//    override fun helper(helper: ViewHolder) {
//        super.helper(helper)
//        Toast.makeText(context,"你点击了取消按钮"+this.desc,Toast.LENGTH_LONG).show()
//        helper.setText(R.id.tv_message,this.desc)
//        helper.setOnClickListener(R.id.tv_cancel,View.OnClickListener {
//            Toast.makeText(context,"你点击了取消按钮",Toast.LENGTH_LONG).show()
//            dismiss()
//        })
//    }
}