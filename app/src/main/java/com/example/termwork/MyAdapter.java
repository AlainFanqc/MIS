package com.example.termwork;

import android.content.Context;
import android.media.Rating;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.RatingBar;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

public class MyAdapter extends ArrayAdapter {


    public MyAdapter(Context context, int resource, ArrayList<HashMap<String,String>> list) {
        super(context, resource, list);
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View itemView = convertView;
        if(itemView == null){
            itemView = LayoutInflater.from(getContext()).inflate(R.layout.score_show,parent,false);
        }

        HashMap<String,String> map = (HashMap<String, String>) getItem(position);
        TextView dishname = (TextView) itemView.findViewById(R.id.dish_name);
        TextView dishprice = (TextView) itemView.findViewById(R.id.dish_price);
        RatingBar bar =(RatingBar) itemView.findViewById(R.id.dish_bar);

        dishname.setText(map.get("name"));
        dishprice.setText(map.get("price"));
        bar.setRating(Float.valueOf(map.get("score")));

        return itemView;
    }
}