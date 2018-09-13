package com.ahmedabdelghafar.legarage;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

/**
 * Created by orcl on 06/08/2017.
 */

public class List_Adapter_receiving extends BaseAdapter {
    receiving receivingss;


    List_Adapter_receiving(receiving receivingss) { this.receivingss = receivingss;}

    @Override
    public int getCount() {
        return receivingss.countries.size();
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
        TextView item_name;
        TextView quntity;
        TextView price_start;
        TextView price_sales;
        TextView price_Compensatory;
        TextView display_expenses;
        TextView commission;
        TextView dr_receiving;
        TextView value_plus;



    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        List_Adapter_receiving.ViewHolderItem holder = new List_Adapter_receiving.ViewHolderItem();
        if (convertView == null) {
            LayoutInflater inflater = (LayoutInflater) receivingss.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.cell_receiving, null);

            holder.id_order = (TextView) convertView.findViewById(R.id.id_order);
            holder.ddate = (TextView) convertView.findViewById(R.id.ddate);
            holder.item_name = (TextView) convertView.findViewById(R.id.item_name);
            holder.quntity = (TextView) convertView.findViewById(R.id.quntity);
            holder.price_start = (TextView) convertView.findViewById(R.id.price_start);
            holder.price_sales = (TextView) convertView.findViewById(R.id.price_sales);
            holder.price_Compensatory = (TextView) convertView.findViewById(R.id.price_Compensatory);
            holder.display_expenses = (TextView) convertView.findViewById(R.id.display_expenses);
            holder.commission = (TextView) convertView.findViewById(R.id.commission);
            holder.dr_receiving = (TextView) convertView.findViewById(R.id.dr_receiving);
            holder.value_plus = (TextView) convertView.findViewById(R.id.value_plus);



            convertView.setTag(holder);
        } else {
            holder = (List_Adapter_receiving.ViewHolderItem) convertView.getTag();
        }


        holder.id_order.setText(this.receivingss.countries.get(position).id_order);

       /* String uu = this.receivingss.countries.get(position).ddate;
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm");
        Date dddd = null;
        try {
            dddd = dateFormat.parse(uu.toString());
        } catch (ParseException e) {
            e.printStackTrace();
        }
*/

        holder.ddate.setText(this.receivingss.countries.get(position).ddate);
        holder.item_name.setText(this.receivingss.countries.get(position).item_name);
        holder.quntity.setText(this.receivingss.countries.get(position).quntity);
        holder.price_start.setText(this.receivingss.countries.get(position).price_start);
        holder.price_sales.setText(this.receivingss.countries.get(position).price_sales);
        holder.price_Compensatory.setText(this.receivingss.countries.get(position).price_Compensatory);
        holder.display_expenses.setText(this.receivingss.countries.get(position).display_expenses);
        holder.commission.setText(this.receivingss.countries.get(position).commission);
        holder.dr_receiving.setText(this.receivingss.countries.get(position).dr_receiving);
        holder.value_plus.setText(this.receivingss.countries.get(position).value_plus);



        return convertView;
    }

}