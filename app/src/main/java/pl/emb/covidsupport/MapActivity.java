package pl.emb.covidsupport;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class MapActivity extends AppCompatActivity {
    WebView mWebView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map);

        mWebView = (WebView) findViewById(R.id.activity_webview);
        //enable javascript engine
        mWebView.getSettings().setJavaScriptEnabled(true);
        mWebView.loadUrl("https://flo.uri.sh/visualisation/4141169/embed?auto=1");
        //mWebView.loadDataWithBaseURL(null, content, "text/html", "utf-8", null); // to jeśli mamy
        //                                      HTMla zadeklarowanego w Stringu w ramach tej klasy
        //mWebView.setWebViewClient(new WebViewClient());
        //load webpage from assets
        //mWebView.loadUrl("map.html");
    }

    public void onClickRegionsBtn(View view) {
        Intent regionsIntent = new Intent(getApplicationContext(), RegionsActivity.class);
        startActivity(regionsIntent);
    }

}