package com.example.surfchampionship;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class InsertSurfist extends AppCompatActivity {

    private EditText edt_name;
    private EditText edt_country;

    private SurfistDAO dao;
    private Surfist surfistUp = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_insert_surfist);

        edt_name = findViewById(R.id.edt_name);
        edt_country = findViewById(R.id.edt_country);

        dao = new SurfistDAO(this);

        Intent intent = getIntent();
        if (intent.hasExtra("surfist")){
            surfistUp = (Surfist) intent.getSerializableExtra("surfist");
            edt_name.setText(surfistUp.getName());
            edt_country.setText(surfistUp.getCountry());
        }
    }

    public void save(View view) {

        if (surfistUp == null){

            surfistUp = new Surfist();;
            surfistUp.setName(edt_name.getText().toString());
            surfistUp.setCountry(edt_country.getText().toString());

            long id = dao.insertSurfist(surfistUp);
            Toast.makeText(this, "Surfer inserted with id: " + id, Toast.LENGTH_LONG).show();
            finish();

        }else{

            surfistUp.setName(edt_name.getText().toString());
            surfistUp.setCountry(edt_country.getText().toString());
            dao.updateSurfist(surfistUp);

            Toast.makeText(this, "Surfer updated with id: " + surfistUp.getId().toString(), Toast.LENGTH_LONG).show();
            finish();
        }

    }

    public void back(View view) {
        finish();
    }
}
