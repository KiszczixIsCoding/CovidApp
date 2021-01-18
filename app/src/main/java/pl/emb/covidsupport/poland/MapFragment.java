package pl.emb.covidsupport.poland;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import org.jetbrains.annotations.NotNull;

import java.util.Arrays;
import java.util.List;

import pl.emb.covidsupport.R;
import pl.emb.covidsupport.RegionsFragment;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MapFragment extends Fragment implements AdapterView.OnItemSelectedListener {
    WebView mWebView;
    View root;
    private Spinner regionsSpinner;
    private List<String> regionsList;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        root =  inflater.inflate(R.layout.fragment_map, container, false);
        mWebView = root.findViewById(R.id.activity_webview);
        //spinner initialization:
        regionsSpinner = root.findViewById(R.id.regionsSpinner);
        regionsSpinner.setOnItemSelectedListener(this);
        regionsList = Arrays.asList(getResources().getStringArray(R.array.regions));
        ArrayAdapter regionsAdapter = new ArrayAdapter(getContext(),
                android.R.layout.simple_spinner_item, regionsList);
        regionsAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        regionsSpinner.setAdapter(regionsAdapter);

        Call<PolishCovidStats> polishStats = RetrofitClientBuilder.
                getPolishCovidDataService().getAllStats();
        polishStats.enqueue(new Callback<PolishCovidStats>() {
            @Override
            public void onResponse(Call<PolishCovidStats> call, Response<PolishCovidStats> response) {
                if (response.isSuccessful()) {
                    Log.e("success", response.body().toString());
                } else {
                    Log.e("failure", "fail");
                }
            }

            @Override
            public void onFailure(Call<PolishCovidStats> call, Throwable t) {
                Log.e("failure", t.getLocalizedMessage());
            }
        });


//        Call<List<PolishCovidStats>> polishStatsList = RetrofitClientBuilder.
//                getPolishCovidDataService().getAllStats();
//        polishStatsList.enqueue(new Callback<List<PolishCovidStats>>() {
//            @Override
//            public void onResponse(@NotNull Call<List<PolishCovidStats>> call, @NotNull Response<List<PolishCovidStats>> response) {
//
//                Log.e("success", response.body().toString());
//                if (response.isSuccessful()) {
//                    Log.e("success", response.body().toString());
//                } else {
//                    Log.e("failure", "fail");
//                }
//            }
//
//            @Override
//            public void onFailure(Call<List<PolishCovidStats>> call, Throwable t) {
//                Log.e("failure", t.getLocalizedMessage());
//            }
//        });
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

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}