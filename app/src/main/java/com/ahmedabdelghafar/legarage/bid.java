package com.ahmedabdelghafar.legarage;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.nex3z.notificationbadge.NotificationBadge;
import com.squareup.picasso.Picasso;
import com.txusballesteros.bubbles.BubbleLayout;
import com.txusballesteros.bubbles.BubblesManager;
import com.txusballesteros.bubbles.OnInitializedCallback;

import java.util.ArrayList;
import java.util.List;

public class bid extends AppCompatActivity implements View.OnClickListener{
    TextView id_order_TV;
    TextView name_TV;
    TextView detalis_TV;
    TextView data_of_close_TV;
    EditText send_ET;
    ImageView imagess_flogss;
    FloatingActionButton send_BT;
    RecyclerView rec_rec;
    NestedScrollView Scroll_page;
    private RecyclerView.LayoutManager mLayoutManager;
    public ProgressDialog pd ;
    private Acution_Adapter adapter;
    String DEFAULT = "NA";

    //declare bubble
    private BubblesManager bubblesManager;
    private NotificationBadge mBadge;
    private int MY_PERMISSION = 1000;
    // end declare bubble
    static boolean active = false;
    int Budge = 1 ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bid);

        pd = new ProgressDialog(bid.this);
        pd.setMessage("Please Wait To Load Data");
        pd.setIndeterminate(true);
        pd.setProgress(0);
        pd.show();

        id_order_TV =(TextView)findViewById(R.id.id_order_TV);
        name_TV = (TextView)findViewById(R.id.name_TV);
        detalis_TV = (TextView)findViewById(R.id.detalis_TV);
        data_of_close_TV = (TextView)findViewById(R.id.data_of_close_TV);
        imagess_flogss = (ImageView)findViewById(R.id.imagess_flogss);
        send_BT = (FloatingActionButton)findViewById(R.id.send_BT);
        send_ET = (EditText)findViewById(R.id.send_ET);
        rec_rec = (RecyclerView)findViewById(R.id.rec_rec);
        Scroll_page = (NestedScrollView) findViewById(R.id.Scroll_page);

        adapter = new Acution_Adapter(new ArrayList<acution_bid>(),this);
        rec_rec.setAdapter(adapter);
        mLayoutManager = new LinearLayoutManager(this);

        rec_rec.setLayoutManager(mLayoutManager);
        rec_rec.setHasFixedSize(true);



        //bubble
        initBubble();
        //end bubble

        FirebaseDatabase.getInstance().getReference()
                .child("acution")
                .addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        acution acss = dataSnapshot.getValue(acution.class);
                        id_order_TV.setText(Integer.toString(acss.id_id));
                        name_TV.setText(acss.name);
                        detalis_TV.setText(acss.details);
                        data_of_close_TV.setText(acss.date_of_close);
                        Picasso.with(bid.this).load(acss.image).placeholder(R.drawable.ahmed
                        ).into(imagess_flogss);
                        pd.dismiss();
                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {

                    }
                });

        send_BT.setOnClickListener(this);

        getChattingList();
        scrooller();
    }



    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.send_BT:
                send();
                send_ET.requestFocus();
                break;
        }
    }

    private void initBubble() {
        bubblesManager = new BubblesManager.Builder(this)
                .setTrashLayout(R.layout.bubble_remove)
                .setInitializationCallback(new OnInitializedCallback() {
                    @Override
                    public void onInitialized() {
                        //addNewBubble();
                    }
                }).build();
        bubblesManager.initialize();
    }

    private void send() {
        String message = send_ET.getText().toString().trim();

        SharedPreferences sharedPreferences = getSharedPreferences("Data", Context.MODE_PRIVATE);
//        if (!sharedPreferences.getString("seller_id", DEFAULT).equals("NA")) {
//            String seller_id = sharedPreferences.getString("seller_id", "0");
//        }

        if(!TextUtils.isEmpty(message)){
            send_ET.setText("");

            acution_bid acution_bid_s = new acution_bid();
            acution_bid_s.setBid(message);
            acution_bid_s.setSeller_id(sharedPreferences.getString("seller_id", DEFAULT));
            acution_bid_s.setSeller_name(sharedPreferences.getString("getName_string", DEFAULT));
            String facebookImage = "https://graph.facebook.com/"+sharedPreferences.getString("getId_string", DEFAULT)+"/picture?type=large";
            acution_bid_s.setUrlimage(facebookImage);

            String uniquekey = FirebaseDatabase.getInstance().getReference()
                    .child("chat").push().getKey();

            FirebaseDatabase.getInstance().getReference()
                    .child("chat")
                    .child(uniquekey)
                    .setValue(acution_bid_s)

                    .addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            Toast.makeText(bid.this, e.getMessage().toString(), Toast.LENGTH_SHORT).show();
                        }
                    });

        }
        scrooller();

    }

    private void getChattingList() {

        FirebaseDatabase.getInstance().getReference()
                .child("chat")
                .addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        List<acution_bid> bid = new ArrayList<>();

                        for (DataSnapshot child : dataSnapshot.getChildren()){
                            acution_bid pppo = child.getValue(acution_bid.class);
                            bid.add(pppo);
                        }
//                        rec_rec.setAdapter(new Acution_Adapter(bid,bid.this));

                        adapter.updateData(bid);
//                        ;
                        //Scroll_page.fullScroll(ScrollView.FOCUS_DOWN);
                        rec_rec.scrollToPosition(bid.size() - 1);
                        scrooller();
                        if(!active) {
                            addNewBubble();
                        }



                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {

                    }
                });

    }

    private void scrooller() {

        Scroll_page.fullScroll(NestedScrollView.FOCUS_DOWN );
    }

    private void addNewBubble() {
//        if (Budge > 1) {
//            bubblesManager.recycle();
//
//        }
        BubbleLayout bubbleView = (BubbleLayout) LayoutInflater.from(bid.this).inflate(R.layout.bubble_layout,null);
        mBadge = (NotificationBadge)bubbleView.findViewById(R.id.count);

        mBadge.setNumber(Budge);

        bubbleView.setOnBubbleRemoveListener(new BubbleLayout.OnBubbleRemoveListener() {
            @Override
            public void onBubbleRemoved(BubbleLayout bubble) {
//                Toast.makeText(splash.this, "Removed", Toast.LENGTH_SHORT).show();
                bubble.removeAllViews();
            }
        });
        bubbleView.setOnBubbleClickListener(new BubbleLayout.OnBubbleClickListener() {
            @Override
            public void onBubbleClick(BubbleLayout bubble) {
//                Toast.makeText(splash.this, "Clicked", Toast.LENGTH_SHORT).show();
                Intent i = new Intent(bid.this, bid.class);
                startActivity(i);
//                finish();

            }
        });
//        bubblesManager.removeBubble();
        bubbleView.setShouldStickToWall(true);

        bubblesManager.addBubble(bubbleView, 60, 20);
        Budge = Budge + 1;
    }

//    protected void onDestroy() {
//        super.onDestroy();
//        bubblesManager.recycle();
//    }


    @Override
    public void onStart() {
        super.onStart();
        active = true;

    }

    @Override
    public void onStop() {
        super.onStop();
        active = false;

    }
}
