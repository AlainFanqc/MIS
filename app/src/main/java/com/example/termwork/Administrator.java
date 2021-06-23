package com.example.termwork;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class Administrator extends AppCompatActivity {

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
        add_submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                insert();

            }
        });
    }

    public void insert() {
        try {
            Connection con = MySQLConnections.getConnection();
            PreparedStatement ps_0 = con.prepareStatement("select dishnum from canteen");
            ResultSet rs = ps_0.executeQuery();
            while (rs.last()) {
                int x = rs.getInt(1);
                Log.i("X", "insert:X:  "+x);
                String sql = "insert into canteen values(?,?,?,?)";
                PreparedStatement ps = con.prepareStatement(sql);
                ps.setInt(1,x+1);
                ps.setString(2, add_dishname.getText().toString());
                ps.setString(3, add_introduction.getText().toString());
                ps.setFloat(4, Float.valueOf(add_dishprice.getText().toString()));
                int i = ps.executeUpdate();
                Log.i(".....", "insert: i: " + i);
                break;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}