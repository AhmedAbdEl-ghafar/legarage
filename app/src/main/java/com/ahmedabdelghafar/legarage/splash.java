package com.ahmedabdelghafar.legarage;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.os.Handler;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.TextView;

public class splash extends Activity {
    TextView textView;
//    ImageView imageView;
    Animation fromimage;
    private final int SPLASH_DISPLAY_LENGTH=3000;

    //declare bubble
//    private BubblesManager bubblesManager;
//    private NotificationBadge mBadge;
//    private int MY_PERMISSION = 1000;
    // end declare bubble

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

//        //splash
//        EasySplashScreen config = new EasySplashScreen(splash.this)
//                .withFullScreen()
//                .withTargetActivity(MainActivity.class)
//                .withSplashTimeOut(3000)
//                .withBackgroundResource(android.R.color.white)
//                .withHeaderText("Header")
//                .withFooterText("Copyright 2016")
//                .withBeforeLogoText("My cool company")
//                .withLogo(R.drawable.icon_legarage)
//                .withAfterLogoText("Some more details with custom font");
//
//
//        //set your own animations
//        myCustomTextViewAnimation(config.getFooterTextView());
//
//        //customize all TextViews
//        Typeface pacificoFont = Typeface.createFromAsset(getAssets(), "fonts/legaragefont.OTF");
//        config.getAfterLogoTextView().setTypeface(pacificoFont);
//
//
//        config.getHeaderTextView().setTextColor(Color.BLACK);
//        config.getFooterTextView().setTextColor(Color.BLACK);
//
//        //create the view
//        View easySplashScreenView = config.create();
//
//
//        //end splash
//        setContentView(easySplashScreenView);
        setContentView(R.layout.activity_splash);

//        imageView = (ImageView) findViewById(R.id.imagesplash);
        textView=(TextView)findViewById(R.id.textView6);
        Typeface face=Typeface.createFromAsset(this.getAssets(),"fonts/legaragefont.OTF");
        textView.setTypeface(face);
        fromimage = AnimationUtils.loadAnimation(this,R.anim.fromimage);
//        imageView.setAnimation(fromimage);
        textView.setAnimation((fromimage));


        new Handler().postDelayed(new Runnable(){
            @Override
            public void run() {
                Intent mainIntent = new Intent(splash.this,MainActivity.class);
                splash.this.startActivity(mainIntent);
                //splash.this.finish();
            }
        },SPLASH_DISPLAY_LENGTH);




        //bubble
//        initBubble();
//
//
//        if(Build.VERSION.SDK_INT >= 23)
//        {
//            if(!Settings.canDrawOverlays(splash.this))
//            {
//                Intent intent = new Intent(Settings.ACTION_MANAGE_OVERLAY_PERMISSION,
//                        Uri.parse("package:"+getPackageName()));
//                startActivityForResult(intent,MY_PERMISSION);
//            }
//        }
//        else {
//            Intent intent = new Intent(splash.this, Service.class);
//            startService(intent);
//        }
        //end bubble

    }

//    private void initBubble() {
//        bubblesManager = new BubblesManager.Builder(this)
//                .setTrashLayout(R.layout.bubble_remove)
//                .setInitializationCallback(new OnInitializedCallback() {
//                    @Override
//                    public void onInitialized() {
//                        addNewBubble();
//                    }
//                }).build();
//        bubblesManager.initialize();
//    }

//    private void addNewBubble() {
//        BubbleLayout bubbleView = (BubbleLayout) LayoutInflater.from(splash.this).inflate(R.layout.bubble_layout,null);
//        mBadge = (NotificationBadge)bubbleView.findViewById(R.id.count);
//        mBadge.setNumber(88);
//
//        bubbleView.setOnBubbleRemoveListener(new BubbleLayout.OnBubbleRemoveListener() {
//            @Override
//            public void onBubbleRemoved(BubbleLayout bubble) {
//                Toast.makeText(splash.this, "Removed", Toast.LENGTH_SHORT).show();
//            }
//        });
//        bubbleView.setOnBubbleClickListener(new BubbleLayout.OnBubbleClickListener() {
//            @Override
//            public void onBubbleClick(BubbleLayout bubble) {
//                Toast.makeText(splash.this, "Clicked", Toast.LENGTH_SHORT).show();
//                Intent i = new Intent(splash.this, bid.class);
//                startActivity(i);
//                finish();
//
//            }
//        });
//        bubbleView.setShouldStickToWall(true);
//        bubblesManager.addBubble(bubbleView,60,20);
//    }
//    private void myCustomTextViewAnimation(TextView tv){
//        Animation animation=new TranslateAnimation(0,0,480,0);
//        animation.setDuration(1200);
//        tv.startAnimation(animation);
//    }
//    @Override
//    protected void onDestroy() {
//        super.onDestroy();
//        bubblesManager.recycle();
//    }
}
