package com.EmpatikAndroid;


import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import org.xml.sax.HandlerBase;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;


public class Database extends SQLiteOpenHelper {

    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "EmpatikAndroid.db";

    public Database(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db){
        String CREATE_TABLE_HABER = "CREATE TABLE "+Haber.TABLE_NAME+ "( "+
                Haber.ID+" INTEGER PRIMARY KEY AUTOINCREMENT, "+
                Haber.BASLIK+" NVARCHAR(100), "+
                Haber.ICERIK+" TEXT, "+
                Haber.RESIM+" NVARCHAR(100), "+
                Haber.TARIH+" DATETIME, "+
                Haber.YAZAR+" NVARCHAR(50), "+
                Haber.KATEGORI_ID+" INTEGER, "+
                Haber.GAZETE_ID+" INTEGER, "+
                Haber.URL+" NVARCHAR(150) "+
                ")";
        db.execSQL(CREATE_TABLE_HABER);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + Haber.TABLE_NAME);
        onCreate(db);
    }

    public void haberEkle(Haber haber){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put(Haber.BASLIK, haber.getBaslik());
        values.put(Haber.ICERIK, haber.getIcerik());
        values.put(Haber.RESIM, haber.getResim());
        values.put(Haber.TARIH, "");
        values.put(Haber.YAZAR, haber.getYazar());
        values.put(Haber.KATEGORI_ID, haber.getKategoriId());
        values.put(Haber.GAZETE_ID, haber.getGazeteId());
        values.put(Haber.URL, haber.getUrl());

        db.insert(Haber.TABLE_NAME, null, values);
        db.close();
    }

    public void haberSil(Haber haber){
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(Haber.TABLE_NAME, Haber.ID + " =?", new String[]{String.valueOf(haber.getId())});
        db.close();
    }

    public Haber getHaber(int id){
        SQLiteDatabase db = this.getReadableDatabase();

        String selectQuery = "SELECT * FROM "+Haber.TABLE_NAME+" WHERE "+Haber.ID+" = "+id;
        Cursor c = db.rawQuery(selectQuery, null);
        Log.e("selectQuery", selectQuery);

      /*  Cursor c = db.query(Haber.TABLE_NAME,
                new String[]{Haber.ID, Haber.BASLIK, Haber.ICERIK, Haber.RESIM, Haber.TARIH, Haber.YAZAR, Haber.KATEGORI_ID, Haber.GAZETE_ID, Haber.URL},
        Haber.ID + "=?",new String[]{String.valueOf(id)},null,null,null );*/

        if(c!=null)
            c.moveToFirst();

        Haber haber = new Haber();
        haber.setId(c.getInt(c.getColumnIndex(Haber.ID)));
        haber.setBaslik(c.getString(c.getColumnIndex(Haber.BASLIK)));
        haber.setResim(c.getString(c.getColumnIndex(Haber.RESIM)));
        haber.setUrl(c.getString(c.getColumnIndex(Haber.URL)));

        return haber;
    }

    public List<Haber> getAllHaber(){
        List<Haber> haberList = new ArrayList<Haber>();
        String selectQuery = "SELECT * FROM " +Haber.TABLE_NAME;

        SQLiteDatabase db = getWritableDatabase();
        Cursor c = db.rawQuery(selectQuery, null);

        if(c.moveToFirst()){
            do{
                Haber haber = new Haber();
                haber.setId(c.getInt(c.getColumnIndex(Haber.ID)));
                haber.setBaslik(c.getString(c.getColumnIndex(Haber.BASLIK)));
                haber.setResim(c.getString(c.getColumnIndex(Haber.RESIM)));
                haber.setUrl(c.getString(c.getColumnIndex(Haber.URL)));

                haberList.add(haber);
            }while(c.moveToNext());
        }
        return haberList;
    }

    public int getHaberCount(){
        String countQuery = "SELECT * FROM "+Haber.TABLE_NAME;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor c  = db.rawQuery(countQuery, null);
        c.close();

        return c.getCount();
    }

    public int haberGuncelle(Haber haber){
        SQLiteDatabase db = getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(Haber.ID, haber.getUrl());

        return db.update(Haber.TABLE_NAME, values, Haber.ID+ " =?", new String[]{String.valueOf(haber.getId()) });
    }
}
