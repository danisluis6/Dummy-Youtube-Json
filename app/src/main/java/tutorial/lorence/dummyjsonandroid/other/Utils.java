package tutorial.lorence.dummyjsonandroid.other;

import android.content.Context;
import android.net.ConnectivityManager;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import tutorial.lorence.dummyjsonandroid.data.storage.DatabaseInfo;
import tutorial.lorence.dummyjsonandroid.data.storage.database.entities.Schedule;
import tutorial.lorence.dummyjsonandroid.di.module.AppModule_ProvideApplicationFactory;

/**
 * Created by vuongluis on 4/14/2018.
 *
 * @author vuongluis
 * @version 0.0.1
 */

public class Utils {

    private static long sLastClickTime = 0;

    private Utils() {
    }

    public static boolean equalsIgnoreCase(String a, String b) {
        return a != null && b != null && a.length() == b.length() && a.equalsIgnoreCase(b);
    }

    public static boolean isDoubleClick() {
        long clickTime = System.currentTimeMillis();
        if (clickTime - sLastClickTime < Constants.DOUBLE_CLICK_TIME_DELTA) {
            sLastClickTime = clickTime;
            return true;
        }
        sLastClickTime = clickTime;
        return false;
    }

    public static boolean isInternetOn(Context context) {
        final ConnectivityManager connectivityManager = ((ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE));
        return connectivityManager.getActiveNetworkInfo() != null && connectivityManager.getActiveNetworkInfo().isConnected();
    }

    public static List<Schedule> convertStringToObject(List<String> list) {
        List<Schedule> tempsSchedules = new ArrayList<>();
        for (int index = 1; index < list.size(); index++) {
            String mydata = list.get(index);
            String _player = "", _enemy = "", _time = "", _date = "";

            Matcher player = Pattern.compile("\\d{1}\\s{1}.*\\s+\\d{1}").matcher(mydata);
            if (player.find())
            {
                _player = player.group().substring(1, player.group().length()-1);
            }

            Matcher enemy = Pattern.compile("\\d{2}\\:\\d{2}\\s{1}.*").matcher(mydata);
            if (enemy.find())
            {
                _enemy = enemy.group().substring(5, enemy.group().length()-1);
            }

            Matcher date = Pattern.compile("\\d{2}\\/\\d{2}\\s+").matcher(mydata);
            if (date.find())
            {
                _date = date.group();
            }

            Matcher time = Pattern.compile("\\s+\\d{2}\\:\\d{2}\\s+").matcher(mydata);
            if (time.find())
            {
                _time = time.group();
            }
            tempsSchedules.add(new Schedule(_player, _enemy, _time, _date));
        }
        return tempsSchedules;
    }
}
