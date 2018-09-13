package com.ahmedabdelghafar.legarage;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.Signature;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.ahmedabdelghafar.legarage.Download_data.download_complete;
import com.facebook.AccessToken;
import com.facebook.AccessTokenTracker;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.FacebookSdk;
import com.facebook.GraphRequest;
import com.facebook.GraphResponse;
import com.facebook.LoggingBehavior;
import com.facebook.Profile;
import com.facebook.ProfileTracker;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;
import com.google.firebase.FirebaseApp;
import com.google.firebase.iid.FirebaseInstanceId;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.io.OutputStream;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.SimpleDateFormat;
import java.util.Arrays;

import static android.util.Base64.encodeToString;

public class MainActivity extends AppCompatActivity implements download_complete {

    LoginButton loginButton;
    TextView textView5, textView, textView2 ,textView4;
    //ProfilePictureView profilePictureView;
    CallbackManager callbackManager;
    AccessTokenTracker accessTokenTracker;
    AccessToken accessToken;
    ProfileTracker profileTracker;
    Download_data download_data;
    String email, birthday;
    //SharedPreferences sp;
    String getId_string;
    String getFirstName_string;
    String getMiddleName_string;
    String getLastName_string;
    String getName_string;
    String toString_string;
    String hashkey;
    int hashCode_string;
    //ImageView imageView;
    String DEFAULT = "NA";
    String ssdd = "0";
    Button button2;
    String PROJECT_NUMBER="343452558486";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        FacebookSdk.sdkInitialize(getApplicationContext());
        setContentView(R.layout.activity_main);
        FirebaseApp.initializeApp(this);

        SharedPreferences sharedPreferences = getSharedPreferences("Data", Context.MODE_PRIVATE);

        if (!sharedPreferences.getString("seller_id", DEFAULT).equals("NA")) {
//            Toast.makeText(MainActivity.this, "حسابك مفعل" , Toast.LENGTH_LONG).show();
            //Intent intent = new Intent(this, Home.class);
            //intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            //finish();
            //startActivity(intent);
            Intent intent = new Intent(this, Home.class);
            finish();
            startActivity(intent);
        }

        // خاص بالكروت
        ViewPager viewPager = (ViewPager) findViewById(R.id.viewPager);

        CardFragmentPagerAdapter pagerAdapter = new CardFragmentPagerAdapter(getSupportFragmentManager(), dpToPixels(2, this));
        ShadowTransformer fragmentCardShadowTransformer = new ShadowTransformer(viewPager, pagerAdapter);
        fragmentCardShadowTransformer.enableScaling(true);

        viewPager.setAdapter(pagerAdapter);
        viewPager.setPageTransformer(false, fragmentCardShadowTransformer);
        viewPager.setOffscreenPageLimit(3);
        // نهاية خاص بالكروت



/*
// البداية
        loginButton = (LoginButton) findViewById(R.id.fb_login_bn);
        button2 = (Button) findViewById(R.id.button2);
        //button2.setVisibility(View.GONE);
        textView5 = (TextView) findViewById(R.id.textView5);
        textView = (TextView) findViewById(R.id.textView);
        textView2 = (TextView) findViewById(R.id.textView2);
        imageView = (ImageView) findViewById(R.id.imageView2);
        //FacebookSdk.addLoggingBehavior(LoggingBehavior.REQUESTS);

        processFacebookLogin();


  */













        loginButton = (LoginButton) findViewById(R.id.fb_login_bn);
        button2 = (Button) findViewById(R.id.button2);
        //button2.setVisibility(View.GONE);
        textView5 = (TextView) findViewById(R.id.textView5);
        textView = (TextView) findViewById(R.id.textView);
        textView2 = (TextView) findViewById(R.id.textView2);
       // textView4 = (TextView) findViewById(R.id.textView4);
        //imageView = (ImageView) findViewById(R.id.toolbarimage);
        FacebookSdk.addLoggingBehavior(LoggingBehavior.REQUESTS);
        //textView5.setText(loginButton.getText().toString());









        Download_data download_data = new Download_data((Download_data.download_complete) this);



