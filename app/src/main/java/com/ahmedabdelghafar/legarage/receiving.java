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
import android.widget.ListView;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class receiving extends Activity implements Download_data.download_complete {
    public ListView listView;
    public ArrayList<Countries> countries = new ArrayList<Countries>();
    public List_Adapter_receiving adapter;
    public ProgressDialog pd ;
    public TextView textviewaddress;
    public SwipeRefreshLayout swipeContainer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_receiving);



        pd = new ProgressDialog(receiving.this);
        pd.setMessage("Please Wait To Load Data");
        pd.setIndeterminate(true);
        pd.setProgress(0);
        pd.show();

        textviewaddress = (TextView) findViewById(R.id.text_sales);
        textviewaddress.setText("قطع تم تسليمها للبيع");

        listView = (ListView) findViewById(R.id.ListView_receiving);
        adapter = new List_Adapter_receiving(this);
        listView.setAdapter(adapter);

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

                                                String selected = ((TextView) view.findViewById(R.id.menu_id)).getText().toString();

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

        swipeContainer = (SwipeRefreshLayout) findViewById(R.id.swiperefresh_receiving);
        swipeContainer.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                SharedPreferences sharedPreferences = getSharedPreferences("Data", Context.MODE_PRIVATE);
                Download_data download_data = new Download_data((Download_data.download_complete) receiving.this);
                download_data.download_data_from_link("http://197.51.253.242:3000/api/showdataonlocation/"+getIntent().getStringExtra("menu_id") +","+ sharedPreferences.getString("seller_id", "0"));
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
        Intent i = new Intent(receiving.this, menu_m.class);
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

//                DateTimeFormatter formatter = DateTimeFormat.forPattern("dd/MM/yyyy HH:mm:ss");
  //              DateTime dt = formatter.parseDateTime(string);



                //String uu = obj.getString("ddate");
                //SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm");
                //Date d = dateFormat.parse(uu.toString());



                add.ddate = obj.getString("ddate");
                add.item_name = obj.getString("item_name");
                add.quntity = obj.getString("quntity");
                add.price_start = obj.getString("price_start");
                add.price_sales = obj.getString("price_sales");
                add.price_Compensatory = obj.getString("price_Compensatory");
                add.display_expenses = obj.getString("display_expenses");
                add.commission = obj.getString("commission");
                add.dr_receiving = obj.getString("dr_receiving");
                add.value_plus = obj.getString("value_plus");


                countries.add(add);

            }

            adapter.notifyDataSetChanged();

        } catch (JSONException e) {
            e.printStackTrace();
        }

        pd.dismiss();


    }


}
