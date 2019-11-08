package per.zzch.wanandroid.model

/**
 *
 * @author :曾佐丞
 * @e-mail :zengzuocheng@jimimax.com
 * @date   :2019/11/7
 * @desc   :
 */
data class ProjectKind(
    val children: List<String>? = null,
    val courseId: Int? = 0,
    val id: Int? = 0,
    val name: String? = null,
    val order: Int? = 0,
    val parentChapterId: Int? = 0,
    val userControlSetTop: Boolean? = false,
    val visible: Int? = 0
)