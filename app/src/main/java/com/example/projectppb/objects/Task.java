package com.example.projectppb.objects;

import android.os.Parcel;
import android.os.Parcelable;

public class Task implements Parcelable {
    int id;
    String nama;
    String tgl;
    int kategori;

    public Task(int id, String nama, String tgl, int kategori) {
        this.id = id;
        this.nama = nama;
        this.tgl = tgl;
        this.kategori = kategori;
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

    public String getTgl() {
        return tgl;
    }

    public void setTgl(String tgl) {
        this.tgl = tgl;
    }

    public int getKategori() {
        return kategori;
    }

    public void setKategori(int kategori) {
        this.kategori = kategori;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.id);
        dest.writeString(this.nama);
        dest.writeString(this.tgl);
        dest.writeInt(this.kategori);
    }

    protected Task(Parcel in) {
        this.id = in.readInt();
        this.nama = in.readString();
        this.tgl = in.readString();
        this.kategori = in.readInt();
    }

    public static final Parcelable.Creator<Task> CREATOR = new Parcelable.Creator<Task>() {
        @Override
        public Task createFromParcel(Parcel source) {
            return new Task(source);
        }

        @Override
        public Task[] newArray(int size) {
            return new Task[size];
        }
    };
}
