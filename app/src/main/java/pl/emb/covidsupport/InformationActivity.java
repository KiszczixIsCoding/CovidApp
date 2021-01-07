package pl.emb.covidsupport;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class InformationActivity extends Fragment implements AdapterView.OnItemSelectedListener {

    //private Spinner infoSpinner;
    private EditText userInput;
    private ImageButton btn_send;
    private String item;
    RecyclerView recyclerView;              // to create dynamic list with messengers (between user and bot)
    List<ResponseMessage> responseMessageList;
    MessageAdapter messageAdapter;
    ViewGroup root;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        this.root = (ViewGroup) inflater.inflate(R.layout.fragment_information,
                container, false);
        init();
        return root;
    }

    //    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_information);
//
//        init();                             // function to initialize all important things
//    }

    private void init() {
        //spinnerThings();                    // function to add all elements to the spinner with questions
        inputThings();
        recyclerView = root.findViewById(R.id.conversation);

        responseMessageList = new ArrayList<>();
        messageAdapter = new MessageAdapter(responseMessageList, root.getContext());

        recyclerView.setLayoutManager(new LinearLayoutManager(root.getContext(),
                LinearLayoutManager.VERTICAL, false));
        recyclerView.setAdapter(messageAdapter);

        // a method to take a selected question and add to a variable 'item'
        //infoSpinner.setOnItemSelectedListener(this);
    }

    /*private void spinnerThings() {
        infoSpinner = root.findViewById(R.id.spinnerInfo);

        // adapter with a list of questions
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(root.getContext(),
                R.array.infoNames, R.layout.support_simple_spinner_dropdown_item);

        infoSpinner.setAdapter(adapter);
    }*/

    private void inputThings() {
        userInput = root.findViewById(R.id.userInput);
        btn_send = root.findViewById(R.id.btn_send);

        btn_send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String msg = userInput.getText().toString();
                if (!msg.equals("")) {
                    ResponseMessage responseMessage = new ResponseMessage(userInput.getText().toString(), true);
                    responseMessageList.add(responseMessage);

                    ResponseMessage responseMessage2 = new ResponseMessage(userInput.getText().toString(), false);
                    responseMessageList.add(responseMessage2);

                    messageAdapter.notifyDataSetChanged();

                    if(!isLastVisible()) {
                        recyclerView.smoothScrollToPosition(messageAdapter.getItemCount() - 1);
                    }
                }
            }
        });

    }

    private boolean isLastVisible() {
        LinearLayoutManager layoutManager = ((LinearLayoutManager) recyclerView.getLayoutManager());
        int pos = layoutManager.findLastCompletelyVisibleItemPosition();
        int numItems = recyclerView.getAdapter().getItemCount();
        return (pos >= numItems);
    }

    // lines below are for the future, if bot don't work with writing questions

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