package pl.emb.covidsupport;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Spinner;
import android.widget.TextView;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Locale;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {
    private Spinner countriesSpinner;
    private TextView newCasesText, newDeathsText, totalCasesText, totalDeathsText;
    private VirusViewModel virusViewModel;
    private LiveData<List<VirusStatistics>> data;

    private static class LocalesComparator implements Comparator<Locale> {

        @Override
        public int compare(Locale locale, Locale t1) {
            return locale.getDisplayCountry().compareTo(t1.getDisplayCountry());
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        newCasesText = findViewById(R.id.newCasesText);
        newDeathsText = findViewById(R.id.newDeathsText);
        totalCasesText = findViewById(R.id.totalCasesText);
        totalDeathsText = findViewById(R.id.totalDeathsText);
        countriesSpinner = findViewById(R.id.spinner);

        ArrayList<CountryItem> countriesList1 = new ArrayList<>();

        List<Locale> localesList = new ArrayList<>();
        for (String country : Locale.getISOCountries()) {
            localesList.add(new Locale("en", country));
        }

        Collections.sort(localesList, new LocalesComparator());
        int defaultPosition = 0;
        for(Locale localeCountry : localesList) {
            int id = getResources().getIdentifier(
                    localeCountry.getCountry().toLowerCase(), "raw", getPackageName());
            String countryName = localeCountry.getDisplayCountry();

            countriesList1.add(new CountryItem(countryName, id));
            if (countryName.equals("Poland")) {
                defaultPosition = localesList.indexOf(localeCountry);
            }
        }

        CountriesAdapter countriesAdapter = new CountriesAdapter(this, countriesList1);
        countriesSpinner.setAdapter(countriesAdapter);

        virusViewModel = new ViewModelProvider(this).get(VirusViewModel.class);

        countriesSpinner.setSelection(defaultPosition);
        countriesSpinner.setOnItemSelectedListener(this);
    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
        String country = ((TextView) findViewById(R.id.country)).getText().toString();
        data = virusViewModel.getStatistics(country);
        data.observe(this, new Observer<List<VirusStatistics>>() {
            @Override
            public void onChanged(List<VirusStatistics> virusStatistics) {
                newCasesText.setText(virusStatistics.get(0).getCases());
                newDeathsText.setText(virusStatistics.get(0).getDeaths());
                totalCasesText.setText(virusStatistics.get(1).getCases());
                totalDeathsText.setText(virusStatistics.get(1).getDeaths());
            }
        });
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }

    public void onClickRegionsBtn(View view) {
        Intent regionsIntent = new Intent(getApplicationContext(), RegionsActivity.class);
        startActivity(regionsIntent);
    }

    public void onClickInfoBtn(View view) {
        Intent infoIntent = new Intent(getApplicationContext(), InformationActivity.class);
        startActivity(infoIntent);
    }
}