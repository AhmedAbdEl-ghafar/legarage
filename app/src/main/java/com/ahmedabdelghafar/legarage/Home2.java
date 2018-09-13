package com.ahmedabdelghafar.legarage;

import android.Manifest;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.app.ActivityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.text.SpannableString;
import android.text.style.TextAppearanceSpan;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.github.siyamed.shapeimageview.CircularImageView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;

public class Home2 extends AppCompatActivity {
    public RecyclerView recyclerView;
    public ArrayList<Countries> countries = new ArrayList<Countries>();
    public List_Adapter_acution_now3 adapter;
    Download_data download_data;
    TextView textView4,textwelcome,textview456,lagarge_name;
    CircularImageView imageView;
    String DEFAULT = "";
    Toolbar toolbar;
    private DrawerLayout mDrawerLayout;
    private ActionBarDrawerToggle mtoggle;
   public ProgressDialog pd ;
    public SwipeRefreshLayout swipeContainer;
    int counttttt = 0;
    int total_item = 0;
    int loop_item = 5;
    JSONArray data_array;
    private RecyclerView.LayoutManager mLayoutMagager;



    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home2);

        //recyclerView = (RecyclerView) findViewById(R.id.acution_open2);

        //list = new ArrayList<>();
        //mLayoutMagager = new LinearLayoutManager(this);
        //recyclerView.setLayoutManager(mLayoutMagager);
        //AndroidNetworking.initialize(getApplicationContext());
        //Fresco.initialize(this);

        pd = new ProgressDialog(Home2.this);
        pd.setMessage("Please Wait To Load Data");
        pd.setIndeterminate(true);
        pd.setProgress(0);
        pd.show();

