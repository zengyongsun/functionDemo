package com.example.administrator.functiondemo.api;

import com.example.administrator.functiondemo.entity.ArticleListData;
import com.example.administrator.functiondemo.http.ObjectLoader;
import com.example.administrator.functiondemo.http.RetrofitServiceManager;

import io.reactivex.Observable;

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
        return observe(mHomeService.getHomeArticleList());
    }

}
