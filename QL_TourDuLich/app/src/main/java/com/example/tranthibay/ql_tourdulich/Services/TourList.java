package com.example.tranthibay.ql_tourdulich.Services;

import android.os.AsyncTask;

public class TourList extends AsyncTask <String,Integer, Double>{
    public Double getVal() {
        return val;
    }

    public Double val;
    @Override
    protected void onPreExecute() {
        super.onPreExecute();
    }



    @Override
    protected void onPostExecute(Double aDouble) {
        super.onPostExecute( aDouble );
        val=aDouble;

    }

    @Override
    protected void onProgressUpdate(Integer... values) {
        super.onProgressUpdate( values );
    }

    @Override
    protected Double doInBackground(String... strings) {
        String a=strings[0];
        return 99.99;
    }
}

