package per.zzch.wanandroid.view

import android.graphics.Color
import android.widget.ImageView
import android.widget.TextView
import androidx.core.view.GravityCompat
import androidx.databinding.ObservableField
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentPagerAdapter
import androidx.viewpager.widget.ViewPager
import per.zzch.library.base.BaseBindingActivity
import per.zzch.library.utils.LogUtil
import per.zzch.library.utils.ToastUtil
import per.zzch.wanandroid.R
import per.zzch.wanandroid.databinding.MainB
import per.zzch.wanandroid.view.fragment.BannerFragment
import per.zzch.wanandroid.view.fragment.NavigationFragment
import per.zzch.wanandroid.view.fragment.ProjectFragment
import per.zzch.wanandroid.view.fragment.SearchFragment
import per.zzch.wanandroid.viewmodel.MainVM

class MainActivity : BaseBindingActivity<MainVM, MainB>(R.layout.activity_main),
    ViewPager.OnPageChangeListener {

    private val mBannerFragment = BannerFragment.newInstance()
    private val mProjectFragment = ProjectFragment.newInstance()
    private val mNavigationFragment = NavigationFragment.newInstance()
    private val mSearchFragment = SearchFragment.newInstance()

    private var mFragmentTitle = ObservableField<String>()

    override fun initEventAndData() {

        initUserNavigation()

        with(mBinding) {
            title = mFragmentTitle
            mToolbar.setStartImg(R.mipmap.ic_more)
            mToolbar.setStartClickListener {
                mDrawer.openDrawer(GravityCompat.START)
            }
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

    /***
     * ViewPager的OnPageChangeListener相关方法
     */

    override fun onPageScrollStateChanged(state: Int) {
    }

    override fun onPageScrolled(position: Int, positionOffset: Float, positionOffsetPixels: Int) {
    }

    override fun onPageSelected(position: Int) {
        LogUtil.i("position: $position")
        clickTab(position)
    }

    /**
     *
     * @param position Int
     * @return Fragment
     */
    private fun getFragment(position: Int): Fragment {
        return when (position) {
            0 -> mBannerFragment
            1 -> mProjectFragment
            2 -> mNavigationFragment
            3 -> mSearchFragment
            else -> mBannerFragment
        }
    }

    private fun changeFragmentTitle(position: Int) {
        mFragmentTitle.set(
            when (position) {
                0 -> "首页"
                1 -> "项目"
                2 -> "导航"
                3 -> "搜索"
                else -> "首页"
            }
        )
    }

    /**
     *
     * @param position Int
     */
    private fun clickTab(position: Int) {
        changeFragmentTitle(position)
        clearTab()
        mBinding.mViewPager.currentItem = position
        when (position) {
            0 -> mBinding.mPage1.setBackgroundColor(Color.rgb(238, 238, 238))
            1 -> mBinding.mPage2.setBackgroundColor(Color.rgb(238, 238, 238))
            2 -> mBinding.mPage3.setBackgroundColor(Color.rgb(238, 238, 238))
            3 -> mBinding.mPage4.setBackgroundColor(Color.rgb(238, 238, 238))
        }
    }

    /**
     *
     */
    private fun clearTab() {
        with(mBinding) {
            mPage1.setBackgroundColor(Color.rgb(255, 255, 255))
            mPage2.setBackgroundColor(Color.rgb(255, 255, 255))
            mPage3.setBackgroundColor(Color.rgb(255, 255, 255))
            mPage4.setBackgroundColor(Color.rgb(255, 255, 255))
        }
    }

    private fun initUserNavigation() {
        with(mBinding.navView) {
            val userImg = getHeaderView(0).findViewById<ImageView>(R.id.iv_user_img)
            val userName = getHeaderView(0).findViewById<TextView>(R.id.tv_user_name)
            val userInfo = menu.findItem(R.id.menu_item_user_info)
            val userCollect = menu.findItem(R.id.menu_item_user_collect)
            setNavigationItemSelectedListener {
                when (it) {
                    userInfo -> ToastUtil.show("个人信息")
                    userCollect -> ToastUtil.show("我的收藏")
                }
                true
            }
            userImg.setOnClickListener {
                ToastUtil.show("登录")
            }
            userName.setOnClickListener {
                LoginActivity.start(mContext)
            }
        }
    }
}
