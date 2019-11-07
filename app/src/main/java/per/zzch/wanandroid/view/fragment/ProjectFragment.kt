package per.zzch.wanandroid.view.fragment

import per.zzch.library.base.BaseBindingFragment
import per.zzch.wanandroid.R
import per.zzch.wanandroid.databinding.ProjectB
import per.zzch.wanandroid.viewmodel.ProjectVM

/**
 *
 * @author :曾佐丞
 * @e-mail :zengzuocheng@jimimax.com
 * @date   :2019/11/6
 * @desc   :
 */
class ProjectFragment : BaseBindingFragment<ProjectVM, ProjectB>(R.layout.fragment_project) {

    companion object {
        fun newInstance() = ProjectFragment()
    }

    override fun initEventAndData() {

    }

    override fun onActivityCreate() {

    }


}