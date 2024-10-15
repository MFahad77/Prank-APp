package com.example.prankapp.Model;

import android.os.Parcel;
import android.os.Parcelable;

public class Question implements Parcelable {
    private String questionText;
    private String userName;

    public Question(String questionText, String userName) {
        this.questionText = questionText;
        this.userName = userName;
    }

    protected Question(Parcel in) {
        questionText = in.readString();
        userName = in.readString();
    }

    public static final Creator<Question> CREATOR = new Creator<Question>() {
        @Override
        public Question createFromParcel(Parcel in) {
            return new Question(in);
        }

        @Override
        public Question[] newArray(int size) {
            return new Question[size];
        }
    };

    public String getQuestionText() {
        return questionText;
    }

    public void setQuestionText(String questionText) {
        this.questionText = questionText;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int flags) {
        parcel.writeString(questionText);
        parcel.writeString(userName);
    }
}
