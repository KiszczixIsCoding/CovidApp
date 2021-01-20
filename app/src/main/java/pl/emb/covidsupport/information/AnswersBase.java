package pl.emb.covidsupport.information;

import java.util.ArrayList;
import java.util.Queue;

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

        // covid start
        questions.add("ROZPOCZĘŁA SIĘ");        // 29
        questions.add("ROZPOCZELA SIE");        // 30
        questions.add("POCZĄTEK");              // 31
        questions.add("POCZĄTEK");              // 32
        questions.add("ZACZĘŁA SIĘ");           // 33
        questions.add("ZACZELA SIE");           // 34
        questions.add("KIEDY");                 // 35
        questions.add("WYKRYTY");               // 36
        questions.add("SKĄD");                  // 37
        questions.add("SKAD");                  // 38
        questions.add("WZIĄŁ");                 // 39
        questions.add("WZIAL");                 // 40

        // age dependance
        questions.add("GRUP WIEKOWYCH");        // 41
        questions.add("WIEK");                  // 42
        questions.add("OD WIEKU");              // 43

        // reliable data
        questions.add("WIARYGODNE");            // 44
        questions.add("INFORMACJE");            // 45
        questions.add("ZRODLA");                // 46
        questions.add("ŹRÓDŁA");                // 47
        questions.add("DANE");                  // 48

        // where make test
        questions.add("ZROBIĆ TEST");           // 49
        questions.add("ZROBIC TEST");           // 50
        questions.add("GDZIE");                 // 51
        questions.add("DOKĄD");                 // 52
        questions.add("DOKAD");                 // 53

        // vaccination
        questions.add("ZGŁOSIĆ SIĘ");           // 54
        questions.add("ZGLOSIC SIE");           // 55
        questions.add("ZGŁOSZENIE");            // 56
        questions.add("ZGLOSZENIE");            // 57
        questions.add("SZCZEPIENIE");           // 58

        // referral
        questions.add("SKIEROWANIE");           // 59

        // complications
        questions.add("POWIKŁANIA");            // 60
        questions.add("POWIKLANIA");            // 61
        questions.add("SKUTKI");                // 62
        questions.add("WYWOŁUJE");              // 63
        questions.add("WYWOLUJE");              // 64

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

        // 5
        answers.add("Koronawirus został po raz pierwszy zdiagnozowany w grudniu 2019 roku, w chińskim " +
                "mieście Wuhan, a dokładniej prowincji Hubei. Sytuacja kryzysowa została oficjalnie " +
                "ogłoszona przez Międzynarodową Organizację Zdrowia w styczniu. To wtedy nadano wirusowi" +
                " nazwę COVID-19.");

        // 6
        answers.add("Koronawirus jest szczególnie niebezpieczny dla seniorów (osób 70+) oraz osób " +
                "chorujących przewlekle na inne choroby.");

        // 7
        answers.add("Wiarygodne dane na temat koronawirusa można znaleźć na stronie " +
                "https://www.gov.pl/web/koronawirus, a jeśli chodzi o statystyki to znajdują się one" +
                " na stronie Ministerstwa Zdrowia.");

        // 8
        answers.add("Pobranie wymazu do wykonania testu następuje w mobilnym punkcie pobrań lub w domu " +
                "pacjenta");

        // 9
        answers.add("Od 15 stycznia 2021 można zgłosić chęć zaszczepienia się przeciw COVID-19 przez " +
                "formularz online. W momencie, kiedy szczepienie będą dostępne dla Twojej grupy - dostaniesz" +
                " maila.");

        // 10
        answers.add("Skierowanie od lekarza jest konieczne, żeby wykonać test na COVID-19.");

        // 11
        answers.add("Do najczęstszych powikłań po chorowaniu na COVID-19 należą: problemy z oddechem, " +
                "objawy sercowo-naczyniowe, np. bóle w klatce piersiowej, zmęczenie, gorączka, ból " +
                "mięsni i stawów, a także objawy neurologiczne, np. zaburzenia snu.");
    }
}
