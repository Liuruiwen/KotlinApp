package com.ruiwenliu.kotlin.moudle.ui.home.bean

import android.os.Parcel
import android.os.Parcelable

/**
 * Created by Amuse
 * Date:2020/3/16.
 * Desc:
 */
class ItemBodyBean(override val itemType: Int) : Parcelable, MultiItemBean{
    var imageUrl:String?=null;
    var index=0
    var urlGroup:String?=null
    constructor(parcel: Parcel) : this(parcel.readInt()) {
        imageUrl = parcel.readString()
        index=parcel.readInt()
        urlGroup = parcel.readString()
    }

    override fun getSpanSize(): Int {
        return 3
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeInt(itemType)
        parcel.writeString(imageUrl)
        parcel.writeInt(index)
        parcel.writeString(urlGroup)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<ItemBodyBean> {
        override fun createFromParcel(parcel: Parcel): ItemBodyBean {
            return ItemBodyBean(parcel)
        }

        override fun newArray(size: Int): Array<ItemBodyBean?> {
            return arrayOfNulls(size)
        }
    }
}