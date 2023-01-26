package com.example.step15webview;

import android.os.Bundle;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //WebView 객체의 참조값 얻어오기
        WebView webView=findViewById(R.id.webView);
        //WebView의 설정 객체 얻어오기
        WebSettings ws=webView.getSettings();
        //javaScript 해석 가능하게 설정
        ws.setJavaScriptEnabled(true);
        //WebView 클라이언트에 객체 넣기
        webView.setWebViewClient(new WebViewClient());
        //webView.setWebChromeClient(new WebChromeClient());
        /*
        원하는 url 로딩시키기
        인터넷 자원을 사용해야 한다. => 비용 발생 가능성 => 사용자의 허가가 필요하다.

        허가(permission)
        인터넷을 사용하곘다는 permission이 AndroidManifest.xml 문서에 있어야한다.
         */
        webView.loadUrl("https://naver.com");

    }
}
