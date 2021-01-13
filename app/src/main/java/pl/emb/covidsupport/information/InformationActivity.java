package pl.emb.covidsupport.information;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ImageButton;

import java.util.ArrayList;
import java.util.List;

import pl.emb.covidsupport.R;

public class InformationActivity extends Fragment {

    private EditText userInput;
    private ImageButton btn_send;
    private String item;
    RecyclerView recyclerView;              // to create dynamic list with messengers (between user and bot)
    List<ResponseMessage> responseMessageList;
    MessageAdapter messageAdapter;
    ViewGroup root;
    AnswersBase answersBase;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        this.root = (ViewGroup) inflater.inflate(R.layout.fragment_information,
                container, false);
        init();
        return root;
    }

    private void init() {
        //spinnerThings();                    // function to add all elements to the spinner with questions
        inputThings();
        recyclerView = root.findViewById(R.id.conversation);

        responseMessageList = new ArrayList<>();
        messageAdapter = new MessageAdapter(responseMessageList, root.getContext());

        answersBase = new AnswersBase();

        recyclerView.setLayoutManager(new LinearLayoutManager(root.getContext(),
                LinearLayoutManager.VERTICAL, false));
        recyclerView.setAdapter(messageAdapter);
    }

    private void inputThings() {
        userInput = root.findViewById(R.id.userInput);
        btn_send = root.findViewById(R.id.btn_send);

        btn_send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String msg = userInput.getText().toString();
                if (!msg.equals("")) {
                    ResponseMessage responseMessage = new ResponseMessage(msg, true);
                    responseMessageList.add(responseMessage);

                    Boolean pom = false;

                    for (int i = 0; i < answersBase.questions.size(); i++) {
                        if (msg.contains(answersBase.questions.get(i))) {
                            ResponseMessage responseMessage2 = new ResponseMessage(answersBase.ansewrs.get(i), false);
                            responseMessageList.add(responseMessage2);
                            pom = true;
                        }
                    }

                    if (pom == false) {
                        ResponseMessage responseMessage2 = new ResponseMessage("Przykro mi, ale nie rozumiem :c", false);
                        responseMessageList.add(responseMessage2);
                    }

                    userInput.setText("");

                    messageAdapter.notifyDataSetChanged();

                    if (!isLastVisible()) {
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
}