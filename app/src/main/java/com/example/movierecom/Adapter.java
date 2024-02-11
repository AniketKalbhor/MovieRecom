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
    private final SelectMovie selectMovie;
    private Context mContext;
    private List<MovieModalClass> mData;

    public Adapter(Context mContext, List<MovieModalClass> mData, SelectMovie selectMovie) {
        this.mContext = mContext;
        this.mData = mData;
        this.selectMovie = selectMovie;
    }

    @NonNull
    @Override
    public Adapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v;
        LayoutInflater inflater = LayoutInflater.from(mContext);
        v = inflater.inflate(R.layout.item, parent, false);

        return new MyViewHolder(v, selectMovie);
    }

    @Override
    public void onBindViewHolder(@NonNull Adapter.MyViewHolder holder, int position) {
        holder.title.setText(mData.get(position).getTitle());
        holder.overview.setText(mData.get(position).getOverview());
        holder.rating.setText(mData.get(position).getRating());

        Glide.with(mContext)
                .load("https://image.tmdb.org/t/p/w500"+mData.get(position).getImg())
                .into(holder.img);
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }
    public static class MyViewHolder extends RecyclerView.ViewHolder{
        TextView overview;
        TextView title;
        TextView rating;
        ImageView img;

//        CardView cardView;
        public MyViewHolder(@NonNull View itemView, SelectMovie selectMovie) {
            super(itemView);
            overview = itemView.findViewById(R.id.Overview);
//            cardView = itemView.findViewById(R.id.cardView);
            title = itemView.findViewById(R.id.Title);
            rating = itemView.findViewById(R.id.rating);
            img = itemView.findViewById(R.id.Poster);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(selectMovie !=null){
                        int pos = getAdapterPosition();
                        if(pos != RecyclerView.NO_POSITION){
                            selectMovie.onItemClick(pos);
                        }
                    }
                }
            });
        }
    }
}
