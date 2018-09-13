package com.ahmedabdelghafar.legarage;

import android.content.Context;
import android.text.method.LinkMovementMethod;
import android.text.util.Linkify;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

/**
 * Created by orcl on 23/08/2017.
 */

public class List_Adapter_nodelivery extends BaseAdapter {
    nodelivery nodeliveryss;


    List_Adapter_nodelivery(nodelivery nodeliveryss) { this.nodeliveryss = nodeliveryss;}

    @Override
    public int getCount() {
        return nodeliveryss.countries.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {

        return 0;

    }

    static class ViewHolderItem {
        TextView id_order;
        TextView ddate;
        TextView discreption;
        TextView url;
        TextView price;
        TextView seller_name;
        ImageView imagess_flogss;

    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        List_Adapter_nodelivery.ViewHolderItem holder = new List_Adapter_nodelivery.ViewHolderItem();
        if (convertView == null) {
            LayoutInflater inflater = (LayoutInflater) nodeliveryss.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.cell_nodelivery, null);

            holder.id_order = (TextView) convertView.findViewById(R.id.id_order);
            holder.ddate = (TextView) convertView.findViewById(R.id.ddate);
            holder.discreption = (TextView) convertView.findViewById(R.id.discreption);
            holder.url = (TextView) convertView.findViewById(R.id.url);
            holder.price = (TextView) convertView.findViewById(R.id.price);
            holder.imagess_flogss = (ImageView) convertView.findViewById(R.id.imagess_flog_nodelivery);
            //  holder.seller_name = (TextView) convertView.findViewById(R.id.seller_name);
            /*holder.weight_st = (TextView) convertView.findViewById(R.id.weight_st);
            holder.start_price = (TextView) convertView.findViewById(R.id.start_price);
            holder.end_price = (TextView) convertView.findViewById(R.id.end_price);
           */ //holder.dr_receiving = (TextView) convertView.findViewById(R.id.dr_receiving);
            //holder.value_plus = (TextView) convertView.findViewById(R.id.value_plus);



            convertView.setTag(holder);
        } else {
            holder = (List_Adapter_nodelivery.ViewHolderItem) convertView.getTag();
        }


        holder.id_order.setText(this.nodeliveryss.countries.get(position).id_order);
        holder.ddate.setText(this.nodeliveryss.countries.get(position).ddate);
        holder.discreption.setText(this.nodeliveryss.countries.get(position).discreption);
        holder.url.setText(this.nodeliveryss.countries.get(position).url);
        holder.url.setLinksClickable(true);
        holder.url.setAutoLinkMask(Linkify.WEB_URLS);
        holder.url.setMovementMethod(LinkMovementMethod.getInstance());
//If the edit text contains previous text with potential links
        Linkify.addLinks(holder.url, Linkify.WEB_URLS);

        holder.price.setText(this.nodeliveryss.countries.get(position).price);
        Glide.with(nodeliveryss.getApplicationContext()).load(this.nodeliveryss.countries.get(position).imagessflog).into(holder.imagess_flogss);

        //holder.seller_name.setText(this.ondeliveryss.countries.get(position).seller_name);
       /* holder.weight_st.setText(this.readytoacutionss.countries.get(position).weight_st);
        holder.start_price.setText(this.readytoacutionss.countries.get(position).start_price);
        holder.end_price.setText(this.readytoacutionss.countries.get(position).end_price);
        // holder.dr_receiving.setText(this.discreptionss.countries.get(position).dr_receiving);
        //holder.value_plus.setText(this.discreptionss.countries.get(position).value_plus);


*/
        return convertView;
    }

}