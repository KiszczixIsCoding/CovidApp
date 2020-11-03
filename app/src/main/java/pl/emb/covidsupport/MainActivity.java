package pl.emb.covidsupport;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import android.os.Bundle;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final TextView newCasesText = findViewById(R.id.newCasesText);
        final TextView newDeathsText = findViewById(R.id.newDeathsText);

        VirusViewModel virusViewModel = new ViewModelProvider(this)
                .get(VirusViewModel.class);

        LiveData<VirusStatistics> data = virusViewModel.getStatistics();
        data.observe(this, new Observer<VirusStatistics>() {
            @Override
            public void onChanged(VirusStatistics virusStatistics) {
                newCasesText.setText(virusStatistics.getCases());
                newDeathsText.setText(virusStatistics.getDeaths());
            }
        });

//      SimpleDateFormat date = new SimpleDateFormat("dd MMMM yyyy", new Locale("pl"));
//      textView.setText(date.format(Calendar.getInstance().getTime()));
    }
}