package com.example.surfchampionship;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

public class BatteryDAO {

    private Connection connection;
    private SQLiteDatabase db;

    public BatteryDAO(Context context){
        connection = new Connection(context);
        db = connection.getWritableDatabase();
    }

    public long insertBattery(Surfist s1, Surfist s2){
        ContentValues values = new ContentValues();

        values.put("fk_surfist1", s1.getId());
        values.put("fk_surfist2", s2.getId());

        return db.insert("battery", null, values);
    }

    public Surfist getSurfistById(int id){
        List<Surfist> surfists = new ArrayList<>();
        String _id = "" + id;
        Cursor cursor = db.query("surfist",
                new String[]{"id_surfist", "name", "country"},
                "id_surfist = ?",
                new String[]{_id},
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
        return surfists.get(0);
    }

    public List<Battery> getAllBatteries(){
        List<Battery> batteries = new ArrayList<>();
        Cursor cursor = db.query("battery",
                new String[]{"id_battery", "fk_surfist1", "fk_surfist2"},
                null,
                null,
                null,
                null,
                null);

        while(cursor.moveToNext()){
            Battery b = new Battery();
            b.setId(cursor.getInt(0));
            b.setSurfist1(getSurfistById(cursor.getInt(1)));
            b.setSurfist2(getSurfistById(cursor.getInt(2)));

            batteries.add(b);
        }
        return batteries;
    }
}
