package com.example.christiansandjon.appsonores;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.Spinner;

import com.example.christiansandjon.appsonores.Models.DataModel;
import com.example.christiansandjon.appsonores.Tools.SonorAdapter;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private SearchView searchView;
    private Spinner spinner_level, spinner_decibel;
    private ListView listView;
    private ArrayList<DataModel> dataModels;
    private SonorAdapter adapter;
    private String levelValue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        spinner_decibel = findViewById(R.id.spinner_decibel);
        spinner_level = findViewById(R.id.spinner_level);
        listView=findViewById(R.id.list_sonore);

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
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });



// data
        dataModels= new ArrayList<>();

        dataModels.add(new DataModel("Bruxelles", "bf formation", "20/09/2018",20.4));
        dataModels.add(new DataModel("Anvers", "bf formation", "20/09/2018",20.4));
        dataModels.add(new DataModel("Liege", "bf formation", "20/09/2018",20.4));
        dataModels.add(new DataModel("Bruxelles", "bf formation", "20/09/2018",20.4));
        dataModels.add(new DataModel("Anvers", "bf formation", "20/09/2018",20.4));
        dataModels.add(new DataModel("Liege", "bf formation", "20/09/2018",20.4));
        dataModels.add(new DataModel("Bruxelles", "bf formation", "20/09/2018",20.4));
        dataModels.add(new DataModel("Anvers", "bf formation", "20/09/2018",20.4));
        dataModels.add(new DataModel("Liege", "bf formation", "20/09/2018",20.4));
        dataModels.add(new DataModel("Bruxelles", "bf formation", "20/09/2018",20.4));
        dataModels.add(new DataModel("Anvers", "bf formation", "20/09/2018",20.4));
        dataModels.add(new DataModel("Liege", "bf formation", "20/09/2018",20.4));
        adapter= new SonorAdapter(dataModels,getApplicationContext());

        listView.setAdapter(adapter);

    }
}
