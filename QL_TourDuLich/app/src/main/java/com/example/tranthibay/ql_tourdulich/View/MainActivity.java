package com.example.tranthibay.ql_tourdulich.View;

import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;

import com.example.tranthibay.ql_tourdulich.R;
import com.example.tranthibay.ql_tourdulich.Services.TourList;
import com.example.tranthibay.ql_tourdulich.View.Fragment2.Fragment2;
import com.example.tranthibay.ql_tourdulich.View.Fragment3.Fragment3;
import com.example.tranthibay.ql_tourdulich.View.ShowTour.Fragment1;

public class MainActivity extends AppCompatActivity {
    Fragment selectedFragment;
    private FragmentTransaction fragmentTransaction;
    private FragmentManager fragmentManager;
    BottomNavigationView bottomNavigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_main );



        this.bottomNavigationView = (BottomNavigationView) findViewById( R.id.bottomNav );
        this.selectedFragment = new Fragment1();
        this.bottomNavigationView.setOnNavigationItemSelectedListener( new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                switch (item.getItemId()) {
                    case R.id.item1: {
                        selectedFragment = new Fragment1();
                    }
                    break;
                    case R.id.item2: {
                        selectedFragment = new Fragment2();
                    }
                    break;
                    case R.id.item3: {
                        selectedFragment = new Fragment3();
                    }
                    break;
                }
                fragmentManager = getSupportFragmentManager();
                fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace( R.id.root_layout, selectedFragment );
                fragmentTransaction.commit();
                return true;
            }
        } );
        this.fragmentManager = getSupportFragmentManager();
        this.fragmentTransaction = this.fragmentManager.beginTransaction();
        this.fragmentTransaction.replace( R.id.root_layout, selectedFragment );
        this.fragmentTransaction.commit();
    }
}
