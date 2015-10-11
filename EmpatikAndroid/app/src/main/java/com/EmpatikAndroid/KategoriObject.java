package com.EmpatikAndroid;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Casper on 13.9.2015.
 */
public class KategoriObject implements Parcelable {
    private String name;
    private int photoId;

    public KategoriObject(String name, int photoId){
        this.name = name;
        this.photoId = photoId;
    }

    protected KategoriObject(Parcel in) {
        name = in.readString();
        photoId = in.readInt();
    }

    public static final Creator<KategoriObject> CREATOR = new Creator<KategoriObject>() {
        @Override
        public KategoriObject createFromParcel(Parcel in) {
            return new KategoriObject(in);
        }

        @Override
        public KategoriObject[] newArray(int size) {
            return new KategoriObject[size];
        }
    };

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPhoto() {
        return photoId;
    }

    public void setPhoto(int photoId) {
        this.photoId = photoId;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(name);
        dest.writeInt(photoId);
    }
}
