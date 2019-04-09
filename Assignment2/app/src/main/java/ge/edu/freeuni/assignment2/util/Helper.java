package ge.edu.freeuni.assignment2.util;

import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Helper {

    public static String convertTime(long time, boolean week) {
        Date date = new Date(time * 1000);
        String formatString = "EEEE dd MMM yyyy";
        if (!week) {
            formatString = "dd MM yyyy";
        }
        Format format = new SimpleDateFormat(formatString);
        return format.format(date);
    }
}
