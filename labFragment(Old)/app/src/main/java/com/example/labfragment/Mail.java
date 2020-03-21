package com.example.labfragment;

import android.os.Parcel;
import android.os.Parcelable;

public class Mail implements Parcelable {

    private int id;
    private String title;
    private String description;
    private String detailedInfo;
    static Mail selected;

    public Mail(int id , String title , String description , String detailedInfo){
        this.id = id;
        this.title = title;
        this.description = description;
        this.detailedInfo = detailedInfo;
    }

    protected Mail(Parcel source){
        id = source.readInt();
        title = source.readString();
        description = source.readString();
        detailedInfo = source.readString();
    }

    public static final Creator<Mail> CREATOR = new Creator<Mail>() {
        @Override
        public Mail createFromParcel(Parcel source) {
            return new Mail(source);
        }

        @Override
        public Mail[] newArray(int size) {
            return new Mail[size];
        }
    };



//    getters and setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDetailedInfo() {
        return detailedInfo;
    }

    public void setDetailedInfo(String detailedInfo) {
        this.detailedInfo = detailedInfo;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(id);
        dest.writeString(title);
        dest.writeString(description);
        dest.writeString(detailedInfo);
    }
}
