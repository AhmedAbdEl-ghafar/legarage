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
import android.support.v7.widget.Toolbar;
import android.text.SpannableString;
import android.text.style.TextAppearanceSpan;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.ahmedabdelghafar.legarage.Download_data.download_complete;
import com.bumptech.glide.Glide;
import com.github.siyamed.shapeimageview.CircularImageView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;

public class menu_m extends AppCompatActivity implements download_complete{
    public ListView listView;
    public ArrayList<Countries> countries = new ArrayList<Countries>();
    public List_Adapter_menu adapter;
    public ProgressDialog pd ;

    TextView textView4,textwelcome;
    CircularImageView imageView;
    String DEFAULT = "";
    Toolbar toolbar;
    private DrawerLayout mDrawerLayout;
    private ActionBarDrawerToggle mtoggle;
    public SwipeRefreshLayout swipeContainer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_m);

        toolbar = (Toolbar) findViewById(R.id.toolbarss_sales);
        setSupportActionBar(toolbar);

        mDrawerLayout = (DrawerLayout)findViewById(R.id.drawerLayout_2);
        mtoggle = new ActionBarDrawerToggle(this,mDrawerLayout,R.string.open,R.string.close);
        mDrawerLayout.setDrawerListener(mtoggle);


        mDrawerLayout.addDrawerListener(mtoggle);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        //getSupportActionBar().setHomeAsUpIndicator(R.drawable.icon_legarage);
        mtoggle.syncState();

        SharedPreferences sharedPreferences = getSharedPreferences("Data", Context.MODE_PRIVATE);
        String facebookImage = "https://graph.facebook.com/"+sharedPreferences.getString("getId_string", DEFAULT)+"/picture?type=large";
        final NavigationView navigationView = (NavigationView) findViewById(R.id.navigation_xx2);
        View headerView = navigationView.inflateHeaderView(R.layout.header_toolbar);
        navigationView.bringToFront();

        Menu menu = navigationView.getMenu();

        //MenuItem mene_item2= menu.findItem(R.id.menu2);
        //SpannableString s2 = new SpannableString(mene_item2.toString());
        //s2.setSpan(new TextAppearanceSpan(this, R.style.TextAppearance44), 0, s2.length(), 0);
        //mene_item2.setTitle(s2);

        MenuItem mene_item3= menu.findItem(R.id.menu3);
        SpannableString s3 = new SpannableString(mene_item3.toString());
        s3.setSpan(new TextAppearanceSpan(this, R.style.TextAppearance44), 0, s3.length(), 0);
        mene_item3.setTitle(s3);

       /* MenuItem mene_item6= menu.findItem(R.id.menu6);
        SpannableString s6 = new SpannableString(mene_item6.toString());
        s6.setSpan(new TextAppearanceSpan(this, R.style.TextAppearance44), 0, s6.length(), 0);
        mene_item6.setTitle(s6);
*/
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



        pd = new ProgressDialog(menu_m.this);
        pd.setMessage("Please Wait To Load Data");
        pd.setIndeterminate(true);
        pd.setProgress(0);
        pd.show();

        listView = (ListView) findViewById(R.id.ListView_menu);
        adapter = new List_Adapter_menu(this);
        listView.setAdapter(adapter);




        //Toast.makeText(menu_m.this, sharedPreferences.getString("seller_id", "0"), Toast.LENGTH_LONG).show();

        Download_data download_data = new Download_data((Download_data.download_complete) this);
        download_data.download_data_from_link("http://197.51.253.242:3000/api/menu_app/"+sharedPreferences.getString("seller_id", "0"));

        //Toast.makeText(this, "Welcome " + getIntent().getStringExtra("dr_no") + " In Le Garage System", Toast.LENGTH_LONG).show();

        //dr_no = getIntent().getStringExtra("dr_no");

        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {

            @Override
            public boolean onNavigationItemSelected(MenuItem item) {


                if (item.isChecked()) {
                    item.setChecked(false);
                    //Toast.makeText(menu_m.this, "محاولة 1", Toast.LENGTH_LONG).show();

                } else {
                    item.setChecked(true);
                    //Toast.makeText(menu_m.this, "محاولة 2", Toast.LENGTH_LONG).show();
                }

                //Closing drawer on item click
                mDrawerLayout.closeDrawers();





                switch (item.getItemId()) {

                    case R.id.menu1:
                         Intent isw = new Intent(menu_m.this, Home.class);
                         startActivity(isw);
                        break;

                    case R.id.menu2:
                        Intent isw23 = new Intent(menu_m.this, reciving_notification.class);
                        startActivity(isw23);
                        break;

                    case R.id.menu3:
                        // Intent is = new Intent(MainActivity.this, Home.class);
                        //startActivity(is);
                        Toast.makeText(menu_m.this, "هذه الصفحة تحت التطوير", Toast.LENGTH_LONG).show();
                        break;

                    case R.id.menu4:
                        // Intent is = new Intent(MainActivity.this, Home.class);
                        //startActivity(is);
                        //Toast.makeText(menu_m.this, "هذه الصفحة تحت التطوير", Toast.LENGTH_LONG).show();
                        Intent i = new Intent(Intent.ACTION_CALL);
                        //sNum = numTxt.getText().toString();
                        //if(sNum.trim().isEmpty()){
                        i.setData(Uri.parse("tel:01008587700"));
                        //}

                        if (ActivityCompat.checkSelfPermission(menu_m.this, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
                            Toast.makeText(menu_m.this,"Please grant the  permission to call",Toast.LENGTH_SHORT).show();
                            requestPermission();
                        }
                        else {
                            startActivity(i);
                        }
                        break;




                     case R.id.menu6:
                        // Intent is = new Intent(MainActivity.this, Home.class);
                        //startActivity(is);
                         Intent iss = new Intent(menu_m.this, menu_parchases.class);
                         startActivity(iss);
                        break;

                    case R.id.menu7:
                        // Intent is = new Intent(MainActivity.this, Home.class);
                        //startActivity(is);
                        Toast.makeText(menu_m.this, "هذه الصفحة تحت التطوير", Toast.LENGTH_LONG).show();
                        break;

                    case R.id.menu8:
                        // Intent is = new Intent(MainActivity.this, Home.class);
                        //startActivity(is);
                        Toast.makeText(menu_m.this, "هذه الصفحة تحت التطوير", Toast.LENGTH_LONG).show();
                        break;

                    case R.id.menu9:
                        // Intent is = new Intent(MainActivity.this, Home.class);
                        //startActivity(is);
                        Toast.makeText(menu_m.this, "هذه الصفحة تحت التطوير", Toast.LENGTH_LONG).show();
                        break;

                    case R.id.menu10:
                        // Intent is = new Intent(MainActivity.this, Home.class);
                        //startActivity(is);
                        try {
                            String packageName = getApplicationContext().getPackageName();
                            Runtime runtime = Runtime.getRuntime();
                            runtime.exec("pm clear "+packageName);
                            Intent iswe = new Intent(menu_m.this, MainActivity.class);
                            startActivity(iswe);
                            //Intent ises = new Intent(menu_m.this, MainActivity.class);
                            //ises.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP|Intent.FLAG_ACTIVITY_NEW_TASK);
                            //startActivity(ises);
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                        Toast.makeText(menu_m.this, "برجاء تشغيل البرنامج مرة اخري", Toast.LENGTH_LONG).show();
                        break;

                    default:
                        break;
                }




                return true;
            }


        });


        listView.setOnItemClickListener(new AdapterView.OnItemClickListener(){

                                            @Override
                                            public void onItemClick(AdapterView<?> parent, View view, int position, long id){

                                                int itemPosition = position ;

                                                String itemValue = (String) listView.getItemAtPosition(position);
                                                // String itemText = (String) listView.getSelectedItem("category_id"));

                                                String selected = ((TextView) view.findViewById(R.id.menu_id)).getText().toString();
                                                String sum_sum = ((TextView) view.findViewById(R.id.menu_sum)).getText().toString();
                                                //Toast.makeText(getApplicationContext(),"Position :" + itemPosition + " ListItem : " + selected , Toast.LENGTH_LONG).show();

                                                //Toast.makeText(menu_m.this, selected.toString(), Toast.LENGTH_LONG).show();
                                                //Toast.makeText(menu_m.this, sum_sum.toString(), Toast.LENGTH_LONG).show();
                                                switch (selected.toString()) {
                                                    case "2":
                                                        switch (sum_sum.toString()) {
                                                            case "0":
                                                                Toast.makeText(menu_m.this, "لا توجد بيانات لكي يتم عرضها", Toast.LENGTH_LONG).show();
                                                                break;
                                                            default:
                                                                Intent i = new Intent(menu_m.this, receiving.class);
                                                                ////// //i.putExtra("dr_no",getIntent().getStringExtra("dr_no"));
                                                                i.putExtra("menu_id",selected.toString());
                                                                /////////i.putExtra("G_Code",selected.toString());
                                                                startActivity(i);
                                                        }
                                                        break;
                                                    case "4":
                                                        switch (sum_sum.toString()) {
                                                            case "0":
                                                                Toast.makeText(menu_m.this, "لا توجد بيانات لكي يتم عرضها", Toast.LENGTH_LONG).show();
                                                                break;
                                                            default:
                                                                //Toast.makeText(menu_m.this, "4", Toast.LENGTH_LONG).show();
                                                                Intent i2 = new Intent(menu_m.this, discreption.class);
                                                                ////// //i.putExtra("dr_no",getIntent().getStringExtra("dr_no"));
                                                                i2.putExtra("menu_id", selected.toString());
                                                                /////////i.putExtra("G_Code",selected.toString());
                                                                startActivity(i2);
                                                        }
                                                        break;
                                                    case "6":
                                                        switch (sum_sum.toString()) {
                                                            case "0":
                                                                Toast.makeText(menu_m.this, "لا توجد بيانات لكي يتم عرضها", Toast.LENGTH_LONG).show();
                                                                break;
                                                            default:
                                                                //Toast.makeText(menu_m.this, "4", Toast.LENGTH_LONG).show();
                                                                Intent i2 = new Intent(menu_m.this, onphoto.class);
                                                                ////// //i.putExtra("dr_no",getIntent().getStringExtra("dr_no"));
                                                                i2.putExtra("menu_id", selected.toString());
                                                                /////////i.putExtra("G_Code",selected.toString());
                                                                startActivity(i2);
                                                        }
                                                        break;
                                                    case "8":
                                                        switch (sum_sum.toString()) {
                                                            case "0":
                                                                Toast.makeText(menu_m.this, "لا توجد بيانات لكي يتم عرضها", Toast.LENGTH_LONG).show();
                                                                break;
                                                            default:
                                                                //Toast.makeText(menu_m.this, "4", Toast.LENGTH_LONG).show();
                                                                Intent i2 = new Intent(menu_m.this, revision.class);
                                                                ////// //i.putExtra("dr_no",getIntent().getStringExtra("dr_no"));
                                                                i2.putExtra("menu_id", selected.toString());
                                                                /////////i.putExtra("G_Code",selected.toString());
                                                                startActivity(i2);
                                                        }
                                                        break;
                                                    case "10":
                                                        switch (sum_sum.toString()) {
                                                            case "0":
                                                                Toast.makeText(menu_m.this, "لا توجد بيانات لكي يتم عرضها", Toast.LENGTH_LONG).show();
                                                                break;
                                                            default:
                                                                //Toast.makeText(menu_m.this, "4", Toast.LENGTH_LONG).show();
                                                                Intent i2 = new Intent(menu_m.this, wait_approve.class);
                                                                ////// //i.putExtra("dr_no",getIntent().getStringExtra("dr_no"));
                                                                i2.putExtra("menu_id", selected.toString());
                                                                /////////i.putExtra("G_Code",selected.toString());
                                                                startActivity(i2);
                                                        }
                                                        break;
                                                    case "12":
                                                        switch (sum_sum.toString()) {
                                                            case "0":
                                                                Toast.makeText(menu_m.this, "لا توجد بيانات لكي يتم عرضها", Toast.LENGTH_LONG).show();
                                                                break;
                                                            default:
                                                                //Toast.makeText(menu_m.this, "4", Toast.LENGTH_LONG).show();
                                                                Intent i2 = new Intent(menu_m.this, readytoacution.class);
                                                                ////// //i.putExtra("dr_no",getIntent().getStringExtra("dr_no"));
                                                                i2.putExtra("menu_id", selected.toString());
                                                                /////////i.putExtra("G_Code",selected.toString());
                                                                startActivity(i2);
                                                        }
                                                        break;
                                                    case "14":
                                                        switch (sum_sum.toString()) {
                                                            case "0":
                                                                Toast.makeText(menu_m.this, "لا توجد بيانات لكي يتم عرضها", Toast.LENGTH_LONG).show();
                                                                break;
                                                            default:
                                                                //Toast.makeText(menu_m.this, "4", Toast.LENGTH_LONG).show();
                                                                Intent i2 = new Intent(menu_m.this, onacution.class);
                                                                ////// //i.putExtra("dr_no",getIntent().getStringExtra("dr_no"));
                                                                i2.putExtra("menu_id", selected.toString());
                                                                /////////i.putExtra("G_Code",selected.toString());
                                                                startActivity(i2);
                                                        }
                                                        break;
                                                    case "16":
                                                        switch (sum_sum.toString()) {
                                                            case "0":
                                                                Toast.makeText(menu_m.this, "لا توجد بيانات لكي يتم عرضها", Toast.LENGTH_LONG).show();
                                                                break;
                                                            default:
                                                                //Toast.makeText(menu_m.this, "4", Toast.LENGTH_LONG).show();
                                                                Intent i2 = new Intent(menu_m.this, ondelivery.class);
                                                                ////// //i.putExtra("dr_no",getIntent().getStringExtra("dr_no"));
                                                                i2.putExtra("menu_id", selected.toString());
                                                                /////////i.putExtra("G_Code",selected.toString());
                                                                startActivity(i2);
                                                        }
                                                        break;

                                                    case "20":
                                                        switch (sum_sum.toString()) {
                                                            case "0":
                                                                Toast.makeText(menu_m.this, "لا توجد بيانات لكي يتم عرضها", Toast.LENGTH_LONG).show();
                                                                break;
                                                            default:
                                                                //Toast.makeText(menu_m.this, "4", Toast.LENGTH_LONG).show();
                                                                Intent i2 = new Intent(menu_m.this, dondelivery.class);
                                                                ////// //i.putExtra("dr_no",getIntent().getStringExtra("dr_no"));
                                                                i2.putExtra("menu_id", selected.toString());
                                                                /////////i.putExtra("G_Code",selected.toString());
                                                                startActivity(i2);
                                                        }
                                                        break;

                                                    case "21":
                                                        switch (sum_sum.toString()) {
                                                            case "0":
                                                                Toast.makeText(menu_m.this, "لا توجد بيانات لكي يتم عرضها", Toast.LENGTH_LONG).show();
                                                                break;
                                                            default:
                                                                //Toast.makeText(menu_m.this, "4", Toast.LENGTH_LONG).show();
                                                                Intent i2 = new Intent(menu_m.this, nodelivery.class);
                                                                ////// //i.putExtra("dr_no",getIntent().getStringExtra("dr_no"));
                                                                i2.putExtra("menu_id", selected.toString());
                                                                /////////i.putExtra("G_Code",selected.toString());
                                                                startActivity(i2);
                                                        }
                                                        break;

                                                    case "22":
                                                        switch (sum_sum.toString()) {
                                                            case "0":
                                                                Toast.makeText(menu_m.this, "لا توجد بيانات لكي يتم عرضها", Toast.LENGTH_LONG).show();
                                                                break;
                                                            default:
                                                                //Toast.makeText(menu_m.this, "4", Toast.LENGTH_LONG).show();
                                                                Intent i2 = new Intent(menu_m.this, avilabel.class);
                                                                ////// //i.putExtra("dr_no",getIntent().getStringExtra("dr_no"));
                                                                i2.putExtra("menu_id", selected.toString());
                                                                /////////i.putExtra("G_Code",selected.toString());
                                                                startActivity(i2);
                                                        }
                                                        break;

                                                    case "24":
                                                        switch (sum_sum.toString()) {
                                                            case "0":
                                                                Toast.makeText(menu_m.this, "لا توجد بيانات لكي يتم عرضها", Toast.LENGTH_LONG).show();
                                                                break;
                                                            default:
                                                                //Toast.makeText(menu_m.this, "4", Toast.LENGTH_LONG).show();
                                                                Intent i2 = new Intent(menu_m.this, endss.class);
                                                                ////// //i.putExtra("dr_no",getIntent().getStringExtra("dr_no"));
                                                                i2.putExtra("menu_id", selected.toString());
                                                                /////////i.putExtra("G_Code",selected.toString());
                                                                startActivity(i2);
                                                        }
                                                        break;

                                                    case "26":
                                                        switch (sum_sum.toString()) {
                                                            case "0":
                                                                Toast.makeText(menu_m.this, "لا توجد بيانات لكي يتم عرضها", Toast.LENGTH_LONG).show();
                                                                break;
                                                            default:
                                                                //Toast.makeText(menu_m.this, "4", Toast.LENGTH_LONG).show();
                                                                Intent i2 = new Intent(menu_m.this, return_seller.class);
                                                                ////// //i.putExtra("dr_no",getIntent().getStringExtra("dr_no"));
                                                                i2.putExtra("menu_id", selected.toString());
                                                                /////////i.putExtra("G_Code",selected.toString());
                                                                startActivity(i2);
                                                        }
                                                        break;
                                                }
/*                                                if (selected ="2") {
                                                    //if (sum_sum == "0"); {
                                                      //  Toast.makeText(menu_m.this, "لا توجد بيانات", Toast.LENGTH_LONG).show();
                                                    //}
                                                    Intent i = new Intent(menu_m.this, receiving.class);
                                                   ////// //i.putExtra("dr_no",getIntent().getStringExtra("dr_no"));
                                                    i.putExtra("menu_id",selected.toString());
                                                    /////////i.putExtra("G_Code",selected.toString());
                                                    startActivity(i);

                                                };
                                                if (selected.equals("4")); {
                                                    //if (sum_sum == "0"); {
                                                      //  Toast.makeText(menu_m.this, "لا توجد بيانات", Toast.LENGTH_LONG).show();
                                                    //}
                                                        Intent i = new Intent(menu_m.this, discreption.class);
                                                        ////// //i.putExtra("dr_no",getIntent().getStringExtra("dr_no"));
                                                        i.putExtra("menu_id", selected.toString());
                                                        /////////i.putExtra("G_Code",selected.toString());
                                                        startActivity(i);


                                                };

*/



                                            }


                                        }
        );
        swipeContainer = (SwipeRefreshLayout) findViewById(R.id.swiperefresh_menu_m);
        swipeContainer.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                SharedPreferences sharedPreferences = getSharedPreferences("Data", Context.MODE_PRIVATE);
                Download_data download_data = new Download_data((Download_data.download_complete) menu_m.this);
                download_data.download_data_from_link("http://197.51.253.242:3000/api/menu_app/"+sharedPreferences.getString("seller_id", "0"));
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
        Intent i = new Intent(menu_m.this, Home.class);
        //i.putExtra("dr_no",getIntent().getStringExtra("dr_no"));
        startActivity(i);
    }
    public void get_data(String data)
    {

        try {
            countries.clear();

            JSONArray data_array=new JSONArray(data);

            for (int i = 0 ; i < data_array.length() ; i++)
            {
                JSONObject obj=new JSONObject(data_array.get(i).toString());

                Countries add=new Countries();
                add.menu_id = obj.getString("menu_id");
                add.menu_name = obj.getString("menu_name");
                add.menu_sum = obj.getString("menu_sum");
               /* add.phone2 = obj.getString("phone2");
                add.governorate_name = obj.getString("governorate_name");
                add.area_name = obj.getString("area_name");
                add.address = obj.getString("address");
                add.country_name = obj.getString("country_name");
                add.cod = obj.getString("cod");
                add.price = obj.getString("price");
                add.total = obj.getString("total");
*/

                countries.add(add);

            }

            adapter.notifyDataSetChanged();

        } catch (JSONException e) {
            e.printStackTrace();
        }

        pd.dismiss();


    }

    public boolean onOptionsItemSelected(MenuItem item) {




        if(mtoggle.onOptionsItemSelected(item)){
            switch (item.getItemId()) {

                case R.id.menu5:
                    //Toast.makeText(Home.this, "test 4", Toast.LENGTH_LONG).show();
                    Intent is = new Intent(menu_m.this, menu_m.class);
                    startActivity(is);
                    break;

                case R.id.menu2:
                    // Intent is = new Intent(MainActivity.this, Home.class);
                    //startActivity(is);
                    Toast.makeText(menu_m.this, "test 4", Toast.LENGTH_LONG).show();
                    break;

                default:
                    break;
            }
        }

        return super.onOptionsItemSelected(item);
    }

    private void requestPermission() {
        ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.CALL_PHONE},1);
    }

}
