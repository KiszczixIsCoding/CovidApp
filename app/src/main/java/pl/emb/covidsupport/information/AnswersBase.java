package pl.emb.covidsupport.information;

import java.util.ArrayList;

public class AnswersBase {
    ArrayList<String> questions = new ArrayList<>();
    ArrayList<String> answers = new ArrayList<>();

    // constructor
    public AnswersBase() {
        // QUESTIONS

        // welcome
        questions.add("HEJ");           // 0
        questions.add("CZEŚĆ");         // 1
        questions.add("CZESC");         // 2
        questions.add("HEJKA");         // 3
        questions.add("WITAJ");         // 4
        questions.add("DZIEN DOBRY");   // 5
        questions.add("DZIEŃ DOBRY");   // 6

        // symptoms
         questions.add("OBJAWY");        // 7
        questions.add("JAKIE");         // 8
        questions.add("COVID");        // 9

        // infection
        questions.add("ROZPRZESTRZENIANIA");    // 10
        questions.add("SPOSÓB");                // 11
        questions.add("SPOSOBY");               // 12
        questions.add("PORUSZA");               // 13
        questions.add("PRZEMIESZCZA");          // 14
        questions.add("ROZNOSI");               // 15
        questions.add("PRZENOSI");              // 16

        // prevention
        questions.add("JAK");                   // 17
        questions.add("UCHRONIĆ");              // 18
        questions.add("UCHRONIC");              // 19
        questions.add("ZAPOBIEGANIA");          // 20
        questions.add("PRZECIWDZIAŁAĆ");        // 21
        questions.add("PRZECIWDZIALAC");        // 22
        questions.add("ROZPRZESTRZENIANIU");    // 23
        questions.add("SRODKI OSTROZNOSC");     // 24
        questions.add("ŚRODKI OSTROŻNOŚCI");    // 25

        // positive test
        questions.add("TEST");                  // 26
        questions.add("POZYTYWNY");             // 27
        questions.add("WYNIK");                 // 28


        // ANSWERS
        // 0
        answers.add("Witaj, cieszę się, że chcesz ze mną porozmawiać. Czy masz do mnie jakieś " +
                "pytania?");

        // 1
        answers.add("Osoby zakażone koronawirusem zwykle mają: wysoką gorączkę, kaszel, duszności, " +
                "utratę węchu lub smaku. Rzadziej pojawiają się takie objawy, jak bóle mięśni i " +
                "głowy, biegunka lub wysypka.");

        // 2
        answers.add("Do zakażenia koronawirusem może dojść przede wszystkim drogą kropelkową. Oznacza" +
                " to, że największe ryzyko zakażenia jest wtedy, kiedy ktoś na nas nakaszle lub " +
                "kichnie.");

        // 3
        answers.add("Podstawą ochrony przed zakażeniem jest odpowiednia higiena osobita. Powinno" +
                    " się często myć ręce i unikać kontaktów społecznych oraz przedmiotów, które nie " +
                "należą do nas. W kontaktach z ludźmi poza domostwem należy zakrywać nos i usta.");

        // 4
        answers.add("Od momentu uzyskania pozytywnego wyniku testu, powinieneś poddać się izolacji w " +
                "domu, która w przypadku bez objawów trwa 10 dni. Po 7 dniach izolacji, ale nie później" +
                " niż w jej 10 dniu skontaktuje się z Tobą Twój lekarz rodzinny, aby sprawdzić Twój stan" +
                " zdrowia. Jeśli wystąpią objawy, okres twojej izolacji wydłuży się.");

    }
}
