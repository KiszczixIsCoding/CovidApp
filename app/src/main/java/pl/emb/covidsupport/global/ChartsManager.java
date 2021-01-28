package pl.emb.covidsupport.global;

import android.content.Context;
import android.util.Log;

import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.formatter.IAxisValueFormatter;
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import pl.emb.covidsupport.R;
import pl.emb.covidsupport.global.markers.ChartMarkerView;
import pl.emb.covidsupport.global.markers.LineMarkerView;

public class ChartsManager {
    private final SimpleDateFormat lineChartDateFormat
            = new SimpleDateFormat("dd-MM-20yy", new Locale("pl"));

    private final SimpleDateFormat barChartDateFormat
            = new SimpleDateFormat("dd/MM", new Locale("pl"));

    private final SimpleDateFormat engDataFormat = new SimpleDateFormat(
            "MM/dd/yy", new Locale("en"));

    private Context context;

    public ChartsManager(Context context) {
        this.context = context;
    }

    public void drawLineChart(List<String> listX, List<Integer> listY1, LineChart lineChart, int color) {

//        Prepare axis data
        ArrayList<Entry> entriesList = new ArrayList<>();

        for (int i = 0; i < listX.size(); i++) {
            entriesList.add(new Entry(i, listY1.get(i)));
        }

//        1st data set settings
        LineDataSet set1;

        if (color == R.color.colorCasesLine) {
            set1 = new LineDataSet(entriesList, "Liczba zachorowań");
        } else {
            set1 = new LineDataSet(entriesList, "Liczba zgonów");
        }

        set1.setDrawCircles(false);
        set1.setColor(context.getColor(color));
        set1.setLineWidth(6);


        ArrayList<ILineDataSet> dataSets = new ArrayList<>();
        dataSets.add(set1);
        LineData data = new LineData(dataSets);

//        LineChart settings
        lineChart.setData(data);
        lineChart.getXAxis().setValueFormatter(
                getDateXAxisFormatter(listX, lineChartDateFormat, 5));
        lineChart.getXAxis().setPosition(XAxis.XAxisPosition.BOTTOM);
        lineChart.animateXY(3000, 3000);
        lineChart.getDescription().setEnabled(false);
        lineChart.getAxisRight().setEnabled(false);
        lineChart.getLineData().setDrawValues(false);

        LineMarkerView marker = new LineMarkerView(context, R.layout.marker_layout,
                "Liczba ozdrowieńców:", listX.get(listX.size() - 1), listX.size());
        if (color == R.color.colorCasesLine) {
            marker.setSection("Liczba zachorowań:");
        } else {
            marker.setSection("Liczba zgonów:");
        }
        lineChart.setMarker(marker);
        lineChart.invalidate();  // Draw LineChart
    }

    public void drawLineChart(List<String> listX, List<Integer> listY1, LineChart lineChart) {
//        Prepare axis data
        ArrayList<Entry> recoveredList = new ArrayList<>();

        for (int i = 0; i < listX.size(); i++) {
            recoveredList.add(new Entry(i, listY1.get(i)));
        }

//        1st data set settings
        LineDataSet set1 = new LineDataSet(recoveredList, "Liczba ozdrowieńców");
        set1.setDrawCircles(false);
        set1.setColor(context.getColor(R.color.colorLine));
        set1.setDrawFilled(true);
        set1.setFillColor(context.getColor(R.color.colorFill));
        set1.setLineWidth(5);


        ArrayList<ILineDataSet> dataSets = new ArrayList<>();
        dataSets.add(set1);
        LineData data = new LineData(dataSets);

//        LineChart settings
        lineChart.setData(data);
        lineChart.getXAxis().setValueFormatter(
                getDateXAxisFormatter(listX, lineChartDateFormat, 5));
        lineChart.getXAxis().setPosition(XAxis.XAxisPosition.BOTTOM);
        lineChart.animateXY(3000, 3000);
        lineChart.getDescription().setEnabled(false);
        lineChart.getAxisRight().setEnabled(false);
        lineChart.getLineData().setDrawValues(false);

        LineMarkerView marker = new LineMarkerView(context, R.layout.marker_layout,
                "Liczba ozdrowieńców:", listX.get(listX.size() - 1), listX.size());
        lineChart.setMarker(marker);
        lineChart.invalidate();  // Draw LineChart
    }

    public void drawBarChart(List<String> dates, List<Integer> newCases, BarChart barChart, int color) {

//        Prepare axis data
        List<BarEntry> lastData= new ArrayList<>();
        for (int i = 0; i < dates.size(); i++) {
            lastData.add(new BarEntry(i, newCases.get(i)));
        }

//        Data set settings
        BarDataSet barDataSet;
        if (color == context.getColor(R.color.colorCasesChart)) {
            barDataSet = new BarDataSet(lastData, "Liczba zachorowań");
        } else {
            barDataSet = new BarDataSet(lastData, "Liczba zgonów");
        }
        barDataSet.setColor(context.getColor(color));
        BarData data = new BarData(barDataSet);

//        BarChart settings
        barChart.setData(data);
        barChart.getXAxis().setValueFormatter(
                getDateXAxisFormatter(dates, barChartDateFormat, -1));
        barChart.getXAxis().setPosition(XAxis.XAxisPosition.BOTTOM);
        barChart.getXAxis().setLabelRotationAngle(-30);
        barChart.getDescription().setEnabled(false);
        barChart.getAxisRight().setEnabled(false);
        barChart.getBarData().setDrawValues(false);


        ChartMarkerView marker;

        if (color == context.getColor(R.color.colorCasesChart)) {
             marker = new ChartMarkerView(context, R.layout.marker_layout,
                    "Liczba zachorowań:", dates.get(dates.size() - 1), 14);
        } else {
             marker = new ChartMarkerView(context, R.layout.marker_layout,
                    "Liczba zgonów:", dates.get(dates.size() - 1), 14);
        }
        barChart.setMarker(marker);
        barChart.invalidate(); //Draw BarChart
    }

    public IAxisValueFormatter getDateXAxisFormatter(
            List<String> dates, SimpleDateFormat dateFormat, int labelsNumber) {
        return (value, axis) -> {
            if (labelsNumber >= 0) {
                axis.setLabelCount(labelsNumber, true);
            }

            String dateTime = "";
            try {
                Date date = engDataFormat.parse(dates.get((int)value));
                dateTime = dateFormat.format(date);
            } catch (ParseException e) {
                e.printStackTrace();
            }
            return dateTime;
        };
    }
}
