package com.hi1122.jeonhyein_reactive_test;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.JsonArray;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    ArrayList<Item> items=new ArrayList<>();
    Adapter adapter;
    RecyclerView recyclerView;
    TextView tv;

//    String json=null;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tv=findViewById(R.id.tv);


        recyclerView=findViewById(R.id.recyclerview);
        adapter=new Adapter(this,items);
        recyclerView.setAdapter(adapter);



//        items.add(new Item(null,"hah","hahaha"));

        new Thread(){
            @Override
            public void run() {
                String serverurl="https://yts.mx/api/v2/list_movies.json";



                try {
                    URL url=new URL(serverurl);
                    InputStream is=url.openStream();
                    InputStreamReader isr=new InputStreamReader(is);

                    Gson gson=new Gson();
                    final Item item=gson.fromJson(isr,Item.class);

                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            Log.i("tag",item.title);
                            tv.setText(item.title+","+item.rating);
                        }
                    });

                } catch (MalformedURLException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }

            }
        }.start();





    }
}