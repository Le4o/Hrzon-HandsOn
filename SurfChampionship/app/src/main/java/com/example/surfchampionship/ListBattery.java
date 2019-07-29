package com.example.surfchampionship;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.io.Serializable;
import java.util.List;

public class ListBattery extends AppCompatActivity {

    private ListView listView;
    private BatteryDAO bDao;
    private List<Battery> batteries;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_battery);

        listView = findViewById(R.id.lsv_batteries);

        bDao = new BatteryDAO(this);

        batteries = bDao.getAllBatteries();
        final ArrayAdapter<Battery> adapter = new ArrayAdapter<Battery>(this, android.R.layout.simple_list_item_1, batteries);
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Battery b = batteries.get(i);
                System.out.println(i);
                System.out.println(batteries.get(i).getId());
                addWave(b);
            }
        });

        registerForContextMenu(listView);
    }

    public void addWave(Battery b){
        //To Implement
    }

    public void back(View view) {
        finish();
    }
}


