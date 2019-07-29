package com.example.surfchampionship;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    private Button btn_insert_surfist;
    private Button btn_list_surfists;
    private Button btn_insert_battery;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btn_insert_surfist = findViewById(R.id.btn_insert_surfist);
        btn_list_surfists = findViewById(R.id.btn_list_surfist);
        btn_insert_battery = findViewById(R.id.btn_insert_battery);

        btn_insert_surfist.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, InsertSurfist.class);
                startActivity(intent);
            }
        });

        btn_list_surfists.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, ListSurfists.class);
                startActivity(intent);
            }
        });

        btn_insert_battery.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, ListBattery.class);
                startActivity(intent);
            }
        });
    }



}
