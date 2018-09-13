package com.ahmedabdelghafar.legarage;

import android.content.Context;
import android.text.method.LinkMovementMethod;
import android.text.util.Linkify;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

/**
 * Created by orcl on 10/08/2017.
 */

public class List_Adapter_endss extends BaseAdapter {
    endss endssss;


    List_Adapter_endss(endss endssss) { this.endssss = endssss;}

    @Override
    public int getCount() {
        return endssss.countries.size();
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
        TextView ddate2;
        TextView net;


    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        List_Adapter_endss.ViewHolderItem holder = new List_Adapter_endss.ViewHolderItem();
        if (convertView == null) {
            LayoutInflater inflater = (LayoutInflater) endssss.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.cell_endss, null);

            holder.id_order = (TextView) convertView.findViewById(R.id.id_order);
            holder.ddate = (TextView) convertView.findViewById(R.id.ddate);
            holder.discreption = (TextView) convertView.findViewById(R.id.discreption);
            holder.url = (TextView) convertView.findViewById(R.id.url);
            holder.url.setLinksClickable(true);
            holder.url.setAutoLinkMask(Linkify.WEB_URLS);
            holder.url.setMovementMethod(LinkMovementMethod.getInstance());
//If the edit text contains previous text with potential links
            Linkify.addLinks(holder.url, Linkify.WEB_URLS);
            //holder.url.setMovementMethod(LinkMovementMethod.getInstance());

            holder.price = (TextView) convertView.findViewById(R.id.price);
            holder.seller_name = (TextView) convertView.findViewById(R.id.seller_name);
            holder.ddate2 = (TextView) convertView.findViewById(R.id.ddate2);
            holder.net = (TextView) convertView.findViewById(R.id.net);
            /*holder.end_price = (TextView) convertView.findViewById(R.id.end_price);
           */ //holder.dr_receiving = (TextView) convertView.findViewById(R.id.dr_receiving);
            //holder.value_plus = (TextView) convertView.findViewById(R.id.value_plus);



            convertView.setTag(holder);
        } else {
            holder = (List_Adapter_endss.ViewHolderItem) convertView.getTag();
        }


        holder.id_order.setText(this.endssss.countries.get(position).id_order);
        holder.ddate.setText(this.endssss.countries.get(position).ddate);
        holder.discreption.setText(this.endssss.countries.get(position).discreption);
        holder.url.setText(this.endssss.countries.get(position).url);
        holder.url.setLinksClickable(true);
        holder.url.setAutoLinkMask(Linkify.WEB_URLS);
        holder.url.setMovementMethod(LinkMovementMethod.getInstance());
//If the edit text contains previous text with potential links
        Linkify.addLinks(holder.url, Linkify.WEB_URLS);


        holder.price.setText(this.endssss.countries.get(position).price);
        holder.seller_name.setText(this.endssss.countries.get(position).seller_name);
        holder.ddate2.setText(this.endssss.countries.get(position).ddate2);
        holder.net.setText(this.endssss.countries.get(position).net);
       /* holder.end_price.setText(this.readytoacutionss.countries.get(position).end_price);
        // holder.dr_receiving.setText(this.discreptionss.countries.get(position).dr_receiving);
        //holder.value_plus.setText(this.discreptionss.countries.get(position).value_plus);


*/
        return convertView;
    }

}