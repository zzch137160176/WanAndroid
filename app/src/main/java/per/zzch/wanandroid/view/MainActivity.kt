package per.zzch.wanandroid.view

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentPagerAdapter
import androidx.viewpager.widget.ViewPager
import per.zzch.library.base.BaseBindingActivity
import per.zzch.library.utils.LogUtil
import per.zzch.wanandroid.R
import per.zzch.wanandroid.databinding.MainB
import per.zzch.wanandroid.viewmodel.MainVM

class MainActivity : BaseBindingActivity<MainVM, MainB>(R.layout.activity_main),
    ViewPager.OnPageChangeListener {

    private val mBannerFragment = BannerFragment.newInstance()
    private val mArticleFragment = ArticleFragment.newInstance()
    private val mProjectFragment = ProjectFragment.newInstance()
    private val mResourceFragment = ResourceFragment.newInstance()

    override fun initEventAndData() {

        with(mBinding) {
            mViewPager.addOnPageChangeListener(this@MainActivity)
            mViewPager.adapter = object : FragmentPagerAdapter(
                supportFragmentManager,
                BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT
            ) {
                override fun getItem(position: Int): Fragment {
                    return getFragment(position)
                }

                override fun getCount(): Int {
                    return 4
                }
            }
            mViewPager.offscreenPageLimit = 3

            mPage1.setOnClickListener {
                clickTab(0)
            }
            mPage2.setOnClickListener {
                clickTab(1)
            }
            mPage3.setOnClickListener {
                clickTab(2)
            }
            mPage4.setOnClickListener {
                clickTab(3)
            }
        }
        clickTab(0)
    }

    override fun onCreate() {

    }

    override fun onPageScrollStateChanged(state: Int) {
    }

    override fun onPageScrolled(position: Int, positionOffset: Float, positionOffsetPixels: Int) {
    }

    override fun onPageSelected(position: Int) {
        LogUtil.i("position: $position")
        clickTab(position)
    }

    private fun getFragment(position: Int): Fragment {
        return when (position) {
            0 -> mBannerFragment
            1 -> mArticleFragment
            2 -> mProjectFragment
            3 -> mResourceFragment
            else -> mBannerFragment
        }
    }

    private fun clickTab(position: Int) {
        clearTab()
        mBinding.mViewPager.currentItem = position
        when (position) {
            0 -> mBinding.mPage1.setBackgroundColor(resources.getColor(R.color.check))
            1 -> mBinding.mPage2.setBackgroundColor(resources.getColor(R.color.check))
            2 -> mBinding.mPage3.setBackgroundColor(resources.getColor(R.color.check))
            3 -> mBinding.mPage4.setBackgroundColor(resources.getColor(R.color.check))
        }
    }

    private fun clearTab() {
        with(mBinding) {
            mPage1.setBackgroundColor(resources.getColor(R.color.white))
            mPage2.setBackgroundColor(resources.getColor(R.color.white))
            mPage3.setBackgroundColor(resources.getColor(R.color.white))
            mPage4.setBackgroundColor(resources.getColor(R.color.white))
        }
    }
}
