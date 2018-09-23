package com.example.tranthibay.ql_tourdulich.View.ShowTour;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;

import android.widget.GridView;
import android.widget.Toast;

import com.example.tranthibay.ql_tourdulich.R;
import com.example.tranthibay.ql_tourdulich.View.DangNhap.LoginActivity;

import java.util.ArrayList;
import java.util.List;

public class TourActivity extends AppCompatActivity {

    private List<String> lst;
    private GridView gv_tour;
    private BottomNavigationView bottomNav;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_tour);
        gv_tour = (GridView) findViewById(R.id.show_tour_gv_tour);
        lst = new ArrayList<>();
        lst.add("Tran Kha");
        lst.add("Tran Thanh Thoi");
        lst.add("Tran Khoi");
        lst.add("Tran Thi Bay");
        lst.add("Tran Kha");
        lst.add("Tran Thanh Thoi");
        lst.add("Tran Khoi");
        lst.add("Tran Thi Bay");
        lst.add("Tran Kha");
        lst.add("Tran Thanh Thoi");
        lst.add("Tran Khoi");
        lst.add("Tran Thi Bay");

//        TourAdapter adapter = new TourAdapter(this, lst);
//        gv_tour.setAdapter(adapter);
//        bottomNav = (BottomNavigationView) findViewById(R.id.navigation);
//        bottomNav.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
//            @Override
//            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
//                Intent intent;
//                switch (menuItem.getItemId()) {
//                    case R.id.navigation_home:
//                        Toast.makeText(TourActivity.this, "Home", Toast.LENGTH_SHORT).show();
//                        intent = new Intent(TourActivity.this, LoginActivity.class);
//                        startActivity(intent);
//                        return true;
//                    case R.id.navigation_dashboard: {
//                        Toast.makeText(TourActivity.this, "DashBoard", Toast.LENGTH_SHORT).show();
//
//                        return true;
//                    }
//                    case R.id.navigation_notifications:
//                        Toast.makeText(TourActivity.this, "Notification", Toast.LENGTH_SHORT).show();
//                        return true;
//                }
//                return false;
//            }
//        });
    }
}
