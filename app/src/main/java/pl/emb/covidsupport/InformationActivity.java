package pl.emb.covidsupport;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

public class InformationActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_information);

        Spinner infoSpinner = findViewById(R.id.spinnerInfo);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.infoNames, R.layout.support_simple_spinner_dropdown_item);
        infoSpinner.setPrompt("Miejsce na informacje");
        infoSpinner.setAdapter(adapter);
    }
}