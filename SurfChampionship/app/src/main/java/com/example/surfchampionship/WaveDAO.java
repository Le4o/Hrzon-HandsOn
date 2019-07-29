package com.example.surfchampionship;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

public class WaveDAO {

    private Connection connection;
    private SQLiteDatabase db;

    public WaveDAO(Context context){
        connection = new Connection(context);
        db = connection.getWritableDatabase();
    }

    public List<Surfist> getAllWaves(){
        List<Surfist> surfists = new ArrayList<>();
        Cursor cursor = db.query("surfist",
                new String[]{"id_surfist", "name", "country"},
                null,
                null,
                null,
                null,
                null);

        while(cursor.moveToNext()){
            Surfist s = new Surfist();
            s.setId(cursor.getInt(0));
            s.setName(cursor.getString(1));
            s.setCountry(cursor.getString(2));

            surfists.add(s);
        }
        return surfists;
    }
}
