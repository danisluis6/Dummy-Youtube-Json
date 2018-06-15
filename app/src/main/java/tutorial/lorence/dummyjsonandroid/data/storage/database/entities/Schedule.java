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
    private String player_path;
    private String enemy;
    private String enemy_path;
    private String time;
    private String date;

    public Schedule() {
    }

    public Schedule(int index, String player, String player_path, String enemy, String enemy_path, String time, String date) {
        this.index = index;
        this.player = player;
        this.player_path = player_path;
        this.enemy = enemy;
        this.enemy_path = enemy_path;
        this.time = time;
        this.date = date;
    }

    public Schedule(String player, String player_path, String enemy, String enemy_path, String time, String date) {
        this.player = player;
        this.player_path = player_path;
        this.enemy = enemy;
        this.enemy_path = enemy_path;
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

    public String getPlayerPath() {
        return player_path;
    }

    public void setPlayerPath(String player_path) {
        this.player_path = player_path;
    }

    public String getEnemy() {
        return enemy;
    }

    public void setEnemy(String enemy) {
        this.enemy = enemy;
    }

    public String getEnemyPath() {
        return enemy_path;
    }

    public void setEnemyPath(String enemy_path) {
        this.enemy_path = enemy_path;
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
