package com.example.christiansandjon.appsonores;

import android.os.AsyncTask;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.sql.Time;
import java.util.Date;

public class RequestTask extends AsyncTask<String, Void, StationInfo> {

    //region Callback
    public interface IRequestEvent {
        void onRequestResult(StationInfo info);
    }

    private IRequestEvent callback;

    public void setCallback(IRequestEvent callback) {
        this.callback = callback;
    }
    //endregion

    private final String URL_BASE = "https://api.irail.be/liveboard/?station=";
    private final String URL_OPTION = "&format=json&lang=fr";


    @Override
    protected StationInfo doInBackground(String... stations) {
        if (stations == null || stations.length != 1 || stations[0].trim().isEmpty()) {
            return null;
        }

        //region Request to WebAPI

        String requestResult = null;
        HttpURLConnection connection = null;

        try {
            URL url = new URL(URL_BASE + stations[0] + URL_OPTION);

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
        } catch (IOException e) {
            //UTilisation de la connection
            e.printStackTrace();
        } finally {
            if (connection != null) {
                connection.disconnect();
            }
        }
        //endregion

        //region Convert : Result => Json => StationInfo
        StationInfo response = null;

        if(requestResult != null){

            try {
                JSONObject json = new JSONObject(requestResult);

                //Creation de l'objet reponse avec le  nom de la gare
                String name = json.getString("station");
                response = new StationInfo(name);

                JSONObject departures = json.getJSONObject("departures");
                int nbElement = departures.getInt("number");
                JSONArray trains = departures.getJSONArray("departure");

                for (int i = 0; i < nbElement && i < 15; i++) {

                    JSONObject train = trains.getJSONObject(i);

                    String dest = train.getString("station");
                    int plateform = train.getInt("platform");

                    //Utilisation de l'objet "LocalDate" disponible a partir de l'API 26
                    Date time = (new Date(train.getLong("time") * 1000L));
                    Time delay = new Time(train.getInt("delay") * 1000L);

                    response.addDeparture(new TrainInfo(dest, plateform, time, delay));
                }

            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        //endregion

        return response;
    }

    @Override
    protected void onPostExecute(StationInfo stationInfo) {
        if(callback != null) {
            callback.onRequestResult(stationInfo);
        }
    }
}