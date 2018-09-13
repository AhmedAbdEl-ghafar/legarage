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
import com.facebook.drawee.backends.pipeline.Fresco;
import com.github.siyamed.shapeimageview.CircularImageView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;

import static com.ahmedabdelghafar.legarage.R.id.menu1;

public class Home extends AppCompatActivity implements Download_data.download_complete{
    public RecyclerView recyclerView;
    public ArrayList<Countries> countries = new ArrayList<Countries>();
    public List_Adapter_acution_now2 adapter;
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
    private Uri filePath;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        Fresco.initialize(this);

        pd = new ProgressDialog(Home.this);
        pd.setMessage("Please Wait To Load Data");
        pd.setIndeterminate(true);
        pd.setProgress(0);
        pd.show();




        recyclerView = (RecyclerView) findViewById(R.id.acution_open);
        //listView.bringToFront();

        //adapter = new List_Adapter_acution_now2(this);
        adapter = new List_Adapter_acution_now2(this);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setNestedScrollingEnabled(false);
        recyclerView.setAdapter(adapter);
        //Start text

          recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {

            public void onScrolled(RecyclerView recyclerView,int dx,int dy){
                if (!recyclerView.canScrollVertically(-1)) {
                    //onScrolledToTop();
              } else if (!recyclerView.canScrollVertically(1)) {
                    //onScrolledToBottom();
                    //Toast.makeText(Home.this, "loading ...", Toast.LENGTH_SHORT).show();
                    //ILoadMore.onLoadMore();
//                    Download_data download_data = new Download_data((Download_data.download_complete) Home.this);
//                    download_data.download_data_from_link("http://197.51.253.242:3000/api/showacutions");
                    // start text

                    for (int i = ((loop_item * counttttt) + 1); i <= ((loop_item * counttttt) + 5) ; i++)
                    {
                        if (total_item == data_array.length()) {
                            Toast.makeText(Home.this, "Complated", Toast.LENGTH_LONG).show();
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
                        Toast.makeText(Home.this, "Scroll Down To View More Item", Toast.LENGTH_SHORT).show();
                   }
// else {
//                        Toast.makeText(Home.this, "Scroll Down To View Item", Toast.LENGTH_LONG).show();
//                        Toast.makeText(Home.this, "" + total_item, Toast.LENGTH_LONG).show();
//                    }
                    // end test

                }
                if (dy < 0) {
                    //onScrolledUp(dy);
                } else if (dy > 0) {
                    //onScrolledDown(dy);

                }

            }
        });
        //end text



//        toolbar.showOverflowMenu();
  //      setSupportActionBar(toolbar);


      //  if (isConnectedToServer()) {
      //      Toast.makeText(Home.this, "لا يمكن الاتصال بالخادم الان حاول مرة اخري بعد قليل", Toast.LENGTH_SHORT).show();
      //  }else {

//        }

        Download_data download_data = new Download_data((Download_data.download_complete) this);
        download_data.download_data_from_link("http://197.51.253.242:3000/api/showacutions");


        //mToolbar = FindViewById<SupportToolbar>(Resource.Id.toolbar);
        toolbar = (Toolbar) findViewById(R.id.toolbarss);
        setSupportActionBar(toolbar);

        mDrawerLayout = (DrawerLayout)findViewById(R.id.drawerLayout);
        mtoggle = new ActionBarDrawerToggle(this,mDrawerLayout,R.string.open,R.string.close);
        mDrawerLayout.setDrawerListener(mtoggle);


        mDrawerLayout.addDrawerListener(mtoggle);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        //getSupportActionBar().setHomeAsUpIndicator(R.drawable.icon_legarage);
        mtoggle.syncState();

        //textView4 = (TextView) findViewById(R.id.username);
        //imageView = (ImageView) findViewById(R.id.nav_header);
        //toolbar = (Toolbar) findViewById(R.id.toolbar);
        // يجيب الصورة يحطها مكان الشعار واسم المستخدم
        SharedPreferences sharedPreferences = getSharedPreferences("Data", Context.MODE_PRIVATE);
        String facebookImage = "https://graph.facebook.com/"+sharedPreferences.getString("getId_string", DEFAULT)+"/picture?type=large";

