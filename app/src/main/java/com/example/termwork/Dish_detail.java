package com.example.termwork;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import android.util.Log;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RatingBar;
import android.widget.TextView;
import java.util.ArrayList;

public class Dish_detail extends AppCompatActivity {
    public TextView name;
    public TextView price;
    public TextView batching;
    public RatingBar rb;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dish_detail);
        name= findViewById(R.id.datail_name);
        price = findViewById(R.id.detail_price);
        batching = findViewById(R.id.detail_comments);
        rb = findViewById(R.id.detail_bar);

        Intent it =getIntent();
        float score = Float.valueOf(it.getStringExtra("score"));
        rb.setRating(score);
        name.setText("Name: "+it.getStringExtra("name"));
        price.setText("Price: "+it.getStringExtra("price"));
        batching.setText(it.getStringExtra("comments"));
    }

}