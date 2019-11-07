package per.zzch.wanandroid.remote

import io.reactivex.Single
import per.zzch.wanandroid.model.Article
import per.zzch.wanandroid.model.Banner
import per.zzch.wanandroid.model.Page
import per.zzch.wanandroid.model.Result
import retrofit2.http.GET
import retrofit2.http.Path

/**
 *
 * @author :曾佐丞
 * @e-mail :zengzuocheng@jimimax.com
 * @date   :2019/11/6
 * @desc   :
 */
interface API {

    @GET("banner/json")
    fun getBanner(): Single<Result<List<Banner>>>

    @GET("article/list/{page}/json")
    fun getArticle(@Path("page") page: Int): Single<Result<Page<Article>>>

}