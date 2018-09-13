package com.ahmedabdelghafar.legarage;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.SpannableString;
import android.text.style.TextAppearanceSpan;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.github.siyamed.shapeimageview.CircularImageView;

/**
 * Created by orcl on 22/08/2017.
 */

public class BaseActivity_drawer extends AppCompatActivity {
    Toolbar toolbar;
    private DrawerLayout mDrawerLayout;
    private ActionBarDrawerToggle mtoggle;
    TextView textView4,textwelcome;
    CircularImageView imageView;
    String DEFAULT = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    protected boolean useToolbar() {
        return true;
    }


    @Override
    public void setContentView(int layoutResID) {

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

        final NavigationView navigationView = (NavigationView) findViewById(R.id.navigation_xx_500);
        View headerView = navigationView.inflateHeaderView(R.layout.header_toolbar);
        //navigationView.getMenu().findItem(menu1).setTextColor(Color.BLUE);

        Menu menu = navigationView.getMenu();

        MenuItem mene_item2= menu.findItem(R.id.menu2);
        SpannableString s2 = new SpannableString(mene_item2.toString());
        s2.setSpan(new TextAppearanceSpan(this, R.style.TextAppearance44), 0, s2.length(), 0);
        mene_item2.setTitle(s2);

        MenuItem mene_item3= menu.findItem(R.id.menu3);
        SpannableString s3 = new SpannableString(mene_item3.toString());
        s3.setSpan(new TextAppearanceSpan(this, R.style.TextAppearance44), 0, s3.length(), 0);
        mene_item3.setTitle(s3);

        MenuItem mene_item6= menu.findItem(R.id.menu6);
        SpannableString s6 = new SpannableString(mene_item6.toString());
        s6.setSpan(new TextAppearanceSpan(this, R.style.TextAppearance44), 0, s6.length(), 0);
        mene_item6.setTitle(s6);

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

        SharedPreferences sharedPreferences = getSharedPreferences("Data", Context.MODE_PRIVATE);
        String facebookImage = "https://graph.facebook.com/"+sharedPreferences.getString("getId_string", DEFAULT)+"/picture?type=large";

        Glide.with(getApplicationContext()).load(facebookImage).into(imageView);
        textView4.setText(sharedPreferences.getString("getName_string", DEFAULT));
        //Toast.makeText(Home.this, "test 1", Toast.LENGTH_LONG).show();


        //mainNavigationMenu_ = (NavigationView) findViewById(R.id.navigation_xx);



    }

}
