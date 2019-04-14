package ge.edu.freeuni.assignment2.util;

import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateHelper {

    public static String getDatetime(long time, boolean week) {
        Date date = new Date(time * 1000);
        String formatString = "EEEE dd MMM yyyy hh:mm";
        if (!week) {
            formatString = "dd MM yyyy";
        }
        Format format = new SimpleDateFormat(formatString);
        return format.format(date);
    }
}
