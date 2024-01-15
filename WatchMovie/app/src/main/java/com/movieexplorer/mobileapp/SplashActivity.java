package com.movieexplorer.mobileapp;

import static androidx.constraintlayout.helper.widget.MotionEffect.TAG;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;
import com.movieexplorer.mobileapp.Models.FavouriteMovie;
import com.movieexplorer.mobileapp.Models.Movie;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class SplashActivity extends AppCompatActivity {

    public static List<Movie> moviesList = new ArrayList<>();
    public static ArrayList<FavouriteMovie> favouriteMoviesList = new ArrayList<>();
    public static final String GLOBAL_MOVIE_URL = "https://app-vpigadas.herokuapp.com/api/movies/";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        // get list in splash from fsdb

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {

                new MovieApi(SplashActivity.this).getMovieList(new MovieApi.MovieListResponseListener() {
                    @Override
                    public void onSuccess(List<Movie> movieList) {
                        moviesList = movieList;
                        Collections.shuffle(SplashActivity.moviesList);
                        if(new SavedSharedPreferences(SplashActivity.this).getBooleanValue()){

                            try {
                                FirebaseFirestore.getInstance().collection("FavouriteMovies")
                                        .whereEqualTo("userId", FirebaseAuth.getInstance().getCurrentUser().getUid())
                                        .get()
                                        .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                                            @Override
                                            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                                                if (task.isSuccessful()) {
                                                    for (QueryDocumentSnapshot document : task.getResult()) {
                                                        Log.e(TAG, "onTaskSuccessful" + " => " + document.getString("userId"));
                                                        favouriteMoviesList.add(new FavouriteMovie(document.getBoolean("adult"), document.getString("original_title"),
                                                                document.getString("release_date"), document.getString("original_language"), document.getDouble("vote_average"),
                                                                document.getLong("id"),  document.getString("poster_path")));
                                                        startActivity(new Intent(SplashActivity.this, MainActivity.class));
                                                        finish();
                                                    }
                                                } else {
                                                    Log.d(TAG, "Error getting documents: ", task.getException());
                                                }
                                            }
                                        });
                            } catch (Exception e){

                            }
                        } else {
                            startActivity(new Intent(SplashActivity.this, SignUpActivity.class));
                            finish();
                        }
                    }

                    @Override
                    public void onError(String error) {

                    }
                });

            }
        }, 3000);

    }
}