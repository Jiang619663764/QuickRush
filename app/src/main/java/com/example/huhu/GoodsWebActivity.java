package com.example.huhu;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.Toast;

import com.example.huhu.bean.ShoppingInfo;
import com.example.huhu.util.DBManager;
import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshWebView;

public class GoodsWebActivity extends AppCompatActivity {

    private PullToRefreshWebView refreshWebView;

    private Button mBtn_save_shop;

    private Button mBtn_buy_shop;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.content_goods_web);

        initView();
        WebView webView = refreshWebView.getRefreshableView();
        webView.getSettings().setJavaScriptEnabled(true);
        webView.loadUrl("http://baidu.com");
        webView.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                view.loadUrl(url);
                return true;
            }
        });
        refreshWebView.setOnRefreshListener(new PullToRefreshBase.OnRefreshListener<WebView>() {
            @Override
            public void onRefresh(PullToRefreshBase<WebView> refreshView) {
                refreshWebView.onRefreshComplete();
            }
        });

        mBtn_save_shop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                DBManager db=new DBManager(GoodsWebActivity.this);
                ShoppingInfo info=new ShoppingInfo();
                info.setProName("雪碧");
                info.setProPrice(2);
                info.setProDetail("......");
                info.setProImage("111111");
                db.add(info);
                Toast.makeText(GoodsWebActivity.this,"添加成功",Toast.LENGTH_LONG).show();

            }
        });
    }

    private void initView() {
        refreshWebView = (PullToRefreshWebView) findViewById(R.id.refreshWebView_goods);
        mBtn_save_shop = (Button) findViewById(R.id.btn_addtoshoop_goods);
        mBtn_buy_shop = (Button) findViewById(R.id.btn_buy_goods);
    }

}
