package per.zzch.wanandroid.remote

import io.reactivex.Single
import per.zzch.wanandroid.model.*
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

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

    @GET("project/tree/json")
    fun getProjectKind(): Single<Result<List<ProjectKind>>>

    @GET("project/list/{page}/json")
    fun getProject(@Path("page") page: Int, @Query("cid") kind: Int): Single<Result<Page<Article>>>

}