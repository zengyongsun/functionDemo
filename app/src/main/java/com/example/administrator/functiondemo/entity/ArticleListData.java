package com.example.administrator.functiondemo.entity;

import java.util.List;

/**
 * <pre>
 *    @author : Zeyo
 *     e-mail : zengyongsun@163.com
 *     time   : 2018/05/07
 *     desc   :
 *     version: 1.0
 * </pre>
 */
public class ArticleListData<T> extends BaseEntity<ArticleListData> {

    /**
     * 当前第几页
     */
    public int curPage;

    public int offset;

    public int pageCount;

    public int size;

    public int total;

    public boolean over;

    public List<T> datas;

}
