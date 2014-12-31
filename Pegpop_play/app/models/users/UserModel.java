package models.users;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by joanna on 30/12/14.
 */
public class UserModel {

    String uid;
    String firstname;
    String lastname;
    String username;
    String profilePicture;
    int privacy;
    int toc = 0;
    Map<String, Object> userMap = new HashMap<String, Object>();

    public UserModel() {

    }

    public UserModel(String uid, String firstname, String lastname, String username, String profilePicture, int privacy, int toc) {
        this.uid = uid;
        this.firstname = firstname;
        this.lastname = lastname;
        this.username = username;
        this.profilePicture = profilePicture;
        this.privacy = privacy;
        this.toc = toc;
        //Set mapping
//        Map<String, Object> userMap = new HashMap<String, Object>();
        userMap.put("1", uid);
        userMap.put("2", firstname);
        userMap.put("3", lastname);
        userMap.put("4", username);
        userMap.put("5", profilePicture);
        userMap.put("6", privacy);
        userMap.put("7", toc);
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getProfilePicture() {
        return profilePicture;
    }

    public void setProfilePicture(String profilePicture) {
        this.profilePicture = profilePicture;
    }

    public int getPrivacy() {
        return privacy;
    }

    public void setPrivacy(int privacy) {
        this.privacy = privacy;
    }


    public int getToc() {
        return toc;
    }

    public void setToc(int toc) {
        this.toc = toc;
    }


    public Map<String, Object> getUserMap() {
        return userMap;
    }

    public void setUserMap(Map<String, Object> userMap) {
        this.userMap = userMap;
    }

}