        //LoginManager.getInstance().logInWithReadPermissions(
          //      this,
            //    Arrays.asList("email"));
/*
        //علشان يجيب الصورة من المخزن الي معرض الصورة الشخصية
        SharedPreferences sharedPreferences = getSharedPreferences("Data", Context.MODE_PRIVATE);
        String previouslyEncodedImage = sharedPreferences.getString("imagess", "");

        if( !previouslyEncodedImage.equalsIgnoreCase("") ){
            //System.out.print("display::"+previouslyEncodedImage);
            try {
                byte[] b = Base64.decode(previouslyEncodedImage, Base64.DEFAULT);
                Bitmap bitmap = BitmapFactory.decodeByteArray(b, 0, b.length);
                System.out.print("display::"+b.length);
                imageView.setImageBitmap(bitmap);
            }catch (Exception e) {
                System.out.print("cannot::donnot");
            }
        }
        // النهاية
*/
     /*
        // يجيب الصورة يحطها مكان الشعار واسم المستخدم
        SharedPreferences sharedPreferences = getSharedPreferences("Data", Context.MODE_PRIVATE);
        String facebookImage = "https://graph.facebook.com/"+sharedPreferences.getString("getId_string", DEFAULT)+"/picture?type=large";
        Glide.with(getApplicationContext()).load(facebookImage).into(imageView);
        textView4.setText("hi "+sharedPreferences.getString("getName_string", DEFAULT));
        //النهاية
*/
        if (loginButton.getText().toString().equals("Continue with Facebook")) {
            loginButton.setVisibility(View.VISIBLE);
            button2.setVisibility(View.GONE);

        } else if (loginButton.getText().toString().equals("Log out")) {
            loginButton.setVisibility(View.GONE);
            button2.setVisibility(View.VISIBLE);
            //SharedPreferences sharedPreferences = getSharedPreferences("Data", Context.MODE_PRIVATE);
            if (sharedPreferences.getString("seller_id", DEFAULT) != "NA") {
                textView.setText("");
                textView2.setText("");
                textView5.setText("");
            } else {
                textView.setText("شكراً");
                textView2.setText("تم تسجيل دخولك بنجاح");
                textView5.setText("سيتم تفعيل حسابك خلال 24 ساعة حتي تتمكن من متابعة حساباتك لدينا");
            }
        }


