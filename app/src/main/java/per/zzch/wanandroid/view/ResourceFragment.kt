package per.zzch.wanandroid.view

import per.zzch.library.base.BaseBindingFragment
import per.zzch.wanandroid.R
import per.zzch.wanandroid.databinding.ResourceB
import per.zzch.wanandroid.viewmodel.ResourceVM

/**
 *
 * @author :曾佐丞
 * @e-mail :zengzuocheng@jimimax.com
 * @date   :2019/11/6
 * @desc   :
 */
class ResourceFragment : BaseBindingFragment<ResourceVM, ResourceB>(R.layout.fragment_resource) {

    companion object {
        fun newInstance() = ResourceFragment()
    }

    override fun initEventAndData() {

    }

    override fun onActivityCreate() {

    }


}