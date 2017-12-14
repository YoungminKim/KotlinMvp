package test.ym.kotilnpj.utils

import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ImageView
import java.lang.ref.WeakReference

/**
 * Created by no.1 on 2017-08-25.
 */

object RecycleUtils {
    /**
     * View MEMORY 해제
     *
     * @param root
     * 해제하고자 하는 View
     */
    fun recursiveRecycle(root: View?) {
        var root: View? = root ?: return
        root!!.setBackgroundDrawable(null)
        if (root is ViewGroup) {
            val group = root as ViewGroup?
            val count = group!!.childCount
            for (i in 0..count - 1) {
                recursiveRecycle(group.getChildAt(i))
            }
            if (root !is AdapterView<*>) {
                group.removeAllViews()
            }

        }
        if (root is ImageView) {
            root.setImageDrawable(null)
        }
        root = null
        return
    }

    /**
     * Adapter 객체에서 사용되는 View 해제
     *
     * @param recycleList
     * View를 참조하는 WeakReference 리스트 객체
     */
    fun recursiveRecycle(recycleList: List<WeakReference<View>>) {
        for (ref in recycleList) {
            recursiveRecycle(ref.get())
        }
    }
}