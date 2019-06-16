package com.example.projectppb.objects;

import android.os.Parcel;
import android.os.Parcelable;

public class Kategori implements Parcelable {
    int id;
    String nama;
    int prioritas;

    public Kategori(int id, String nama, int prioritas) {
        this.id = id;
        this.nama = nama;
        this.prioritas = prioritas;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public int getPrioritas() {
        return prioritas;
    }

    public void setPrioritas(int prioritas) {
        this.prioritas = prioritas;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.id);
        dest.writeString(this.nama);
        dest.writeInt(this.prioritas);
    }

    protected Kategori(Parcel in) {
        this.id = in.readInt();
        this.nama = in.readString();
        this.prioritas = in.readInt();
    }

    public static final Parcelable.Creator<Kategori> CREATOR = new Parcelable.Creator<Kategori>() {
        @Override
        public Kategori createFromParcel(Parcel source) {
            return new Kategori(source);
        }

        @Override
        public Kategori[] newArray(int size) {
            return new Kategori[size];
        }
    };
}
