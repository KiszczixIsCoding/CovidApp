package pl.emb.covidsupport.global.markers;

import android.content.Context;
import android.util.Log;
import android.widget.TextView;

import androidx.core.content.res.ResourcesCompat;

import com.github.mikephil.charting.components.MarkerView;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.highlight.Highlight;
import com.github.mikephil.charting.utils.MPPointF;

import org.joda.time.DateTime;

import pl.emb.covidsupport.R;
import pl.emb.covidsupport.global.DateFormatter;

public class ChartMarkerView extends MarkerView {
    private float xOffsetMultiplier;
    private String section;
    private final String lastDate;
    private int daysLeft;

    public void setSection(String section) {
        this.section = section;
    }

    public ChartMarkerView(Context context, int layoutResource, String section, String lastDate, int daysLeft) {
        super(context, layoutResource);
        this.section = section;
        this.lastDate = lastDate;
        this.daysLeft = daysLeft;
    }

    @Override
    public void refreshContent(Entry e, Highlight highlight) {
        TextView casesLabel = findViewById(R.id.casesLabel);
        casesLabel.setText(section);
        TextView cases = findViewById(R.id.casesText);
        cases.setText(String.valueOf((int)highlight.getY()));

        TextView dateText = findViewById(R.id.dateText);
        String highlightedDate = DateFormatter.formatStateDate(lastDate, 14 - (int)e.getX());

        dateText.setText(highlightedDate);

        if (e.getX() < 4) {
            xOffsetMultiplier = 9.8f;
            setBackground(ResourcesCompat.getDrawable(getResources(), R.drawable.marker_left, null));
        } else if (e.getX() > 9) {
            //timestamps is an array containing xValues timestamps
            xOffsetMultiplier = 1.12f;
            setBackground(ResourcesCompat.getDrawable(getResources(), R.drawable.marker_right, null));
        } else {
            xOffsetMultiplier = 2;
            setBackground(ResourcesCompat.getDrawable(getResources(), R.drawable.marker_middle, null));
        }
    }

    @Override
    public MPPointF getOffset() {
        return new MPPointF(-(getWidth() / xOffsetMultiplier), -getHeight());
    }
}
