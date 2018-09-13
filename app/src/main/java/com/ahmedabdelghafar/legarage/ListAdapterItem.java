package com.ahmedabdelghafar.legarage;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.util.List;

/**
 * Created by orcl on 22/04/2018.
 */

public class ListAdapterItem extends RecyclerView.Adapter<ListAdapterItem.ListAdapterItemHolder> {
    private List<item_item> listss;
    private Context mContext;

    public ListAdapterItem(List<item_item> responseahmed, ssss ssss4) {
        this.listss = responseahmed;
        this.mContext = ssss4;
    }

    @Override
    public ListAdapterItem.ListAdapterItemHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.cell_acution_now2,parent,false);
        return new ListAdapterItemHolder(view);
    }

    @Override
    public void onBindViewHolder(ListAdapterItemHolder holder, int position) {
       // holder.Brand.setText("ahmed");
        holder.Brandss.setText(listss.get(position).getBrand());
        //holder.discreption.setText(list.get(position).discreption);

//        holder.url.setText(list.get(position).url);
//        holder.url.setLinksClickable(true);
//        holder.url.setAutoLinkMask(Linkify.WEB_URLS);
//        holder.url.setMovementMethod(LinkMovementMethod.getInstance());
//        Linkify.addLinks(holder.url, Linkify.WEB_URLS);
//        holder.date_of_close.setText(list.get(position).date_of_close);
////        Picasso.with(homess).load(list.get(position).imagessflog).placeholder(R.drawable.ahmed
////        ).into(holder.imagess_flogss);
//
//
//        Typeface face=Typeface.createFromAsset(list.getAssets(),"fonts/legaragefont.OTF");
//        holder.lagarge_name.setTypeface(face);


    }

    @Override
    public int getItemCount() {
        return listss.size();
    }

    public class ListAdapterItemHolder extends RecyclerView.ViewHolder{
        TextView Brandss;
        TextView discreption;
        TextView url;
        TextView date_of_close;
        ImageView imagess_flogss;
        TextView lagarge_name ;
        RecyclerView recyclerView;
        public ProgressBar progressBar;

        public ListAdapterItemHolder(View itemView) {
            super(itemView);
            Brandss = (TextView) itemView.findViewById(R.id.Brand);
//            discreption = (TextView) itemView.findViewById(R.id.discreption);
//            discreption = (TextView) itemView.findViewById(R.id.discreption);
//            url = (TextView) itemView.findViewById(R.id.url);
//            date_of_close = (TextView) itemView.findViewById(R.id.date_of_close);
//            imagess_flogss = (ImageView) itemView.findViewById(R.id.imagess_flog);
//            lagarge_name = (TextView) itemView.findViewById(R.id.legarage_name);
        }
    }
}
