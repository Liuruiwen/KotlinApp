package com.ruiwenliu.kotlin.moudle.ui

import android.content.ContentValues
import android.content.Context
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.media.MediaScannerConnection
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.provider.MediaStore
import android.text.TextUtils
import android.view.LayoutInflater
import android.view.View
import android.widget.ImageView
import androidx.viewpager.widget.ViewPager
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.target.Target
import com.ruiwenliu.glide.library.GlideManager
import com.ruiwenliu.glide.library.ProgressInterceptor
import com.ruiwenliu.glide.library.ProgressListener
import com.ruiwenliu.kotlin.R
import com.ruiwenliu.kotlin.moudle.MainPresenter
import com.ruiwenliu.kotlin.moudle.base.BaseActivity
import com.ruiwenliu.kotlin.moudle.base.bean.BaseBean
import com.ruiwenliu.kotlin.moudle.base.until.FileUtil
import com.ruiwenliu.kotlin.moudle.base.until.OnViewHolder
import com.ruiwenliu.kotlin.moudle.base.until.ViewHolder
import com.ruiwenliu.kotlin.moudle.base.widget.TitleView
import com.ruiwenliu.kotlin.moudle.ui.adapter.CarouselAdapter
import com.ruiwenliu.kotlin.moudle.ui.dialog.DownloaderSuccessDialog
import io.reactivex.Observable
import io.reactivex.ObservableOnSubscribe
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.functions.Consumer
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.activity_image_browse.*
import java.io.File
import java.io.FileOutputStream
import java.io.IOException
import java.net.URL
import kotlin.Exception


/**
 * 预览图片
 */
class ImageBrowseActivity : BaseActivity<MainPresenter>() {

    var index=0
    var titleView:TitleView?=null
    companion object{
      fun  getIntent(context:Context,list:ArrayList<String>,index:Int):Intent{
          return  Intent(context,ImageBrowseActivity().javaClass)
              .putExtra("index",index)
              .putStringArrayListExtra("list",list)

        }
    }


    override fun setTitle(title: CharSequence?) {
        super.setTitle(title)
    }

    override fun setLayout(): Int {
      return  R.layout.activity_image_browse
    }

    override fun initData(savedInstanceState: Bundle?, titleView: TitleView) {
        this.titleView=titleView
      val list=  intent.getStringArrayListExtra("list")
        index=intent.getIntExtra("index",0)
        this.titleView?.setTitle(String.format("%1d / %2d",index+1,list.size))
//        GlideManager.getInstance(mActivity!!).loadImage(list[0],iv_test)
        view_pager.adapter=CarouselAdapter(getListImageView(list))
        view_pager.setCurrentItem(index)
        view_pager.addOnPageChangeListener(object :ViewPager.OnPageChangeListener{
            override fun onPageScrollStateChanged(state: Int) {
            }
            override fun onPageSelected(position: Int) {
                index=position
                titleView?.setTitle(String.format("%1d / %2d",index+1,list.size))
            }

            override fun onPageScrolled(
                position: Int,
                positionOffset: Float,
                positionOffsetPixels: Int
            ) {
            }
        })

    }


    override fun <T : BaseBean> onShowResult(requestType: Int?, result: T) {
    }

    var dialogs:DownloaderSuccessDialog?=null

    fun getListImageView(listData:List<String>):List<ImageView>
    {
        val listImage=ArrayList<ImageView>()
            for (i  in 0  until  listData.size){
            val iv=LayoutInflater.from(mActivity).inflate(R.layout.item_image_view,null) as ImageView
            GlideManager.getInstance(mActivity!!).loadImage(listData.get(i),iv)
                iv.setOnLongClickListener {
                 dialogs=    DownloaderSuccessDialog(mActivity!!,object :OnViewHolder{
                    override fun helper(helper: ViewHolder) {
                        helper.setText(R.id.tv_message,"是否保存到本地？")
                        helper.setOnClickListener(View.OnClickListener {
                            when(it.id){
                                R.id.tv_cancel->dialogs?.dismiss()
                                R.id.tv_confirm->downloaderImage(listData.get(i))
                            }
                                },R.id.tv_cancel,R.id.tv_confirm)
                    }
                })
                    dialogs?.show()
                    true}

            listImage.add(iv)
        }

        return listImage
    }

    private fun downloaderImage( url:String){

        ProgressInterceptor.addListener(url,object :ProgressListener{
            override fun onProgress(progress: Int) {
               onShowLoading()
            }
        })



             GlideManager.getInstance(mActivity!!).downloaderImage(url,object :
                 RequestListener<File> {
                 override fun onLoadFailed(
                     e: GlideException?,
                     model: Any?,
                     target: Target<File>?,
                     isFirstResource: Boolean
                 ): Boolean {
                     onHideLoading()
                     ProgressInterceptor.removeListener(url);

                     return false
                 }

                 override fun onResourceReady(
                     resource: File?,
                     model: Any?,
                     target: Target<File>?,
                     dataSource: DataSource?,
                     isFirstResource: Boolean
                 ): Boolean {
                     onHideLoading()
                     ProgressInterceptor.removeListener(url);
//                     var bitmap=BitmapFactory.decodeFile( resource?.getAbsolutePath())
//                     saveImage("warp",bitmap)
                     resource?.getAbsolutePath()?.let { saveImage(it) };
                     return false
                 }
             })
    }



     fun getBitMBitmap(urlpath: String?): Bitmap? {

        var map:Bitmap?= null;
        try {
            val url =  URL(urlpath);
            val conn = url.openConnection();
            conn.connect()
            val stream = conn.getInputStream();
            map = BitmapFactory.decodeStream(stream);
            // TODO Auto-generated catch block
        } catch ( e: IOException) {
            e.printStackTrace();
        }
        return map;
    }


