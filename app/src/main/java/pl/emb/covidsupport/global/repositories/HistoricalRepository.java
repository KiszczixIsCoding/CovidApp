package pl.emb.covidsupport.global.repositories;

import pl.emb.covidsupport.global.stats.HistoricalCovidStats;
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
 * Repository for specific country's historical covid statistics
 */
public class HistoricalRepository {
    private HistoricalCovidStats historicalCovidStats;
    private static final String MAIN_BASE_URL = "https://corona.lmao.ninja/v2/historical/";

    public interface RepositoryCallback<T> {
        void onComplete(Result<T> result);
    }

    public interface VirusApiHolder {
        @GET
        Call<HistoricalCovidStats> getCountries(@Url String url);
    }

    public void makeCountries(final RepositoryCallback<HistoricalCovidStats> callback, String url) {
        Retrofit apiProvider = new Retrofit.Builder().baseUrl(MAIN_BASE_URL)
                .addConverterFactory(GsonConverterFactory.create()).build();

        VirusApiHolder virusApiHolder = apiProvider.create(VirusApiHolder.class);
        Call<HistoricalCovidStats> call = virusApiHolder.getCountries(url + "?lastdays=all");
        call.enqueue(new Callback<HistoricalCovidStats>() {
            @EverythingIsNonNull
            @Override
            public void onResponse(Call<HistoricalCovidStats> call,
                                   Response<HistoricalCovidStats> response) {

                if (!response.isSuccessful()) {
//                    Result<List<VirusStatistics>> errorResult = Result.Error<>();
                } else {
                    historicalCovidStats = response.body();
                }
                Result<HistoricalCovidStats> result = new Result.Success<>(historicalCovidStats);
                callback.onComplete(result);
            }

            @Override
            @EverythingIsNonNull
            public void onFailure(Call<HistoricalCovidStats> call, Throwable t) {
//                Log.println(Log.ERROR, t.getMessage(), "abc");
            }
        });
    }
}
