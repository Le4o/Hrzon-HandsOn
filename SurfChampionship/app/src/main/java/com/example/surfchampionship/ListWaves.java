package com.example.surfchampionship;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.List;

public class ListWaves extends AppCompatActivity {

    private ListView listView;
    private DAO Dao;
    private List<Wave> waves;
    private String id_battery;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_waves);

        Intent intent = getIntent();

        if (intent.hasExtra("id_battery")) id_battery = intent.getExtras().getString("id_battery");

        Dao = new DAO(this);
        waves = Dao.getAllWaves(id_battery);

        listView = findViewById(R.id.lsv_waves);

        ArrayAdapter<Wave> adapter = new ArrayAdapter<Wave>(this, android.R.layout.simple_list_item_1, waves);
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Wave w = waves.get(i);
                addNote(w);
            }
        });

        registerForContextMenu(listView);

    }

    public void addNote(Wave w){
        Intent intent = new Intent(this, InsertNote.class);
        intent.putExtra("id_wave", w.getId().toString());
        startActivity(intent);
    }

    public void back(View view) {
        finish();
    }
}
