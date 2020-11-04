package pl.emb.covidsupport;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final TextView newCasesText = findViewById(R.id.newCasesText);
        final TextView newDeathsText = findViewById(R.id.newDeathsText);
        final TextView totalCasesText = findViewById(R.id.totalCasesText);
        final TextView totalDeathsText = findViewById(R.id.totalDeathsText);
        Spinner countriesSpinner = findViewById(R.id.spinner);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.countriesNames, R.layout.support_simple_spinner_dropdown_item );

        countriesSpinner.setAdapter(adapter);

        VirusViewModel virusViewModel = new ViewModelProvider(this).get(VirusViewModel.class);

        LiveData<List<VirusStatistics>> data = virusViewModel.getStatistics();
        data.observe(this, new Observer<List<VirusStatistics>>() {
            @Override
            public void onChanged(List<VirusStatistics> virusStatistics) {
                newCasesText.setText(virusStatistics.get(0).getCases());
                newDeathsText.setText(virusStatistics.get(0).getDeaths());
                totalCasesText.setText(virusStatistics.get(1).getCases());
                totalDeathsText.setText(virusStatistics.get(1).getDeaths());
            }
        });

//      SimpleDateFormat date = new SimpleDateFormat("dd MMMM yyyy", new Locale("pl"));
//      textView.setText(date.format(Calendar.getInstance().getTime()));
    }
}