//        AndroidNetworking.get("http://197.51.253.242:3000/api/showacutions")
//                .build()
//                .getAsObjectList(ListAdapterItem.class, new ParsedRequestListener<List<Countries>>() {
//                    @Override
//                    public void onResponse(List<Countries> response) {
//                        Toast.makeText(Home2.this, "ok", Toast.LENGTH_SHORT).show();
//                        //Toast.makeText(Home2.this, response.toString(), Toast.LENGTH_SHORT).show();
//                        //adapter = new ListAdapterItem(response, Home2.this);
//                        recyclerView.setAdapter(adapter);
//                    }
//
//                    @Override
//                    public void onError(ANError error) {
//                        // handle error
//                        Toast.makeText(Home2.this, error.getErrorDetail(), Toast.LENGTH_SHORT).show();
//                    }
//                });
//    }
//                    @Override
//                    public void onResponse(List<acution_now_list> list) {
//                        recyclerView.setLayoutManager(mLayoutMagager);
//                        Toast.makeText(Home2.this, list.size(), Toast.LENGTH_LONG).show();
//                    }
//
//                    @Override
//                    public void onError(ANError anError) {
//                        Toast.makeText(Home2.this, anError.getErrorBody(), Toast.LENGTH_LONG).show();
//                        Log.d("ssss:", anError.getErrorBody());
//                    }
//                });
//    }
        recyclerView = (RecyclerView) findViewById(R.id.acution_open);
        adapter = new List_Adapter_acution_now3(this);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setNestedScrollingEnabled(false);
        recyclerView.setAdapter(adapter);

        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {

            public void onScrolled(RecyclerView recyclerView,int dx,int dy){
                if (!recyclerView.canScrollVertically(-1)) {

                } else if (!recyclerView.canScrollVertically(1)) {

                    for (int i = ((loop_item * counttttt) + 1); i <= ((loop_item * counttttt) + 5) ; i++)
                    {
                        if (total_item == data_array.length()) {
                            Toast.makeText(Home2.this, "Complated", Toast.LENGTH_LONG).show();
                            break;
                        } else {
                            try {
                                JSONObject obj = new JSONObject(data_array.get(i).toString());
                                Countries add = new Countries();
                                add.id_order = obj.getString("id_order");
                                add.discreption = obj.getString("discreption");
                                add.url = obj.getString("url");
                                add.date_of_close = obj.getString("date_of_close");
                                add.imagessflog = obj.getString("imagess");
                                countries.add(add);
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }

                            total_item++;

                        }
                    }
                  counttttt = counttttt +  1;
                   adapter.notifyDataSetChanged();
                    if (total_item != data_array.length()) {
                        Toast.makeText(Home2.this, "Scroll Down To View More Item", Toast.LENGTH_SHORT).show();
                    }
                }
                if (dy < 0) {
                } else if (dy > 0) {
                }

            }
        });

        toolbar = (Toolbar) findViewById(R.id.toolbarss);
        setSupportActionBar(toolbar);

        mDrawerLayout = (DrawerLayout)findViewById(R.id.drawerLayout);
        mtoggle = new ActionBarDrawerToggle(this,mDrawerLayout,R.string.open,R.string.close);
        mDrawerLayout.setDrawerListener(mtoggle);


        mDrawerLayout.addDrawerListener(mtoggle);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        mtoggle.syncState();

        SharedPreferences sharedPreferences = getSharedPreferences("Data", Context.MODE_PRIVATE);
        String facebookImage = "https://graph.facebook.com/"+sharedPreferences.getString("getId_string", DEFAULT)+"/picture?type=large";

        final NavigationView navigationView = (NavigationView) findViewById(R.id.navigation_xx);
        View headerView = navigationView.inflateHeaderView(R.layout.header_toolbar);

        Menu menu = navigationView.getMenu();


        MenuItem mene_item3= menu.findItem(R.id.menu3);
        SpannableString s3 = new SpannableString(mene_item3.toString());
        s3.setSpan(new TextAppearanceSpan(this, R.style.TextAppearance44), 0, s3.length(), 0);
        mene_item3.setTitle(s3);


        MenuItem mene_item7= menu.findItem(R.id.menu7);
        SpannableString s7 = new SpannableString(mene_item7.toString());
        s7.setSpan(new TextAppearanceSpan(this, R.style.TextAppearance44), 0, s7.length(), 0);
        mene_item7.setTitle(s7);

        MenuItem mene_item8= menu.findItem(R.id.menu8);
        SpannableString s8 = new SpannableString(mene_item8.toString());
        s8.setSpan(new TextAppearanceSpan(this, R.style.TextAppearance44), 0, s8.length(), 0);
        mene_item8.setTitle(s8);

        MenuItem mene_item9= menu.findItem(R.id.menu9);
        SpannableString s9 = new SpannableString(mene_item9.toString());
        s9.setSpan(new TextAppearanceSpan(this, R.style.TextAppearance44), 0, s9.length(), 0);
        mene_item9.setTitle(s9);




        textView4 = (TextView)headerView.findViewById(R.id.username);
        textwelcome = (TextView)headerView.findViewById(R.id.welcomess);
        textwelcome.setText("مرحباً");

        imageView = (CircularImageView) headerView.findViewById(R.id.circular_image);

        Glide.with(getApplicationContext()).load(facebookImage).into(imageView);
        textView4.setText(sharedPreferences.getString("getName_string", DEFAULT));

        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {

            @Override
            public boolean onNavigationItemSelected(MenuItem item) {


                if (item.isChecked()) {
                    item.setChecked(false);

                } else {
                    item.setChecked(true);
                }


                mDrawerLayout.closeDrawers();





                switch (item.getItemId()) {




                    case R.id.menu2:

                        Intent isw23 = new Intent(Home2.this, reciving_notification.class);
                        startActivity(isw23);
                        break;

                    case R.id.menu3:

                        Toast.makeText(Home2.this, "هذه الصفحة تحت التطوير", Toast.LENGTH_LONG).show();
                        break;

                    case R.id.menu4:

                        Intent i = new Intent(Intent.ACTION_CALL);

                        i.setData(Uri.parse("tel:01008587700"));


                        if (ActivityCompat.checkSelfPermission(Home2.this, android.Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
                            Toast.makeText(Home2.this,"Please grant the  permission to call",Toast.LENGTH_SHORT).show();
                            requestPermission();
                        }
                        else {
                            startActivity(i);
                        }


                        break;

                    case R.id.menu5:
                        Intent is = new Intent(Home2.this, menu_m.class);
                        startActivity(is);
                        break;

                    case R.id.menu6:
                        Intent iss = new Intent(Home2.this, menu_parchases.class);
                        startActivity(iss);
                       break;

                    case R.id.menu7:
                        Toast.makeText(Home2.this, "هذه الصفحة تحت التطوير", Toast.LENGTH_LONG).show();
                        break;

                    case R.id.menu8:
                        Toast.makeText(Home2.this, "هذه الصفحة تحت التطوير", Toast.LENGTH_LONG).show();
                        break;

                    case R.id.menu9:
                        Toast.makeText(Home2.this, "هذه الصفحة تحت التطوير", Toast.LENGTH_LONG).show();
                        break;

                    case R.id.menu10:
                        try {
                            String packageName = getApplicationContext().getPackageName();
                            Runtime runtime = Runtime.getRuntime();
                            runtime.exec("pm clear "+packageName);
                            Intent iswe = new Intent(Home2.this, MainActivity.class);
                            startActivity(iswe);
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                        Toast.makeText(Home2.this, "برجاء تشغيل البرنامج مرة اخري", Toast.LENGTH_LONG).show();
                        break;

                    default:
                        break;
                }




                return true;
            }


        });


        swipeContainer = (SwipeRefreshLayout) findViewById(R.id.swiperefresh);
        swipeContainer.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                Download_data download_data = new Download_data((Download_data.download_complete) Home2.this);
                download_data.download_data_from_link("http://197.51.253.242:3000/api/showacutions");
                swipeContainer.setRefreshing(false);

            }

        });
        swipeContainer.setColorSchemeResources(android.R.color.holo_blue_bright,
                android.R.color.holo_green_light,
                android.R.color.holo_orange_light,
                android.R.color.holo_red_light);

    }


    @Override
    public void onBackPressed() {
        Intent i = new Intent(Home2.this, MainActivity.class);
        startActivity(i);
    }
//    public boolean onOptionsItemSelected(MenuItem item) {
//
//        if(mtoggle.onOptionsItemSelected(item)){
//            switch (item.getItemId()) {
//
//                case R.id.menu1:
//                    Intent is = new Intent(Home2.this, menu_m.class);
//                    startActivity(is);
//                    break;
//
//                case R.id.menu2:
//                   break;
//
//                default:
//                    break;
//            }
//        }
//
//        return super.onOptionsItemSelected(item);
//    }
    private void requestPermission() {
        ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.CALL_PHONE},1);
    }

    public boolean isConnectedToServer() {
        try{
            URL myUrl = new URL("http://www.google.co.uk");
            URLConnection connection = myUrl.openConnection();
            connection.setConnectTimeout(2000);
            connection.connect();
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}

