package com.example.a46animalswithtabs.data;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Simple class to represent single Animal
 * Implements Parcelable interface so it can be passed within Bundle
 */
public class Animal implements Parcelable {
    private String name;
    private int imageId;

    public Animal(String name, int imageId) {
        this.name = name;
        this.imageId = imageId;
    }

    protected Animal(Parcel in) {
        name = in.readString();
        imageId = in.readInt();
    }

    public static final Creator<Animal> CREATOR = new Creator<Animal>() {
        @Override
        public Animal createFromParcel(Parcel in) {
            return new Animal(in);
        }

        @Override
        public Animal[] newArray(int size) {
            return new Animal[size];
        }
    };

    public String getName() {
        return name;
    }

    public int getImageId() {
        return imageId;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(name);
        parcel.writeInt(imageId);
    }
}
