package com.ruiwenliu.kotlin.moudle.util

import android.content.Context
import android.view.View
import android.widget.ImageView
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.bumptech.glide.load.resource.bitmap.CircleCrop
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.request.RequestOptions
import com.chad.library.adapter.base.viewholder.BaseViewHolder
import com.ruiwenliu.glide.library.GlideApp
import com.ruiwenliu.kotlin.R

/**
 * Created by Amuse
 * Date:2020/3/22.
 * Desc:
 */
class BaseGlideHolder(view: View) : BaseViewHolder(view) {
    /**
     * 加载普通图片
     *
     * @param url
     * @param image
     */
    fun loadImage(context: Context, url: String, image: ImageView) {
        GlideApp.with(context)
            .load(url)
            .apply(
                RequestOptions()
                    .skipMemoryCache(true)
                    .diskCacheStrategy(DiskCacheStrategy.ALL)
            )
            .into(image)
    }


    /**
     * 加载普通图片
     *
     * @param url
     * @param image
     */
    fun loadImage(context: Context, url: String?, image: ImageView, x: Int, y: Int
    ) {
        GlideApp.with(context)
            .load(url)
            .apply(
                RequestOptions()
                    .skipMemoryCache(true)
                    .diskCacheStrategy(DiskCacheStrategy.ALL).override(x, y)
            )
            .into(image)
    }

    /**
     * 加载圆角图片
     *
     * @param url
     * @param image
     * @param round
     */
    fun loadRoundImage(context: Context, url: String, image: ImageView, round: Int
    ) {
        GlideApp.with(context)
            .load(getImageUrl(url))
            .placeholder(R.drawable.placeholder)
            .fallback(R.drawable.placeholder)
            .apply(RequestOptions().transform(CenterCrop(), RoundedCorners(round)))
            .into(image)
    }

    /**
     * 加载圆角图片
     * 通过RequestOptions扩展功能,override:采样率,因为ImageView就这么大,可以压缩图片,降低内存消耗,设置图片压缩比例
     *
     * @param url
     * @param image
     * @param round
     * @param x
     * @param y
     */
    fun loadRoundImage(context: Context, url: String, image: ImageView, round: Int, x: Int, y: Int
    ) {
        GlideApp.with(context)
            .load(url)
            .apply(RequestOptions.bitmapTransform(RoundedCorners(round)).override(x, y))
            .into(image)
    }


    /**
     * 加载圆形图片
     *
     * @param url
     * @param image
     */
    fun loadCircleImage(context: Context, url: String, image: ImageView) {
        GlideApp.with(context)
            .load(url)
            .apply(RequestOptions.bitmapTransform(CircleCrop()))
            .into(image)
    }


    /**
     * 加载圆形图片
     *
     * @param url
     * @param image
     */
    fun loadCircle(context: Context, url: String, image: ImageView) {
        GlideApp.with(context)
            .load(url)
            .apply(
                RequestOptions.circleCropTransform()
                    .diskCacheStrategy(DiskCacheStrategy.NONE) //不做磁盘缓存
                    .skipMemoryCache(true) //不做内存缓存
            )
            .into(image)
    }

    fun getImageUrl(url: String): String? {
        return if (url.contains("http://")) {
            url
        } else {
            Common.HTTP_BASE_URL.toString() + url
        }
    }


}