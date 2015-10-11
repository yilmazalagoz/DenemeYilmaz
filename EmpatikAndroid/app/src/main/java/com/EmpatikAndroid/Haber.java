package com.EmpatikAndroid;

import java.util.Date;

/**
 * Created by Casper on 3.10.2015.
 */
public class Haber {

    public static final String TABLE_NAME = "haber";
    public static final String ID = "Id";
    public static final String BASLIK = "baslik";
    public static final String ICERIK = "içerik";
    public static final String RESIM = "resim";
    public static final String TARIH = "tarih";
    public static final String YAZAR = "yazar";
    public static final String KATEGORI_ID = "kategoriId";
    public static final String GAZETE_ID = "gazeteId";
    public static final String URL = "url";

    int id, kategoriId, gazeteId;
    String baslik, icerik, resim, yazar, url;
    Date tarih;

    public Haber() {
        this.baslik = "";
        this.icerik = "";
        this.resim = "";
        this.tarih = null;
        this.yazar = "";
        this.kategoriId = -1;
        this.gazeteId = -1;
        this.url = "";
    }

    public Haber( String baslik, String icerik, String resim, Date tarih, String yazar, int kategoriId, int gazeteId, String url) {
        this.baslik = baslik;
        this.icerik = icerik;
        this.resim = resim;
        this.tarih = tarih;
        this.yazar = yazar;
        this.kategoriId = kategoriId;
        this.gazeteId = gazeteId;
        this.url = url;
    }

    public void setId(int id){ this.id = id; }

    public void setBaslik(String baslik) {
        this.baslik = baslik;
    }

    public void setIcerik(String icerik) {
        this.icerik = icerik;
    }

    public void setResim(String resim) {
        this.resim = resim;
    }

    public void setTarih(Date tarih) {
        this.tarih = tarih;
    }

    public void setYazar(String yazar) {
        this.yazar = yazar;
    }

    public void setKategoriId(int kategoriId) {
        this.kategoriId = kategoriId;
    }

    public void setGazeteId(int gazeteId) {
        this.gazeteId = gazeteId;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public int getId() { return id; }

    public String getBaslik() {
        return baslik;
    }

    public String getIcerik() {
        return icerik;
    }

    public String getResim() {
        return resim;
    }

    public Date getTarih() {
        return tarih;
    }

    public String getYazar() {
        return yazar;
    }

    public int getKategoriId() {
        return kategoriId;
    }

    public int getGazeteId() {
        return gazeteId;
    }

    public String getUrl() {
        return url;
    }

}
