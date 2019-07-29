package com.example.surfchampionship;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

public class ListWaves extends AppCompatActivity {

    private Battery battery = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_waves);

        //To implement
    }

    public void addWave(View view){

    }

    public void back(View view) {
        finish();
    }
}
