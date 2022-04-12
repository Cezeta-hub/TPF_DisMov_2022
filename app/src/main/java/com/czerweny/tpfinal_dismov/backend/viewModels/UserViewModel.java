package com.czerweny.tpfinal_dismov.backend.viewModels;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.czerweny.tpfinal_dismov.backend.models.User;
import com.czerweny.tpfinal_dismov.backend.repositories.UserRepository;

public class UserViewModel extends ViewModel {

    public String userId;
    private LiveData<User> userLiveData;

    public void setUser(String userId) {
        this.userId = userId;
        userLiveData = UserRepository.getUser(userId);
    }

    public LiveData<User> getUser() {
        return userLiveData;
    }

    public void postUser(User user) {
        UserRepository.postUser(user);
    }


}
