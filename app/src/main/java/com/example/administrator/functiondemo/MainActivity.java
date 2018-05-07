package com.example.administrator.functiondemo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;

import com.example.administrator.functiondemo.api.HomeLoader;
import com.example.administrator.functiondemo.entity.ArticleListData;
import com.example.common.packaging.LoggerUtil;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.reactivex.functions.Consumer;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.recyclerView) RecyclerView recyclerView;

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
                LoggerUtil.d(articleListData.pageCount);
            }
        });

    }
}
