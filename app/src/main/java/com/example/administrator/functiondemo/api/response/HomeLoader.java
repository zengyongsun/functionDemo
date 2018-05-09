package com.example.administrator.functiondemo.api.response;

import com.example.administrator.functiondemo.api.entity.ArticleListData;
import com.example.administrator.functiondemo.api.entity.BannerData;
import com.example.administrator.functiondemo.http.ObjectLoader;
import com.example.administrator.functiondemo.http.RetrofitServiceManager;

import io.reactivex.Observable;
import io.reactivex.functions.Function;

/**
 * <pre>
 *    @author : Zeyo
 *     e-mail : zengyongsun@163.com
 *     time   : 2018/05/07
 *     desc   :
 *     version: 1.0
 * </pre>
 */
public class HomeLoader extends ObjectLoader {

    private HomeService mHomeService;

    public HomeLoader() {
        mHomeService = RetrofitServiceManager.getInstance().create(HomeService.class);
    }

    public Observable<ArticleListData> getHomeArticleList() {
        return observe(mHomeService.getHomeArticleList()).map(new Function<ArticleListData, ArticleListData>() {
            @Override
            public ArticleListData apply(ArticleListData articleListData) throws Exception {
                return articleListData;
            }
        });
    }

    public Observable<BannerData> getBannerData(){
        return observe(mHomeService.getHomeBanner()).map(new Function<BannerData, BannerData>() {
            @Override
            public BannerData apply(BannerData bannerData) throws Exception {
                return bannerData;
            }
        });
    }

}
