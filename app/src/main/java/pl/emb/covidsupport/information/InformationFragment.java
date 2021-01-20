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
import android.widget.EditText;
import android.widget.ImageButton;

import java.util.ArrayList;
import java.util.List;

import pl.emb.covidsupport.R;

public class InformationFragment extends Fragment {

    private EditText userInput;
    private ImageButton btn_send;
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

                    boolean pom = false;

                    // welcome
                    for (int i = 0; i < 7; i++) {
                        if (msg.toUpperCase().contains(answersBase.questions.get(i))) {
                            ResponseMessage responseMessage2 = new ResponseMessage(answersBase.answers.get(0), false);
                            responseMessageList.add(responseMessage2);
                            pom = true;
                        }
                    }

                    // symptoms
                    if(msg.toUpperCase().contains(answersBase.questions.get(7)) ||
                            ((msg.toUpperCase().contains(answersBase.questions.get(7)) &&
                                    msg.toUpperCase().contains(answersBase.questions.get(8)))) ||
                            (msg.toUpperCase().contains(answersBase.questions.get(7)) &&
                                    msg.toUpperCase().contains(answersBase.questions.get(9)))) {

                        ResponseMessage responseMessage3 = new ResponseMessage(answersBase.answers.get(1), false);
                        responseMessageList.add(responseMessage3);
                        pom = true;
                    }

                    // infection
                   if(msg.toUpperCase().contains(answersBase.questions.get(10)) ||
                            ((msg.toUpperCase().contains(answersBase.questions.get(10)) &&
                                    msg.toUpperCase().contains(answersBase.questions.get(11)))) ||
                            (msg.toUpperCase().contains(answersBase.questions.get(10)) &&
                                    msg.toUpperCase().contains(answersBase.questions.get(12))) ||
                            msg.toUpperCase().contains(answersBase.questions.get(13)) ||
                            msg.toUpperCase().contains(answersBase.questions.get(14)) ||
                            msg.toUpperCase().contains(answersBase.questions.get(15)) ||
                           msg.toUpperCase().contains(answersBase.questions.get(16))) {

                        ResponseMessage responseMessage4 = new ResponseMessage(answersBase.answers.get(2), false);
                        responseMessageList.add(responseMessage4);
                        pom = true;
                    }

                    // prevention
                    if(((msg.toUpperCase().contains(answersBase.questions.get(17))) &&
                                    msg.toUpperCase().contains(answersBase.questions.get(18))) ||
                            ((msg.toUpperCase().contains(answersBase.questions.get(17))) &&
                                    msg.toUpperCase().contains(answersBase.questions.get(19))) ||
                            (msg.toUpperCase().contains(answersBase.questions.get(13)) &&
                                    msg.toUpperCase().contains(answersBase.questions.get(20))) ||
                            (msg.toUpperCase().contains(answersBase.questions.get(17)) &&
                                    msg.toUpperCase().contains(answersBase.questions.get(21))) ||
                            (msg.toUpperCase().contains(answersBase.questions.get(17)) &&
                                    msg.toUpperCase().contains(answersBase.questions.get(22))) ||
                            msg.toUpperCase().contains(answersBase.questions.get(23)) ||
                            ((msg.toUpperCase().contains(answersBase.questions.get(8))) &&
                                    msg.toUpperCase().contains(answersBase.questions.get(24))) ||
                            ((msg.toUpperCase().contains(answersBase.questions.get(8))) &&
                                    msg.toUpperCase().contains(answersBase.questions.get(25)))) {

                        ResponseMessage responseMessage5 = new ResponseMessage(answersBase.answers.get(3), false);
                        responseMessageList.add(responseMessage5);
                        pom = true;
                    }

                    // positive test
                    if(((msg.toUpperCase().contains(answersBase.questions.get(26)) &&
                                    msg.toUpperCase().contains(answersBase.questions.get(27)))) ||
                            (msg.toUpperCase().contains(answersBase.questions.get(27)) &&
                                    msg.toUpperCase().contains(answersBase.questions.get(28)))) {

                        ResponseMessage responseMessage6 = new ResponseMessage(answersBase.answers.get(4), false);
                        responseMessageList.add(responseMessage6);
                        pom = true;
                    }

                    if (!pom) {
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