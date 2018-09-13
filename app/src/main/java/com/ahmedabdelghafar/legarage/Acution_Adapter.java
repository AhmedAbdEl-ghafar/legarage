package com.ahmedabdelghafar.legarage;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.github.siyamed.shapeimageview.CircularImageView;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by orcl on 22/05/2018.
 */

public class Acution_Adapter extends RecyclerView.Adapter<Acution_Adapter.AcutionHolder> {
    @NonNull
    private List<acution_bid> list;
    private Context mContext;



    public Acution_Adapter(List<acution_bid> list, Context mContext) {
        this.list = list;
        this.mContext = mContext;
    }

    @Override
    public Acution_Adapter.AcutionHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.cell_acution,parent,false);
        return new AcutionHolder(view);
    }

    @Override
    public void onBindViewHolder(final Acution_Adapter.AcutionHolder holder, int position) {
        acution_bid uuuu = list.get(position);
        holder.bid_TV.setText(uuuu.bid);
        holder.seller_name_TV.setText(uuuu.seller_name);
        Picasso.with(mContext).load(uuuu.urlimage).placeholder(R.drawable.ahmed
        ).into(holder.imageView2);
//        FirebaseStorage storage = FirebaseStorage.getInstance();
//        StorageReference storageRef = storage.getReference();
//
//        storageRef.child("10005838.JPG").getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
//            @Override
//            public void onSuccess(Uri uri) {
////
////                Picasso.with(mContext).load(uri).placeholder(R.drawable.ahmed
////                ).into(holder.imageView2);
//            }
//        }).addOnFailureListener(new OnFailureListener() {
//            @Override
//            public void onFailure(@NonNull Exception exception) {
//
//            }
//        });

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public void updateData(List<acution_bid> bid) {
        this.list.clear();
        this.list.addAll(bid);
        notifyDataSetChanged();
    }

    public class AcutionHolder extends RecyclerView.ViewHolder {
        TextView bid_TV;
        TextView seller_name_TV;
        CircularImageView imageView2;

        public AcutionHolder(View itemView) {
            super(itemView);
            bid_TV = (TextView) itemView.findViewById(R.id.bid_TV);
            seller_name_TV = (TextView) itemView.findViewById(R.id.seller_name_TV);
            imageView2 = (CircularImageView)itemView.findViewById(R.id.imageView2);
        }

    }
}