            if (loginButton.getText().toString().equals("تسجيل الخروج")) {
                loginButton.setVisibility(View.GONE);
                button2.setVisibility(View.VISIBLE);
                //SharedPreferences sharedPreferences = getSharedPreferences("Data", Context.MODE_PRIVATE);
                if (sharedPreferences.getString("seller_id", DEFAULT) != "NA") {
                    textView.setText("");
                    textView2.setText("");
                    textView5.setText("");
                } else {
                    textView.setText("شكراً");
                    textView2.setText("تم تسجيل دخولك بنجاح");
                    textView5.setText("سيتم تفعيل حسابك خلال 24 ساعة حتي تتمكن من متابعة حساباتك لدينا");
                }


            Animation animation = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.viewbised);
            Animation animation2 = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.move);
            button2.startAnimation(animation);
            textView.startAnimation(animation2);
            textView2.startAnimation(animation2);
            textView5.startAnimation(animation2);
        }

        // loginButton.setVisibility(View.VISIBLE);
        //textView5.setText(button2.getText());

        //بداية تصريح رقم التليفون

        // if(ContextCompat.checkSelfPermission(MainActivity.this, android.Manifest.permission.READ_PHONE_STATE) != PackageManager)
        if (ContextCompat.checkSelfPermission(MainActivity.this, android.Manifest.permission.READ_PHONE_STATE) != PackageManager.PERMISSION_GRANTED) {
            if (ActivityCompat.shouldShowRequestPermissionRationale(MainActivity.this, android.Manifest.permission.READ_PHONE_STATE)) {
                ActivityCompat.requestPermissions(MainActivity.this, new String[]{android.Manifest.permission.READ_PHONE_STATE}, 1);
            } else {
                ActivityCompat.requestPermissions(MainActivity.this, new String[]{android.Manifest.permission.READ_PHONE_STATE}, 1);
            }

        } else {
           // Toast.makeText(MainActivity.this, "Denied", Toast.LENGTH_SHORT).show();
        }

        // النهاية

        try {
            PackageInfo info = getPackageManager().getPackageInfo(
                    "com.ahmedabdelghafar.legarage",
                    PackageManager.GET_SIGNATURES);
            for (Signature signature : info.signatures) {
                MessageDigest md = MessageDigest.getInstance("SHA");
                md.update(signature.toByteArray());
                Log.d("KeyHash:::", encodeToString(md.digest(), Base64.DEFAULT));
                hashkey = encodeToString(md.digest(), Base64.DEFAULT);
            }
        } catch (PackageManager.NameNotFoundException e) {
            hashkey = "error2";

        } catch (NoSuchAlgorithmException e) {
            hashkey = "error3";
        }

        FacebookSdk.sdkInitialize(getApplicationContext());

        //textView5 = (TextView) findViewById(R.id.textView5);
        download_data = new Download_data((Download_data.download_complete) this);

        //profilePictureView = (ProfilePictureView) findViewById(R.id.friendProfilePicture);
        //imageView = (ImageView)findViewById(R.id.android_photo);
        callbackManager = CallbackManager.Factory.create();

        accessTokenTracker = new AccessTokenTracker() {

            @Override
            protected void onCurrentAccessTokenChanged(
                    AccessToken oldAccessToken,
                    AccessToken currentAccessToken) {
                // Set the access token using
                // currentAccessToken when it's loaded or set.
            }
        };
        // If the access token is available already assign it.
        accessToken = AccessToken.getCurrentAccessToken();

        profileTracker = new ProfileTracker() {
            @Override
            protected void onCurrentProfileChanged(
                    Profile oldProfile,
                    Profile currentProfile) {
                // App code
            }
        };

        //


        loginButton.registerCallback(callbackManager, new FacebookCallback<LoginResult>() {
            @Override
            public void onSuccess(final LoginResult loginResult) {

                //
                //  loginButton.setReadPermissions("user_friends");
                //loginButton.setReadPermissions("public_profile");
                //loginButton.setReadPermissions("email");
                //loginButton.setReadPermissions("user_birthday");
                //accessToken.getCurrentAccessToken().getPermissions();
                //accessToken.getCurrentAccessToken().getDeclinedPermissions();

                loginButton.setReadPermissions(Arrays.asList("user_birthday", "email", "user_education_history", "user_friends", "public_profile"));




                GraphRequest request = GraphRequest.newMeRequest( loginResult.getAccessToken(),new GraphRequest.GraphJSONObjectCallback() {
                            @Override
                            public void onCompleted(JSONObject object, GraphResponse response) {
//                                Log.v("MainActivity:::", response.toString());
                               // System.out.print("Granted Permission::"+loginResult.getRecentlyGrantedPermissions());

                                String userDatil = response.getRawResponse();
                                try {
                                    JSONObject jsonobject = new JSONObject(userDatil);
                                    birthday = jsonobject.getString("birthday");
                                    //textView2.setText(jsonobject.getString("email"));
                                    //textView.setText(jsonobject.getString("gender"));
                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }

                   /*
                   String userDatil = response.getRawResponse();
                        try {
                            JSONObject jsonobject = new JSONObject(userDatil);
                            System.out.print("jsonobject::"+jsonobject);
                            String facebookid = jsonobject.getString("id");
                            textView.setText(facebookid);
                    */


                                //textView5.setText(object.toString());
                                // Application code


                                try {
                                    SimpleDateFormat dmyFormat = new SimpleDateFormat("dd-MM-yyyy");
                                    birthday = object.getString("birthday"); // 01/31/1980 format
                                    //.replace("/", "-");
                                } catch (Exception e) {
                                    birthday = "Null_Null";
                                    //textView5.setText("birthday");
                                }


                                try {
                                    email = object.getString("email");
                                } catch (Exception e) {
                                    email = "Null_Null";
                                    //textView5.setText("email");
                                }



                                //birthday = object.getString("birthday").replace("/","-"); // 01/31/1980 format

                                final Profile profile = Profile.getCurrentProfile();
                                System.out.println(profile.getFirstName());
                                System.out.println(profile.getId());
                                //textView5.setText("Your account is not activated"+"\n"+"Please wait 24 hours for activation");
                                //profilePictureView.setProfileId(profile.getId());
                                //textView5.setText("Login Sucess \n" + "\n" + email + "\n" + birthday + "\n" + email + "\n\n" + profile.getFirstName() + "\n\n" + profile.getId() + "\n\n" + profile.getLastName() + "\n\n" + profile.getMiddleName() + "\n\n" + profile.getName() + "\n\n" + profile.toString() + "\n\n" + profile.hashCode());

                                // sp=getSharedPreferences("profilePicture",MODE_PRIVATE);

                                //if(!sp.getString("dp","").equals("")){
                                //  byte[] decodedString = Base64.decode(sp.getString("dp", ""), Base64.DEFAULT);
                                //Bitmap decodedByte = BitmapFactory.decodeByteArray(decodedString, 0, decodedString.length);
                                //imageView.setImageBitmap(decodedByte);
                                //}


                                // ByteArrayOutputStream baos = new ByteArrayOutputStream();
                                //selectedImage.compress(Bitmap.CompressFormat.JPEG, 100, baos); //bm is the bitmap object
                                //byte[] b = baos.toByteArray();
                                //String encodedImage = Base64.encodeToString(b, Base64.DEFAULT);
                                //sp.edit().putString("dp", encodedImage).commit();


                                //imageView = (ImageView) findViewById(R.id.android_photo);
                                //URL img_url =new URL("https://graph.facebook.com/10213605943483347/picture?type=normal");
                                //Bitmap bmp =BitmapFactory.decodeStream(img_url.openConnection().getInputStream());//bmp a bitmap

                                // URL url = new URL("https://graph.facebook.com/10213605943483347/picture?type=normal");
                                //HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                                //connection.setDoInput(true);
                                //connection.connect();
                                //InputStream input = connection.getInputStream();
                                //Bitmap myBitmap = BitmapFactory.decodeStream(input);
                                //imageView.setImageBitmap(myBitmap);//your linear layout
                                try {
                                    getId_string = profile.getId();
                                } catch (Exception e) {
                                    getId_string = "Null_Null";
                                    //textView5.setText("getId");
                                }
                                if (getId_string.equals("")) {
                                    getId_string = "Null_Null";
                                }


                                try {
                                    getFirstName_string = profile.getFirstName();
                                } catch (Exception e) {
                                    getFirstName_string = "Null_Null";
                                    //textView5.setText("getFirstName");
                                }
                                if (getFirstName_string.equals("")) {
                                    getFirstName_string = "Null_Null";
                                }


                                try {
                                    getMiddleName_string = profile.getMiddleName();
                                } catch (Exception e) {
                                    getMiddleName_string = "Null_Null";
                                    //textView5.setText("getMiddleName");
                                }
                                if (getMiddleName_string.equals("")) {
                                    getMiddleName_string = "Null_Null";
                                }


                                try {
                                    getLastName_string = profile.getLastName();
                                } catch (Exception e) {
                                    getLastName_string = "Null_Null";
                                    //textView5.setText("getLastName");
                                }
                                if (getLastName_string.equals("")) {
                                    getLastName_string = "Null_Null";
                                }


                                try {
                                    getName_string = profile.getName();
                                } catch (Exception e) {
                                    getName_string = "Null_Null";
                                    //textView5.setText("getName");
                                }
                                if (getName_string.equals("")) {
                                    getName_string = "Null_Null";
                                }


                                try {
                                    toString_string = profile.toString();
                                } catch (Exception e) {
                                    toString_string = "Null_Null";
                                    //textView5.setText("tostring");
                                }
                                if (toString_string.equals("")) {
                                    toString_string = "Null_Null";
                                }


                                try {
                                    hashCode_string = profile.hashCode();
                                } catch (Exception e) {
                                    hashCode_string = 0;
                                    //textView5.setText("hashcode");
                                }

                               //String facebookImage = "https://graph.facebook.com/"+profile.getId()+"/picture?type=large";
                               //Glide.with(getApplicationContext()).load(facebookImage).into(imageView);


                                //

                                SharedPreferences sharedPreferences = getSharedPreferences("Data", MODE_PRIVATE);
                                SharedPreferences.Editor editor = sharedPreferences.edit();


                                //editor.putString("PRODUCT_PHOTO", encodeTobase64(bitmap));


                                //حفظ الصورة داخل التليفون
                                try {
                                //editor.putString("namePreferance", itemNAme);
                                //imageView.setDrawingCacheEnabled(true);
                                //    imageView.buildDrawingCache();
                                //Bitmap bitmap = imageView.getDrawingCache();
                                //    BitmapDrawable drawable = (BitmapDrawable) imageView.getDrawable();
                                  //  Bitmap bitmap = drawable.getBitmap();

                                    // حفظ الصورة علي كارت الميموري

                                    //BitmapDrawable draw = (BitmapDrawable) imageView.getDrawable();
                                    //Bitmap bitmap2 = draw.getBitmap();

                                    Bitmap bitmap2;
                                    OutputStream output;

                                    //imageView.buildDrawingCache();
                                    //bitmap2 = imageView.getDrawingCache();

                                    //URL fb_url = new URL("https://graph.facebook.com/"+profile.getId()+"/picture?type=small");//small | noraml | large
                                    //HttpsURLConnection conn1 = (HttpsURLConnection) fb_url.openConnection();
                                    //HttpsURLConnection.setFollowRedirects(true);
                                    //conn1.setInstanceFollowRedirects(true);


/*
                                    //bitmap2 = BitmapFactory.decodeStream(conn1.getInputStream());
                                    bitmap2 = BitmapFactory.decodeFile(imageView.toString());
                                    // bitmap2 = BitmapFactory.decodeFile("https://graph.facebook.com/"+profile.getId()+"/picture?type=small");
                                    //bitmap2 = BitmapFactory.decodeResource(getResources(),R.drawable.home_legarage);
                                    File filepath = Environment.getExternalStorageDirectory();
                                    File dir = new File(filepath.getAbsoluteFile()+"/Save Profile Pic le garage");
                                    dir.mkdir();
                                    File file = new File(dir,"MyProfilePic.png");
                                    Toast.makeText(MainActivity.this,"Image Saved To SD Card",Toast.LENGTH_LONG).show();
                                    try {
                                        output = new FileOutputStream(file);
                                        bitmap2.compress(Bitmap.CompressFormat.PNG,100,output);
                                        output.flush();
                                        output.close();
                                    }catch(Exception e){
                                        e.printStackTrace();
                                    }
*/
                                    // النهاية
                               //     imageView.setDrawingCacheEnabled(true);
                                 //   imageView.buildDrawingCache();
                                   // Bitmap bitmap = Bitmap.createBitmap(imageView.getDrawingCache());
//
  //                              editor.putString("imagess", encodeTobase64(bitmap));
                                    //Log.v("uploadupload::Good","");
                                    //System.out.print("saysay::Goddddddd");
                                 } catch (Exception e) {
                                  //  Log.v("uploadupload::bad","");
                                    System.out.print("saysay::error2");
                                 }
                              //  editor.commit();

                                //النهاية

                                //Log.v("uploadupload::3","");
                                editor.putString("getId_string", getId_string);
                                editor.putString("getFirstName_string", getFirstName_string);
                                editor.putString("getMiddleName_string", getMiddleName_string);
                                editor.putString("getLastName_string", getLastName_string);
                                editor.putString("getName_string", getName_string);
                                editor.putString("toString_string", toString_string);
                                editor.putString("hashkey", hashkey);
                                editor.putString("hashCode_string", String.valueOf(hashCode_string));
                                editor.putString("email", email);
                                editor.putString("birthday", birthday);
                                //editor.putString("ipaddress",ipaddress.getText().toString());
                                editor.commit();


//                                textView5.setText("Login Sucess \n" + "\n hashkey : " + hashkey + "\n email : " + email + "\nbirthday : " + birthday + "\n profile.getFirstName : " + getFirstName_string + "\n\nprofile.getId : " + getId_string + "\n\n profile.getLastName : " + getLastName_string + "\n\n profile.getMiddleName : " + getMiddleName_string + "\n\n profile.getName :" + getName_string + "\n\n profile.toString : " + toString_string + "\n\n profile.hashCode : " + hashCode_string);
                                //textView5.setText("http://197.51.253.242:3000/api/send_profile/" + sharedPreferences.getString("getId_string",DEFAULT) + ",1," + sharedPreferences.getString("getFirstName_string",DEFAULT) + "," + sharedPreferences.getString("getMiddleName_string",DEFAULT) + "," + sharedPreferences.getString("getLastName_string",DEFAULT) + "," + sharedPreferences.getString("getName_string",DEFAULT) + "," + sharedPreferences.getString("toString_string",DEFAULT) + "," + sharedPreferences.getString("hashCode_string",DEFAULT)+"," + sharedPreferences.getString("email",DEFAULT) +","+sharedPreferences.getString("birthday",DEFAULT));

                                    //textView.setText(accessToken.getToken());

                                //


                                //catch (MalformedURLException e) {
                                // e.printStackTrace();
                                // }
                                //catch (IOException e) {
                                // e.printStackTrace();
                                // }
                                //String birthday = object.getString("birthday"); // 01/31/1980 format
                                button2.setVisibility(View.VISIBLE);
                                loginButton.setVisibility(View.GONE);

                            }
                        });

                Bundle parameters = new Bundle();
                //parameters.putString("basic_info", "id,name,birthday,email,gender,education,friends");
                parameters.putString("fields", "id,name,birthday,email,gender,education,friends");
                //parameters.putString("fields", "id,name,email");
                request.setParameters(parameters);
                request.executeAsync();

                //


            }


            @Override

            public void onCancel() {

                textView5.setText("Login Cancelled");

            }

            @Override
            public void onError(FacebookException error) {

            }
        });

        if (loginButton.getVisibility() == View.VISIBLE) {
            //String token = FirebaseInstanceId.getInstance().getToken();
            //try {
           //download_data.download_data_from_link("http://197.51.253.242:3000/api/send_reg_id/0,9999," + token.toString());
            //}catch (Exception ex) {
            //}
        }else{
            try {
                String token = FirebaseInstanceId.getInstance().getToken();
                download_data.download_data_from_link("http://197.51.253.242:3000/api/send_reg_id/" + sharedPreferences.getString("seller_id", "0") + "," + sharedPreferences.getString("getId_string", DEFAULT) + "," + token.toString());
            }catch (Exception ex) {
            }
            }
        }


    public void go_home(View view) {

        SharedPreferences sharedPreferences = getSharedPreferences("Data", Context.MODE_PRIVATE);
        //id_reg.setText(sharedPreferences.getString("id_reg",DEFAULT));

        //Toast.makeText(MainActivity.this, sharedPreferences.getString("seller_id", DEFAULT) , Toast.LENGTH_LONG).show();


        //Toast.makeText(MainActivity.this, token , Toast.LENGTH_LONG).show();


        if (sharedPreferences.getString("seller_id", DEFAULT) !="NA") {
            Toast.makeText(MainActivity.this, "حسابك مفعل" , Toast.LENGTH_LONG).show();
            //Intent intent = new Intent(this, Home.class);
            //intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            //finish();
            //startActivity(intent);

        }

        String urlsss = "http://197.51.253.242:3000/api/send_profile/" + sharedPreferences.getString("getId_string", DEFAULT) + ",1," + sharedPreferences.getString("getFirstName_string", DEFAULT) + "," + sharedPreferences.getString("getMiddleName_string", DEFAULT) + "," + sharedPreferences.getString("getLastName_string", DEFAULT) + "," + sharedPreferences.getString("getName_string", DEFAULT) + "," + sharedPreferences.getString("toString_string", DEFAULT) + "," + sharedPreferences.getString("hashCode_string", DEFAULT) + "," + sharedPreferences.getString("email", DEFAULT) + "," + sharedPreferences.getString("birthday", DEFAULT).replaceAll("/", "%2D");
        //download_data.download_data_from_link("http://197.51.253.242:3000/api/send_profile/" + sharedPreferences.getString("getId_string",DEFAULT) + ",1," + sharedPreferences.getString("getFirstName_string",DEFAULT) + "," + sharedPreferences.getString("getMiddleName_string",DEFAULT) + "," + sharedPreferences.getString("getLastName_string",DEFAULT) + "," + sharedPreferences.getString("getName_string",DEFAULT) + "," + sharedPreferences.getString("toString_string",DEFAULT) + "," + sharedPreferences.getString("hashCode_string",DEFAULT)+"," + sharedPreferences.getString("email",DEFAULT) +","+sharedPreferences.getString("birthday",DEFAULT));

        //String url2 = urlsss.replaceAll("/","%2F");
        Download_data download_data = new Download_data((Download_data.download_complete) this);
        download_data.download_data_from_link(urlsss.replaceAll(" ", "%20"));
        //download_data.download_data_from_link("http://197.51.253.242:3000/api/send_reg_id/"+sharedPreferences.getString("seller_id", "0")+","+sharedPreferences.getString("getId_string", DEFAULT)+","+token.toString());

        //Toast.makeText(MainActivity.this, urlsss.replaceAll(" ", "%20"), Toast.LENGTH_LONG).show();
        //textView5.setText(urlsss.replaceAll(" ", "%20"));
       // imageView.setImageBitmap(sharedPreferences.getString("imagess", DEFAULT));

        /*String previouslyEncodedImage = sharedPreferences.getString("imagess", "");

        if( !previouslyEncodedImage.equalsIgnoreCase("") ){
            //System.out.print("Goodgood::Goddddddd");

            //userpic = ImageHelper.stringToImage(pic);
            //byte[] b = Base64.decode(photo, Base64.DEFAULT);
            //InputStream is = new ByteArrayInputStream(b);
            //bitmap = BitmapFactory.decodeStream(is);
            //your_imageview.setImageBitmap(bitmap);



            try {
            byte[] b = Base64.decode(previouslyEncodedImage, Base64.DEFAULT);
            //InputStream is = new ByteArrayInputStream(b);
            Bitmap bitmap = BitmapFactory.decodeByteArray(b, 0, b.length);
            //Bitmap bitmap = BitmapFactory.decodeStream(is);
                System.out.print("display::"+b.length);
            imageView.setImageBitmap(bitmap);
           // textView.setText(bitmap.toString());
           }catch (Exception e) {
    //  Log.v("uploadupload::bad","");
            System.out.print("cannot::donnot");
            }
        }
        */
        //md.update(signature.toByteArray());
        //encodeToString(md.digest(), Base64.DEFAULT);
        //download_data.download_data_from_link(encode(urlsss.toByte,Base64.DEFAULT));

        //download_data.download_data_from_link(URLEncoder.encode(urlsss,"UTF-8"));
        //TelephonyManager tMgr = (TelephonyManager)mAppContext.getSystemService(Context.TELEPHONY_SERVICE);
        //String mPhoneNumber = tMgr.getLine1Number();

        /*TelephonyManager tel = (TelephonyManager) this.getSystemService(Context.TELEPHONY_SERVICE);
        String PhoneNumber = tel.getLine1Number();
        textView = (TextView) findViewById(R.id.textView);
        textView.setText(tel.getLine1Number() +"-"+ tel.getDeviceId() + "-" +tel.getSimSerialNumber() +"-"+tel.getPhoneType());
*/
     /*
        // محاولة اخري للحصول علي رقم تليفون الهاتف البداية
        String s1 = null;
        String main_data[] = {"data1", "is_primary", "data3", "data2", "data1", "is_primary", "photo_uri", "mimetype"};
        Object object = getContentResolver().query(Uri.withAppendedPath(android.provider.ContactsContract.Profile.CONTENT_URI, "data"),
                main_data, "mimetype=?",
                new String[]{"vnd.android.cursor.item/phone_v2"},
                "is_primary DESC");
        //Toast.makeText(MainActivity.this, object.toString(), Toast.LENGTH_SHORT).show();
        if (object != null) {
            do {
                if (!((Cursor) (object)).moveToNext())
                    break;
                // This is the phoneNumber
                 s1 = ((Cursor) (object)).getString(4);
                Toast.makeText(MainActivity.this, s1, Toast.LENGTH_SHORT).show();
            } while (true);
            ((Cursor) (object)).close();
        }
        textView.setText(s1);

       */ // النهاية
        Intent is = new Intent(MainActivity.this, Home.class);
        startActivity(is);


    }
