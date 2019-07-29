package com.example.surfchampionship;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

public class SurfistDAO {

    private Connection connection;
    private SQLiteDatabase db;

    public SurfistDAO(Context context){
        connection = new Connection(context);
        db = connection.getWritableDatabase();
    }

    public long insertSurfist(Surfist surfist) {
        ContentValues values = new ContentValues();
        values.put("name", surfist.getName());
        values.put("country", surfist.getCountry());

        return db.insert("surfist", null, values);
    }

    public List<Surfist> getAllSurfists(){
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

    public void updateSurfist(Surfist surfist){
        ContentValues values = new ContentValues();
        values.put("name", surfist.getName());
        values.put("country", surfist.getCountry());

        db.update("surfist", values, "id_surfist = ?", new String[]{surfist.getId().toString()});
    }

    public void deleteSurfist(Surfist surfist){
        db.delete("Surfist", "id_surfist = ?", new String[]{surfist.getId().toString()});
    }
}
