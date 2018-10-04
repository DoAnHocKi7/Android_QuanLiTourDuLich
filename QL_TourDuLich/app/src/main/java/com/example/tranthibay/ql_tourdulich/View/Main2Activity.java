package com.example.tranthibay.ql_tourdulich.View;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.tranthibay.ql_tourdulich.R;
import com.squareup.picasso.Picasso;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class Main2Activity extends AppCompatActivity {
    ImageView imgView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_main2 );
        imgView = (ImageView) findViewById( R.id.imageView3 );

        String imgSrc = "http://192.168.110.2:8080/web_QLTourDuLich_php/tour_dulich/images/tour/dn1.jpg";
        URL url = null;
        checkInternetConnection();
        Picasso.get().load(imgSrc).into(imgView);
//        try {
//            url = new URL( imgSrc );
//            HttpURLConnection httpConn = (HttpURLConnection) url.openConnection();
//            httpConn.connect();
//            int resCode = httpConn.getResponseCode();
//
//            if (resCode == HttpURLConnection.HTTP_OK) {
//                InputStream in = httpConn.getInputStream();
//                Bitmap bitmap = BitmapFactory.decodeStream( in );
//
//                imgView.setImageBitmap( bitmap );
//            }
//        } catch (MalformedURLException e) {
//            e.printStackTrace();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }

    }

    private boolean checkInternetConnection() {

        ConnectivityManager connManager =
                (ConnectivityManager) this.getSystemService( Context.CONNECTIVITY_SERVICE );


        NetworkInfo networkInfo = connManager.getActiveNetworkInfo();

        if (networkInfo == null) {
            Toast.makeText( this, "No default network is currently active", Toast.LENGTH_LONG ).show();
            return false;
        }

        if (!networkInfo.isConnected()) {
            Toast.makeText( this, "Network is not connected", Toast.LENGTH_LONG ).show();
            return false;
        }

        if (!networkInfo.isAvailable()) {
            Toast.makeText( this, "Network not available", Toast.LENGTH_LONG ).show();
            return false;
        }
        Toast.makeText( this, "Network OK", Toast.LENGTH_LONG ).show();
        return true;
    }

    void docFile() {
        try {
            InputStream inputStream = openFileInput( "file.xml" );
            int r = -1;
            StringBuffer dl = new StringBuffer();
            while ((r = inputStream.read()) != -1) {
                dl.append( (char) r );
                Toast.makeText( this, r, Toast.LENGTH_LONG ).show();
            }
        } catch (FileNotFoundException e1) {
            e1.printStackTrace();
        } catch (IOException e1) {
            e1.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
