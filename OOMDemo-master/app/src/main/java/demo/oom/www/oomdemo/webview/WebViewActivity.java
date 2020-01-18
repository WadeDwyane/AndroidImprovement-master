package demo.oom.www.oomdemo.webview;

import android.app.Activity;
import android.os.Bundle;
import android.webkit.WebView;

import demo.oom.www.oomdemo.R;

public class WebViewActivity extends Activity {

    private WebView mWebView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_webview);
        mWebView = findViewById(R.id.webview);
        mWebView.loadUrl("www.baidu.com");
    }

    @Override
    protected void onDestroy() {
        destroyWebview();
        //主动去杀掉这个进程，使用这个直接释放内存
        android.os.Process.killProcess(android.os.Process.myPid());
        super.onDestroy();
    }

    private void destroyWebview() {
        if (null != mWebView) {
            mWebView.pauseTimers();
            mWebView.removeAllViews();
            mWebView.destroy();

        }

    }
}
