package pl.emb.covidsupport.global;

import android.content.Context;
import android.graphics.Color;
import android.service.media.MediaBrowserService;
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

    public void drawLineChart(List<String> listX, List<Integer> listY1,
                              List<Integer> listY2, LineChart lineChart) {

//        Prepare axis data
        ArrayList<Entry> casesList = new ArrayList<>();
        ArrayList<Entry> deathsList = new ArrayList<>();

        for (int i = 0; i < listX.size(); i++) {
            casesList.add(new Entry(i, listY1.get(i)));
            deathsList.add(new Entry(i, listY2.get(i)));
        }

//        1st data set settings
        LineDataSet set1 = new LineDataSet(casesList, "Data set 1");
        set1.setDrawCircles(false);
        set1.setColor(context.getColor(R.color.colorCasesLine));
        set1.setLineWidth(6);

//        2nd data set settings
        LineDataSet set2 = new LineDataSet(deathsList, "Data set 2");
        set2.setDrawCircles(false);
        set2.setColor(context.getColor(R.color.colorDeathsLine));
        set2.setLineWidth(6);

        ArrayList<ILineDataSet> dataSets = new ArrayList<>();
        dataSets.add(set1);
        dataSets.add(set2);
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
//        lineChart.getLegend().setEnabled(false);
        lineChart.invalidate();  // Draw LineChart
    }

    public void drawLineChart(List<String> listX, List<Integer> listY1, LineChart lineChart) {
//        Prepare axis data
        ArrayList<Entry> recoveredList = new ArrayList<>();

        for (int i = 0; i < listX.size(); i++) {
            recoveredList.add(new Entry(i, listY1.get(i)));
        }

//        1st data set settings
        LineDataSet set1 = new LineDataSet(recoveredList, "Data set 1");
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
//        lineChart.getLegend().setEnabled(false);
        lineChart.invalidate();  // Draw LineChart
    }

    public void drawBarChart(List<String> dates, List<Integer> newCases, BarChart barChart, int color) {

//        Prepare axis data
        List<BarEntry> lastData= new ArrayList<>();
        for (int i = 0; i < dates.size(); i++) {
            lastData.add(new BarEntry(i, newCases.get(i)));
        }

//        Data set settings
        BarDataSet barDataSet = new BarDataSet(lastData, null);
        barDataSet.setColor(color);
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
//        barChart.getLegend().setEnabled(false);
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
