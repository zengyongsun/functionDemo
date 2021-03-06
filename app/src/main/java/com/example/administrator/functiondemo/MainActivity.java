package com.example.administrator.functiondemo;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.widget.Button;

import com.example.administrator.functiondemo.api.entity.ArticleListData;
import com.example.administrator.functiondemo.api.entity.BannerData;
import com.example.administrator.functiondemo.api.response.HomeLoader;
import com.example.administrator.functiondemo.webview.WebViewActivity;
import com.example.common.packaging.LoggerUtil;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.reactivex.functions.Consumer;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;
    @BindView(R.id.goWeb)
    Button goWeb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        loadData();
    }

    private void loadData() {

        HomeLoader homeLoader = new HomeLoader();
        homeLoader.getHomeArticleList().subscribe(new Consumer<ArticleListData>() {
            @Override
            public void accept(ArticleListData articleListData) throws Exception {
                LoggerUtil.d(articleListData.data.pageCount);
                LoggerUtil.d(articleListData.data.datas.get(0).getChapterName());
            }
        });

        homeLoader.getBannerData().subscribe(new Consumer<BannerData>() {
            @Override
            public void accept(BannerData bannerData) throws Exception {
                LoggerUtil.d(bannerData.data.get(0).getDesc());
            }
        });

    }

    @OnClick(R.id.goWeb)
    public void onViewClicked() {
        startActivity(new Intent(this, WebViewActivity.class));
    }
}
