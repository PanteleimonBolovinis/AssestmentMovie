package com.movieexplorer.mobileapp.Models;

import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.movieexplorer.mobileapp.R;

public class MovieViewHolder extends RecyclerView.ViewHolder {

    ImageView moviePoster, icon18plus;
    TextView movieTitle, iconLang;
    public MovieViewHolder(@NonNull View itemView) {
        super(itemView);

        moviePoster = itemView.findViewById(R.id.moviePoster);
        movieTitle = itemView.findViewById(R.id.movieTitle);
        icon18plus = itemView.findViewById(R.id.icon18plus);
        iconLang = itemView.findViewById(R.id.iconLanguage);

    }
}
