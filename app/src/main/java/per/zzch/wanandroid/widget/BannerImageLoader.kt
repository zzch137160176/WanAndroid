package per.zzch.wanandroid.widget

import android.content.Context
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.youth.banner.loader.ImageLoader

/**
 *
 * @author :曾佐丞
 * @e-mail :zengzuocheng@jimimax.com
 * @date   :2019/11/7
 * @desc   :
 */

class BannerImageLoader(private val activity: Context?) : ImageLoader() {

    override fun displayImage(context: Context?, path: Any?, imageView: ImageView?) {
        if (imageView != null && activity != null) {
            Glide.with(activity).load(path).into(imageView)
        }
    }

}