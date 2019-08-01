package com.example.surfchampionship;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class InsertNote extends AppCompatActivity {

    private EditText edt_note1;
    private EditText edt_note2;
    private EditText edt_note3;
    private DAO Dao;
    private String id_wave;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_insert_note);

        edt_note1 = findViewById(R.id.edt_note1);
        edt_note2 = findViewById(R.id.edt_note2);
        edt_note3 = findViewById(R.id.edt_note3);

        Dao = new DAO(this);

        Intent intent = getIntent();
        if (intent.hasExtra("id_wave")) id_wave = intent.getExtras().getString("id_wave");
    }

    public void save(View view){
        final long id = Dao.insertNote(Integer.parseInt(id_wave),
                                       Integer.parseInt(edt_note1.getText().toString()),
                                       Integer.parseInt(edt_note2.getText().toString()),
                                       Integer.parseInt(edt_note3.getText().toString()));

        Toast.makeText(this, "Notes inserted with id " + id, Toast.LENGTH_SHORT).show();
        finish();
    }

    public void back(View view){
        finish();
    }
}
