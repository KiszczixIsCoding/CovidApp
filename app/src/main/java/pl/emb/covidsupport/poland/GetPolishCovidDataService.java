package pl.emb.covidsupport.poland;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface GetPolishCovidDataService {
    @GET
    Call<List<PolishCovidStats>> getAllStats();
}
