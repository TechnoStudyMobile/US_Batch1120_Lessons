package com.example.threadsimulation;

import android.content.Context;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.ArrayList;
import java.util.List;

public class MyViewModel extends ViewModel {

    private MutableLiveData<List<Object>> usersLiveData = new MutableLiveData<>();
    private List<Object> listOfObjects = new ArrayList<>();

    public void loadUsers() {
        // Do an asynchronous operation to fetch users.
        // Thread John
        new Thread(new Runnable() {
            @Override
            public void run() {
                // we are in new thread
                // Does a lot of stuff and get the info from db
                calculatePart();
                // Task 4 - which calls another class method

                // Task 5

                // Maybe show something on the UI

                // I need to access to my UI

                // set: Should be used on another thread. (not Main)
                usersLiveData.setValue(new ArrayList<>());

                // post: Should be used on Main thread
                //usersLiveData.postValue(new ArrayList<>());
            }
        }).start();

        //Still Main thread
       listOfObjects.add(new Object());

       calculatePart();

    }

    public MutableLiveData<List<Object>> getUsersLiveData() {
        return usersLiveData;
    }

    public int calculatePart() {
        return 100;
    }
}
