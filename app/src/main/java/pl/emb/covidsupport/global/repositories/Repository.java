package pl.emb.covidsupport.global.repositories;

import pl.emb.covidsupport.global.Result;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.Url;
import retrofit2.internal.EverythingIsNonNull;

public class Repository<T> {
    private T jsonObject;
    private final String BASE_URL;

    public Repository(String BASE_URL) {
        this.BASE_URL = BASE_URL;
    }

    interface RepositoryCallback<T> {
        void onComplete(Result<T> result);
    }

    public interface VirusApiHolder<T> {
        @GET
        Call<T> getCountries(@Url String url);
    }

    public void makeCountries(final RepositoryCallback<T> callback, String url) {
        Retrofit apiProvider = new Retrofit.Builder().baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create()).build();

        VirusApiHolder<T> virusApiHolder = apiProvider.create(VirusApiHolder.class);
        Call<T> call = virusApiHolder.getCountries(url + "?yesterday=true");
        call.enqueue(new Callback<T>() {
            @EverythingIsNonNull
            @Override
            public void onResponse(Call<T> call, Response<T> response) {
                if (!response.isSuccessful()) {
//                    Result<T> errorResult = Result.Error<>();
                } else {
                    jsonObject = response.body();
                }
                Result<T> result = new Result.Success<>(jsonObject);
                callback.onComplete(result);
            }

            @Override
            @EverythingIsNonNull
            public void onFailure(Call<T> call, Throwable t) {
//                Log.println(Log.ERROR, t.getMessage(), "abc");
            }
        });
    }
}
