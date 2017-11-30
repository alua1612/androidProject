package com.example.aluakosamanova.newsapp;


import android.arch.persistence.room.Entity;
import android.arch.persistence.room.Ignore;
import android.arch.persistence.room.PrimaryKey;
import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;



/**
 * Created by aluakosamanova on 03.10.17.
 */

@Entity(tableName = "contacts")
public class Contact implements Parcelable{

    @Expose
    @PrimaryKey
    private Integer id;
    @SerializedName("title")
    @Expose
    private String title;

    @SerializedName("body")
    @Expose
    private String body;

    @SerializedName("imagePath")
    @Expose
    private String imagePath;

    @SerializedName("date")
    @Expose
    private String date;

    public Contact() { };

    @Ignore
    public Contact(Integer _id, String _title, String _body, String _imagePath, String _date) {
        this.id=_id;
        this.title = _title;
        this.body = _body;
        this.imagePath = _imagePath;
        this.date = _date;
    }

    protected Contact(Parcel in) {
        title = in.readString();
        body = in.readString();
        imagePath = in.readString();
        date = in.readString();
    }

    public static final Creator<Contact> CREATOR = new Creator<Contact>() {
        @Override
        public Contact createFromParcel(Parcel in) {
            return new Contact(in);
        }

        @Override
        public Contact[] newArray(int size) {
            return new Contact[size];
        }
    };

    public Integer getId(){return id;}
    public void setId(Integer id){this.id=id;}

    public String getTitle() {
        return title;
    }
    public void setTitle(String title) {
        this.title = title;
    }

    public String getBody() {
        return body;
    }
    public void setBody(String body) {
        this.body = body;
    }

    public String getImagePath() { return  imagePath; }
    public  void setImagePath(String imagePath) { this.body = body; }

    public String getDate() {
        return date;
    }
    public void setDate(String date) {
        this.date = date;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(title);
        parcel.writeString(body);
        parcel.writeString(imagePath);
        parcel.writeString(date);
    }
}
