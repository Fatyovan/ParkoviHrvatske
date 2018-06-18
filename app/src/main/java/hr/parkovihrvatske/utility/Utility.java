package hr.parkovihrvatske.utility;

import android.content.Context;
import android.view.View;
import android.view.inputmethod.InputMethodManager;

import com.google.gson.Gson;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;

/**
 * Created by Damjan Miloševski on 05.6.2018.
 * Project: ParkoviHrvatske
 * Copyright: Corvus Info d.o.o.
 * Buzinski prilaz 10, 10010 Zagreb, HR
 */
public class Utility {

    public static boolean isValidEmail(String email) {
        return email.matches("[a-zA-Z0-9\\.]+@[a-zA-Z0-9\\-\\_\\.]+\\.[a-zA-Z0-9]{3}");
    }
    /**
     * Hides keyboard
     *
     * @param context
     * @param view
     */
    public static void hideSoftKeyboard(Context context, View view) {
        InputMethodManager imm = (InputMethodManager) context.getSystemService(Context.INPUT_METHOD_SERVICE);
        if (imm != null) {
            imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
        }
    }
    /**
     * Converts  dateString into Date object
     *
     * @param dateString date in string
     * @return Date
     */
    public static Date getDateFromString(String dateString) {
        //2018-03-07T11:28:15.81
        String[] parts1 = dateString.split("T");
        String[] date1 = parts1[0].split("-");
        Calendar date = Calendar.getInstance(TimeZone.getDefault());
        date.set(Calendar.YEAR, Integer.parseInt(date1[0]));
        int month = Integer.parseInt(date1[1]) - 1;
        date.set(Calendar.MONTH, month);
        date.set(Calendar.DAY_OF_MONTH, Integer.parseInt(date1[2]));
        String[] time = parts1[1].split(":");
        date.set(Calendar.HOUR, Integer.parseInt(time[0]));
        date.set(Calendar.MINUTE, Integer.parseInt(time[1]));

        return date.getTime();
    }
    public static String dateToServerFormat(Date date) {
        //2018-05-02T09:45:49.413
        //Wed May 02 11:04:20 GMT+02:00 2018
        SimpleDateFormat outFormatter = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS");
        return outFormatter.format(date);
    }
    public static String displayDate(Date date, String locale) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy HH:mm",
                new Locale(locale));
        return dateFormat.format(date);
    }

}
