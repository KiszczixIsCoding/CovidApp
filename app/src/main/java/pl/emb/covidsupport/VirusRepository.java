package pl.emb.covidsupport;

import android.util.Log;
import android.widget.Toast;

import org.apache.commons.lang3.StringUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.Executor;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.Url;
import retrofit2.internal.EverythingIsNonNull;

public class VirusRepository {
    private List<VirusStatistics> statistics;
    private static final String BASE_URL = "https://api.covid19api.com/";
    private static final String OVERALL_BASE_URL = "https://corona.lmao.ninja/v2/countries?yesterday&sort";


    interface RepositoryCallback<T> {
        void onComplete(Result<List<VirusStatistics>> result);
    }

    public interface VirusApiHolder {
        @GET
        Call<List<VirusStatistics>> getStatistics(@Url String url);
    }

    public void make(final RepositoryCallback<List<VirusStatistics>> callback, String url) {
        Retrofit apiProvider = new Retrofit.Builder().baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create()).build();

        VirusApiHolder virusApiHolder = apiProvider.create(VirusApiHolder.class);
        Call<List<VirusStatistics>> call = virusApiHolder.getStatistics(url);
        call.enqueue(new Callback<List<VirusStatistics>>() {
            @EverythingIsNonNull
            @Override
            public void onResponse(Call<List<VirusStatistics>> call,
                                   Response<List<VirusStatistics>> response) {
                if (!response.isSuccessful()) {
//                    Result<List<VirusStatistics>> errorResult = Result.Error<>();
                } else {
                    statistics = response.body();
                    for (int i = 1; i < statistics.size(); i++) {
                        int newCases =  statistics.get(i).getConfirmed()
                                - statistics.get(i - 1).getConfirmed();
                        statistics.get(i).setNewCases(newCases);
                        int newDeaths =  statistics.get(i).getDeaths()
                                - statistics.get(i - 1).getDeaths();
                        statistics.get(i).setNewDeaths(newDeaths);
                    }
                    Result<List<VirusStatistics>> result = new Result.Success<>(statistics);
                    callback.onComplete(result);
                }
            }

            @Override
            @EverythingIsNonNull
            public void onFailure(Call<List<VirusStatistics>> call, Throwable t) {
                Log.println(Log.ERROR, t.getMessage(), "abc");
            }
        });
    }
}
