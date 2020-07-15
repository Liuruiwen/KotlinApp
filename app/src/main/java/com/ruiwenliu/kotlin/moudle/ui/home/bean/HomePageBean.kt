package com.ruiwenliu.kotlin.moudle.ui.home.bean

import android.os.Build
import android.os.Parcel
import android.os.Parcelable
import androidx.annotation.RequiresApi
import com.ruiwenliu.kotlin.moudle.base.bean.BaseBean
import com.ruiwenliu.kotlin.moudle.ui.home.adapter.HomeChildPageAdapter

/**
 * Created by Amuse
 * Date:2020/3/1.
 * Desc:首页实体类
 */
 class HomePageBean :BaseBean(){
    var category: List<String>?=null;
    var results: Results?=null;
}



data class Results(
    val Android: List<PageDataBean>,
    val App: List<PageDataBean>,
    val iOS: List<PageDataBean>,
    val 休息视频: List<PageDataBean>,
    val 前端: List<PageDataBean>,
    val 拓展资源: List<PageDataBean>,
    val 瞎推荐: List<PageDataBean>,
    val 福利: List<PageDataBean>
)

 class PageDataBean(override var itemType: Int) : Parcelable,MultiItemBean{
     override fun getSpanSize(): Int {
         return 10
     }

     var _id: String?=null
     var createdAt: String?=null
     var desc: String?=null
     var images: List<String>?=null
     var publishedAt: String?=null
     var source: String?=null
     var type: String?=null
     var url: String?=null
     var used: Boolean?=false
     var who: String?=null

     @RequiresApi(Build.VERSION_CODES.Q)
     constructor(parcel: Parcel) : this(HomeChildPageAdapter.TYPE_ITEM_HEAD) {
         _id=parcel.readString()
         createdAt=parcel.readString()
         desc=parcel.readString()
         images=parcel.createStringArrayList()
         publishedAt=parcel.readString()
         source=parcel.readString()
         url=parcel.readString()
         used=parcel.readBoolean()
         who=parcel.readString()
     }

     @RequiresApi(Build.VERSION_CODES.Q)
     override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(_id)
         parcel.writeString(createdAt)
         parcel.writeString(desc)
         parcel.writeStringList(images)
         parcel.writeString(publishedAt)
         parcel.writeString(source)
         parcel.writeBoolean(used?:false)
         parcel.writeString(who)
     }

     override fun describeContents(): Int {
         return 0
     }

     companion object CREATOR : Parcelable.Creator<PageDataBean> {
         @RequiresApi(Build.VERSION_CODES.Q)
         override fun createFromParcel(parcel: Parcel): PageDataBean {
             return PageDataBean(parcel)
         }

         override fun newArray(size: Int): Array<PageDataBean?> {
             return arrayOfNulls(size)
         }
     }

 }


