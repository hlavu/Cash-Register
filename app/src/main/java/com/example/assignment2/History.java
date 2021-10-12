package com.example.assignment2;

import android.os.Parcel;
import android.os.Parcelable;

public class History implements Parcelable {
    public String name;
    public String qty;
    public String total;
    public String date;

    public History(String name, String qty, String total, String date)
    {
        this.name = name;
        this.qty = qty;
        this.total = total;
        this.date = date;
    }

    protected History(Parcel in) {
        name = in.readString();
        qty = in.readString();
        total = in.readString();
        date = in.readString();
    }

    public static final Creator<History> CREATOR = new Creator<History>() {
        @Override
        public History createFromParcel(Parcel in) {
            return new History(in);
        }

        @Override
        public History[] newArray(int size) {
            return new History[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(name);
        parcel.writeString(qty);
        parcel.writeString(total);
        parcel.writeString(date);
    }
}
