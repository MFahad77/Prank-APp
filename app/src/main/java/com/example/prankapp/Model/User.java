package com.example.prankapp.Model;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.List;

public class User implements Parcelable {
    private String name;
    private List<Question> questions;
    private int imageResourceId;

    public User(String name, List<Question> questions, int imageResourceId) {
        this.name = name;
        this.questions = questions;
        this.imageResourceId = imageResourceId;
    }

    protected User(Parcel in) {
        name = in.readString();
        questions = in.createTypedArrayList(Question.CREATOR);
        imageResourceId = in.readInt();
    }

    public static final Creator<User> CREATOR = new Creator<User>() {
        @Override
        public User createFromParcel(Parcel in) {
            return new User(in);
        }

        @Override
        public User[] newArray(int size) {
            return new User[size];
        }
    };

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getImageResourceId() {
        return imageResourceId;
    }

    public void setImageResourceId(int imageResourceId) {
        this.imageResourceId = imageResourceId;
    }

    public List<Question> getQuestions() {
        return questions;
    }



    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int flags) {
        parcel.writeString(name);
        parcel.writeTypedList(questions);
        parcel.writeInt(imageResourceId);
    }
}
