# Dummy-Youtube-Json
## STEP 1: Launch project with Android Studio
1. Prepare json for testing
```json
[
  {
    "apiVersion": "2.1",
    "data": {
      "id": "VA770wpLX-Q",
      "uploaded": "2011-02-24T22:31:02.000Z",
      "updated": "2012-04-08T21:37:06.000Z",
      "uploader": "drdrevevo",
      "category": "Music",
      "title": "Dr. Dre - I Need A Doctor (Explicit) ft. Eminem, Skylar Grey",
      "description": "Music video by Dr. Dre performing I Need A Doctor featuring Eminem and Skylar Grey (Explicit). © 2011 Aftermath Records",
      "tags": ["Dr", "Dre", "Eminem", "New", "Song", "Skylar", "Grey", "GRAMMYs", "Dr.", "Need", "Doctor", "video", "Eazy", "N.W.A.", "NWA", "easy", "drdre", "and", "em"],
      "thumbnail": {
        "sqDefault": "http://i.ytimg.com/vi/VA770wpLX-Q/default.jpg",
        "hqDefault": "http://i.ytimg.com/vi/VA770wpLX-Q/hqdefault.jpg"
      },
      "player": {
        "default": "http://www.youtube.com/watch?v=VA770wpLX-Q&feature=youtube_gdata_player"
      },
      "content": {
        "5": "http://www.youtube.com/v/VA770wpLX-Q?version=3&f=videos&app=youtube_gdata"
      },
      "duration": 457,
      "aspectRatio": "widescreen",
      "rating": 4.902695,
      "likeCount": "430519",
      "ratingCount": 441253,
      "viewCount": 88270796,
      "favoriteCount": 306556,
      "commentCount": 270597,
      "status": {
        "value": "restricted",
        "reason": "requesterRegion"
      },
      "restrictions": [{
        "type": "country",
        "relationship": "deny",
        "countries": "DE"
      }],
      "accessControl": {
        "comment": "allowed",
        "commentVote": "allowed",
        "videoRespond": "allowed",
        "rate": "allowed",
        "embed": "allowed",
        "list": "allowed",
        "autoPlay": "denied",
        "syndicate": "allowed"
      }
    }
  }
]
```
2. Change Object to parse with Gson libraries
```java
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
```
3. Done
