package com.czerweny.tpfinal_dismov.backend.repositories;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MediatorLiveData;

import com.czerweny.tpfinal_dismov.backend.models.User;
import com.czerweny.tpfinal_dismov.backend.databases.RemoteDB;
import com.google.android.gms.tasks.Task;
import com.google.android.gms.tasks.TaskCompletionSource;

public class UserRepository {

    public static void postUser(User user) {
        RemoteDB.postUser(user).addOnSuccessListener(aVoid -> {});
    }

    public static void updateUser(User user) {
        RemoteDB.updateUser(user).addOnSuccessListener(aVoid -> {});
    }

    public static void updateUserCourses(User user) {
        RemoteDB.updateUserCourses(user).addOnSuccessListener(aVoid -> {});
    }

    public static void updateUserData(User user) {
        RemoteDB.updateUserData(user)
                .addOnSuccessListener(aVoid -> {});
    }

    public static LiveData<User> getUser(String userId) {
        MediatorLiveData<User> data = new MediatorLiveData<>();
        RemoteDB.getUser(userId).addOnSuccessListener(data::setValue);
        return data;
    }

    public static Task<Void> deleteUser(String userId){
        TaskCompletionSource<Void> taskCompletionSource = new TaskCompletionSource<>();
        Task<Void> deleteUserTask = RemoteDB.deleteUser(userId).addOnSuccessListener(aVoid -> {});
        deleteUserTask.addOnSuccessListener(aVoid -> taskCompletionSource.setResult(null))
                      .addOnFailureListener(taskCompletionSource::setException);
        return taskCompletionSource.getTask();
    }

    public static MediatorLiveData<Boolean> userExists(String userId){
        MediatorLiveData<Boolean> exists = new MediatorLiveData<>();
        RemoteDB.userExists(userId).addOnSuccessListener(exists::setValue);
        return exists;
    }

}
