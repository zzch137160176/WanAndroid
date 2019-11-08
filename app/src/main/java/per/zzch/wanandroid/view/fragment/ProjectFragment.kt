package per.zzch.wanandroid.view.fragment

import androidx.lifecycle.Observer
import per.zzch.library.base.BaseBindingFragment
import per.zzch.library.listener.ItemClickListener
import per.zzch.library.widget.DefaultPopup
import per.zzch.wanandroid.R
import per.zzch.wanandroid.adapter.ArticleItemAdapter
import per.zzch.wanandroid.databinding.ProjectB
import per.zzch.wanandroid.model.ProjectKind
import per.zzch.wanandroid.view.MainActivity
import per.zzch.wanandroid.viewmodel.ProjectVM

/**
 *
 * @author :曾佐丞
 * @e-mail :zengzuocheng@jimimax.com
 * @date   :2019/11/6
 * @desc   :
 */
class ProjectFragment : BaseBindingFragment<ProjectVM, ProjectB>(R.layout.fragment_project),
    ItemClickListener<String> {

    private val mAdapter = ArticleItemAdapter()

    private var mPopup: DefaultPopup? = null

    private val mKindNameList = arrayListOf<String>()
    private val mKindIdList = arrayListOf<Int>()

    private var mKind = ProjectVM.START_PAGE

    companion object {

        const val POPUP_HEIGHT = 600

        fun newInstance() = ProjectFragment()
    }

    override fun initEventAndData() {

        showLoading()
        mViewModel.init()

        mBinding.mPorjectKind = "完整项目"
        mBinding.mProjectKindTv.setOnClickListener {
            if (mPopup == null) {
                mPopup = DefaultPopup(it.context, it.width, POPUP_HEIGHT, this)
            }
            mPopup?.show(mKindNameList, it)
        }

        mAdapter.bindRecyclerView(mBinding.mRecyclerView)
        mAdapter.setStartPage(ProjectVM.START_PAGE)
        mAdapter.setOnLoadListener { pageNum ->
            mViewModel.getProject(pageNum, mKind)
        }

        mViewModel.mPage.observe(this, Observer {
            it.refreshPage()
            mAdapter.setPageNow(it)
        })

        mViewModel.mKindList.observe(this, Observer {
            mKindNameList.clear()
            mKindIdList.clear()
            for (kind: ProjectKind in it) {
                if (!kind.name.isNullOrEmpty() && kind.id != null) {
                    mKindNameList.add(kind.name)
                    mKindIdList.add(kind.id)
                }
            }
            dissmissLoading()
        })
    }

    override fun onActivityCreate() {

    }

    override fun itemClickDoing(data: String?, position: Int) {
        mBinding.mPorjectKind = mKindNameList[position]
        mKind = mKindIdList[position]
        mViewModel.getProject(ProjectVM.START_PAGE, mKind)
        mPopup?.dismiss()
    }

}