        //Glide.with(getApplicationContext()).load(facebookImage).into(imageView);
        //textView4.setText("hi "+sharedPreferences.getString("getName_string", DEFAULT));
        //النهاية
        //View hView =  navigationView.inflateHeaderView(R.layout.header_toolbar);
       //imageView = (CircularImageView)hView.findViewById(R.id.circular_image);
        //textView4 = (TextView)hView.findViewById(R.id.username);
        //imgvw .setImageResource();
        //tv.settext("new text");
        //NavigationView navigationView = (NavigationView) findViewById(R.id.navigation_xx);
        //View headerView = LayoutInflater.from(this).inflate(R.layout.header_toolbar, navigationView, false);
        //navigationView.addHeaderView(view);
        final NavigationView navigationView = (NavigationView) findViewById(R.id.navigation_xx);
        View headerView = navigationView.inflateHeaderView(R.layout.header_toolbar);
        //navigationView.getMenu().findItem(menu1).setTextColor(Color.BLUE);

        Menu menu = navigationView.getMenu();

        //MenuItem mene_item2= menu.findItem(R.id.menu2);
      //  SpannableString s2 = new SpannableString(mene_item2.toString());
     //   s2.setSpan(new TextAppearanceSpan(this, R.style.TextAppearance44), 0, s2.length(), 0);
//        mene_item2.setTitle(s2);

        MenuItem mene_item3= menu.findItem(R.id.menu3);
        SpannableString s3 = new SpannableString(mene_item3.toString());
        s3.setSpan(new TextAppearanceSpan(this, R.style.TextAppearance44), 0, s3.length(), 0);
        mene_item3.setTitle(s3);

        /*MenuItem mene_item6= menu.findItem(R.id.menu6);
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


        //if (tools != null && tools instanceof TextView) {
        //((TextView) tools).setTextColor(Color.BLUE);

        //}

        //SpannableString s = new SpannableString(tools.getTitle());
        //s.setSpan(new TextAppearanceSpan(this, R.style.com_facebook_auth_dialog), 0, s.length(), 0);
        //tools.setTitle(s);
        //navigationView.setNavigationItemSelectedListener(this);
        /*View view = findViewById(R.id.menu2);
        if (view != null && view instanceof TextView) {
            ((TextView) view).setTextColor(Color.BLUE);
        }
*/

        textView4 = (TextView)headerView.findViewById(R.id.username);
        textwelcome = (TextView)headerView.findViewById(R.id.welcomess);
        textwelcome.setText("مرحباً");


        //LinearLayout.LayoutParams toastTextLp = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        //LinearLayout.LayoutParams toastTextLp= new LinearLayout.LayoutParams(textwelcome.getLayoutParams());
        //toastTextLp.gravity(textwelcome.getGravity());
        //LinearLayout layout = new LinearLayout(this);
        //layout.setOrientation(LinearLayout.VERTICAL);
        //LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT,Gravity.CENTER_HORIZONTAL);
//       toastTextLp.setMargins(0, 500, 0, 0);
        //toastTextLp.topMargin = 500;
  //      textwelcome.setLayoutParams(toastTextLp);

        //ImageView iv = (ImageView) headerView.findViewById(R.id.nav_header);
        imageView = (CircularImageView) headerView.findViewById(R.id.circular_image);

