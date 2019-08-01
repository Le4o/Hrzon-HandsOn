package com.example.surfchampionship;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.io.Serializable;
import java.util.List;

public class ListBattery extends AppCompatActivity {

    private ListView listView;
    private DAO Dao;
    private List<Battery> batteries;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_battery);

        listView = findViewById(R.id.lsv_batteries);

        Dao = new DAO(this);

        batteries = Dao.getAllBatteries();
        final ArrayAdapter<Battery> adapter = new ArrayAdapter<Battery>(this, android.R.layout.simple_list_item_1, batteries);
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Battery b = batteries.get(i);
                listWaves(b);
            }
        });

        registerForContextMenu(listView);
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);

        MenuInflater m = getMenuInflater();
        m.inflate(R.menu.battery_menu, menu);
    }

    public void listWaves(Battery b){
        Intent i = new Intent(this, ListWaves.class);
        i.putExtra("id_battery", b.getId().toString());
        startActivity(i);
    }

    public void insertWave(MenuItem item){
        final AdapterView.AdapterContextMenuInfo menuInfo = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
        final Battery b = batteries.get(menuInfo.position);

        AlertDialog dialog = new AlertDialog.Builder(this)
                .setTitle("NEW WAVE")
                .setMessage("Select the surfist for the wave")
                .setNegativeButton("" + b.getSurfist1().getName(), new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Dao.insertWave(b, 1);
                        listView.invalidateViews();
                    }
                })
                .setPositiveButton("" + b.getSurfist2().getName(), new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Dao.insertWave(b, 2);
                        listView.invalidateViews();
                    }
                }).create();
        dialog.show();
    }

    public void back(View view) {
        finish();
    }
}


