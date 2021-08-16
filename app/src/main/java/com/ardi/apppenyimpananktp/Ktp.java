package com.ardi.apppenyimpananktp;

import com.google.firebase.database.Exclude;

import java.io.Serializable;

public class Ktp implements Serializable {
    private String nama;
    private String imageURL;
    private String key;
    private String nik;
    private int position;

    public Ktp() {
        //empty constructor needed
    }
    public Ktp (int position){
        this.position = position;
    }
    public Ktp(String nama, String imageUrl , String Des) {
        if (nama.trim().equals("")) {
            nama = "Nama Kosong";
        }
        this.nama = nama;
        this.imageURL = imageUrl;
        this.nik = Des;
    }
    public String getnik() {
        return nik;
    }
    public void setnik(String nik) {
        this.nik = nik;
    }

    public String getnama() {
        return nama;
    }
    public void setnama(String nama) {
        this.nama = nama;
    }
    public String getImageUrl() {
        return imageURL;
    }
    public void setImageUrl(String imageUrl) {
        this.imageURL = imageUrl;
    }

    @Override
    public String toString() {
        return getnama();
    }
    @Exclude
    public String getKey() {
        return key;
    }
    @Exclude
    public void setKey(String key) {
        this.key = key;
    }
}
