package com.movieexplorer.mobileapp.Models;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.movieexplorer.mobileapp.MovieInfoActivity;
import com.movieexplorer.mobileapp.R;
import com.movieexplorer.mobileapp.SplashActivity;

import java.util.ArrayList;
import java.util.List;

public class MovieAdapter extends RecyclerView.Adapter<MovieViewHolder> {

    Context context;
    List<Movie> moviesList;
    List<FavouriteMovie> favMoviesList;
    String note = "";

    public MovieAdapter(Context context, ArrayList<FavouriteMovie> favMoviesList, String note) {
        this.context = context;
        this.favMoviesList = favMoviesList;
        this.note = note;
    }

    public MovieAdapter(Context context, List<Movie> moviesList, String note) {
        this.context = context;
        this.moviesList = moviesList;
        this.note = note;
    }

    @NonNull
    @Override
    public MovieViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new MovieViewHolder(LayoutInflater.from(context).inflate(R.layout.row_movie_item, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull MovieViewHolder holder, int position) {

        if(note.isEmpty()){
            Glide.with(context)
                    .load(moviesList.get(position).getPosterPath())
                    .into(holder.moviePoster);
            holder.movieTitle.setText(moviesList.get(position).getOriginalTitle()+" ("+moviesList.get(position).getReleaseDate().substring(0,4)+")");

            if(moviesList.get(position).isAdult()){
                holder.icon18plus.setVisibility(View.VISIBLE);
            } else {
                holder.icon18plus.setVisibility(View.GONE);
            }

            holder.iconLang.setText(moviesList.get(position).getOriginalLanguage());

            holder.moviePoster.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(context, MovieInfoActivity.class);
                    intent.putExtra("movie_details_url", SplashActivity.GLOBAL_MOVIE_URL +""+moviesList.get(holder.getAdapterPosition()).getId());
                    context.startActivity(intent);
                }
            });
        } else {
            Glide.with(context)
                    .load(favMoviesList.get(position).getPoster_path())
                    .into(holder.moviePoster);
            holder.movieTitle.setText(favMoviesList.get(position).getOriginalTitle()+" ("+favMoviesList.get(position).getReleaseDate().substring(0,4)+")");

            if(favMoviesList.get(position).isAdult()){
                holder.icon18plus.setVisibility(View.VISIBLE);
            } else {
                holder.icon18plus.setVisibility(View.GONE);
            }

            holder.iconLang.setText(favMoviesList.get(position).getOriginalLanguage());

//            holder.moviePoster.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View view) {
//                    Intent intent = new Intent(context, MovieInfoActivity.class);
//                    intent.putExtra("movie_details_url", SplashActivity.GLOBAL_MOVIE_URL +""+moviesList.get(holder.getAdapterPosition()).getId());
//                    context.startActivity(intent);
//                }
//            });
        }

    }

    @Override
    public int getItemCount() {
        if (note.isEmpty()){
            return moviesList.size();
        } else {
            return favMoviesList.size();
        }
    }
}
