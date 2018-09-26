package com.example.christiansandjon.appsonores.Models;


import android.os.Parcel;
import android.os.Parcelable;

public class DataModel implements Parcelable{

    private String ville;
    private String adresse;
    private String date;
    private String heure;
    private Double db;

    public DataModel(String ville, String adresse, String date, String heure, Double db) {
        this.ville = ville;
        this.adresse = adresse;
        this.date = date;
        this.heure = heure;
        this.db = db;
    }

    public String getVille() {
        return ville;
    }

    public void setVille(String ville) {
        this.ville = ville;
    }

    public String getAdresse() {
        return adresse;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public Double getDb() {
        return db;
    }

    public void setDb(Double db) {
        this.db = db;
    }

    public String getHeure() {
        return heure;
    }

    public void setHeure(String heure) {
        this.heure = heure;
    }

    public DataModel(Parcel in){
        String[] data = new String[5];

        in.readStringArray(data);

        // the order needs to be the same as in writeToParcel() method
        this.ville = data[0];
        this.adresse = data[1];
        this.date = data[2];
        this.heure = data[3];
        this.db = Double.parseDouble(data[4]);
    }

    @Override
    public int describeContents(){
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeStringArray(new String[] {
                this.ville,
                this.adresse,
                this.date,
                this.heure,
                ""+this.db});
    }
    public static final Parcelable.Creator CREATOR = new Parcelable.Creator() {
        public DataModel createFromParcel(Parcel in) {
            return new DataModel(in);
        }

        public DataModel[] newArray(int size) {
            return new DataModel[size];
        }
    };
}
