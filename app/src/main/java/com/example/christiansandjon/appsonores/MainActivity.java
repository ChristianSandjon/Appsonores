package com.example.christiansandjon.appsonores;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.Spinner;

import com.example.christiansandjon.appsonores.Models.DataModel;
import com.example.christiansandjon.appsonores.Tools.SonorAdapter;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private SearchView searchView;
    private Spinner spinner1, spinner2;
    private ListView listView;
    private ArrayList<DataModel> dataModels;
    private SonorAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        listView=(ListView)findViewById(R.id.list_sonore);

        dataModels= new ArrayList<>();

        dataModels.add(new DataModel("Bruxelles", "bf formation", "20/09/2018",20.4));

        adapter= new SonorAdapter(dataModels,getApplicationContext());

        listView.setAdapter(adapter);

    }
}