/*
    private void processFacebookLogin() {
        if(accessToken !=null){
            accessToken = com.facebook.AccessToken.getCurrentAccessToken();
            LoginManager.getInstance().logOut();
        }
        LoginManager.getInstance().registerCallback(callbackManager, new FacebookCallback<LoginResult>() {
            @Override
            public void onSuccess(LoginResult loginResult) {
                //check for Granted permission
                System.out.print("Granted Permission::"+loginResult.getRecentlyGrantedPermissions());

                GraphRequest graphRequest = GraphRequest.newMeRequest(loginResult.getAccessToken(),new GraphRequest.GraphJSONObjectCallback(){

                    @Override
                    public void onCompleted(JSONObject object, GraphResponse response) {
                     String userDatil = response.getRawResponse();
                        try {
                            JSONObject jsonobject = new JSONObject(userDatil);
                            System.out.print("jsonobject::"+jsonobject);
                            String facebookid = jsonobject.getString("id");
                            textView.setText(facebookid);
                            String email = jsonobject.getString("email");
                            textView2.setText(email);
                            String name = jsonobject.getString("name");
                            textView5.setText(name);
                            String facebookImage = "https://graph.facebook.com/"+facebookid+"/picture?type=large";
                            Glide.with(getApplicationContext()).load(facebookImage).into(imageView);

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                    }
                });
                Bundle parameters = new Bundle();
                parameters.putString("fields","name,email");
                graphRequest.setParameters(parameters);
                graphRequest.executeAsync();


                loginButton.setOnClickListener(new View.OnClickListener(){

                    @Override
                    public void onClick(View view) {
                        LoginManager.getInstance().logInWithReadPermissions(MainActivity.this,Arrays.asList("public_profile, email"));
                    }
                });

            }

            @Override
            public void onCancel() {

            }

            @Override
            public void onError(FacebookException error) {

            }
        });

    }

*/
    public static String encodeTobase64(Bitmap image) {
    Bitmap immage = image;
    ByteArrayOutputStream baos = new ByteArrayOutputStream();
    immage.compress(Bitmap.CompressFormat.PNG, 100, baos);
    byte[] b = baos.toByteArray();
    String imageEncoded = Base64.encodeToString(b, Base64.DEFAULT);

    Log.d("Image Log:", imageEncoded);
    return imageEncoded;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        accessTokenTracker.stopTracking();
        profileTracker.stopTracking();
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        callbackManager.onActivityResult(requestCode, resultCode, data);
    }

    @Override
    public void get_data(String data) {
        try {
            JSONArray data_array = new JSONArray(data);

            for (int i = 0; i < data_array.length(); i++) {
                JSONObject obj = new JSONObject(data_array.get(i).toString());

                Countries add = new Countries();

                add.seller_id = obj.getString("seller_id");

                String ssss = obj.getString("seller_id");

                //Toast.makeText(MainActivity.this, "ss : " + obj.getString("seller_id") , Toast.LENGTH_LONG).show();

//if (obj.getString("seller_id")= "NULL") {
                //if (ssss !=null &&  !ssss.isEmpty()){
                if (ssss !="null") {
                    //Toast.makeText(MainActivity.this, "YES", Toast.LENGTH_LONG).show();
                    //textView.setText("yes");

                    SharedPreferences sharedPreferences = getSharedPreferences("Data", MODE_PRIVATE);
                    SharedPreferences.Editor editor = sharedPreferences.edit();
                    editor.putString("seller_id", obj.getString("seller_id"));
                    editor.commit();


                    //sharedPreferences.getString("seller_id", "0")

                    //Toast.makeText(MainActivity.this, obj.getString("seller_id"), Toast.LENGTH_LONG).show();
                }


                //TextView textView5 = (TextView) findViewById(R.id.personal_name_text);
                //textView5.setText(obj.getString("save_row"));
                //textView5.setGravity(Gravity.CENTER);
            }

            //adapter.notifyDataSetChanged();

        } catch (JSONException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        switch (requestCode) {
            case 1: {
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    if (ContextCompat.checkSelfPermission(MainActivity.this, android.Manifest.permission.READ_PHONE_STATE) == PackageManager.PERMISSION_GRANTED) {
                        Toast.makeText(MainActivity.this, "Granted", Toast.LENGTH_SHORT).show();


                    } else {
                        Toast.makeText(MainActivity.this, "No Permission", Toast.LENGTH_SHORT).show();
                    }
                    return;
                }
            }

        }
    }
    @Override
    public void onBackPressed() {
        //Toast.makeText(this, "Good Bye",Toast.LENGTH_SHORT).show();


        Intent intent = new Intent(Intent.ACTION_MAIN);
        intent.addCategory(Intent.CATEGORY_HOME);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);//***Change Here***
        startActivity(intent);
        finish();
        System.exit(0);



        /* if (exit) {
            finish(); // finish activity
        } else {
            Toast.makeText(this, "Press Back again to Exit.",
                    Toast.LENGTH_SHORT).show();
            exit = true;
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    exit = false;
                }
            }, 3 * 1000);

        }
        */
    }
    public static float dpToPixels(int dp, Context context) {
        return dp * (context.getResources().getDisplayMetrics().density);
    }

}

























