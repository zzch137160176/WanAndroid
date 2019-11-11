package per.zzch.wanandroid.view.fragment

import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import per.zzch.library.base.BaseBindingFragment
import per.zzch.wanandroid.R
import per.zzch.wanandroid.adapter.NavigationItemAdapter
import per.zzch.wanandroid.databinding.NavigationB
import per.zzch.wanandroid.viewmodel.NavigationVM

/**
 *
 * @author :曾佐丞
 * @e-mail :zengzuocheng@jimimax.com
 * @date   :2019/11/6
 * @desc   :
 */
class NavigationFragment :
    BaseBindingFragment<NavigationVM, NavigationB>(R.layout.fragment_navigation) {

    private val mAdapter = NavigationItemAdapter()

    companion object {
        fun newInstance() = NavigationFragment()
    }

    override fun initEventAndData() {
        mViewModel.init()

        mAdapter.bindRecyclerView(
            mBinding.mRecyclerView,
            LinearLayoutManager(mBinding.mRecyclerView.context, RecyclerView.VERTICAL, false)
        )

        mViewModel.mNavigationList.observe(this, Observer {
            mAdapter.dataList = it
        })
    }

    override fun onActivityCreate() {

    }


}