    fun saveImage(path:String){
        Observable.create<Int>(ObservableOnSubscribe {

            try {
                var folder=FileUtil.getInstance(mActivity!!).getFilePath()
                if(!folder?.exists()!!){
                    folder.mkdir();
                }
                val fileName=""+System.currentTimeMillis()+".jpg"
                var file=File(folder,fileName)
                val fos= FileOutputStream(file)
                var bitmap=BitmapFactory.decodeFile(path)
                if (bitmap != null) {
                        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, fos);
                        fos.flush();
                        fos.close();
//                    FileUtil.getInstance(mActivity!!).saveBitmap(mActivity!!,bitmap,System.currentTimeMillis().toString())

//                    saveImage("warp",bitmap)

                    MediaScannerConnection.scanFile(mActivity, arrayOf(file.getAbsolutePath()), arrayOf(fileName),
                        object:MediaScannerConnection.OnScanCompletedListener{
                            override fun onScanCompleted(path: String?, uri: Uri?) {
                                if(!TextUtils.isEmpty(path)){
                                    it.onNext(1);
                                }
                            }

                        })
                        sendBroadcast( Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE,
                            Uri.fromFile( File(file.getPath()))));
                    }
//                    it.onNext(1);
            }catch (e:Exception){

            }


        }).subscribeOn(Schedulers.newThread())
         .observeOn(AndroidSchedulers.mainThread())
            .subscribe(Consumer {
                dialogs?.dismiss()
            when(it){
                0->showToast("保存失败")
                1->showToast("保存成功")
            }

        })



//        Observable.create(new ObservableOnSubscribe<Integer>() {
//            @Override
//            public void subscribe(ObservableEmitter<Integer> integer) throws Exception {
//                try {
//                    File folder = new File(AppConfig.DEFAULT_IMAGE_PATH);
//                    if (!folder.exists()) {
//                        folder.mkdir();
//                    }
//                    String fileName = System.currentTimeMillis() + ".jpg";
//                    File file = new File(folder, fileName);
//                    FileOutputStream fos = new FileOutputStream(file);
//                    Bitmap bitmap = BitmapFactory.decodeFile(path);
//                    if (bitmap != null) {
//                        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, fos);
//                        fos.flush();
//                        fos.close();
//                        MediaStore.Images.Media.insertImage(TestActivity.this.getContentResolver(),
//                            file.getAbsolutePath(), fileName, null);
//                        sendBroadcast(new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE,
//                            Uri.fromFile(new File(file.getPath()))));
//                    }
//                    integer.onNext(1);
//                } catch (Exception ex) {
//                    integer.onNext(0);
//                }
//            }
//        }).subscribeOn(Schedulers.newThread())
//            .observeOn(AndroidSchedulers.mainThread())
//            .subscribe(new Observer<Integer>() {
//                @Override
//                public void onSubscribe(Disposable d) {
//
//                }
//
//                @Override
//                public void onNext(Integer integer) {
//                    switch (integer) {
//                        case 1:
//                        Toast.makeText(TestActivity.this, "保存成功", Toast.LENGTH_SHORT).show();
//                        break;
//                        case 0:
//                        Toast.makeText(TestActivity.this, "保存失败", Toast.LENGTH_SHORT).show();
//                        break;
//                    }
//                }
//
//                @Override
//                public void onError(Throwable e) {
//
//                }
//
//                @Override
//                public void onComplete() {
//
//                }
//            })
    }

    fun saveImage(fileName:String, bitmap: Bitmap?){
        Observable.create<Int>(ObservableOnSubscribe {

            try{
                //设置保存参数到ContentValues中
                val contentValues=ContentValues()
                //设置文件名
                contentValues.put(MediaStore.Images.Media.DISPLAY_NAME,fileName)
                //设置文件描述，这里以文件名为例子
                contentValues.put(MediaStore.Images.Media.DESCRIPTION, fileName);
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
                    //android Q中不再使用DATA字段，而用RELATIVE_PATH代替
                    //RELATIVE_PATH是相对路径不是绝对路径
                    //DCIM是系统文件夹，关于系统文件夹可以到系统自带的文件管理器中查看，不可以写没存在的名字
                    contentValues.put(MediaStore.Images.Media.RELATIVE_PATH, "DCIM/Camera");
                    //contentValues.put(MediaStore.Images.Media.RELATIVE_PATH, "Music/signImage");
                } else {
                    contentValues.put(MediaStore.Images.Media.DATA,FileUtil.getInstance(mActivity!!).getFilePath().absolutePath);
                }
                //设置文件类型
                contentValues.put(MediaStore.Downloads.MIME_TYPE,"application/vnd.android.package-archive");
                //执行insert操作，向系统文件夹中添加文件
                //EXTERNAL_CONTENT_URI代表外部存储器，该值不变
                val uri = this.getContentResolver().insert(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, contentValues);
                if (uri != null) {
                    //若生成了uri，则表示该文件添加成功
                    //使用流将内容写入该uri中即可
                    val outputStream = getContentResolver().openOutputStream(uri);
                    if (outputStream != null) {
                        bitmap?.compress(Bitmap.CompressFormat.JPEG, 90, outputStream);
                        outputStream.flush();
                        outputStream.close();
                        it.onNext(1);
                    }
                }

            }catch (e:Exception){
                dialogs?.dismiss()
            }
        }).subscribeOn(Schedulers.newThread())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(Consumer {
                dialogs?.dismiss()
                when(it){
                    0->showToast("保存失败")
                    1->showToast("保存成功")
                }

            })
    }
}
