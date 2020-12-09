package pl.emb.covidsupport;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class MapActivity extends AppCompatActivity {
    WebView mWebView;
    String[] idsToHide = { "section1", "section3", "section5" };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map);
        String content="<h1>Heading 1</h1>\n" +
                "        <h2>Heading 2</h2>\n" +
                "        <p>This is some html. Look, here\\'s an <u>underline</u>.</p>\n" +
                "        <p>Look, this is <em>emphasized.</em> And here\\'s some <b>bold</b>.</p>\n" +
                "        <div class=\"flourish-embed flourish-map\" data-src=\"story/4141169\"><script src=\"https://public.flourish.studio/resources/embed.js\"></script></div>\n" +
                "        <p>Here are UL list items:\n" +
                "        <ul>\n" +
                "        <li>One</li>\n" +
                "        <li>Two</li>\n" +
                "        <li>Three</li>\n" +
                "        </ul>\n" +
                "        <p>Here are OL list items:\n" +
                "        <ol>\n" +
                "        <li>One</li>\n" +
                "        <li>Two</li>\n" +
                "        <li>Three</li>\n" +
                "        </ol>";
        //https://flo.uri.sh/visualisation/4141169/embed?auto=1
        //<div class="flourish-embed flourish-map" data-src="story/4141169"><script src="https://public.flourish.studio/resources/embed.js"></script></div>

                //mWebView = new WebView(getApplicationContext());
       // mWebView.loadUrl("file:///android_asset/map.html");
        mWebView = (WebView) findViewById(R.id.activity_webview);
        //enable javascript engine
        mWebView.getSettings().setJavaScriptEnabled(true);
        mWebView.loadUrl("https://flo.uri.sh/visualisation/4141169/embed?auto=1");
        //mWebView.loadDataWithBaseURL(null, content, "text/html", "utf-8", null);
        //mWebView.setWebViewClient(new WebViewClient());

        //        mWebView.setWebViewClient(new WebViewClient() {
//            @Override
//            public void onPageFinished(WebView view, String url) {
//                // TODO Auto-generated method stub
//                super.onPageFinished(view, url);
//
//                //run 'disableSection' for all divs to hide/disable
//                for (String s : idsToHide) {
//                    String surveyId = s;
//                    view.loadUrl("javascript:disableSection('" + surveyId + "');");
//                }
//            }
//        });
        //load webpage from assets
        //mWebView.loadUrl("map.html");
    }

//    @Override
//    public void onBackPressed() {
//        if(mWebView.canGoBack())
//        {
//            mWebView.goBack();
//        }
//
//        else{
//            super.onBackPressed();
//        }
//    }
}