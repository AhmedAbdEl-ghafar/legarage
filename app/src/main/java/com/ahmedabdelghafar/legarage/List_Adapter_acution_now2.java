package com.ahmedabdelghafar.legarage;

import android.content.Context;
import android.content.Intent;
import android.graphics.Typeface;
import android.net.Uri;
import android.support.v7.widget.LinearLayoutManager;
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

import com.like.LikeButton;
import com.squareup.picasso.Picasso;

import java.util.List;


public class List_Adapter_acution_now2 extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
     Home homess;
    private final int VIEW_TYPE_ITEM=0,VIEW_TYPE_LOADING=1;
    private ILoadMore onLoadMoreListener;
    private int lastVisibleItem, totalItemCount;
    private boolean isLoading;
    private int visibleThreshold = 5;


    List_Adapter_acution_now2(Home homess) {
        this.homess = homess;
    }


    private Context context;
    private List<Countries> countries;

    public List_Adapter_acution_now2 (RecyclerView recyclerView,Context c,List<Countries> countries) {
        this.context = homess;
        countries = countries;

        final LinearLayoutManager linearLayoutManager = (LinearLayoutManager) recyclerView.getLayoutManager();
        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                totalItemCount = linearLayoutManager.getItemCount();
                lastVisibleItem = linearLayoutManager.findLastVisibleItemPosition();
                if (!isLoading && totalItemCount <= (lastVisibleItem + visibleThreshold)) {
                    if (onLoadMoreListener != null) {
                        onLoadMoreListener.onLoadMore();
                    }
                    isLoading = true;
                }
            }
        });

    }


    public void setOnLoadMoreListener(ILoadMore mOnLoadMoreListener) {
        this.onLoadMoreListener = mOnLoadMoreListener;
    }


    @Override
    public int getItemViewType(int position) {
        return (homess.countries.get(position) == null) ? VIEW_TYPE_LOADING : VIEW_TYPE_ITEM;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
       if (viewType == VIEW_TYPE_LOADING) {
           View view = LayoutInflater.from(homess).inflate(R.layout.item_loading, viewGroup, false);
           return new LoadingViewHolder(view);
        } else if (viewType == VIEW_TYPE_ITEM){
           View view = LayoutInflater.from(homess).inflate(R.layout.cell_acution_now, viewGroup, false);
           return new ViewHolder(view);
        }
        return null;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof ViewHolder) {
            ViewHolder userViewHolder = (ViewHolder) holder;
            userViewHolder.id_order.setText(this.homess.countries.get(position).id_order);
            userViewHolder.discreption.setText(this.homess.countries.get(position).discreption);
            userViewHolder.url.setText(this.homess.countries.get(position).url);
            userViewHolder.url.setLinksClickable(true);
            userViewHolder.url.setAutoLinkMask(Linkify.WEB_URLS);
            userViewHolder.url.setMovementMethod(LinkMovementMethod.getInstance());
            Linkify.addLinks(userViewHolder.url, Linkify.WEB_URLS);
            userViewHolder.date_of_close.setText(this.homess.countries.get(position).date_of_close);
            Picasso.with(homess).load(homess.countries.get(position).imagessflog).placeholder(R.drawable.ahmed
            ).into(userViewHolder.imagess_flogss);

            userViewHolder.likebutton.setLiked(false);

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
            userViewHolder.lagarge_name.setTypeface(face);


        }
        else if (holder instanceof LoadingViewHolder) {
            LoadingViewHolder loadingViewHolder = (LoadingViewHolder) holder;
            loadingViewHolder.progressBar.setIndeterminate(true);
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

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        TextView id_order;
        TextView discreption;
        TextView url;
        TextView date_of_close;
        ImageView imagess_flogss;
        TextView lagarge_name ;
        RecyclerView recyclerView;
        public ProgressBar progressBar;
        LikeButton likebutton;

        public ViewHolder(View view) {
            super(view);
            id_order = (TextView) itemView.findViewById(R.id.id_order);
            discreption = (TextView) itemView.findViewById(R.id.discreption);
            url = (TextView) itemView.findViewById(R.id.url);
            date_of_close = (TextView) itemView.findViewById(R.id.date_of_close);
            imagess_flogss = (ImageView) itemView.findViewById(R.id.imagess_flog);
            lagarge_name = (TextView) itemView.findViewById(R.id.legarage_name);
            likebutton = (LikeButton) itemView.findViewById(R.id.star_button);
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
//                Intent i = new Intent(Intent.ACTION_VIEW, uri);
                Intent i = new Intent(homess, bid.class);
                homess.startActivity(i);

            } catch (Exception e) {
                Toast.makeText(homess,"جاري العمل علي ربط المزاد علي الفيس بوك" , Toast.LENGTH_LONG).show();
            }
        }
    }


}
