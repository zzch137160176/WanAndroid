package per.zzch.wanandroid.view.fragment

import android.content.Context
import android.widget.ImageView
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.youth.banner.BannerConfig
import com.youth.banner.Transformer
import com.youth.banner.loader.ImageLoader
import per.zzch.library.base.BaseBindingFragment
import per.zzch.library.utils.sToString
import per.zzch.wanandroid.R
import per.zzch.wanandroid.adapter.ArticleItemAdapter
import per.zzch.wanandroid.databinding.BannerBinding
import per.zzch.wanandroid.model.Banner
import per.zzch.wanandroid.viewmodel.BannerVM
import per.zzch.wanandroid.widget.BannerImageLoader


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

    private val mAdapter = ArticleItemAdapter()

    private var page = 0

    override fun initEventAndData() {
        mViewModel.initData()
        mViewModel.getArticle(page)
        getData(0)

        mAdapter.bindRecyclerView(
            mBinding.mRecyclerView,
            LinearLayoutManager(mBinding.mRecyclerView.context, RecyclerView.VERTICAL, false)
        )
        mAdapter.setOnLoadListener {
            getData(it)
        }

        mViewModel.mBannerList.observe(this, Observer {
            for (banner: Banner in it) {
                mImgList.add(banner.imagePath.sToString())
                mContentList.add(banner.title.sToString())
            }
            initBanner()
        })
        mViewModel.mPage.observe(this, Observer {
            mAdapter.setPageNow(it)
        })

    }

    override fun onActivityCreate() {

    }

    private fun initBanner() {
        mBinding.mBanner.setBannerStyle(BannerConfig.CIRCLE_INDICATOR_TITLE_INSIDE)
        mBinding.mBanner.setImageLoader(BannerImageLoader(activity))
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

    private fun getData(page: Int) {
        mViewModel.getArticle(page)
    }

}