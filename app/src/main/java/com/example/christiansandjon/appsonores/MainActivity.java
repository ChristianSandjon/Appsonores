package com.example.christiansandjon.appsonores;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.Spinner;

import com.example.christiansandjon.appsonores.Models.DataModel;
import com.example.christiansandjon.appsonores.Tools.SonorAdapter;

import java.util.ArrayList;


public class MainActivity extends AppCompatActivity implements SearchView.OnQueryTextListener, RequestTask.IRequestEvent, View.OnClickListener {

    private SearchView searchView;
    private Spinner spinner_level, spinner_decibel;
    private ListView listView;
    //private ArrayList<DataModel> dataModels;
    private SonorAdapter adapter;
    public static final String KEY = "sonor";
    private String levelValue, decibelValue, searchValue;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        spinner_decibel = findViewById(R.id.spinner_decibel);
        spinner_level = findViewById(R.id.spinner_level);
        listView=findViewById(R.id.list_sonore);

        //searchview region

        searchView = findViewById(R.id.search_view);

        searchView.setOnQueryTextListener(this);
        boolean isIconfied=searchView.isIconfiedByDefault();
        searchView.setIconifiedByDefault(false);

        //spinner couleur
        String[] colors = getResources().getStringArray(R.array.colors);
        ArrayAdapter<String> adapterColors = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item ,colors);
        spinner_level.setAdapter(adapterColors);


        //spinner decibel
        String[] decibels = getResources().getStringArray(R.array.decibels);
        ArrayAdapter<String> adapterDecibel = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, decibels);
        spinner_decibel.setAdapter(adapterDecibel);

        spinner_level.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view,
                                       int position, long id) {
                switch(position){
                    case 0 : levelValue = "Vert";
                    break;
                    case 1 : levelValue = "Orange";
                    break;
                    case 2 : levelValue = "Rouge";
                    break;
                }
                spinner_level.setSelection(position);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });

        spinner_decibel.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected( AdapterView<?> parent, View view, int position, long id ) {

                    switch ( position ) {
                        case 0: decibelValue = "10";
                        break;
                        case 1: decibelValue = "20";
                            break;
                        case 2: decibelValue = "30";
                            break;
                        case 3: decibelValue = "40";
                            break;
                        case 4: decibelValue = "50";
                            break;
                        case 5: decibelValue = "60";
                            break;
                        case 6: decibelValue = "70";
                            break;
                        case 7: decibelValue = "80";
                            break;
                        default:
                            break;
                    }
                    spinner_decibel.setSelection(position);
                }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });

        loadDatas();
    }

    @Override
    public boolean onQueryTextSubmit(String query) {
        return false;
    }

    @Override
    public boolean onQueryTextChange(String newText) {
        String text = newText;
        adapter.filter(text);
        return false;
    }

    @Override
    public void onClick(View v) {
    }

    public void loadDatas(){
        RequestTask task = new RequestTask();
        task.setCallback(this);
        task.execute();
    }

    @Override
    public void onRequestResult(ArrayList<DataModel> info) {
        adapter= new SonorAdapter(info, getApplicationContext());
        listView.setAdapter(adapter);
    }
}
