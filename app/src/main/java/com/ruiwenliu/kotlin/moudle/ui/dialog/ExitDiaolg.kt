package com.ruiwenliu.kotlin.moudle.ui.dialog

import android.app.Dialog
import android.content.Context
import android.view.Gravity
import android.view.View
import android.view.ViewGroup
import com.ruiwenliu.kotlin.R

/**
 * Created by Amuse
 * Date:2020/3/29.
 * Desc:
 */
class ExitDiaolg  : Dialog {
//    constructor(context: Context) : this(context,0)
    constructor(context: Context) : super(context, R.style.AlertTipsDialogTheme){
        setContentView(R.layout.dialog_downloader_success)
        window?.setGravity(Gravity.CENTER)
        window?.setLayout(ViewGroup.LayoutParams.MATCH_PARENT,ViewGroup.LayoutParams.WRAP_CONTENT)
    }
//    public fun setOnclickListener(listener: View.OnClickListener){
//        if (positivebtexit !== null){
//            positivebtexit.setOnClickListener(listener)
//        }
//        if (negetiveimgexit !== null){
//            negetiveimgexit.setOnClickListener(listener)
//        }
//
//    }
}