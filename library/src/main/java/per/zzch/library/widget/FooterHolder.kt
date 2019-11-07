package per.zzch.library.widget

import android.text.TextUtils
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.ProgressBar
import android.widget.TextView
import per.zzch.library.R

class FooterHolder(parent: ViewGroup) {

    private val progress: ProgressBar
    private val mHint: TextView

    val view: View = LayoutInflater.from(parent.context).inflate(R.layout.layout_load_more, parent, false)

    private val footEmptyRoot: View
    private val footEmptyImg: ImageView
    private val footEmptyText: TextView
    private val footEmptyTodo: TextView

    init {
        progress = view.findViewById<View>(R.id.load_more_progress) as ProgressBar
        mHint = view.findViewById<View>(R.id.load_more_text) as TextView
        footEmptyRoot = view.findViewById(R.id.foot_empty_root)
        footEmptyImg = view.findViewById(R.id.foot_empty_img)
        footEmptyText = view.findViewById(R.id.foot_empty_text)
        footEmptyTodo = view.findViewById(R.id.foot_empty_todo)
    }


    fun setFooterNoMore() {
        progress.visibility = View.GONE
        mHint.visibility = View.VISIBLE
        mHint.setText(R.string.no_data)
        hideEmptyView()
    }


    fun reSetFooter() {
        progress.visibility = View.VISIBLE
        //        mHint.setText(R.string.loading);
        mHint.visibility = View.GONE
        hideEmptyView()
    }


    fun setFootMessage(msg: String?) {
        hideEmptyView()
        progress.visibility = View.GONE
        mHint.visibility = View.VISIBLE
        mHint.text = msg
    }

    fun hideFooter() {
        hideProgress()
        hideEmptyView()
    }

    private fun hideProgress() {
        progress.visibility = View.GONE
        mHint.visibility = View.GONE
    }

    fun hideEmptyView() {
        footEmptyRoot.visibility = View.GONE
        footEmptyImg.setImageResource(R.mipmap.ic_empty_1)
        footEmptyText.setText(R.string.no_data)
        footEmptyTodo.visibility = View.GONE
    }


    fun showFootEmpty(height: Int) {
        hideProgress()
        val layoutParams = footEmptyRoot.layoutParams as LinearLayout.LayoutParams
        layoutParams.height = height
        footEmptyRoot.layoutParams = layoutParams
        footEmptyRoot.visibility = View.VISIBLE
    }

    fun showNetError(itemCount: Int, height: Int, imgResId: Int, msg: String?, listener: View.OnClickListener?) {
        var imgRes = imgResId
        if (itemCount > 0) {
            setFootMessage(msg)
            return
        }
        hideProgress()
        val layoutParams = footEmptyRoot.layoutParams as LinearLayout.LayoutParams
        layoutParams.height = height
        footEmptyRoot.layoutParams = layoutParams
        footEmptyRoot.visibility = View.VISIBLE
        if (imgRes == 0) {
            imgRes = R.mipmap.ic_empty_error
        }
        footEmptyImg.setImageResource(imgRes)
        if (!TextUtils.isEmpty(msg)) {
            footEmptyText.text = msg
        }
        if (listener != null) {
            footEmptyTodo.visibility = View.VISIBLE
            footEmptyTodo.setOnClickListener(listener)
        } else {
            footEmptyTodo.visibility = View.GONE
        }

    }

    fun showEmptyTodo(height: Int, imgResId: Int, msgRes: Int, todoRes: Int, listener: View.OnClickListener) {
        var imgRes = imgResId
        hideProgress()
        val layoutParams = footEmptyRoot.layoutParams as LinearLayout.LayoutParams
        layoutParams.height = height
        footEmptyRoot.layoutParams = layoutParams
        footEmptyRoot.visibility = View.VISIBLE
        if (imgRes == 0) {
            imgRes = R.mipmap.ic_empty_error
        }
        footEmptyImg.setImageResource(imgRes)
        footEmptyText.setText(msgRes)
        footEmptyTodo.setText(todoRes)
        footEmptyTodo.visibility = View.VISIBLE
        footEmptyTodo.setOnClickListener(listener)
    }
}
