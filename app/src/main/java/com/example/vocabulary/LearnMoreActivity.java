package com.example.vocabulary;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebResourceRequest;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;

public class LearnMoreActivity extends AppCompatActivity {
    private WebView webViewMemory;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_learn_more);

        webViewMemory = findViewById(R.id.wv2);
        webViewMemory.getSettings().setJavaScriptEnabled(true);
        webViewMemory.setWebViewClient(new MyWebVIewClient());
        webViewMemory.setWebChromeClient(new MyWebChromeClient());
        webViewMemory.loadUrl("Https://m.baidu.com");

    }


    //自己的MyWebViewClient
    class MyWebVIewClient extends WebViewClient {
        @Override
        public boolean shouldOverrideUrlLoading(WebView view, WebResourceRequest request) {
            view.loadUrl(request.getUrl().toString());
            return true;
        }
        //页面开始和结束打印日志文件
        @Override
        public void onPageStarted(WebView view, String url, Bitmap favicon) {
            super.onPageStarted(view, url, favicon);
            Log.d("webview","onPageFinished");
        }

        @Override
        public void onPageFinished(WebView view, String url) {
            super.onPageFinished(view, url);
            Log.d("webView","onPageFinished");
        }
    }

    class MyWebChromeClient extends WebChromeClient {
        @Override
        public void onProgressChanged(WebView view, int newProgress) {

            super.onProgressChanged(view, newProgress);
        }

        //将网页的标题设置为Activity的标题
        @Override
        public void onReceivedTitle(WebView view, String title) {
            super.onReceivedTitle(view, title);
            setTitle(title);
        }
    }

    //监听按键，如果是返回键，判断是否有返回页面，如果有则返回上一页
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if(keyCode == KeyEvent.KEYCODE_BACK && webViewMemory.canGoBack()){
            webViewMemory.goBack();
            return true;
        }
        return super.onKeyDown(keyCode,event);
    }

}