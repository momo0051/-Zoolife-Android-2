package com.zoolife.app.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.zoolife.app.R;
import com.zoolife.app.activity.AdInfoActivity;
import com.zoolife.app.models.ExploreModels;

import java.util.List;

public class ExploreAdapter extends RecyclerView.Adapter<ExploreAdapter.MyViewHolder> {

    Context context;
    List<ExploreModels> data;
    ExploreModels current;

    public ExploreAdapter(Context context, List<ExploreModels> data){
        this.context = context;
        this.data=data;
    }


    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View searchResultView = LayoutInflater.from(parent.getContext()).inflate(R.layout.single_explore_grid,parent,false);
        return new MyViewHolder(searchResultView);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        current = data.get(position);
        holder.title.setText(current.title);
        holder.postedDate.setText(current.date);
        holder.image.setImageResource(current.image);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, AdInfoActivity.class);
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{
        TextView title,postedDate;
        ImageView image;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
//            title = itemView.findViewById(R.id.title_article);
//            postedDate = itemView.findViewById(R.id.date_posted_article);
//            image = itemView.findViewById(R.id.explore_image);
        }
    }

}
