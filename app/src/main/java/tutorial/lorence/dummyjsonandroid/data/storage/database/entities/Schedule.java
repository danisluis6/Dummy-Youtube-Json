package tutorial.lorence.dummyjsonandroid.data.storage.database.entities;

/**
 * Created by vuongluis on 4/14/2018.
 *
 * @author vuongluis
 * @version 0.0.1
 */

public class Schedule {

    private int index;
    private String player;
    private String enemy;
    private String time;
    private String date;

    public Schedule() {
    }

    public Schedule(int index, String player, String enemy, String time, String date) {
        this.index = index;
        this.player = player;
        this.enemy = enemy;
        this.time = time;
        this.date = date;
    }

    public Schedule(String player, String enemy, String time, String date) {
        this.player = player;
        this.enemy = enemy;
        this.time = time;
        this.date = date;
    }

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    public String getPlayer() {
        return player;
    }

    public void setPlayer(String player) {
        this.player = player;
    }

    public String getEnemy() {
        return enemy;
    }

    public void setEnemy(String enemy) {
        this.enemy = enemy;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