        Glide.with(getApplicationContext()).load(facebookImage).into(imageView);
        textView4.setText(sharedPreferences.getString("getName_string", DEFAULT));
        //Toast.makeText(Home.this, "test 1", Toast.LENGTH_LONG).show();

//
//        if(sharedPreferences.getString("URL_Firbase_image", DEFAULT).equals("")) {
//            InputStream stream = null;
//
//            FirebaseStorage storage = FirebaseStorage.getInstance();
//            StorageReference storageRef = storage.getReference();
//            StorageReference mountainsRef = storageRef.child(sharedPreferences.getString("seller_id", "0") + ".jpg");
//
////                mountainsRef.getName().equals(mountainImagesRef.getName());
////            filePath = facebookImage;
////            UploadTask uploadTask = mountainsRef.putFile(new URI(facebookImage));
//            UploadTask uploadTask = mountainsRef.put
//            uploadTask.addOnFailureListener(new OnFailureListener() {
//                @Override
//                public void onFailure(@NonNull Exception exception) {
//                    // Handle unsuccessful uploads
//                }
//            }).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
//                @Override
////                public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
////                    // taskSnapshot.getMetadata() contains file metadata such as size, content-type, etc.
//                // ...
//                public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
//                    // taskSnapshot.getMetadata() contains file metadata such as size, content-type, and download URL.
//                    Uri downloadUrl = taskSnapshot.getDownloadUrl();
//
//                    SharedPreferences sharedPreferences = getSharedPreferences("Data", MODE_PRIVATE);
//                    SharedPreferences.Editor editor = sharedPreferences.edit();
//                    editor.putString("URL_Firbase_image", downloadUrl.toString());
//                    editor.commit();
//                }
//            });
//        }


//            imageView.setDrawingCacheEnabled(true);
//            imageView.buildDrawingCache();
//            Bitmap bitmap = imageView.getDrawingCache();
//            ByteArrayOutputStream baos = new ByteArrayOutputStream();
//            bitmap.compress(Bitmap.CompressFormat.JPEG, 100, baos);
//            byte[] data = baos.toByteArray();
//
//            InputStream stream = new FileInputStream(new File(facebookImage));
//            FirebaseStorage storage = FirebaseStorage.getInstance();
//            StorageReference storageRef = storage.getReference();
//            StorageReference mountainsRef = storageRef.child(sharedPreferences.getString("seller_id", "0") + ".jpg");
//
//            mountainsRef.getName().equals(mountainImagesRef.getName());
//            UploadTask uploadTask = mountainsRef.putStream(stream);
////            FirebaseStorage storage = FirebaseStorage.getInstance();
//            StorageReference storageRef = storage.getReference();
//            StorageReference mountainsRef = storageRef.child(sharedPreferences.getString("seller_id", "0") + ".jpg");
//            UploadTask uploadTask = mountainsRef.putBytes(data);
//            UploadTask uploadTask.addOnFailureListener(new OnFailureListener() {
//                @Override
//                public void onFailure(@NonNull Exception exception) {
//                    // Handle unsuccessful uploads
//                }
//            }).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
//                @Override
//                public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
//                    // taskSnapshot.getMetadata() contains file metadata such as size, content-type, and download URL.
//                    Uri downloadUrl = taskSnapshot.getDownloadUrl();
//
//                    SharedPreferences sharedPreferences = getSharedPreferences("Data", MODE_PRIVATE);
//                    SharedPreferences.Editor editor = sharedPreferences.edit();
//                    editor.putString("URL_Firbase_image", downloadUrl.toString());
//                    editor.commit();
//
//                }
//            });

