package tutorial.lorence.dummyjsonandroid.other;

import android.content.Context;
import android.net.ConnectivityManager;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import tutorial.lorence.dummyjsonandroid.data.storage.database.entities.Schedule;

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

    public static List<Schedule> convertStringToObject(List<String> list, List<String> paths) {
        List<Schedule> tempsSchedules = new ArrayList<>();
        for (int index = 1; index < list.size(); index++) {

            String mydata = list.get(index);
            String _player = "", _player_path = "", _enemy = "",_enemy_path = "" , _time = "", _date = "";

            Matcher player = Pattern.compile("\\d{1}\\s{1}.*\\s+\\d{1}").matcher(mydata);
            if (player.find())
            {
                _player = player.group().substring(1, player.group().length()-1);
            }

            Matcher enemy = Pattern.compile("\\d{2}\\:\\d{2}\\s{1}.*").matcher(mydata);
            if (enemy.find())
            {
                _enemy = enemy.group().substring(5, enemy.group().length());
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
            tempsSchedules.add(new Schedule(_player, _player_path, _enemy, _enemy_path, _time, _date));
            updatePathForCountry(tempsSchedules, convertStringToFlag(paths));
        }
        return tempsSchedules;
    }

    private static void updatePathForCountry(List<Schedule> schedules, List<String> paths) {
        for (int index = 0; index < schedules.size(); index++) {
            Log.i("TAG", schedules.get(index).getPlayer() + " : " + schedules.get(index).getEnemy());
            switch (schedules.get(index).getPlayer()) {
                case "Nga":
                    schedules.get(index).setPlayerPath(paths.get(0));
                    break;
                case "Ả Rập Xê-út":
                    schedules.get(index).setPlayerPath(paths.get(1));
                    break;
                case "Ai Cập":
                    schedules.get(index).setPlayerPath(paths.get(2));
                    break;
                case "Uruguay":
                    schedules.get(index).setPlayerPath(paths.get(3));
                    break;
                case "Ma rốc":
                    schedules.get(index).setPlayerPath(paths.get(4));
                    break;
                case "Iran":
                    schedules.get(index).setPlayerPath(paths.get(5));
                    break;
                case "Bồ Đào Nha":
                    schedules.get(index).setPlayerPath(paths.get(6));
                    break;
                case "Tây Ban Nha":
                    schedules.get(index).setPlayerPath(paths.get(7));
                    break;
                case "Pháp":
                    schedules.get(index).setPlayerPath(paths.get(8));
                    break;
                case "Australia":
                    schedules.get(index).setPlayerPath(paths.get(9));
                    break;
                case "Argentina":
                    schedules.get(index).setPlayerPath(paths.get(10));
                    break;
                case "Ai-xơ-len":
                    schedules.get(index).setPlayerPath(paths.get(11));
                    break;
                case "Peru":
                    schedules.get(index).setPlayerPath(paths.get(12));
                    break;
                case "Đan Mạch":
                    schedules.get(index).setPlayerPath(paths.get(13));
                    break;
                case "Croatia":
                    schedules.get(index).setPlayerPath(paths.get(14));
                    break;
                case "Costa Rica":
                    schedules.get(index).setPlayerPath(paths.get(16));
                    break;
                case "Nigeria":
                    schedules.get(index).setPlayerPath(paths.get(15));
                    break;
                case "Serbia":
                    schedules.get(index).setPlayerPath(paths.get(17));
                    break;
                case "Đức":
                    schedules.get(index).setPlayerPath(paths.get(18));
                    break;
                case "Mexico":
                    schedules.get(index).setPlayerPath(paths.get(19));
                    break;
                case "Brazil":
                    schedules.get(index).setPlayerPath(paths.get(20));
                    break;
                case "Thụy Sĩ":
                    schedules.get(index).setPlayerPath(paths.get(21));
                    break;
                case "Panama":
                    schedules.get(index).setPlayerPath(paths.get(25));
                    break;
                case "Bỉ":
                    break;
                case "Thụy Điển":
                    schedules.get(index).setPlayerPath(paths.get(22));
                    break;
                case "Hàn Quốc":
                    schedules.get(index).setPlayerPath(paths.get(23));
                    break;
                case "Colombia":
                    schedules.get(index).setPlayerPath(paths.get(24));
                    break;
                case "Nhật Bản":

                    break;
                case "Ba Lan":
                    break;
                case "Senegal":
                    break;
                case "Tunisia":
                    break;
                case "Anh":
                    break;
            }
        }
    }

    private static List<String> convertStringToFlag(List<String> list) {
        List<String> tempFlags = new ArrayList<>();
        for (int index = 1; index < list.size(); index++) {
            String mydata = list.get(index);
            Pattern pattern = Pattern.compile("https://static.bongda24h.vn/Medias/icon/\\d{4}/\\d{1}/\\d{1}/.*");
            Matcher matcher = pattern.matcher(mydata);
            if (matcher.find())
            {
                tempFlags.add(matcher.group());
            }
            if (tempFlags.size() == 32) return tempFlags;
        }
        return null;
    }
}
