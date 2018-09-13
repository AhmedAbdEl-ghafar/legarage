package com.ahmedabdelghafar.legarage;

import android.content.Context;
import android.content.Intent;
import android.graphics.Typeface;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.text.method.LinkMovementMethod;
import android.text.util.Linkify;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by orcl on 27/04/2018.
 */

public class List_Adapter_acution_now3 extends RecyclerView.Adapter<List_Adapter_acution_now3.ViewHolder> {
    Home2 homess;
    private final int VIEW_TYPE_ITEM=0,VIEW_TYPE_LOADING=1;

    List_Adapter_acution_now3(Home2 homess) {
        this.homess = homess;
    }

    @Override
    public int getItemViewType(int position) {
        return (homess.countries.get(position) == null) ? VIEW_TYPE_LOADING : VIEW_TYPE_ITEM;
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        TextView id_order;
        TextView discreption;
        TextView url;
        TextView date_of_close;
        ImageView imagess_flogss;
        TextView lagarge_name ;
        RecyclerView recyclerView;
        public ProgressBar progressBar;

        public ViewHolder(View view) {
            super(view);
            id_order = (TextView) itemView.findViewById(R.id.id_order);
            discreption = (TextView) itemView.findViewById(R.id.discreption);
            url = (TextView) itemView.findViewById(R.id.url);
            date_of_close = (TextView) itemView.findViewById(R.id.date_of_close);
            imagess_flogss = (ImageView) itemView.findViewById(R.id.imagess_flog);
            lagarge_name = (TextView) itemView.findViewById(R.id.legarage_name);

            recyclerView = (RecyclerView) itemView.findViewById(R.id.acution_open);

            //Uri uri = Uri.parse("https://raw.githubusercontent.com/facebook/fresco/master/docs/static/logo.png");
            //SimpleDraweeView draweeView = (SimpleDraweeView) itemView.findViewById(R.id.imagess_flog);
            //draweeView.setImageURI(uri);


            itemView.setOnClickListener(this);
            //SimpleDraweeView draweeView = (SimpleDraweeView) itemView.findViewById(R.id.imagess_flog);

        }

        @Override
        public void onClick(View view) {
            try {
                String selected = ((TextView) view.findViewById(R.id.url)).getText().toString();
                Uri uri = Uri.parse(selected);
                Intent i = new Intent(Intent.ACTION_VIEW, uri);
                homess.startActivity(i);

            } catch (Exception e) {
                Toast.makeText(homess,"جاري العمل علي ربط المزاد علي الفيس بوك" , Toast.LENGTH_LONG).show();
            }
        }
    }

    private Context context;
    private List<Countries> countries;

    public List_Adapter_acution_now3 (Context c,List<Countries> countries) {
        this.context = homess;
        countries = countries;
    }

    @Override
    public List_Adapter_acution_now3.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        if (viewType == VIEW_TYPE_LOADING) {
            View view = LayoutInflater.from(homess).inflate(R.layout.item_loading, viewGroup, false);
            return new List_Adapter_acution_now3.ViewHolder(view);
        } else if (viewType == VIEW_TYPE_ITEM){
            View view = LayoutInflater.from(homess).inflate(R.layout.cell_acution_now, viewGroup, false);
            return new List_Adapter_acution_now3.ViewHolder(view);
        }
        return null;
    }

    @Override
    public void onBindViewHolder(List_Adapter_acution_now3.ViewHolder holder, int position) {
        if (holder instanceof List_Adapter_acution_now3.ViewHolder) {
            List_Adapter_acution_now3.ViewHolder userViewHolder = (List_Adapter_acution_now3.ViewHolder) holder;
            holder.id_order.setText(this.homess.countries.get(position).id_order);
            holder.discreption.setText(this.homess.countries.get(position).discreption);
            holder.url.setText(this.homess.countries.get(position).url);
            holder.url.setLinksClickable(true);
            holder.url.setAutoLinkMask(Linkify.WEB_URLS);
            holder.url.setMovementMethod(LinkMovementMethod.getInstance());
            Linkify.addLinks(holder.url, Linkify.WEB_URLS);
            holder.date_of_close.setText(this.homess.countries.get(position).date_of_close);
            Picasso.with(homess).load(homess.countries.get(position).imagessflog).placeholder(R.drawable.ahmed
            ).into(holder.imagess_flogss);

            //Glide.with(homess).load(this.homess.countries.get(position).imagessflog).into(holder.imagess_flogss);
//            URL urls = null;
//            try {
//                urls = new URL("http://image10.bizrate-images.com/resize?sq=60&uid=2216744464");
//            } catch (MalformedURLException e) {
//                e.printStackTrace();
//            }
//            Bitmap bmp = null;
//            try {
//                bmp = BitmapFactory.decodeStream(urls.openConnection().getInputStream());
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//            holder.imagess_flogss.setImageBitmap(bmp);

            Typeface face=Typeface.createFromAsset(homess.getAssets(),"fonts/legaragefont.OTF");
            holder.lagarge_name.setTypeface(face);


        }
//        else if (null instanceof LoadingViewHolder) {
//            LoadingViewHolder loadingViewHolder = (LoadingViewHolder) holder;
//            loadingViewHolder.progressBar.setIndeterminate(true);
//        }
    }
    @Override
    public int getItemCount() {
        return homess.countries.size();
    }

    private class LoadingViewHolder extends RecyclerView.ViewHolder {
        public ProgressBar progressBar;

        public LoadingViewHolder(View view) {
            super(view);
            progressBar = (ProgressBar) view.findViewById(R.id.progressBar1);
        }
    }

}
