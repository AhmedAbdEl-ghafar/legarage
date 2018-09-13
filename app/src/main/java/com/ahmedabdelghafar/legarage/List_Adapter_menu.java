package com.ahmedabdelghafar.legarage;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

/**
 * Created by orcl on 31/07/2017.
 */

public class List_Adapter_menu extends BaseAdapter {
    menu_m menuss;


    List_Adapter_menu(menu_m menuss) { this.menuss = menuss;}

    @Override
    public int getCount() {
        return menuss.countries.size();
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
        TextView menu_id;
        TextView menu_name;
        TextView menu_sum;




    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        List_Adapter_menu.ViewHolderItem holder = new List_Adapter_menu.ViewHolderItem();
        if (convertView == null) {
            LayoutInflater inflater = (LayoutInflater) menuss.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.cell_menu, null);

            holder.menu_id = (TextView) convertView.findViewById(R.id.menu_id);
            holder.menu_name = (TextView) convertView.findViewById(R.id.menu_name);
            holder.menu_sum = (TextView) convertView.findViewById(R.id.menu_sum);
            /*holder.phone2 = (TextView) convertView.findViewById(R.id.phone2);
            holder.contry = (TextView) convertView.findViewById(R.id.contry);
            holder.gov = (TextView) convertView.findViewById(R.id.gov);
            holder.areas = (TextView) convertView.findViewById(R.id.areas);
            holder.address = (TextView) convertView.findViewById(R.id.address);
            //holder.prices = (TextView) convertView.findViewById(R.id.prices);
            //holder.cod = (TextView) convertView.findViewById(R.id.cod);
            holder.Total = (TextView) convertView.findViewById(R.id.Total);
*/


            convertView.setTag(holder);
        } else {
            holder = (List_Adapter_menu.ViewHolderItem) convertView.getTag();
        }


        holder.menu_id.setText(this.menuss.countries.get(position).menu_id);
        holder.menu_name.setText(this.menuss.countries.get(position).menu_name);
        holder.menu_sum.setText(this.menuss.countries.get(position).menu_sum);
        /*holder.phone2.setText(this.shipment_s.countries.get(position).phone2);
        holder.contry.setText(this.shipment_s.countries.get(position).country_name);
        holder.gov.setText(this.shipment_s.countries.get(position).governorate_name);
        holder.areas.setText(this.shipment_s.countries.get(position).area_name);
        holder.address.setText(this.shipment_s.countries.get(position).address);
        //holder.prices.setText(this.shipment_s.countries.get(position).price);
        //holder.cod.setText(this.shipment_s.countries.get(position).cod);
        holder.Total.setText(this.shipment_s.countries.get(position).total);
*/


        return convertView;
    }

}