package com.czerweny.tpfinal_dismov.backend.repositories;

import android.os.Build;

import androidx.annotation.RequiresApi;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MediatorLiveData;

import com.czerweny.tpfinal_dismov.backend.databases.RemoteDB;
import com.czerweny.tpfinal_dismov.backend.models.Comment;
import com.czerweny.tpfinal_dismov.backend.models.Course;
import com.czerweny.tpfinal_dismov.backend.models.File;

import java.util.Comparator;
import java.util.List;

public class CourseRepository {

    @RequiresApi(api = Build.VERSION_CODES.N)
    public static LiveData<List<Course>> getCourses() {
        MediatorLiveData<List<Course>> data = new MediatorLiveData<>();

        RemoteDB.getCourses().addOnSuccessListener(courses -> {
            courses.sort(Comparator.comparing(Course::getName));
            data.setValue(courses);
        });

        return data;
    }

    public static LiveData<Course> getCourse(String courseId) {
        MediatorLiveData<Course> data = new MediatorLiveData<>();
        RemoteDB.getCourse(courseId).addOnSuccessListener(data::setValue);
        return data;
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    public static LiveData<List<Course>> getCoursesForProfile(List<String> courseIds) {
        MediatorLiveData<List<Course>> data = new MediatorLiveData<>();

        RemoteDB.getCoursesForProfile(courseIds).addOnSuccessListener(courses -> {
            courses.sort(Comparator.comparing(Course::getName));
            data.setValue(courses);
        });
        return data;
    }

    public static void updateCourseScore(Course course) {
        RemoteDB.updateCourseScore(course)
                .addOnSuccessListener(aVoid -> {});
    }

    public static void updateCourseStudents(Course course) {
        RemoteDB.updateCourseStudents(course)
                .addOnSuccessListener(aVoid -> {});
    }

    public static LiveData<List<File>> getCourseFiles(String courseId) {
        MediatorLiveData<List<File>> data = new MediatorLiveData<>();
        RemoteDB.getCourseFiles(courseId).addOnSuccessListener(data::setValue);
        return data;
    }

    public static LiveData<List<Comment>> getCourseComments(String courseId) {
        MediatorLiveData<List<Comment>> data = new MediatorLiveData<>();
        RemoteDB.getCourseComments(courseId).addOnSuccessListener(data::setValue);
        return data;
    }

    public static void postCourseComment(Comment comment) {
        RemoteDB.postCourseComment(comment)
                .addOnSuccessListener(aVoid -> {});
    }

    public static void postCourseFile(File file) {
        RemoteDB.postCourseFile(file)
                .addOnSuccessListener(aVoid -> {});
    }
}
