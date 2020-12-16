package pl.emb.covidsupport;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import java.util.ArrayList;
import java.util.List;

public class InformationActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    private Spinner infoSpinner;
    private String item;
    RecyclerView recyclerView;              // to create dynamic list with messengers (between user and bot)
    List<ResponseMessage> responseMessageList;
    MessageAdapter messageAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_information);

        init();                             // function to initialize all important things
    }

    private void init() {
        spinnerThings();                    // function to add all elements to the spinner with questions
        recyclerView = findViewById(R.id.conversation);

        responseMessageList = new ArrayList<>();
        messageAdapter = new MessageAdapter(responseMessageList, this);

        recyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        recyclerView.setAdapter(messageAdapter);

        infoSpinner.setOnItemSelectedListener(this);
    }

    private void spinnerThings() {
        infoSpinner = (Spinner) findViewById(R.id.spinnerInfo);
        // adapter with a list of questions
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.infoNames, R.layout.support_simple_spinner_dropdown_item);
        infoSpinner.setAdapter(adapter);

        // a method to take a selected question and add to a variable 'item'
        //infoSpinner.setOnItemSelectedListener(this);
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        // on selecting a spinner item
        this.item = parent.getItemAtPosition(position).toString();

       ResponseMessage message = new ResponseMessage(this.item, true);
        responseMessageList.add(message);

        ResponseMessage messageBot = new ResponseMessage(this.item, false);
        responseMessageList.add(messageBot);

        messageAdapter.notifyDataSetChanged();
    }

    public void onNothingSelected(AdapterView<?> arg0) {
        // TODO Auto-generated method stub
    }
}