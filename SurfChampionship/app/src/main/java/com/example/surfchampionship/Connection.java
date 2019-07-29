package com.example.surfchampionship;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class Connection extends SQLiteOpenHelper {

    private static final String name = "database.db";
    private static final int version = 1;

    public Connection(Context context) {
        super(context, name, null, version);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL("" +
                "CREATE TABLE surfist(" +
                "    id_surfist          INTEGER PRIMARY KEY AUTOINCREMENT,\n" +
                "    name                VARCHAR(50),\n" +
                "    country             VARCHAR(20)\n" +
                ");\n"
        );
        sqLiteDatabase.execSQL("" +
                "CREATE TABLE battery(\n" +
                "    id_battery          INTEGER PRIMARY KEY AUTOINCREMENT,\n" +
                "    fk_surfist1         INTEGER,\n" +
                "    fk_surfist2         INTEGER\n" +
                ");\n"
        );
        sqLiteDatabase.execSQL(""+
                "CREATE TABLE wave(\n" +
                "    id_wave             INTEGER PRIMARY KEY AUTOINCREMENT,\n" +
                "    fk_battery          INTEGER,\n" +
                "    number_surfist      INTEGER\n" +
                ");\n"
        );
        sqLiteDatabase.execSQL(""+
                "CREATE TABLE note(\n" +
                "    id_note             INTEGER PRIMARY KEY AUTOINCREMENT,\n" +
                "    fk_wave             INTEGER,\n" +
                "    parcial_note1       INTEGER,\n" +
                "    parcial_note2       INTEGER,\n" +
                "    parcial_note3       INTEGER\n" +
                ");"
        );
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}
