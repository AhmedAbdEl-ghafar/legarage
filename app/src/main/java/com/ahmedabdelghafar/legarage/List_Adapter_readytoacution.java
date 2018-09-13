package com.ahmedabdelghafar.legarage;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

/**
 * Created by orcl on 08/08/2017.
 */

public class List_Adapter_readytoacution extends BaseAdapter {
    readytoacution readytoacutionss;


    List_Adapter_readytoacution(readytoacution readytoacutionss) { this.readytoacutionss = readytoacutionss;}

    @Override
    public int getCount() {
        return readytoacutionss.countries.size();
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
        TextView detailed_description;
        TextView main_category_name;
        TextView sub_category_name;
        TextView weight_st;
        ImageView imagess_flogss;
        //TextView start_price;
        //TextView end_price;


    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        List_Adapter_readytoacution.ViewHolderItem holder = new List_Adapter_readytoacution.ViewHolderItem();
        if (convertView == null) {
            LayoutInflater inflater = (LayoutInflater) readytoacutionss.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.cell_readytoacution, null);

            holder.id_order = (TextView) convertView.findViewById(R.id.id_order);
            holder.ddate = (TextView) convertView.findViewById(R.id.ddate);
            holder.discreption = (TextView) convertView.findViewById(R.id.discreption);
            holder.detailed_description = (TextView) convertView.findViewById(R.id.detailed_description);
            holder.main_category_name = (TextView) convertView.findViewById(R.id.main_category_name);
            holder.sub_category_name = (TextView) convertView.findViewById(R.id.sub_category_name);
            holder.weight_st = (TextView) convertView.findViewById(R.id.weight_st);
            holder.imagess_flogss = (ImageView) convertView.findViewById(R.id.imagess_flog_readytoacution);
            //holder.start_price = (TextView) convertView.findViewById(R.id.start_price);
            //holder.end_price = (TextView) convertView.findViewById(R.id.end_price);
            //holder.dr_receiving = (TextView) convertView.findViewById(R.id.dr_receiving);
            //holder.value_plus = (TextView) convertView.findViewById(R.id.value_plus);



            convertView.setTag(holder);
        } else {
            holder = (List_Adapter_readytoacution.ViewHolderItem) convertView.getTag();
        }


        holder.id_order.setText(this.readytoacutionss.countries.get(position).id_order);
        holder.ddate.setText(this.readytoacutionss.countries.get(position).ddate);
        holder.discreption.setText(this.readytoacutionss.countries.get(position).discreption);
        holder.detailed_description.setText(this.readytoacutionss.countries.get(position).detailed_description);
        holder.main_category_name.setText(this.readytoacutionss.countries.get(position).main_category_name);
        holder.sub_category_name.setText(this.readytoacutionss.countries.get(position).sub_category_name);
        holder.weight_st.setText(this.readytoacutionss.countries.get(position).weight_st);
        Glide.with(readytoacutionss.getApplicationContext()).load(this.readytoacutionss.countries.get(position).imagessflog).into(holder.imagess_flogss);

        //holder.start_price.setText(this.readytoacutionss.countries.get(position).start_price);
        //holder.end_price.setText(this.readytoacutionss.countries.get(position).end_price);
        // holder.dr_receiving.setText(this.discreptionss.countries.get(position).dr_receiving);
        //holder.value_plus.setText(this.discreptionss.countries.get(position).value_plus);



        return convertView;
    }

}