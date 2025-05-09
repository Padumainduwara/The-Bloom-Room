package com.example.thebloomroom.Activities.User;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.HorizontalScrollView;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.thebloomroom.Activities.Admin.AdminDash;
import com.example.thebloomroom.Database.DatabaseHelper;
import com.example.thebloomroom.Classes.Flower;
import com.example.thebloomroom.Classes.RecViewAdapters.HomePageRecViewAdapter;
import com.example.thebloomroom.R;
import com.google.android.material.floatingactionbutton.ExtendedFloatingActionButton;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;

public class HomePage extends AppCompatActivity {

    private ExtendedFloatingActionButton btnOrder;
    private ImageView backIcon;
    private RecyclerView flowerList;
    private HorizontalScrollView chips;
    private int id;
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_home);
        backIcon = findViewById(R.id.ic_back_arrow);
        flowerList = findViewById(R.id.flower_list_rec_view);
        btnOrder = findViewById(R.id.place_order);

        List<Flower> flowers = defaultFlowerList();
        HomePageRecViewAdapter homePageRecViewAdapter = new HomePageRecViewAdapter(this);
        homePageRecViewAdapter.setFlowers(flowers);
        flowerList.setAdapter(homePageRecViewAdapter);
        flowerList.setLayoutManager(new LinearLayoutManager(this));

        Intent intent = getIntent();
        id = intent.getIntExtra("id", -1);

        backIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent login = new Intent(HomePage.this, LoginPage.class);
                startActivity(login);
            }
        });

        btnOrder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(HomePage.this, "Your order has been placed", Toast.LENGTH_SHORT).show();
                Intent login = new Intent(HomePage.this, HomePage.class);
                startActivity(login);
            }
        });
        
    }

    public List<Flower> defaultFlowerList() {
        DatabaseHelper databaseHelper = new DatabaseHelper(HomePage.this);
        return databaseHelper.getAllFlowers();
    }
}