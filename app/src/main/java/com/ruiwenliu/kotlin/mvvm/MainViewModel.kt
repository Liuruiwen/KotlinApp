package com.ruiwenliu.kotlin.mvvm

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

/*   
* @Author:      Amuser
* @CreateDate:   2019-12-23 13:31
*@Description: 
*/
class MainViewModel  :ViewModel(){

    var name = MutableLiveData<String>()
    var sex = MutableLiveData<String>()
    init {
        name.value="王大明"
        sex.value="男"
    }
}