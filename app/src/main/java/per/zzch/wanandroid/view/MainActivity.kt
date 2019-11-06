package per.zzch.wanandroid.view

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentPagerAdapter
import per.zzch.library.base.BaseBindingActivity
import per.zzch.wanandroid.R
import per.zzch.wanandroid.databinding.MainB
import per.zzch.wanandroid.viewmodel.MainVM

class MainActivity : BaseBindingActivity<MainVM, MainB>(R.layout.activity_main) {

    private val mBannerFragment = BannerFragment.newInstance()
    private val mArticleFragment = ArticleFragment.newInstance()
    private val mProjectFragment = ProjectFragment.newInstance()
    private val mResourceFragment = ResourceFragment.newInstance()

    override fun initEventAndData() {
        with(mBinding) {
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
                clearTab()
                mPage1.setBackgroundColor(resources.getColor(R.color.check))
                mViewPager.currentItem = 0
            }
            mPage2.setOnClickListener {
                clearTab()
                mPage2.setBackgroundColor(resources.getColor(R.color.check))
                mViewPager.currentItem = 1
            }
            mPage3.setOnClickListener {
                clearTab()
                mPage3.setBackgroundColor(resources.getColor(R.color.check))
                mViewPager.currentItem = 2
            }
            mPage4.setOnClickListener {
                clearTab()
                mPage4.setBackgroundColor(resources.getColor(R.color.check))
                mViewPager.currentItem = 3
            }
        }
    }

    override fun onCreate() {

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

    private fun clickTab() {

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
