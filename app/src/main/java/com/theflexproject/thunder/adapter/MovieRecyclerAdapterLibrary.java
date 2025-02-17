package com.theflexproject.thunder.adapter;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
import com.bumptech.glide.request.RequestOptions;
import com.theflexproject.thunder.Constants;
import com.theflexproject.thunder.R;
import com.theflexproject.thunder.model.File;
import com.theflexproject.thunder.utils.sizetoReadablesize;

import java.util.List;



public class MovieRecyclerAdapterLibrary extends RecyclerView.Adapter<MovieRecyclerAdapterLibrary.MovieRecyclerAdapterLibraryHolder> {

    Context context;
    List<File> movieList;
    private MovieRecyclerAdapter.OnItemClickListener listener;
    public MovieRecyclerAdapterLibrary(Context context, List<File> movieList, MovieRecyclerAdapter.OnItemClickListener listener) {
        this.context = context;
        this.movieList = movieList;
        this.listener= listener;
    }

    @NonNull
    @Override
    public MovieRecyclerAdapterLibraryHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.movie_item_library, parent, false);
        return new MovieRecyclerAdapterLibraryHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MovieRecyclerAdapterLibraryHolder holder, int position) {
        if(movieList.get(position).getTitle()!=null){
            holder.name.setText(movieList.get(position).getTitle());
            Glide.with(context)
                    .load(Constants.TMDB_IMAGE_BASE_URL+movieList.get(position).getPoster_path())
                    .placeholder(new ColorDrawable(Color.BLACK))
                    .apply(RequestOptions.bitmapTransform(new RoundedCorners(14)))
                    .into(holder.poster);
        }else{
            holder.name.setText(movieList.get(position).getName());
        }
        holder.textSize.setText(sizetoReadablesize.humanReadableByteCountBin(Long.parseLong(movieList.get(position).getSize())));


    }

    @Override
    public int getItemCount() {
        return (movieList==null)?0:movieList.size();
    }



    public class MovieRecyclerAdapterLibraryHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        TextView name;
        ImageView poster;
        TextView textSize;
        public MovieRecyclerAdapterLibraryHolder(@NonNull View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.movieName);
            poster= itemView.findViewById(R.id.moviePoster2);
            textSize = itemView.findViewById(R.id.text_size);
            itemView.setOnClickListener(this);
        }



        @Override
        public void onClick(View v) {
            listener.onClick(v,getAbsoluteAdapterPosition());
        }
    }
}
