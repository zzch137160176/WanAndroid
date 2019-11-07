package per.zzch.wanandroid.model

/**
 *
 * @author :曾佐丞
 * @e-mail :zengzuocheng@jimimax.com
 * @date   :2019/11/7
 * @desc   :
 */

data class Article(
    val apkLink: String? = null,
    val audit: Int = 0,
    val author: String? = null,
    val chapterId: Int = 0,
    val chapterName: String? = null,
    val collect: Boolean = false,
    val courseId: Int = 0,
    val desc: String? = null,
    val envelopePic: String? = null,
    val fresh: Boolean = false,
    val id: Int = 0,
    val link: String? = null,
    val niceDate: String? = null,
    val niceShareDate: String? = null,
    val origin: String? = null,
    val prefix: String? = null,
    val projectLink: String? = null,
    val publishTime: Long = 0,
    val selfVisible: Int = 0,
    val shareDate: Long = 0,
    val shareUser: String? = null,
    val superChapterId: Int = 0,
    val superChapterName: String? = null,
    val tags: List<Tags>? = null,
    val title: String? = null,
    val type: Int = 0,
    val userId: Int = 0,
    val visible: Int = 0,
    val zan: Int = 0
)