package com.example.assignment2;

import android.os.Parcel;
import android.os.Parcelable;

public class Product implements Parcelable {
    public String name;
    public double price;
    public int qty;

    public Product(String name, double price, int qty){
        this.name = name;
        this.price = price;
        this.qty = qty;
    }

    protected Product(Parcel in) {
        name = in.readString();
        price = in.readDouble();
        qty = in.readInt();
    }

    public static final Creator<Product> CREATOR = new Creator<Product>() {
        @Override
        public Product createFromParcel(Parcel in) {
            return new Product(in);
        }

        @Override
        public Product[] newArray(int size) {
            return new Product[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(name);
        parcel.writeDouble(price);
        parcel.writeInt(qty);
    }
}
