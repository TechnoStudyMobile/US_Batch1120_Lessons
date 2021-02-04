package com.example.myrecycleritems;

import android.util.Log;

public class Person {
    public String fullName = "";
    public int age = 0;

    public Person(String fullName) {
        this.fullName = fullName;
    }

    public Person(String fullName, int age) {
        this.fullName = fullName;
        this.age = age;
    }

    void speak(){
        // Speak
    }
}
