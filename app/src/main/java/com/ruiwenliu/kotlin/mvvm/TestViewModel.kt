package com.ruiwenliu.kotlin.mvvm

import androidx.databinding.ObservableField
import androidx.lifecycle.ViewModel

/*   
* @Author:      Amuser
* @CreateDate:   2019-12-23 14:05
*@Description: 
*/
class TestViewModel :ViewModel(){
    var context = ObservableField<String>()
    init {
        context.set("???")
    }
}