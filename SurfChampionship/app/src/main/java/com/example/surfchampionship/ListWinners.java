package com.example.surfchampionship;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.List;

public class ListWinners extends AppCompatActivity {

    private ListView listView;
    private DAO Dao;
    private List<Surfist> winners;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_winners);

        Dao = new DAO(this);

        listView = findViewById(R.id.lsv_winners);
        winners = Dao.getWinner();

        ArrayAdapter<Surfist> adapter = new ArrayAdapter<Surfist>(this, android.R.layout.simple_list_item_1, winners);
        listView.setAdapter(adapter);
    }

    public void back(View view) {
        finish();
    }
}
