package com.example.androidanimation;

import android.os.Bundle;
import android.os.PersistableBundle;
import android.webkit.WebSettings;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.androidanimation.databinding.ActivityWebBinding;

public
class WebActivity extends AppCompatActivity {
    private ActivityWebBinding binding;
    private WebSettings mWebSettings;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityWebBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        initWebView();
        binding.web.loadUrl("http://test2.cameltec.cn/openapp.html");
    }

    private void initWebView(){

        mWebSettings = binding.web.getSettings();
        mWebSettings.setJavaScriptEnabled(true);    //设置是否允许执行Js脚本
        mWebSettings.setJavaScriptCanOpenWindowsAutomatically(true);    //设置Js脚本是否允许自动打开弹窗
        //     mWebSettings.setLayoutAlgorithm(WebSettings.LayoutAlgorithm.SINGLE_COLUMN);//设置WebView底层的布局算法
//        1.NARROW_COLUMNS：可能的话使所有列的宽度不超过屏幕宽度
//        2.NORMAL：正常显示不做任何渲染
//        3.SINGLE_COLUMN：把所有内容放大webview等宽的一列中
        mWebSettings.setUseWideViewPort(true);  //设置WebView是否使用viewport
        mWebSettings.setLoadWithOverviewMode(true); //设置WebView是否使用预览模式加载界面。
        mWebSettings.setSupportZoom(true);// 允许放大缩小
        mWebSettings.setBuiltInZoomControls(true);// 显示放大缩小按钮
        mWebSettings.setDisplayZoomControls(false);// api 11以上                                                                          `
        mWebSettings.setSupportMultipleWindows(false);   //设置WebView是否支持多屏窗口
        mWebSettings.setDomStorageEnabled(true);// 支持html DOM storage API
    }
}
