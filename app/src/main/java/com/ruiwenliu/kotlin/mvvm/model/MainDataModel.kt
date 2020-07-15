package com.ruiwenliu.kotlin.mvvm.model


/**
 * Created by Amuse
 * Date:2020/2/10.
 * Desc:
 */
class MainDataModel : MutableLiveData<MainBean>() {

         companion object{
             private  var instance:MainDataModel?=null;
             @Synchronized
             fun get():MainDataModel{
                 if (instance==null)
                 {instance=MainDataModel();}
                 return instance as MainDataModel;

             }
         }



}