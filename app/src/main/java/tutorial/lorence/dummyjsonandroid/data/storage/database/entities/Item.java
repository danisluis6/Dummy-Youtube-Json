package tutorial.lorence.dummyjsonandroid.data.storage.database.entities;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by vuongluis on 4/14/2018.
 *
 * @author vuongluis
 * @version 0.0.1
 */

public class Item {

    @SerializedName("userid")
    @Expose
    private int userid;

    @SerializedName("username")
    @Expose
    private String username;

    @SerializedName("password")
    @Expose
    private String password;

    @SerializedName("fullname")
    @Expose
    private String fullname;

    @SerializedName("path")
    @Expose
    private String path;

    @SerializedName("email")
    @Expose
    private String email;

    @SerializedName("address")
    @Expose
    private String address;

    public Item() {
    }

    public Item(int userid, String username, String password, String fullname, String avatar, String email, String address) {
        this.userid = userid;
        this.username = username;
        this.password = password;
        this.fullname = fullname;
        this.path = avatar;
        this.email = email;
        this.address = address;
    }

    public int getUserID() {
        return userid;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getFullname() {
        return fullname;
    }

    public String getPath() {
        return path;
    }

    public String getEmail() {
        return email;
    }

    public String getAddress() {
        return address;
    }
}
