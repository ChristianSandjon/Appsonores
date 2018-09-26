package com.example.christiansandjon.appsonores;

import android.os.AsyncTask;

import com.example.christiansandjon.appsonores.Models.DataModel;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;


public class RequestTask extends AsyncTask<String, Void, ArrayList<DataModel>> {

    //region Callback
    public interface IRequestEvent {
        void onRequestResult(ArrayList<DataModel> info);
    }

    private IRequestEvent callback;

    public void setCallback(IRequestEvent callback) {
        this.callback = callback;
    }
    //endregion

    private ArrayList<DataModel> infosonores;
    private final String URL_BASE = "http://smartcityteam5.eu-central-1.elasticbeanstalk.com";
    private final String URL_OPTION = "/measurements";


    @Override
    protected ArrayList<DataModel> doInBackground(String... datas) {

        //region Request API

        String requestResult = null;
        HttpURLConnection connection = null;

        try {
            URL url = new URL(URL_BASE + URL_OPTION);

            //Send Request URL
            connection = (HttpURLConnection) url.openConnection();
            connection.connect();

            if(connection.getResponseCode() != HttpURLConnection.HTTP_OK) {
                return null;
            }

            //On a recu les datas!
            //Read result
            InputStreamReader streamReader = new InputStreamReader(connection.getInputStream());
            BufferedReader reader = new BufferedReader(streamReader);

            StringBuilder data = new StringBuilder();
            String line;
            while((line = reader.readLine()) != null) {
                data.append(line);
                data.append('\n');
            }
            reader.close();
            streamReader.close();

            requestResult = data.toString();

        } catch (MalformedURLException e) {
            //Creation del'objet URL
            e.printStackTrace();
        }
          catch (IOException ioe){
              ioe.printStackTrace();
            }

            finally {
            if (connection != null) {
                connection.disconnect();
            }
        }
        //endregion

        infosonores = new ArrayList<>();

        if(requestResult != null){

            try {
                JSONArray liste = new JSONArray(requestResult);

                for (int i = 0; i < liste.length(); i++) {

                    JSONObject infoSon = liste.getJSONObject(i); // recupere chaque item de la liste
                    String date = infoSon.getString("date");
                    Double decibel = infoSon.getDouble("laeq60");

                    infosonores.add(new DataModel("Bruxelles"+i,"rue de stalle 60"+i,date,"15h00", decibel));
                }

            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        //endregion

        return infosonores;
    }

    @Override
    protected void onPostExecute(ArrayList<DataModel> DataModel) {
        if(callback != null) {
            callback.onRequestResult(DataModel);
        }
    }
}