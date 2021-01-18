package pl.emb.covidsupport.poland;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface PolishCovidDataService {
//    @GET("items/")
    @GET("records/LATEST?disableRedirect=true/")
    Call<PolishCovidStats> getAllStats();
//    Call<List<PolishCovidStats>> getAllStats();
}
