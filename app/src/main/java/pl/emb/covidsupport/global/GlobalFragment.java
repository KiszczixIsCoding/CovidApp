package pl.emb.covidsupport.global;

import android.app.Dialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModelProvider;
import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.charts.LineChart;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import pl.emb.covidsupport.R;
import pl.emb.covidsupport.global.stats.HistoricalCovidStats;
import pl.emb.covidsupport.global.stats.MainCovidStats;

public class GlobalFragment extends Fragment implements AdapterView.OnItemClickListener {
    private final static int DEFAULT_SPINNER_POS = 135;
    private final static int DEFAULT_DAYS_RANGE = 14;
    private View root; // GlobalFragment view
    private TextView stateText, newCasesText, newDeathsText, totalCasesText, totalDeathsText;
    private VirusViewModel virusViewModel;
    private Dialog spinnerDialog;
    private ImageView currentImage;
    private TextView currentCountry;
    private CountriesAdapter countriesAdapter;
    private EditText searchBox;

    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        root = inflater.inflate(R.layout.fragment_global, container, false);
        stateText = root.findViewById(R.id.stateLabel);
        newCasesText = root.findViewById(R.id.newCasesText);
        newDeathsText = root.findViewById(R.id.newDeathsText);
        totalCasesText = root.findViewById(R.id.totalCasesText);
        totalDeathsText = root.findViewById(R.id.totalDeathsText);
        currentImage = root.findViewById(R.id.currentFlag);
        currentCountry = root.findViewById(R.id.currentCountry);

        // country list expansion bar
        List<String> excludedCountries =
                Arrays.asList(getResources().getStringArray(R.array.excludedIsoCodes));

        ArrayList<CountryItem> countriesList = new ArrayList<>();

        // loop to save countries to the list excluding countries without covid stats
        for (String iso : Locale.getISOCountries()) {
            if (!excludedCountries.contains(iso)) {
                Locale locale = new Locale("", iso);
                countriesList.add(new CountryItem(root.getContext(), locale));
            }
        }

        // sort countries by polish names
        Collections.sort(countriesList);

        virusViewModel = new ViewModelProvider(this).get(VirusViewModel.class);
        countriesAdapter = new CountriesAdapter(root.getContext(),
                countriesList);

        // Prepare dialog with countries to choose
        spinnerDialog = new Dialog(root.getContext());
        spinnerDialog.setContentView(R.layout.searchable_dialog);
        spinnerDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        spinnerDialog.getWindow().setLayout(1300, 1800);

        LinearLayout spinnerView = root.findViewById(R.id.linear);
        spinnerView.setOnClickListener(view -> spinnerDialog.show());

        ListView listView = spinnerDialog.findViewById(R.id.listView);
        listView.setAdapter(countriesAdapter);

        searchBox = spinnerDialog.findViewById(R.id.editText);
        searchBox.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                countriesAdapter.getFilter().filter(charSequence);
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

        TextView closeText = spinnerDialog.findViewById(R.id.close);
        closeText.setOnClickListener(view -> spinnerDialog.dismiss());

        // Set listener on click on country
        listView.setOnItemClickListener(this);

        // Perform default choice (Poland)
        listView.performItemClick(listView.getAdapter().getView(DEFAULT_SPINNER_POS,
                null, null),
                DEFAULT_SPINNER_POS, listView.getAdapter().getItemId(DEFAULT_SPINNER_POS));

        listView.setSelection(DEFAULT_SPINNER_POS);

        return root;
    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

        // Change current image and country name
        currentImage.setImageResource(countriesAdapter.getItem(i).getCountryId());
        currentCountry.setText(countriesAdapter.getItem(i).getPolishCountryName());
        // Clear filter
        countriesAdapter.getFilter().filter("");
        // Clear searchable box
        searchBox.setText("");
        // Close countries' dialog
        spinnerDialog.dismiss();

        LiveData<MainCovidStats> mainData =
                virusViewModel.getNovelMainStats(countriesAdapter.getItem(i).getIso2());
        mainData.observe(this, novelMainStatistics -> {
            newCasesText.setText(String.valueOf(novelMainStatistics.getTodayCases()));
            newDeathsText.setText(String.valueOf(novelMainStatistics.getTodayDeaths()));
            totalCasesText.setText(String.valueOf(novelMainStatistics.getCases()));
            totalDeathsText.setText(String.valueOf(novelMainStatistics.getDeaths()));
        });

        LiveData<HistoricalCovidStats> historicalData =
                virusViewModel.getHistorical(countriesAdapter.getItem(i).getIso2());
        historicalData.observe(this, historicalStats ->  {

//          Format and display the day of last update
            String stateWithDate = stateText.getText().subSequence(0,7) + " " + formatStateDate(
                    historicalStats.getDates().get(historicalStats.getDates().size() - 1));
            stateText.setText(stateWithDate);

            List<String> dates = historicalStats.getDates();
            List<Integer> cases = historicalStats.getCases();
            List<Integer> deaths = historicalStats.getDeaths();

            List<String> subDates = historicalStats.getLastDates(DEFAULT_DAYS_RANGE);
            List<Integer> subCases = historicalStats.getLastCases(DEFAULT_DAYS_RANGE);
            List<Integer> subDeaths = historicalStats.getLastDeaths(DEFAULT_DAYS_RANGE);
            List<Integer> recovered = historicalStats.getRecovered();

            ChartsManager chartsManager = new ChartsManager(getContext());

            // Prepare and draw 1st chart (line chart of all cases & deaths)
            LineChart casesChart = root.findViewById(R.id.casesChart);
            chartsManager.drawLineChart(dates, cases, deaths, casesChart);

            // Prepare and draw 2nd chart (bar chart of cases during last days)
            BarChart barChart = root.findViewById(R.id.newCasesChart);
            chartsManager.drawBarChart(subDates, subCases,
                    barChart, getContext().getColor(R.color.colorCasesChart));

            // Prepare and draw 3rd chart (bar chart of deaths during last days)
            BarChart barChart1 = root.findViewById((R.id.newDeathsChart));
            chartsManager.drawBarChart(subDates, subDeaths,
                    barChart1, getContext().getColor(R.color.colorDeathsChart));

            // Prepare and draw 4th chart (line chart of all recovered)
            LineChart recoveredChart = root.findViewById(R.id.recoveredChart);
            chartsManager.drawLineChart(dates, recovered, recoveredChart);
        });
    }

    public String formatStateDate(String date) {

        SimpleDateFormat plFormat = new SimpleDateFormat(
                "dd.MM.20yy", new Locale("pl"));
        SimpleDateFormat engFormat = new SimpleDateFormat(
                "MM/dd/yy", new Locale("eng"));
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
