package com.example.termwork;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.app.LauncherActivity;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.nfc.Tag;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.RatingBar;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;

public class contents extends AppCompatActivity implements Runnable {


    private boolean T=false;//发送标志位
    //数据库连接类
    private static PreparedStatement stmt = null;
    private static Connection con = null;
    Handler handler;
    private static final String TAG = "contents";
    private ArrayList<HashMap<String, String>> listItems;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contents);
        ListView listView = findViewById(R.id.list_view);

        Thread t = new Thread(this);
        t.start();

        handler = new Handler() {
            @Override
            public void handleMessage(@NonNull Message msg) {
                if (msg.what == 1) {
                    listItems = (ArrayList<HashMap<String, String>>) msg.obj;
                    MyAdapter adapter = new MyAdapter(contents.this,R.layout.score_show,listItems);
                    listView.setAdapter(adapter);
                    listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                        @Override
                        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                            Intent it = new Intent(contents.this,Dish_detail.class);
                            it.putExtra("imgid",listItems.get(position).get("imgid"));
                            it.putExtra("name",listItems.get(position).get("name"));
                            it.putExtra("price",listItems.get(position).get("price"));
                            it.putExtra("score",listItems.get(position).get("score"));
                            it.putExtra("comments",listItems.get(position).get("comments"));
                            //it.putExtra("batching",listItems.get(position).get("batching"));
                            startActivity(it);
                        }
                    });
                }
                super.handleMessage(msg);
            }
        };

        //单击事件处理
    }


    @Override
    public void run() {
        ArrayList<HashMap<String,String>> listItems = new ArrayList<HashMap<String, String>>();
            try {
                con = MySQLConnections.getConnection();
                String sql ="select * from canteen,dishstar where canteen.dishnum = dishstar.dishnum";
                if (con!=null) {

                    stmt = con.prepareStatement(sql);
                    // 关闭事务自动提交 ,这一行必须加上
                    con.setAutoCommit(false);
                    ResultSet rs = stmt.executeQuery();//创建数据对象

                    while (rs.next()) {
                        HashMap<String, String> item = new HashMap<String, String>();
//                        int id = rs.getInt(rs.findColumn("id"));
                        int imgid = rs.getInt(rs.findColumn("id"));
                        float score = rs.getFloat(rs.findColumn("star"));
                        String name = rs.getString(rs.findColumn("dishname"));
                        float price = rs.getFloat(rs.findColumn("price"));
                        String comments = rs.getString(rs.findColumn("dishcontent"));
                        //String batching = rs.getString(rs.findColumn("batching"));
                        item.put("imgid",String.valueOf(imgid));
                        item.put("score", String.valueOf(score));
                        item.put("name", name);
                        item.put("price", String.valueOf(price));
                        item.put("comments",comments);
                        //item.put("batching",batching);
                        listItems.add(item);

                    }
                    con.commit();
                    rs.close();
                    stmt.close();

                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            Message msg = handler.obtainMessage();
            msg.obj = listItems;
            msg.what = 1;
            handler.sendMessage(msg);
        }
}