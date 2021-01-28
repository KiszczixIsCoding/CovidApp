package pl.emb.covidsupport.poland;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import pl.emb.covidsupport.R;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RegionsFragment extends Fragment {

    View root;
    TextView numberCases, numberDeaths, wojewodztwoText, dateText;
    Button goBackBtn;

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
        wojewodztwoText = root.findViewById(R.id.wojewodztwoTextView);
        dateText = root.findViewById(R.id.dateTextView);
        goBackBtn = root.findViewById(R.id.goBackButton);

        Call<PolishCovidStats> polishStats = RetrofitClientBuilder.
                getPolishCovidDataService().getAllStats();
        polishStats.enqueue(new Callback<PolishCovidStats>() {
            @Override
            public void onResponse(Call<PolishCovidStats> call, Response<PolishCovidStats> response) {
                if (response.isSuccessful()) {
                    Log.e("success", response.body().toString());
                    if (response.body().infectedByRegion.get(regionNumber).get("region").equals("Caly kraj")) {
                        wojewodztwoText.setText(response.body().infectedByRegion.get(regionNumber).get("region"));
                    } else {
                        String wojewodztwoTextToDisplay = wojewodztwoText.getText() + " " + response.body().infectedByRegion.get(regionNumber).get("region");
                        wojewodztwoText.setText(wojewodztwoTextToDisplay);
                    }
                    numberCases.setText(response.body().getInfectedByRegion().get(regionNumber).get("infectedCount"));
                    numberDeaths.setText(response.body().getInfectedByRegion().get(regionNumber).get("deceasedCount"));
                    String dateTextToDisplay = dateText.getText() + " " + formatStateDate(response.body().getLastUpdatedAtApify());
                    dateText.setText(dateTextToDisplay);

                } else {
                    Log.e("failure", "fail");
                }
            }

            @Override
            public void onFailure(Call<PolishCovidStats> call, Throwable t) {
                Log.e("failure", t.getLocalizedMessage());
            }
        });

        // ustawiamy onClick na przycisk (nie mozemy zrobić jako osobna metodę z uwagi, że mamy do
        // czynienia z fragmentem.
        goBackBtn.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        getParentFragmentManager().beginTransaction().replace
                                (R.id.fragmentPolandContainer, new PolandFragment()).commit();
                    }
                });

        return root;
    }

    public String formatStateDate(String date) {

        SimpleDateFormat plFormat = new SimpleDateFormat(
                "dd.MM.20yy", new Locale("pl"));
        SimpleDateFormat engFormat = new SimpleDateFormat(
                "yyyy-MM-dd'T'HH:mm:ss.SSSXXX", new Locale("eng"));
        String formattedDate = "";
        try {
            Date lastDate = engFormat.parse(date);
            formattedDate = plFormat.format(lastDate);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return formattedDate;
    }

}