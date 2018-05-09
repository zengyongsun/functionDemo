package com.example.administrator.functiondemo.base;

import com.example.administrator.functiondemo.http.RetrofitServiceManager;
import com.example.common.CommonApplication;

/**
 * <pre>
 *    @author : Zeyo
 *     e-mail : zengyongsun@163.com
 *     time   : 2018/05/05
 *     desc   :
 *     version: 1.0
 * </pre>
 */
public class FunctionApplication extends CommonApplication {

    @Override
    public void onCreate() {
        super.onCreate();
        RetrofitServiceManager.getInstance();
    }
}
