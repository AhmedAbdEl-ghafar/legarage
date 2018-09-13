package com.ahmedabdelghafar.legarage;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

/**
 * Created by orcl on 11/11/2017.
 */

public class List_Adapter_reciving_notification extends BaseAdapter {
    reciving_notification reciving_notificationss;


    List_Adapter_reciving_notification(reciving_notification reciving_notificationss) { this.reciving_notificationss = reciving_notificationss;}

    @Override
    public int getCount() {
        return reciving_notificationss.countries.size();
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
        //TextView id_order;
        TextView ddate;
        TextView notification;
        /*TextView quntity;
        TextView price_start;
        TextView price_sales;
        TextView price_Compensatory;
        TextView display_expenses;
        TextView commission;
        TextView dr_receiving;
        TextView value_plus;
*/


    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        List_Adapter_reciving_notification.ViewHolderItem holder = new List_Adapter_reciving_notification.ViewHolderItem();
        if (convertView == null) {
            LayoutInflater inflater = (LayoutInflater) reciving_notificationss.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.cell_reciving_notification, null);

            //holder.id_order = (TextView) convertView.findViewById(R.id.id_order);
            holder.ddate = (TextView) convertView.findViewById(R.id.ddate);
            holder.notification = (TextView) convertView.findViewById(R.id.notification);
           /* holder.quntity = (TextView) convertView.findViewById(R.id.quntity);
            holder.price_start = (TextView) convertView.findViewById(R.id.price_start);
            holder.price_sales = (TextView) convertView.findViewById(R.id.price_sales);
            holder.price_Compensatory = (TextView) convertView.findViewById(R.id.price_Compensatory);
            holder.display_expenses = (TextView) convertView.findViewById(R.id.display_expenses);
            holder.commission = (TextView) convertView.findViewById(R.id.commission);
            holder.dr_receiving = (TextView) convertView.findViewById(R.id.dr_receiving);
            holder.value_plus = (TextView) convertView.findViewById(R.id.value_plus);
*/


            convertView.setTag(holder);
        } else {
            holder = (List_Adapter_reciving_notification.ViewHolderItem) convertView.getTag();
        }


        //holder.id_order.setText(this.reciving_notificationss.countries.get(position).id_order);

       /* String uu = this.receivingss.countries.get(position).ddate;
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm");
        Date dddd = null;
        try {
            dddd = dateFormat.parse(uu.toString());
        } catch (ParseException e) {
            e.printStackTrace();
        }
*/

        holder.ddate.setText(this.reciving_notificationss.countries.get(position).ddate);
        holder.notification.setText(this.reciving_notificationss.countries.get(position).notification);
       /* holder.quntity.setText(this.receivingss.countries.get(position).quntity);
        holder.price_start.setText(this.receivingss.countries.get(position).price_start);
        holder.price_sales.setText(this.receivingss.countries.get(position).price_sales);
        holder.price_Compensatory.setText(this.receivingss.countries.get(position).price_Compensatory);
        holder.display_expenses.setText(this.receivingss.countries.get(position).display_expenses);
        holder.commission.setText(this.receivingss.countries.get(position).commission);
        holder.dr_receiving.setText(this.receivingss.countries.get(position).dr_receiving);
        holder.value_plus.setText(this.receivingss.countries.get(position).value_plus);

*/

        return convertView;
    }

}