package pl.emb.covidsupport.poland;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import pl.emb.covidsupport.R;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RegionsFragment extends Fragment {

    View root;
    TextView numberCases, numberDeaths;

    int regionNumber;

    public RegionsFragment(int regionNumber) {
        this.regionNumber = regionNumber;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        root = inflater.inflate(R.layout.fragment_regions, container, false);

        numberCases = root.findViewById(R.id.number_cases);
        numberDeaths = root.findViewById(R.id.number_deaths);

        Call<PolishCovidStats> polishStats = RetrofitClientBuilder.
                getPolishCovidDataService().getAllStats();
        polishStats.enqueue(new Callback<PolishCovidStats>() {
            @Override
            public void onResponse(Call<PolishCovidStats> call, Response<PolishCovidStats> response) {
                if (response.isSuccessful()) {
                    Log.e("success", response.body().toString());
                    numberCases.setText(response.body().getInfectedByRegion().get(regionNumber).get("infectedCount"));  //TODO: fix
                    numberDeaths.setText(response.body().getInfectedByRegion().get(regionNumber).get("deceasedCount"));

                } else {
                    Log.e("failure", "fail");
                }
            }

            @Override
            public void onFailure(Call<PolishCovidStats> call, Throwable t) {
                Log.e("failure", t.getLocalizedMessage());
            }
        });


        return root;
    }
}