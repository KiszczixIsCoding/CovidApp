package pl.emb.covidsupport.poland;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitClientBuilder {
    private static Retrofit retrofit;
    private static final String BASE_URL
            = "https://api.apify.com/v2/datasets/L3VCmhMeX0KUQeJto/items?format=json&clean=1";

    public static Retrofit getRetrofitInstance() {
        if (retrofit == null) {
            retrofit = new retrofit2.Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return retrofit;
    }
}
