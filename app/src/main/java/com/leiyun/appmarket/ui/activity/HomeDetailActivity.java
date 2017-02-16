package com.leiyun.appmarket.ui.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.FrameLayout;

import com.leiyun.appmarket.R;
import com.leiyun.appmarket.domain.AppInfo;
import com.leiyun.appmarket.http.protocol.HomeDetailProtocol;
import com.leiyun.appmarket.ui.holder.DetailAppInfoHolder;
import com.leiyun.appmarket.ui.view.LoadingPage;
import com.leiyun.appmarket.utils.UIUtils;

import static android.icu.lang.UCharacter.GraphemeClusterBreak.L;

/**
 * 首页应用详情页
 * Created by LeiYun on 2017/2/15 0015.
 */

public class HomeDetailActivity extends BaseActivity {

    private LoadingPage mLoadingPage;
    private String mPackageName;
    private AppInfo data;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mLoadingPage = new LoadingPage(UIUtils.getContext()) {
            @Override
            public View onCreateSuccessView() {
                return HomeDetailActivity.this.onCreateSuccessView();

            }

            @Override
            public ResultState onLoad() {
                return HomeDetailActivity.this.onLoad();
            }
        };

        setContentView(mLoadingPage); // 直接将一个View对象设置给activity

        // 获取包名
        mPackageName = getIntent().getStringExtra("packageName");

        // 开始加载网络数据
        mLoadingPage.loadData();
    }

    public View onCreateSuccessView() {
        // 初始化成功的布局
        View view = UIUtils.inflate(R.layout.page_home_detail);

        // 初始化应用信息模块
        FrameLayout flDetailAppInfo = (FrameLayout) view.findViewById(R.id.fl_detail_appinfo);

        // 动态的给帧布局填充页面
        DetailAppInfoHolder appInfoHolder = new DetailAppInfoHolder();
        appInfoHolder.setData(data);
        flDetailAppInfo.addView(appInfoHolder.getRootView());

        return view;
    }

    public LoadingPage.ResultState onLoad() {
        // 请求网络，加载数据
        HomeDetailProtocol protocol = new HomeDetailProtocol(mPackageName);
        data = protocol.getData(0);
        if (data != null) {
            return LoadingPage.ResultState.STATE_SUCCESS;
        } else {
            return LoadingPage.ResultState.STATE_ERROR;
        }
    }
}