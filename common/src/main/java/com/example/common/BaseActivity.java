package com.example.common;

import android.support.v7.app.AppCompatActivity;

import com.example.common.netstatus.NetChangeObserver;
import com.example.common.utils.NetWorkUtil;
import com.example.common.packaging.LoggerUtil;

/**
 * <pre>
 *    @author : Zeyo
 *     e-mail : zengyongsun@163.com
 *     time   : 2018/05/08
 *     desc   :
 *     version: 1.0
 * </pre>
 */
public class BaseActivity extends AppCompatActivity implements NetChangeObserver {

    @Override
    public void onConnect(NetWorkUtil.NetType type) {
        LoggerUtil.i("net change call back onConnect(NetType type) not implements");
        LoggerUtil.i(String.format("NetType:", type.name()));
    }

    @Override
    public void onDisConnect() {
        LoggerUtil.d("net change call back onDisConnect() not implements");
    }
}
