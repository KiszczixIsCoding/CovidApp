package pl.emb.covidsupport.information;

import java.util.ArrayList;

public class AnswersBase {
    ArrayList<String> questions = new ArrayList<>();
    ArrayList<String> ansewrs = new ArrayList<>();

    // constructor
    public AnswersBase() {
        // welcome
        questions.add("HEJ");
        questions.add("Hej");
        questions.add("hej");
        questions.add("Czesc");
        questions.add("Cześć");
        questions.add("CZESC");
        questions.add("cześć");
        questions.add("czesc");
        questions.add("HEJKA");
        questions.add("hejka");
        questions.add("Hejka");
        questions.add("WITAJ");
        questions.add("Witaj");
        questions.add("witaj");
        questions.add("DZIEN DOBRY");
        questions.add("Dzien dobry");
        questions.add("Dzień dobry");
        questions.add("dzien dobry");
        questions.add("dzień dobry");

        for (int i = 0; i < 19; i++) {
            ansewrs.add("Witaj, cieszę się, że chcesz ze mną porozmawiać. " +
                    "Czy masz do mnie jakieś pytania?");
        }
    }
}
