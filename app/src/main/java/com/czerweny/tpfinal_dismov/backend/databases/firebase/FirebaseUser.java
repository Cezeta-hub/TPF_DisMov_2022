package com.czerweny.tpfinal_dismov.backend.databases.firebase;

import com.czerweny.tpfinal_dismov.backend.models.User;

import java.util.HashMap;
import java.util.List;

public class FirebaseUser {

    private String name;
    private String bio;
    private String born;
    private List<String> myCourses;
    private String notification_token = "";
    private String provider;

    public FirebaseUser(String name, String born, String bio, List<String> myCourses, String provider, String notification_token) {
        this.name = name;
        this.born = born;
        this.bio = bio;
        this.myCourses = myCourses;
        this.notification_token = notification_token;
        this.provider = provider;
    }

    public FirebaseUser(User user) {
        name = user.getFullName();
        born = user.getBirthDate();
        bio = user.getBio();
        myCourses = user.getMyCourses();
        provider = user.getProvider();
        notification_token = user.getDeviceId();
    }

    public FirebaseUser() { }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getBio() { return bio; }
    public void setBio(String bio) { this.bio = bio; }

    public String getBorn() { return born; }
    public void setBorn(String born) { this.born = born; }

    public List<String> getMyCourses() { return myCourses; }
    public void setMyCourses(List<String> myCourses) { this.myCourses = myCourses; }

    public String getNotification_token() { return notification_token; }
    public void setNotification_token(String notification_token) { this.notification_token = notification_token; }

    public String getProvider() { return provider; }
    public void setProvider(String provider) { this.provider = provider; }

    public HashMap<String, Object> toMap() {
        HashMap<String, Object> result = new HashMap<>();

        result.put("name", name);
        result.put("bio", bio);
        result.put("born", born);
        result.put("myCourses", myCourses);
        result.put("notification_token", notification_token);
        result.put("provider", provider);

        return result;
    }
    public HashMap<String, Object> toMapUpdateLogin() {
        HashMap<String, Object> result = new HashMap<>();

        result.put("name", name);
        result.put("provider", provider);
        result.put("notification_token", notification_token);

        return result;
    }

    public HashMap<String, Object> toMapUpdateData() {
        HashMap<String, Object> result = new HashMap<>();

        result.put("born", born);
        result.put("bio", bio);

        return result;
    }

    public HashMap<String, Object> toMapUpdateCourses() {
        HashMap<String, Object> result = new HashMap<>();
        result.put("myCourses", myCourses);
        return result;
    }

}
