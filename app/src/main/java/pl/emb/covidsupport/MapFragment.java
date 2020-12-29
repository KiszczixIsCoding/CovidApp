package pl.emb.covidsupport;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;

public class MapFragment extends Fragment {
    WebView mWebView;
    View root;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        root =  inflater.inflate(R.layout.fragment_map, container, false);
        mWebView = root.findViewById(R.id.activity_webview);
        //enable javascript engine
        mWebView.getSettings().setJavaScriptEnabled(true);
        mWebView.loadUrl("https://flo.uri.sh/visualisation/4141169/embed?auto=1");
        return root;
    }

//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_map);
//
//        mWebView = (WebView) findViewById(R.id.activity_webview);
//        //enable javascript engine
//        mWebView.getSettings().setJavaScriptEnabled(true);
//        mWebView.loadUrl("https://flo.uri.sh/visualisation/4141169/embed?auto=1");
//        //mWebView.loadDataWithBaseURL(null, content, "text/html", "utf-8", null); // to jeśli mamy
//        //                                      HTMla zadeklarowanego w Stringu w ramach tej klasy
//        //mWebView.setWebViewClient(new WebViewClient());
//        //load webpage from assets
//        //mWebView.loadUrl("map.html");
//    }

    public void onClickRegionsBtn(View view) {
        Intent regionsIntent = new Intent(root.getContext(), RegionsFragment.class);
        startActivity(regionsIntent);
    }

}