        //mainNavigationMenu_ = (NavigationView) findViewById(R.id.navigation_xx);

        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {

            @Override
            public boolean onNavigationItemSelected(MenuItem item) {


                if (item.isChecked()) {
                    item.setChecked(false);

                } else {
                    item.setChecked(true);
                }

                //Closing drawer on item click
                mDrawerLayout.closeDrawers();





                switch (item.getItemId()) {




                    case R.id.menu2:
                        // Intent is = new Intent(MainActivity.this, Home.class);
                        //startActivity(is);
                        //Toast.makeText(Home.this, "هذه الصفحة تحت التطوير", Toast.LENGTH_LONG).show();
                        Intent isw23 = new Intent(Home.this, reciving_notification.class);
                        startActivity(isw23);
                        break;

                    case R.id.menu3:
                        // Intent is = new Intent(MainActivity.this, Home.class);
                        //startActivity(is);
                        Toast.makeText(Home.this, "هذه الصفحة تحت التطوير", Toast.LENGTH_LONG).show();
                        break;

                    case R.id.menu4:
                        // Intent is = new Intent(MainActivity.this, Home.class);
                        //startActivity(is);
                        //Toast.makeText(Home.this, "هذه الصفحة تحت التطوير", Toast.LENGTH_LONG).show();
                        Intent i = new Intent(Intent.ACTION_CALL);
                        //sNum = numTxt.getText().toString();
                        //if(sNum.trim().isEmpty()){
                            i.setData(Uri.parse("tel:01008587700"));
                        //}

                        if (ActivityCompat.checkSelfPermission(Home.this, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
                            Toast.makeText(Home.this,"Please grant the  permission to call",Toast.LENGTH_SHORT).show();
                            requestPermission();
                        }
                        else {
                            startActivity(i);
                        }


                        break;

                    case R.id.menu5:
                        Intent is = new Intent(Home.this, menu_m.class);
                        startActivity(is);
                        break;

                    case R.id.menu6:
                        Intent iss = new Intent(Home.this, menu_parchases.class);
                        startActivity(iss);
                        //Toast.makeText(Home.this, "هذه الصفحة تحت التطوير", Toast.LENGTH_LONG).show();
                        break;

                    case R.id.menu7:
                        // Intent is = new Intent(MainActivity.this, Home.class);
                        //startActivity(is);
                        Toast.makeText(Home.this, "هذه الصفحة تحت التطوير", Toast.LENGTH_LONG).show();
                        break;

                    case R.id.menu8:
                        // Intent is = new Intent(MainActivity.this, Home.class);
                        //startActivity(is);
                        Toast.makeText(Home.this, "هذه الصفحة تحت التطوير", Toast.LENGTH_LONG).show();
                        break;

                    case R.id.menu9:
                        // Intent is = new Intent(MainActivity.this, Home.class);
                        //startActivity(is);
                        Toast.makeText(Home.this, "هذه الصفحة تحت التطوير", Toast.LENGTH_LONG).show();
                        break;

                    case R.id.menu10:
                        try {
                            String packageName = getApplicationContext().getPackageName();
                            Runtime runtime = Runtime.getRuntime();
                            runtime.exec("pm clear "+packageName);
                            Intent iswe = new Intent(Home.this, MainActivity.class);
                            startActivity(iswe);
                            //Intent ises = new Intent(menu_m.this, MainActivity.class);
                            //ises.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP|Intent.FLAG_ACTIVITY_NEW_TASK);
                            //startActivity(ises);
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                        Toast.makeText(Home.this, "برجاء تشغيل البرنامج مرة اخري", Toast.LENGTH_LONG).show();
                        break;

                    default:
                        break;
                }




                return true;
            }


        });

        //Toast.makeText(Home.this, "test 1", Toast.LENGTH_LONG).show();

        // pop
//        listView.setOnItemClickListener(new AdapterView.OnItemClickListener(){
//
//                                            @Override
//                                            public void onItemClick(AdapterView<?> parent, View view, int position, long id){
//                                                 try {
//                                                     int itemPosition = position;
//
//                                                     String itemValue = (String) listView.getItemAtPosition(position);
//                                                     // String itemText = (String) listView.getSelectedItem("category_id"));
//
//                                                     String selected = ((TextView) view.findViewById(R.id.url)).getText().toString();
//                                                     //String sum_sum = ((TextView) view.findViewById(R.id.menu_sum)).getText().toString();
//                                                     //Toast.makeText(getApplicationContext(),"Position :" + itemPosition + " ListItem : " + selected , Toast.LENGTH_LONG).show();
//
//                                                     //Toast.makeText(menu_m.this, selected.toString(), Toast.LENGTH_LONG).show();
//                                                     //Toast.makeText(menu_m.this, sum_sum.toString(), Toast.LENGTH_LONG).show();
//                                                     //Intent i = new Intent(Home.this, selected);
//                                                     ////// //i.putExtra("dr_no",getIntent().getStringExtra("dr_no"));
//                                                     //i.putExtra("menu_id",selected.toString());
//                                                     Uri uri = Uri.parse(selected);
//                                                     Intent i = new Intent(Intent.ACTION_VIEW, uri);
//                                                     /////////i.putExtra("G_Code",selected.toString());
//                                                     startActivity(i);
//
//                                                 } catch (Exception e) {
//                                                     Toast.makeText(getApplicationContext(),"جاري العمل علي ربط المزاد علي الفيس بوك" , Toast.LENGTH_LONG).show();
//                                                 }
//
//
//
//
//                                            }
//
//
//                                        }
//        );
        // end pop
        swipeContainer = (SwipeRefreshLayout) findViewById(R.id.swiperefresh);
        swipeContainer.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                Download_data download_data = new Download_data((Download_data.download_complete) Home.this);
                download_data.download_data_from_link("http://197.51.253.242:3000/api/showacutions");
                swipeContainer.setRefreshing(false);

            }

        });
        swipeContainer.setColorSchemeResources(android.R.color.holo_blue_bright,
                android.R.color.holo_green_light,
                android.R.color.holo_orange_light,
                android.R.color.holo_red_light);

    }

