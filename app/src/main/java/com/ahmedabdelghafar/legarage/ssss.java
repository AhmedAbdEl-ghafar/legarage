package com.ahmedabdelghafar.legarage;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.ParsedRequestListener;

import java.util.List;

public class ssss extends AppCompatActivity {
    public RecyclerView recyclerView;
    public ListAdapterItem adapter;
    public ProgressDialog pd ;
    TextView textview ;
    private RecyclerView.LayoutManager mLayoutMagager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ssss);
        AndroidNetworking.initialize(getApplicationContext());
        mLayoutMagager = new LinearLayoutManager(this);
        recyclerView = (RecyclerView) findViewById(R.id.acution_open3);
        recyclerView.setLayoutManager(mLayoutMagager);

        AndroidNetworking.get("https://fonoapi.freshpixl.com/v1/getlatest")
                .addQueryParameter("token","ee9ee18e9af2d67f6d9ceb4e966533afa03ec57d088480c3")
                .build()
                .getAsObjectList(ListAdapterItem.class, new ParsedRequestListener<List<item_item>>() {
                    @Override
                    public void onResponse(List<item_item> responseahmed) {
                        Toast.makeText(ssss.this, "ok", Toast.LENGTH_SHORT).show();
                        //Toast.makeText(Home2.this, response.toString(), Toast.LENGTH_SHORT).show();
                       adapter = new ListAdapterItem(responseahmed, ssss.this);
                        recyclerView.setAdapter(adapter);
                        Log.d("ssssss", "userList size : " + responseahmed.size());
                        //Toast.makeText(ssss.this, response.size(), Toast.LENGTH_LONG).show();

                    }

                    @Override
                    public void onError(ANError error) {
                        // handle error
                        Toast.makeText(ssss.this, error.getErrorDetail(), Toast.LENGTH_SHORT).show();
                    }
                });
    }
    }

