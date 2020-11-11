package pl.emb.covidsupport;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.AxisBase;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.formatter.IAxisValueFormatter;
import com.github.mikephil.charting.interfaces.datasets.IBarDataSet;
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Objects;
import java.util.Set;

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

        virusViewModel = new ViewModelProvider(this).get(VirusViewModel.class);
        CountriesAdapter countriesAdapter = new CountriesAdapter(this, countriesList1);

        countriesSpinner.setAdapter(countriesAdapter);
        countriesSpinner.setSelection(defaultPosition);
        countriesSpinner.setOnItemSelectedListener(this);
    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
        String country = ((TextView)findViewById(R.id.country)).getText().toString();
        data = virusViewModel.getStatistics(country);
        data.observe(this, virusStatistics -> {
            newCasesText.setText(String.valueOf(
                    virusStatistics.get(virusStatistics.size() - 1).getNewCases()));
            newDeathsText.setText(String.valueOf(
                    virusStatistics.get(virusStatistics.size() - 1).getNewDeaths()));
            totalCasesText.setText(String.valueOf(
                    virusStatistics.get(virusStatistics.size() - 1).getConfirmed()));
            totalDeathsText.setText(String.valueOf(
                    virusStatistics.get(virusStatistics.size() - 1).getDeaths()));

            List<String> allDates = new ArrayList<>();
            List<Integer> allCases = new ArrayList<>();
            List<Integer> allDeaths = new ArrayList<>();
            for (int k = 0; k < virusStatistics.size(); k++) {
                allDates.add(virusStatistics.get(k).getDate());
                allCases.add(virusStatistics.get(k).getConfirmed());
                allDeaths.add(virusStatistics.get(k).getDeaths());
            }

            LineChart casesChart = findViewById(R.id.casesChart);
            drawLineChart(allDates, allCases, allDeaths, casesChart);

            List<String> dates = new ArrayList<>();
            List<Integer> cases = new ArrayList<>();
            List<Integer> deaths = new ArrayList<>();
            for (int k = 0; k < 14; k++) {
                int index = virusStatistics.size() - 14 + k;
                dates.add(virusStatistics.get(index).getDate());
                cases.add(virusStatistics.get(index).getNewCases());
                deaths.add(virusStatistics.get(index).getNewDeaths());
            }

            BarChart barChart = findViewById(R.id.newCasesChart);
            drawBarChart(dates, cases, barChart, Color.BLUE);

            BarChart barChart1 = findViewById((R.id.newDeathsChart));
            drawBarChart(dates, deaths, barChart1, Color.GREEN);
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

    public void drawLineChart(List<String> listX, List<Integer> listY1,
                              List<Integer> listY2, LineChart lineChart) {

        SimpleDateFormat dateFormat = new SimpleDateFormat(
                "dd/MM/yyyy", new Locale("pl"));


        ArrayList<Entry> casesList = new ArrayList<>();
        ArrayList<Entry> deathsList = new ArrayList<>();

        for (int i = 0; i < listX.size(); i++) {
            casesList.add(new Entry(i, listY1.get(i)));
            deathsList.add(new Entry(i, listY2.get(i)));
        }

        LineDataSet set1 = new LineDataSet(casesList, "Data set 1");
        set1.setDrawCircles(false);
        set1.setColor(getColor(R.color.colorCases));
        set1.setLineWidth(6);
        LineDataSet set2 = new LineDataSet(deathsList, "Data set 2");
        set2.setDrawCircles(false);
        set2.setColor(Color.RED);
        set2.setLineWidth(6);
        ArrayList<ILineDataSet> dataSets = new ArrayList<>();
        dataSets.add(set1);
        dataSets.add(set2);

        LineData data = new LineData(dataSets);
        lineChart.setData(data);
        lineChart.getXAxis().setValueFormatter(
                getDateXAxisFormatter(listX, dateFormat, 6));
        lineChart.getXAxis().setPosition(XAxis.XAxisPosition.BOTTOM);
        lineChart.animateXY(3000, 3000);
        lineChart.invalidate();
    }

    public void drawBarChart(List<String> dates, List<Integer> newCases, BarChart barChart, int color) {
        SimpleDateFormat dateFormat = new SimpleDateFormat(
                "dd/MM", new Locale("pl"));

        List<BarEntry> lastMonthCases = new ArrayList<>();
        for (int i = 0; i < dates.size(); i++) {
            lastMonthCases.add(new BarEntry(i, newCases.get(i)));
        }

        BarDataSet barDataSet = new BarDataSet(lastMonthCases, "Data set 1");
        barDataSet.setColor(color);
        BarData data = new BarData(barDataSet);
        barChart.setData(data);
        barChart.getXAxis().setValueFormatter(
                getDateXAxisFormatter(dates, dateFormat, -1));
        barChart.getXAxis().setPosition(XAxis.XAxisPosition.BOTTOM);
        barChart.getXAxis().setLabelRotationAngle(-30);
        barChart.invalidate();
    }

    public IAxisValueFormatter getDateXAxisFormatter(
            List<String> dates, SimpleDateFormat dateFormat, int labelsNumber) {
        return (value, axis) -> {
            if (labelsNumber >= 0) {
                axis.setLabelCount(6, true);
            }
            SimpleDateFormat format = new SimpleDateFormat(
                    "yyyy-MM-dd'T'HH:mm:ss'Z'", new Locale("pl"));

            String dateTime = "";
            try {
                Date date = format.parse(dates.get((int)value));
                dateTime = dateFormat.format(date);
            } catch (ParseException e) {
                e.printStackTrace();
            }
            return dateTime;
        };
    }
}