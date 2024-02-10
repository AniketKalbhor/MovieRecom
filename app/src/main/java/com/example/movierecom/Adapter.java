package com.example.movierecom;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.List;

public class Adapter extends RecyclerView.Adapter<Adapter.MyViewHolder> {
    private Context mContext;
    private List<MovieModalClass> mData;

    public Adapter(Context mContext, List<MovieModalClass> mData) {
        this.mContext = mContext;
        this.mData = mData;
    }

    @NonNull
    @Override
    public Adapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v;
        LayoutInflater inflater = LayoutInflater.from(mContext);
        v = inflater.inflate(R.layout.item, parent, false);

        return new MyViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull Adapter.MyViewHolder holder, int position) {
        holder.title.setText(mData.get(position).getTitle());
        holder.overview.setText(mData.get(position).getOverview());

        Glide.with(mContext)
                .load(mData.get(position).getImg())
                .into(holder.img);
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }
    public static class MyViewHolder extends RecyclerView.ViewHolder{
        TextView overview;
        TextView title;
        ImageView img;
//        CardView cardView;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            overview = itemView.findViewById(R.id.Overview);
//            cardView = itemView.findViewById(R.id.cardView);
            title = itemView.findViewById(R.id.Title);
            img = itemView.findViewById(R.id.Poster);
        }
    }
}
