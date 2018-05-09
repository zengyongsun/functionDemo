package com.example.administrator.functiondemo.api.response;

import com.example.administrator.functiondemo.api.entity.ArticleListData;
import com.example.administrator.functiondemo.api.entity.BannerData;

import io.reactivex.Observable;
import retrofit2.http.GET;

/**
 * <pre>
 *    @author : Zeyo
 *     e-mail : zengyongsun@163.com
 *     time   : 2018/05/07
 *     desc   :
 *     version: 1.0
 * </pre>
 */
public interface HomeService {

    //获取首页文章列表，页码从零开始
    @GET("article/list/1/json")
    Observable<ArticleListData> getHomeArticleList();

    @GET("banner/json")
    Observable<BannerData> getHomeBanner();
}
