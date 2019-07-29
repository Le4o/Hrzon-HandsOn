package com.example.surfchampionship;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.SparseBooleanArray;
import android.view.ActionMode;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.CheckedTextView;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class ListSurfists extends AppCompatActivity {

    private ListView listView;
    private SurfistDAO sDao;
    private BatteryDAO bDao;
    private List<Surfist> surfists;
    private List<Surfist> fSurfists = new ArrayList<>();
    private List<Surfist> surfistsForBattery = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_surfists);

        listView = findViewById(R.id.lsv_surfists);

        sDao = new SurfistDAO(this);
        bDao = new BatteryDAO(this);

        surfists = sDao.getAllSurfists();
        fSurfists.addAll(surfists);

        ArrayAdapter<Surfist> adapter = new ArrayAdapter<Surfist>(this, android.R.layout.simple_list_item_multiple_choice, fSurfists);
        listView.setAdapter(adapter);
        listView.setChoiceMode(AbsListView.CHOICE_MODE_MULTIPLE);
        listView.setMultiChoiceModeListener(new AbsListView.MultiChoiceModeListener() {
            @Override
            public void onItemCheckedStateChanged(ActionMode actionMode, int i, long l, boolean b) {

            }

            @Override
            public boolean onCreateActionMode(ActionMode actionMode, Menu menu) {
                return false;
            }

            @Override
            public boolean onPrepareActionMode(ActionMode actionMode, Menu menu) {
                return false;
            }

            @Override
            public boolean onActionItemClicked(ActionMode actionMode, MenuItem menuItem) {
                menuItem.setChecked(!menuItem.isChecked());
                return false;
            }

            @Override
            public void onDestroyActionMode(ActionMode actionMode) {

            }
        });
        registerForContextMenu(listView);
    }

    @Override
    public void onResume(){
        super.onResume();

        surfists = sDao.getAllSurfists();
        fSurfists.clear();
        fSurfists.addAll(surfists);
        listView.invalidateViews();
    }

    public boolean onCreateOptionsMenu(Menu menu){
        MenuInflater m = getMenuInflater();
        m.inflate(R.menu.search_menu, menu);

        SearchView s = (SearchView) menu.findItem(R.id.app_bar_search).getActionView();
        s.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String s) {
                searchByCountry(s);
                return false;
            }
        });

        return true;
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);

        MenuInflater m = getMenuInflater();
        m.inflate(R.menu.info_menu, menu);
    }

    public void searchByCountry(String country){

        fSurfists.clear();
        for (Surfist s : surfists){
            if (s.getCountry().toLowerCase().contains(country.toLowerCase())){
                fSurfists.add(s);
            }
        }
        listView.invalidateViews();
    }

    public void deleteSurfist(MenuItem item){
        final AdapterView.AdapterContextMenuInfo menuInfo = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
        final Surfist surfistToDelete = fSurfists.get(menuInfo.position);

        AlertDialog dialog = new AlertDialog.Builder(this)
                .setTitle("ATTENTION")
                .setMessage("Do you really want to delete the Surfist?")
                .setNegativeButton("No", null)
                .setPositiveButton("Yes, i want", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        fSurfists.remove(surfistToDelete);
                        surfists.remove(surfistToDelete);
                        sDao.deleteSurfist(surfistToDelete);
                        listView.invalidateViews();
                    }
                }).create();
        dialog.show();
    }

    public void updateSurfist(MenuItem item){
        final AdapterView.AdapterContextMenuInfo menuInfo = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
        final Surfist surfistToUpdate = fSurfists.get(menuInfo.position);

        Intent intent = new Intent(this, InsertSurfist.class);
        intent.putExtra("surfist", surfistToUpdate);
        startActivity(intent);
    }

    public void createBattery(View view){

        surfistsForBattery.clear();
        int len = listView.getCount();
        SparseBooleanArray checked = listView.getCheckedItemPositions();
        for (int i = 0; i < len; i++)
            if (checked.get(i)) {
                Surfist s = fSurfists.get(i);
                surfistsForBattery.add(s);
            }

        if (surfistsForBattery.size() == 2){
            final long id = bDao.insertBattery(surfistsForBattery.get(0), surfistsForBattery.get(1));
            Toast.makeText(this, "Battery created with id " + id, Toast.LENGTH_SHORT).show();
        }else{
            Toast.makeText(this, "Wrong number of surfists selected. Choose two.  ", Toast.LENGTH_SHORT).show();
        }

    }

    public void back(View view) {
        finish();
    }
}
