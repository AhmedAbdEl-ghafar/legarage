package com.ahmedabdelghafar.legarage;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Typeface;
import android.os.AsyncTask;
import android.text.method.LinkMovementMethod;
import android.text.util.Linkify;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.io.InputStream;


/**
 * Created by orcl on 20/08/2017.
 */

public class List_Adapter_acution_now extends BaseAdapter {
//public class List_Adapter_acution_now extends RecyclerView.Adapter<RecyclerAdapter.ViewHolder> {
    Home homess;


    List_Adapter_acution_now(Home homess) {
        this.homess = homess;
    }

    @Override
    public int getCount() {
        return homess.countries.size();
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
        //TextView ddate;
        TextView discreption;
        TextView url;
        //TextView date_of_open;
        TextView date_of_close;
        ImageView imagess_flogss;
        TextView lagarge_name ;


    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        List_Adapter_acution_now.ViewHolderItem holder = new List_Adapter_acution_now.ViewHolderItem();
        if (convertView == null) {
            LayoutInflater inflater = (LayoutInflater) homess.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.cell_acution_now, null);

            holder.id_order = (TextView) convertView.findViewById(R.id.id_order);
            //holder.ddate = (TextView) convertView.findViewById(R.id.ddate);
            holder.discreption = (TextView) convertView.findViewById(R.id.discreption);
            holder.url = (TextView) convertView.findViewById(R.id.url);
            //holder.date_of_open = (TextView) convertView.findViewById(R.id.date_of_open);
            holder.date_of_close = (TextView) convertView.findViewById(R.id.date_of_close);
            holder.imagess_flogss = (ImageView) convertView.findViewById(R.id.imagess_flog);
          /*  holder.weight_st = (TextView) convertView.findViewById(R.id.weight_st);
            holder.start_price = (TextView) convertView.findViewById(R.id.start_price);
            holder.end_price = (TextView) convertView.findViewById(R.id.end_price);
           */ //holder.dr_receiving = (TextView) convertView.findViewById(R.id.dr_receiving);
            //holder.value_plus = (TextView) convertView.findViewById(R.id.value_plus);
            holder.lagarge_name = (TextView) convertView.findViewById(R.id.legarage_name);
            Typeface face=Typeface.createFromAsset(homess.getAssets(),"fonts/legaragefont.OTF");
            holder.lagarge_name.setTypeface(face);


            convertView.setTag(holder);
        } else {
            holder = (List_Adapter_acution_now.ViewHolderItem) convertView.getTag();
        }


        holder.id_order.setText(this.homess.countries.get(position).id_order);
        //holder.ddate.setText(this.homess.countries.get(position).ddate);
        holder.discreption.setText(this.homess.countries.get(position).discreption);
        holder.url.setText(this.homess.countries.get(position).url);
        holder.url.setLinksClickable(true);
        holder.url.setAutoLinkMask(Linkify.WEB_URLS);
        holder.url.setMovementMethod(LinkMovementMethod.getInstance());
        //If the edgeit text contains previous text with potential links
        Linkify.addLinks(holder.url, Linkify.WEB_URLS);

        //holder.date_of_open.setText(this.homess.countries.get(position).date_of_open);
        holder.date_of_close.setText(this.homess.countries.get(position).date_of_close);

        //String pathToFile = this.homess.countries.get(position).imagessflog;
        //DownloadImageWithURLTask downloadTask = new DownloadImageWithURLTask(holder.imagess_flogss);
        //downloadTask.execute(pathToFile);

       /* Picasso.with(homess.getApplicationContext())
                .load("http://197.51.253.242:3000/api/image/view_image_app/"+holder.id_order.getText())
                //.resize(500, 500)
                //.centerCrop()
                .into(holder.imagess_flogss);
*/


        //holder.imagess_flog.get
        //new DownloadImageTask(holder.imagess_flogss).execute(this.homess.countries.get(position).imagessflog);

        //ImageLoader imgLoader = new ImageLoader(homess.getApplicationContext());
        // holder.imagess_flogss.setImageResource(R.drawable.icon_legarage);
        //Picasso.with(holder.imagess_flogss.getContext()).load(this.homess.countries.get(position).imagessflog).into(holder.imagess_flogss);
        //Glide.with(holder.imagess_flogss.getContext()).load(this.homess.countries.get(position).imagessflog).into(holder.imagess_flogss);
        //Glide.get(homess.getApplicationContext()).clearDiskCache();
        //try {
        //String sss = this.homess.countries.get(position).imagessflog;
        Glide.with(homess.getApplicationContext()).load(this.homess.countries.get(position).imagessflog).into(holder.imagess_flogss);
        // try {
        //ImageView i = (ImageView)findViewById(R.id.image);
        //Bitmap bitmap = BitmapFactory.decodeStream((InputStream)new URL(this.homess.countries.get(position).imagessflog).getContent());

