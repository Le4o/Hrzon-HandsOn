package com.example.surfchampionship;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;


public class DAO {

    private Connection connection;
    private SQLiteDatabase db;

    public DAO(Context context){
        connection = new Connection(context);
        db = connection.getWritableDatabase();
    }

    //Surfist DAO implementation -----------------------------------------
    public long insertSurfist(Surfist surfist) {
        ContentValues values = new ContentValues();
        values.put("name", surfist.getName());
        values.put("country", surfist.getCountry());

        return db.insert("surfist", null, values);
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

    //Battery DAO implementation -----------------------------------------
    public long insertBattery(Surfist s1, Surfist s2){
        ContentValues values = new ContentValues();

        values.put("fk_surfist1", s1.getId());
        values.put("fk_surfist2", s2.getId());

        return db.insert("battery", null, values);
    }

    public Battery getBatteryById(int id){
        List<Battery> batteries = new ArrayList<>();
        String _id = "" + id;
        Cursor cursor = db.query("battery",
                new String[]{"id_battery", "fk_surfist1", "fk_surfist2"},
                "id_battery = ?",
                new String[]{_id},
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
        cursor.close();
        return batteries.get(0);
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

    //Wave DAO implementation -----------------------------------------
    public void insertWave(Battery battery, Integer number_surfist){
        ContentValues values = new ContentValues();
        values.put("fk_battery", battery.getId());
        values.put("number_surfist", number_surfist);

        db.insert("wave", null, values);
    }

    public List<Wave> getAllWaves(String fk_battery){
        List<Wave> waves = new ArrayList<>();
        Cursor cursor = db.query("wave",
                new String[]{"id_wave", "fk_battery", "number_surfist"},
                "fk_battery = ?",
                new String[]{fk_battery},
                null,
                null,
                null);

        while(cursor.moveToNext()){
            Wave w = new Wave();

            w.setId(cursor.getInt(0));
            w.setBattery(getBatteryById(cursor.getInt(1)));

            int ns = cursor.getInt(2);
            if (ns == 1) w.setSurfist(w.getBattery().getSurfist1());
            else w.setSurfist(w.getBattery().getSurfist2());

            waves.add(w);
        }
        cursor.close();
        return waves;
    }

    //Note DAO implementation -----------------------------------------
    public long insertNote(Integer id_wave, Integer note1, Integer note2, Integer note3){
        ContentValues values = new ContentValues();
        values.put("fk_wave", id_wave);
        values.put("parcial_note1", note1);
        values.put("parcial_note2", note2);
        values.put("parcial_note3", note3);

        return db.insert("note", null, values);
    }

    public List<Note> getAllNotes(String id_wave){
        List<Note> notes = new ArrayList<>();
        Cursor cursor = db.query("note",
                new String[]{"id_note", "fk_wave", "parcial_note1", "parcial_note2", "parcial_note3"},
                "fk_wave = ?",
                new String[]{id_wave},
                null,
                null,
                null);

        while(cursor.moveToNext()){
            Note n = new Note();

            n.setId(cursor.getInt(0));
            n.setFk_wave(cursor.getInt(1));
            n.setNote1(cursor.getInt(2));
            n.setNote2(cursor.getInt(3));
            n.setNote3(cursor.getInt(4));

            notes.add(n);
        }
        cursor.close();
        return notes;
    }

    private Integer average(Note n){
        return (n.getNote1() + n.getNote2() + n.getNote3()) / 3;
    }

    public List<Surfist> getWinner(){
        //Pegando todas as baterias
        List<Battery> batteries = getAllBatteries();

        //Lista de ganhadores
        List<Surfist> winners = new ArrayList<>();

        for (Battery b: batteries) {
            //Adicionando todas as ondas de uma certa bateria a uma lista
            List<Wave> waves = getAllWaves(b.getId().toString());

            List<Wave> surfist1_waves = new ArrayList<>();
            List<Wave> surfist2_waves = new ArrayList<>();

            List<Note> surfist1_note = new ArrayList<>();
            List<Note> surfist2_note = new ArrayList<>();

            //Dividindo a lista de ondas entre os dois respectivos surfistas
            for (Wave w : waves) {
                if (w.getSurfist().getId() == b.getSurfist1().getId()) surfist1_waves.add(w);
                else surfist2_waves.add(w);
            }

            //Pegando todas as notas de cada onda surfada por cada um dos surfistas
            for (Wave w: surfist1_waves) {
                surfist1_note.addAll(getAllNotes(w.getId().toString()));
            }
            for (Wave w: surfist2_waves) {
                surfist2_note.addAll(getAllNotes(w.getId().toString()));
            }

            //Encontrando as maiores notas

            List<Integer> ordered_notes1 = new ArrayList<>();
            List<Integer> ordered_notes2 = new ArrayList<>();

            for (Note n : surfist1_note) {
                Integer i = average(n);
                ordered_notes1.add(i);
            }
            for (Note n : surfist2_note) {
                Integer i = average(n);
                ordered_notes2.add(i);
            }

            Collections.sort(ordered_notes1, Collections.reverseOrder());
            Collections.sort(ordered_notes2, Collections.reverseOrder());

            Integer n1 = 0, n2 = 0;
            if (!ordered_notes1.isEmpty()) n1 = ordered_notes1.get(0);
            if (ordered_notes1.size() > 1) n1 += ordered_notes1.get(1);

            if (!ordered_notes2.isEmpty()) n2 = ordered_notes1.get(0);
            if (ordered_notes2.size() > 1 ) n2 += ordered_notes1.get(1);

            if (n1 > n2) winners.add(b.getSurfist1());
            else winners.add(b.getSurfist2());

        }
        return winners;
    }
}
