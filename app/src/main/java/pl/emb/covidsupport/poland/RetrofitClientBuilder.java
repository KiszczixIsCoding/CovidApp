package pl.emb.covidsupport.poland;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitClientBuilder {
    private static Retrofit retrofit;
    private static final String BASE_URL
            = "https://api.apify.com/v2/key-value-stores/3Po6TV7wTht4vIEid/";
//            = "https://api.apify.com/v2/datasets/L3VCmhMeX0KUQeJto/";

    public static Retrofit getRetrofitInstance() {

        HttpLoggingInterceptor httpLoggingInterceptor = new HttpLoggingInterceptor();
        httpLoggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);

        OkHttpClient okHttpClient = new OkHttpClient.Builder().addInterceptor(httpLoggingInterceptor).build();

        if (retrofit == null) {
            retrofit = new retrofit2.Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .client(okHttpClient)
                    .build();
        }
        return retrofit;
    }

    public static PolishCovidDataService getPolishCovidDataService() {
        PolishCovidDataService polishCovidDataService = getRetrofitInstance().
                create(PolishCovidDataService.class);

        return polishCovidDataService;
    }
}
