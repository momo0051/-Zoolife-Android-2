package com.zoolife.app.adapter;

import android.content.Context;
import android.net.Uri;

import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;


import com.zoolife.app.R;
import com.zoolife.app.interfaces.ImageListener;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by Rana on 8/12/2016.
 */
public class AdapterAdsImages extends RecyclerView.Adapter<AdapterAdsImages.MyViewHolder> {

    private List<Uri> adsImagesList;
    private Context _cntx;
    private ImageListener listener;
    int selectedPosition=0;

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public ImageView ad_img;

        public MyViewHolder(View view) {
            super(view);
            ad_img = (ImageView) view.findViewById(R.id.ad_img);
        }
    }

    public AdapterAdsImages(List<Uri> adsImagesList, Context _cntx, ImageListener listener) {
        this.adsImagesList = adsImagesList;
        this._cntx = _cntx;
        this.listener = listener;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView;

            itemView = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.row_adimage, parent, false);
        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, final int position) {
        final Uri imageuri = adsImagesList.get(position);

        if (imageuri.toString().startsWith("http")) {
            Picasso.get()
                    .load(imageuri)
                    .into(holder.ad_img);
        } else {
            holder.ad_img.setImageURI(imageuri);
        }

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                selectedPosition=position;
                listener.getSelectedImage(position);
                notifyDataSetChanged();
            }
        });

    }

    @Override
    public int getItemCount() {
        return adsImagesList.size();
    }

}