            /*URL url = new URL(this.homess.countries.get(position).imagessflog);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setReadTimeout(60000 /* milliseconds );
           /* conn.setConnectTimeout(65000 /* milliseconds );
            /*conn.setRequestMethod("GET");
            conn.setDoInput(true);
            conn.connect();
            int response = conn.getResponseCode();
            //Log.d(TAG, "The response is: " + response);
            InputStream  is = conn.getInputStream();


            BufferedInputStream bufferedInputStream = new BufferedInputStream(is);

            Bitmap bmpImage = BitmapFactory.decodeStream(bufferedInputStream);
            holder.imagess_flogss.setImageBitmap(bmpImage);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        */
/*
            Observable.just(this.homess.countries.get(position).imagessflog)
                    .filter(new Predicate<String>() {
                        @Override public boolean test(String url) throws Exception {
                            return StringUtils.isNotBlank(url);
                        }
                    })
                    .map(new Function<String, Drawable>() {
                        @Override public Drawable apply(String s) throws Exception {
                            URL url = null;
                            try {
                                url = new URL(s);
                                return Drawable.createFromStream((InputStream) url.getContent(), "profile");
                            } catch (final IOException ex) {
                                return null;
                            }
                        }
                    })
                    .filter(new Predicate<Drawable>() {
                        @Override public boolean test(Drawable drawable) throws Exception {
                            return drawable != null;
                        }
                    })
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(new Consumer<Drawable>() {
                        @Override public void accept(Drawable drawable) throws Exception {

                            holder.imagess_flogss.setImageDrawable(drawable);

                        }
                    });
*/

         //Glide.with(homess.getApplicationContext())
           //      .load(this.homess.countries.get(position).imagessflog)
             //.signature(new StringSignature(String.valueOf(System.currentTimeMillis())))
             //    .into(holder.imagess_flogss);
        //} catch (Exception e) {
         // Glide.get(homess.getApplicationContext()).clearDiskCache();
         //}


     /*   Glide.with(homess.getApplicationContext())
                .load(this.homess.countries.get(position).imagessflog)
                .asBitmap()
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .placeholder(R.drawable.icon_legarage)
                .into(new BitmapImageViewTarget(holder.imagess_flogss) {
                    @Override
                    protected void setResource(Bitmap resource) {

                        RoundedBitmapDrawable circularBitmapDrawable = RoundedBitmapDrawableFactory.create(homess.getApplicationContext().getResources(), resource);
                        circularBitmapDrawable.setCircular(true);
                        viewHolder.friendIcon.setImageDrawable(circularBitmapDrawable);
                    }
                });
       */
        /*ImageLoader imgLoader = new ImageLoader(homess.getApplicationContext());
        imgLoader.DisplayImage(image_url, loader, image);
        int loader = R.drawable.icon_legarage;
       imgLoader.DisplayImage(this.homess.countries.get(position).imagessflog,loader,holder.imagess_flogss);
*/

        //holder.imagess_flog.DisplayImage(this.homess.countries.get(position).imagessflog);

        //holder.imagess_flog.setText(this.homess.countries.get(position).weight_st);
       /* holder.start_price.setText(this.readytoacutionss.countries.get(position).start_price);
        holder.end_price.setText(this.readytoacutionss.countries.get(position).end_price);
        // holder.dr_receiving.setText(this.discreptionss.countries.get(position).dr_receiving);
        //holder.value_plus.setText(this.discreptionss.countries.get(position).value_plus);


*/
        return convertView;
    }

    class DownloadImageTask extends AsyncTask<String, Void, Bitmap> {
        ImageView bmImage;

        public DownloadImageTask(ImageView bmImage) {
            this.bmImage = bmImage;
        }

        protected Bitmap doInBackground(String... urls) {
            String urldisplay = urls[0];
            Bitmap mIcon11 = null;
            try {
                InputStream in = new java.net.URL(urldisplay).openStream();
                mIcon11 = BitmapFactory.decodeStream(in);
            } catch (Exception e) {
                Log.e("Error", e.getMessage());
                e.printStackTrace();
            }
            return mIcon11;
        }

        protected void onPostExecute(Bitmap result) {
            bmImage.setImageBitmap(result);
        }


    }

    //public static List_Adapter_acution_now getInstance() {
    // TODO Auto-generated method stub
    //  return BaseAdapter;
//    }
    private class DownloadImageWithURLTask extends AsyncTask<String, Void, Bitmap> {
        ImageView bmImage;

        public DownloadImageWithURLTask(ImageView bmImage) {
            this.bmImage = bmImage;
        }

        protected Bitmap doInBackground(String... urls) {
            String pathToFile = urls[0];
            Bitmap bitmap = null;
            try {
                InputStream in = new java.net.URL(pathToFile).openStream();
                bitmap = BitmapFactory.decodeStream(in);
            } catch (Exception e) {
                Log.e("Error", e.getMessage());
                e.printStackTrace();
            }
            return bitmap;
        }

        protected void onPostExecute(Bitmap result) {
            bmImage.setImageBitmap(result);
        }
    }
}