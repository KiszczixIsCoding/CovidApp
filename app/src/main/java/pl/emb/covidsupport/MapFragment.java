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
import android.webkit.WebViewClient;

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
        //mWebView.loadUrl("https://flo.uri.sh/visualisation/4141169/embed?auto=1");
        // na razie ładujemy cała strona, koncowo chce pobrac sama mape ze strony i zamiescic ale na razie nie wiem jeszce jak
        mWebView.loadUrl("https://www.gov.pl/web/koronawirus/wykaz-zarazen-koronawirusem-sars-cov-2");
        return root;
    }

    // to odkomentowac, jesli chcemy pobrac mape z map.html (na razie jest tam szkic mapy, trzeba dodac js wykorzytywany na gov.pl, tego jeszcze nie wiem jak zrobic)
//    @Override
//    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
//                             @Nullable Bundle savedInstanceState) {
//        root =  inflater.inflate(R.layout.fragment_map, container, false);
//
//        mWebView = root.findViewById(R.id.activity_webview);
//        //enable javascript engine
//        mWebView.getSettings().setJavaScriptEnabled(true);
//        //mWebView.loadUrl("https://flo.uri.sh/visualisation/4141169/embed?auto=1");
//        //mWebView.loadDataWithBaseURL(null, content, "text/html", "utf-8", null); // to jeśli mamy
//        //                                      HTMla zadeklarowanego w Stringu w ramach tej klasy
//        mWebView.setWebViewClient(new WebViewClient());
//        //load webpage from assets
//        mWebView.loadUrl("file:///android_asset/map.html");
//        return root;
//    }

    public void onClickRegionsBtn(View view) {
        Intent regionsIntent = new Intent(root.getContext(), RegionsFragment.class);
        startActivity(regionsIntent);
    }

}