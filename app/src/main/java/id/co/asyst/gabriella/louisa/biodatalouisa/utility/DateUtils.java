package id.co.asyst.gabriella.louisa.biodatalouisa.utility;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class DateUtils {
    public static String formatDate(String currentFormat, String resultFormat, String dateString) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(currentFormat, Locale.getDefault());
        Date newDate = null;
        try {
            newDate = simpleDateFormat.parse(dateString);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        SimpleDateFormat simpleDateFormat1 = new SimpleDateFormat(resultFormat, Locale.getDefault());
        return simpleDateFormat1.format(newDate);
    }
}
