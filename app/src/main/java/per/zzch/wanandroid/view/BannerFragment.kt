package per.zzch.wanandroid.view

import android.content.Context
import android.widget.ImageView
import androidx.lifecycle.Observer
import com.bumptech.glide.Glide
import com.youth.banner.BannerConfig
import com.youth.banner.Transformer
import com.youth.banner.loader.ImageLoader
import per.zzch.library.base.BaseBindingFragment
import per.zzch.library.utils.sToString
import per.zzch.wanandroid.R
import per.zzch.wanandroid.databinding.BannerBinding
import per.zzch.wanandroid.model.Banner
import per.zzch.wanandroid.viewmodel.BannerVM


/**
 *
 * @author :曾佐丞
 * @e-mail :zengzuocheng@jimimax.com
 * @date   :2019/11/6
 * @desc   :
 */
class BannerFragment :
    BaseBindingFragment<BannerVM, BannerBinding>(R.layout.fragment_banner) {

    companion object {
        fun newInstance() = BannerFragment()
    }

    private val mImgList = arrayListOf<String>()
    private val mContentList = arrayListOf<String>()

    override fun initEventAndData() {
        mViewModel.initData()
        mViewModel.mBannerList.observe(this, Observer {
            for (banner: Banner in it) {
                mImgList.add(banner.imagePath.sToString())
                mContentList.add(banner.title.sToString())
            }
            initBanner()
        })
    }

    override fun onActivityCreate() {

    }

    private fun initBanner() {
        mBinding.mBanner.setBannerStyle(BannerConfig.CIRCLE_INDICATOR_TITLE_INSIDE)
        mBinding.mBanner.setImageLoader(MyLoader())
        mBinding.mBanner.setBannerAnimation(Transformer.Default)
        mBinding.mBanner.setImages(mImgList)
        mBinding.mBanner.setBannerTitles(mContentList)
        //切换频率
        mBinding.mBanner.setDelayTime(2000)
        //自动启动
        mBinding.mBanner.isAutoPlay(true)
        //位置设置
        mBinding.mBanner.setIndicatorGravity(BannerConfig.CENTER)
        //开始运行
        mBinding.mBanner.start()
    }

    inner class MyLoader : ImageLoader() {

        override fun displayImage(context: Context?, path: Any?, imageView: ImageView?) {
            if (imageView != null && activity != null) {
                Glide.with(activity!!).load(path).into(imageView)
            }
        }

    }
}