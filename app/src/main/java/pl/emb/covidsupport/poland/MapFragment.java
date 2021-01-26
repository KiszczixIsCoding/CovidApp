package pl.emb.covidsupport.poland;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.widget.ArrayAdapter;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import java.util.Arrays;

import pl.emb.covidsupport.R;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MapFragment extends Fragment {
    WebView mWebView;
    View root;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        root =  inflater.inflate(R.layout.fragment_map, container, false);
        mWebView = root.findViewById(R.id.activity_webview);

        //enable javascript engine
        mWebView.getSettings().setJavaScriptEnabled(true);
        //mWebView.loadUrl("https://flo.uri.sh/visualisation/4141169/embed?auto=1");
        // na razie ładujemy cała strona, koncowo chce pobrac sama mape ze strony i zamiescic ale na razie nie wiem jeszce jak
        //mWebView.loadUrl("https://www.gov.pl/web/koronawirus/wykaz-zarazen-koronawirusem-sars-cov-2");
        mWebView.loadUrl("file:///android_asset/map.html");

        return root;

    }
}