//    public void initFirstData() {
//        countries = new ArrayList<>();
//        for (int i = 0; i < 5; i++) {
//            countries.add(new Countries());
//        }
//    }
/*
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        //getMenuInflater().inflate(R.menu.menu, menu);
        getMenuInflater().inflate(R.menu.menu, menu);
        return super.onCreateOptionsMenu(menu);

        // return true;
    }
*/

    @Override
    public void onBackPressed() {
        Intent i = new Intent(Home.this, MainActivity.class);
        //i.putExtra("dr_no",getIntent().getStringExtra("dr_no"));
        startActivity(i);
    }
    public boolean onOptionsItemSelected(MenuItem item) {

/*
        switch (item.getItemId()) {

            case R.id.menu1:
                Intent is = new Intent(Home.this, menu_m.class);
                startActivity(is);
                break;

            case R.id.menu2:
               // Intent is = new Intent(MainActivity.this, Home.class);
                //startActivity(is);
                break;

            default:
                break;
        }
        return super.onOptionsItemSelected(item);
        */

        //Toast.makeText(Home.this, "test 2", Toast.LENGTH_LONG).show();

        if(mtoggle.onOptionsItemSelected(item)){
            switch (item.getItemId()) {

                case menu1:
                    //Toast.makeText(Home.this, "test 4", Toast.LENGTH_LONG).show();
                    Intent is = new Intent(Home.this, menu_m.class);
                    startActivity(is);
                    break;

                case R.id.menu2:
                    // Intent is = new Intent(MainActivity.this, Home.class);
                    //startActivity(is);
                    break;

                default:
                    break;
            }
        }

        return super.onOptionsItemSelected(item);
    }

    public void get_data(String data)
    {

        //Toast.makeText(Home.this, "" + counttttt, Toast.LENGTH_LONG).show();
        try {
            //if (counttttt == 0 ) {
            countries.clear();
            total_item = 0;
            counttttt = 0;
            //}
            data_array=new JSONArray(data);

            for (int i = 0 ; i < 5 ; i++)
            {
                JSONObject obj=new JSONObject(data_array.get(i).toString());

                Countries add=new Countries();
                add.id_order = obj.getString("id_order");
                add.discreption = obj.getString("discreption");
                add.url = obj.getString("url");
                add.date_of_close = obj.getString("date_of_close");
                add.imagessflog = obj.getString("imagess");

                total_item ++;
                countries.add(add);

            }
            Toast.makeText(Home.this, "Scroll Down To View More Item", Toast.LENGTH_SHORT).show();
            adapter.notifyDataSetChanged();

        } catch (JSONException e) {
            e.printStackTrace();
        }
        counttttt = counttttt +  1;

        pd.dismiss();


    }
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
