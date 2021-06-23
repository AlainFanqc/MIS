package com.example.termwork;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.sql.Connection;
import java.sql.PreparedStatement;

public class Administrator extends AppCompatActivity implements Runnable{

    private static PreparedStatement stmt = null;
    private static Connection con = null;
    EditText add_dishname,add_dishprice,add_introduction;
    Button add_submit;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_administrator);
        add_dishname = findViewById(R.id.add_dishname);
        add_dishprice = findViewById(R.id.add_dishprice);
        add_introduction = findViewById(R.id.add_dishintroduction);
        add_submit = findViewById(R.id.add_submit);
        Thread thread = new Thread(this);
        thread.start();
    }

    @Override
    public void run() {
        try {
            con = MySQLConnections.getConnection();
            String sql ="insert into canteen('dishname','price',''dishcontent) values(?,?,?)";
            if (con!=null){

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}