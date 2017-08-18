package com.myprog.zoop.viewmodel;

import android.os.Parcel;
import android.os.Parcelable;

public class ProductModel implements Parcelable {

    public String id;
    public String name;
    public String price;
    public String currency;

    public static final Creator<ProductModel> CREATOR = new Creator<ProductModel>() {
        @Override
        public ProductModel createFromParcel(Parcel in) {
            return new ProductModel(in);
        }

        @Override
        public ProductModel[] newArray(int size) {
            return new ProductModel[size];
        }
    };

    public ProductModel() {}

    public ProductModel(Parcel in) {
        id = in.readString();
        name = in.readString();
        price = in.readString();
        currency = in.readString();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(id);
        dest.writeString(name);
        dest.writeString(price);
        dest.writeString(currency);
    }
}
