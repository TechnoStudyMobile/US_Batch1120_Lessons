package com.example.threadsimulation;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.ArrayList;
import java.util.List;

public class MyViewModel extends ViewModel {

    private MutableLiveData<List<Object>> usersLiveData = new MutableLiveData<>();

    public void loadUsers() {
        // Do an asynchronous operation to fetch users.
        new Thread(new Runnable() {
            @Override
            public void run() {
                // Does a lot of stuff and get the info from db
                // I need to access to my UI
                usersLiveData.postValue(new ArrayList<>());
            }
        }).start();

    }

    public MutableLiveData<List<Object>> getUsersLiveData() {
        return usersLiveData;
    }
}
