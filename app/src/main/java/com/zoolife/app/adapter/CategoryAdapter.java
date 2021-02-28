package com.zoolife.app.adapter;

import android.content.Context;
import android.content.res.ColorStateList;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.zoolife.app.R;
import com.zoolife.app.activity.SearchActivity;
import com.zoolife.app.fragments.HomeFragment;
import com.zoolife.app.models.CategoryModel;

import java.util.List;

public class CategoryAdapter extends RecyclerView.Adapter<CategoryAdapter.MyViewHolder> {
    Context context;
    List<CategoryModel> data;
    CategoryModel current;
    int row_index = -1;
    HomeFragment homeFragment;
    SearchActivity searchActivity;


    public CategoryAdapter(Context context, List<CategoryModel> data, HomeFragment homeFragment){
        this.context = context;
        this.data = data;
        this.homeFragment = homeFragment;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View searchResultView = LayoutInflater.from(parent.getContext()).inflate(R.layout.category_layout,parent,false);
        return new MyViewHolder(searchResultView);
    }

    @Override
    public void onBindViewHolder(@NonNull final MyViewHolder holder, final int position) {

        current = data.get(position);
        holder.itemTitle.setText(data.get(position).getName());
        Glide
        .with(context)
        .load(current.getImage())
        .diskCacheStrategy(DiskCacheStrategy.ALL)
        .centerCrop()
        .placeholder(R.drawable.placeholder)
        .diskCacheStrategy(DiskCacheStrategy.ALL)
        .into(holder.itemImage);


        holder.categoryLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                row_index=position;
                homeFragment.getSubCategory(Integer.parseInt(data.get(position).getId()));
                homeFragment.getAllPostByCategory(Integer.parseInt(data.get(position).getId()));
                notifyDataSetChanged();
            }
        });
        if(row_index==position){
            holder.categoryLayout.setSelected(true);
//            holder.categoryLayout.setBackground(context.getResources().getDrawable(R.drawable.border_blue_yellow));
//            holder.itemTitle.setTextColor(context.getResources().getColor(R.color.appColor));
//            holder.itemImage.setImageTintList(ColorStateList.valueOf(context.getResources().getColor(R.color.appColor)));

        }
        else
        {
            holder.categoryLayout.setSelected(false);
//            holder.categoryLayout.setBackground(context.getResources().getDrawable(R.drawable.border_blue));
//            holder.itemTitle.setTextColor(context.getResources().getColor(R.color.white));
//            holder.itemImage.setImageTintList(ColorStateList.valueOf(context.getResources().getColor(R.color.white)));
        }

    }
    @Override
    public int getItemCount() {
        return data.size();
    }

    public void setEvenData(){

    }

    public class MyViewHolder extends RecyclerView.ViewHolder{
        ImageView itemImage,itemFavImage;
        TextView itemTitle,itemPOstedDate,itemPostedBy,itemLocation;
        LinearLayout categoryLayout;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            itemImage = itemView.findViewById(R.id.image);
            itemTitle = itemView.findViewById(R.id.title);
            categoryLayout = itemView.findViewById(R.id.categoryLayout);

        }
    }
}
