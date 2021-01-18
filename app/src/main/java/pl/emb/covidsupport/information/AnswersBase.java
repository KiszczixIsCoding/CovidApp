package pl.emb.covidsupport.information;

import java.util.ArrayList;

public class AnswersBase {
    ArrayList<String> questions = new ArrayList<>();
    ArrayList<String> answers = new ArrayList<>();

    // constructor
    public AnswersBase() {
        // welcome
        questions.add("HEJ");
        questions.add("Cześć");
        questions.add("CZESC");
        questions.add("cześć");
        questions.add("HEJKA");
        questions.add("WITAJ");
        questions.add("DZIEN DOBRY");
        questions.add("Dzień dobry");
        questions.add("dzień dobry");

        for (int i = 0; i < 9; i++) {
            answers.add("Witaj, cieszę się, że chcesz ze mną porozmawiać. " +
                    "Czy masz do mnie jakieś pytania?");
        }
    }
}
