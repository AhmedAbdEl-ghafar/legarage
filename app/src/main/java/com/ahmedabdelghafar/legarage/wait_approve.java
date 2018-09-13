package com.ahmedabdelghafar.legarage;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class wait_approve extends Activity implements Download_data.download_complete {
    public ListView listView;
    public ArrayList<Countries> countries = new ArrayList<Countries>();
    public List_Adapter_wait_approve adapter;
    public ProgressDialog pd ;
    public TextView textviewaddress,id_order_ss;
    public SwipeRefreshLayout swipeContainer;
    Button button2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wait_approve);

        pd = new ProgressDialog(wait_approve.this);
        pd.setMessage("Please Wait To Load Data");
        pd.setIndeterminate(true);
        pd.setProgress(0);
        pd.show();

        textviewaddress = (TextView) findViewById(R.id.text_sales);
        textviewaddress.setText("في انتظار موافقتي");


        listView = (ListView) findViewById(R.id.ListView_wait_approve);
        adapter = new List_Adapter_wait_approve(this);
        listView.setAdapter(adapter);

       // button2 = (Button) findViewById(R.id.button2_wait_approve);
        //Animation animation = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.viewbised);
        //button2.startAnimation(animation);

        SharedPreferences sharedPreferences = getSharedPreferences("Data", Context.MODE_PRIVATE);

        Download_data download_data = new Download_data((Download_data.download_complete) this);
        download_data.download_data_from_link("http://197.51.253.242:3000/api/showdataonlocation/"+getIntent().getStringExtra("menu_id") +","+ sharedPreferences.getString("seller_id", "0"));

        //Toast.makeText(this, "Welcome " + getIntent().getStringExtra("dr_no") + " In Le Garage System", Toast.LENGTH_LONG).show();

        //dr_no = getIntent().getStringExtra("dr_no");


        listView.setOnItemClickListener(new AdapterView.OnItemClickListener(){

                                            @Override
                                            public void onItemClick(AdapterView<?> parent, View view, int position, long id){

                                                int itemPosition = position ;

                                                String itemValue = (String) listView.getItemAtPosition(position);

                                                //String selected = ((TextView) view.findViewById(R.id.menu_id)).getText().toString();

                                              /*  if (selected == "2"); {
                                                    Intent i = new Intent(menu_m.this, receiving.class);
                                                    //i.putExtra("dr_no",getIntent().getStringExtra("dr_no"));
                                                    i.putExtra("menu_id",selected.toString());
                                                    //i.putExtra("G_Code",selected.toString());
                                                    startActivity(i);

                                                }
*/



                                            }


                                        }
        );
        swipeContainer = (SwipeRefreshLayout) findViewById(R.id.swiperefresh_revision);
        swipeContainer.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                SharedPreferences sharedPreferences = getSharedPreferences("Data", Context.MODE_PRIVATE);
                Download_data download_data = new Download_data((Download_data.download_complete) wait_approve.this);
                download_data.download_data_from_link("http://197.51.253.242:3000/api/showdataonlocation/"+getIntent().getStringExtra("menu_id") +","+ sharedPreferences.getString("seller_id", "0"));
                swipeContainer.setRefreshing(false);

            }

        });
        swipeContainer.setColorSchemeResources(android.R.color.holo_blue_bright,
                android.R.color.holo_green_light,
                android.R.color.holo_orange_light,
                android.R.color.holo_red_light);
    }

    public void go_Refresh(View view) {

        SharedPreferences sharedPreferences = getSharedPreferences("Data", Context.MODE_PRIVATE);
        id_order_ss = (TextView) findViewById(R.id.id_order_wait);

        Download_data download_data = new Download_data((Download_data.download_complete) this);
        download_data.download_data_from_link("http://197.51.253.242:3000/api/waitapprove/"+id_order_ss.getText()+",8,"+sharedPreferences.getString("seller_id", "0")+",4,ok");
        Toast.makeText(this, "تمت الموافقة بنجاح علي عرض القطعة", Toast.LENGTH_LONG).show();
        //button2.setClickable(false);
        //button2.setEnabled(false);
        pd = new ProgressDialog(wait_approve.this);
        pd.setMessage("Please Wait To Load Data");
        pd.setIndeterminate(true);
        pd.setProgress(0);
        pd.show();
        download_data.download_data_from_link("http://197.51.253.242:3000/api/showdataonlocation/"+getIntent().getStringExtra("menu_id") +","+ sharedPreferences.getString("seller_id", "0"));

    }


    @Override
    public void onBackPressed() {
        Intent i = new Intent(wait_approve.this, menu_m.class);
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
                add.id_order = obj.getString("id_order");
                add.ddate = obj.getString("ddate");
                add.discreption = obj.getString("discreption");
                add.detailed_description = obj.getString("detailed_description");
                add.main_category_name = obj.getString("main_category_name");
                add.sub_category_name = obj.getString("sub_category_name");
                add.weight_st = obj.getString("weight_st");
                add.imagessflog = obj.getString("imagess");
                //add.start_price = obj.getString("start_price");
                //add.end_price = obj.getString("end_price");
                // add.dr_receiving = obj.getString("dr_receiving");
                //add.value_plus = obj.getString("value_plus");


                countries.add(add);

            }

            adapter.notifyDataSetChanged();

        } catch (JSONException e) {
            e.printStackTrace();
        }

        pd.dismiss();


    }


}

