package com.example.christiansandjon.appsonores;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        spinner_decibel = findViewById(R.id.spinner_decibel);
        spinner_level = findViewById(R.id.spinner_level);
        listView=findViewById(R.id.list_sonore);

        //spinner couleur
        String[] colors = new String[]{"Vert", "Orange", "Rouge"};
        ArrayAdapter<String> adapterColors = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, colors);
        spinner_level.setAdapter(adapterColors);

        //spinner decibel
        String[] decibels = new String[]{"0", "10","20", "30","40", "50","60", "70","80", "90","100", "110","120"};
        ArrayAdapter<String> adapterDecibel = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, decibels);
        spinner_decibel.setAdapter(adapterDecibel);

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
