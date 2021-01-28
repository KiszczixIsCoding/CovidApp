package pl.emb.covidsupport.global;

import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

public class DateFormatter {
    public static String formatStateDate(String date) {
        DateTimeFormatter formatter = DateTimeFormat.forPattern("MM/dd/yy");
        DateTime dt = DateTime.parse(date, formatter);
        dt = dt.plusDays(1);
        DateTimeFormatter dtFormatter = DateTimeFormat.forPattern("dd.MM.yyyy");
        return dtFormatter.print(dt);
    }

    public static String formatStateDate(String date, int daysLeft) {
        DateTimeFormatter formatter = DateTimeFormat.forPattern("MM/dd/yy");
        DateTime dt = DateTime.parse(date, formatter);
        dt = dt.minusDays(daysLeft);
        DateTimeFormatter dtFormatter = DateTimeFormat.forPattern("dd.MM.yyyy");
        return dtFormatter.print(dt);
    }
}
