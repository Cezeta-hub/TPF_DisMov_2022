package com.czerweny.tpfinal_dismov.backend.models;

import androidx.annotation.NonNull;

import com.czerweny.tpfinal_dismov.backend.databases.firebase.FirebaseUser;
import com.google.common.collect.Iterables;

import java.util.List;

public class User {

    private String fullName;
    private String birthDate;
    private String email;
    private String bio = "";
    private List<String> myCourses;
    private String provider;
    private String deviceId = "";

    public User(String fullName,String birthDate, String email, String bio, List<String> myCourses, String provider) {
        this.email = email;
        this.fullName = fullName;
        this.birthDate = birthDate;
        this.bio = bio == null ? "" : bio;
        this.myCourses = myCourses;
        this.provider = provider;
    }
    public User(String id, FirebaseUser firebaseUser) {
        email = id;
        fullName = firebaseUser.getName();
        birthDate = firebaseUser.getBorn();
        bio = firebaseUser.getBio() == null ? "" : firebaseUser.getBio();
        myCourses = firebaseUser.getMyCourses();
        provider = firebaseUser.getProvider();
        deviceId = firebaseUser.getNotification_token();
    }

    public String getFullName() { return fullName; }
    public void setFullName(String fullName) { this.fullName = fullName; }

    public String getBirthDate() { return birthDate; }
    public void setBirthDate(String birthDate) { this.birthDate = birthDate; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public String getBio() { return bio; }
    public void setBio(String bio) { this.bio = bio; }

    public List<String> getMyCourses() { return myCourses; }
    public void setMyCourses(List<String> myCourses) { this.myCourses = myCourses; }

    public String getProvider() { return provider; }
    public void setProvider(String provider) { this.provider = provider; }

    public String getDeviceId() { return deviceId; }
    public void setDeviceId(String deviceId) { this.deviceId = deviceId; }

    public String getMyCoursesAsString() {
        String result = "(Sin materias)";
        if (myCourses.size() != 0) {
            result = myCourses.get(0);
            for (String materia : Iterables.skip(myCourses, 1)) {
                result = result + ", " + materia;
            }
        }
        return result;
    }

    public String getBirthDateAsString() {
        String result = "(Sin definir)";
        if (!birthDate.equals("")) {
            result = birthDate;
        }
        return result;
    }

    public String getBioAsString() {
        String result = "(Sin definir)";
        if (!bio.equals("")) {
            result = bio;
        }
        return result;
    }
}
