package pl.emb.covidsupport.global.repositories;

import android.util.Log;

import pl.emb.covidsupport.global.stats.MainCovidStats;
import pl.emb.covidsupport.global.Result;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.Url;
import retrofit2.internal.EverythingIsNonNull;

/***
 * Repository for specific country's overall covid statistics
 */
public class MainStatsRepository {
    private MainCovidStats mainCovidStats;
    private static final String MAIN_BASE_URL = "https://corona.lmao.ninja/v2/countries/";

    public interface RepositoryCallback<T> {
        void onComplete(Result<T> result);
    }

    public interface VirusApiHolder {
        @GET
        Call<MainCovidStats> getCountries(@Url String url);
    }

    public void makeCountries(final RepositoryCallback<MainCovidStats> callback, String url) {
        Retrofit apiProvider = new Retrofit.Builder().baseUrl(MAIN_BASE_URL)
                .addConverterFactory(GsonConverterFactory.create()).build();

        VirusApiHolder virusApiHolder = apiProvider.create(VirusApiHolder.class);
        Call<MainCovidStats> call = virusApiHolder.getCountries(url + "?yesterday=false");
        call.enqueue(new Callback<MainCovidStats>() {
            @EverythingIsNonNull
            @Override
            public void onResponse(Call<MainCovidStats> call,
                                   Response<MainCovidStats> response) {


                if (!response.isSuccessful()) {
//                    Result<Error> errorResult = Result.Error<>(response.code());
                } else {
                    mainCovidStats = response.body();
                }
                Result<MainCovidStats> result = new Result.Success<>(mainCovidStats);
                callback.onComplete(result);
            }

            @Override
            @EverythingIsNonNull
            public void onFailure(Call<MainCovidStats> call, Throwable t) {
                Log.println(Log.ERROR, "", t.getMessage());
            }
        });
    }
}
