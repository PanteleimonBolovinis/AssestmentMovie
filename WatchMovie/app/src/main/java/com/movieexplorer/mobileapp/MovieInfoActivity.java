package com.movieexplorer.mobileapp;

import static android.content.ContentValues.TAG;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.bumptech.glide.Glide;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;
import com.movieexplorer.mobileapp.Models.FavouriteMovie;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class MovieInfoActivity extends AppCompatActivity {

    ImageView backdrop;
    ImageButton shareMovie;
    String movieHomepage, backdropImageLink;
    String movieUrl = "";
    ImageButton addToFavourites;
    FirebaseFirestore database;
    TextView movieName, movieRating, movieSynopsis, movieCast, movieGenre;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_info);

        try {
            movieUrl = getIntent().getExtras().getString("movie_details_url");
        } catch (Exception e){
            Toast.makeText(this, "Getting main Link: "+e, Toast.LENGTH_SHORT).show();
        }

        movieCast = findViewById(R.id.cast_TV_Id);
        movieSynopsis = findViewById(R.id.movieSynopsis_TV_Id);
        movieRating = findViewById(R.id.movieRating_TV_Id);
        movieName = findViewById(R.id.movieName_TV_Id);
        shareMovie = findViewById(R.id.shareMovie_B_Id);
        movieGenre = findViewById(R.id.genre_TV_Id);
        backdrop = findViewById(R.id.movieBackdrop_IV_Id);
        addToFavourites = findViewById(R.id.addToFavourites_B_Id);
        database = FirebaseFirestore.getInstance();

        RequestQueue requestQueue = Volley.newRequestQueue(MovieInfoActivity.this);

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(
                Request.Method.GET,
                movieUrl,
                null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            if(response.getString("homepage").contains("http")){
                                movieHomepage = response.getString("homepage");
                            } else {
                                movieHomepage = response.getString("poster_path");
                            }
                            if(response.has("belongs_to_collection")){
                                JSONObject belongs_to_collection = response.getJSONObject("belongs_to_collection");
                                backdropImageLink = belongs_to_collection.getString("backdrop_path");
                            } else {
                                backdropImageLink = response.getString("backdrop_path");
                            }
                            movieName.setText(response.getString("original_title")+" ("+response.getString("release_date").substring(0,4)+")");
                            movieRating.setText(""+response.getDouble("vote_average")+"/10");
                            movieSynopsis.setText(response.getString("overview"));



                            //show genres




                            String jsonData = response.toString();
                            try {
                                // Parse the JSON string
                                JSONObject jsonObject = new JSONObject(jsonData);

                                // Get the "cast" array
                                JSONArray castArray = jsonObject.getJSONArray("cast");

                                // Create a StringBuilder to store the names
                                StringBuilder namesStringBuilder = new StringBuilder();

                                // Iterate through the "cast" array
                                for (int i = 0; i < castArray.length(); i++) {
                                    // Get the current object in the array
                                    JSONObject castObject = castArray.getJSONObject(i);

                                    // Get the "name" from the current object
                                    String name = castObject.getString("name");

                                    // Append the name to the StringBuilder
                                    namesStringBuilder.append(name);

                                    // If it's not the last element, append a comma and space
                                    if (i < castArray.length() - 1) {
                                        namesStringBuilder.append(", ");
                                    }
                                }

                                // Convert the StringBuilder to a String
                                String namesString = namesStringBuilder.toString();


                                movieCast.setText(namesString);
                                // Print or use the namesString as needed
                                System.out.println("Names: " + namesString);

                            } catch (JSONException e) {
                                e.printStackTrace();

                            }

                            jsonData = response.toString();

                            try {
                                // Parse the JSON string
                                JSONObject jsonObject = new JSONObject(jsonData);

                                // Get the "cast" array
                                JSONArray castArray = jsonObject.getJSONArray("genres");

                                // Create a StringBuilder to store the names
                                StringBuilder namesStringBuilder = new StringBuilder();

                                // Iterate through the "cast" array
                                for (int i = 0; i < castArray.length(); i++) {
                                    // Get the current object in the array
                                    JSONObject castObject = castArray.getJSONObject(i);

                                    // Get the "name" from the current object
                                    String name = castObject.getString("name");

                                    // Append the name to the StringBuilder
                                    namesStringBuilder.append(name);

                                    // If it's not the last element, append a comma and space
                                    if (i < castArray.length() - 1) {
                                        namesStringBuilder.append(", ");
                                    }
                                }

                                // Convert the StringBuilder to a String
                                String namesString = namesStringBuilder.toString();


                                movieGenre.setText(namesString);
                                // Print or use the namesString as needed
                                System.out.println("Names: " + namesString);

                            } catch (JSONException e) {
                                e.printStackTrace();

                            }






                            Glide.with(MovieInfoActivity.this)
                                    .load(backdropImageLink)
                                    .into(backdrop);
                        } catch (Exception e){
                            Toast.makeText(MovieInfoActivity.this, "Response: "+e, Toast.LENGTH_SHORT).show();
                            Log.e(TAG, "onResponse&*&*&*&*&*&*&*&&**&*&: "+e.getLocalizedMessage());
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                    }
                }
        ) {
            @Override
            public Map<String, String> getHeaders() {
                Map<String, String> headers = new HashMap<>();
                headers.put("Content-Type", "application/json");
                return headers;
            }
        };

        requestQueue.add(jsonObjectRequest);

        shareMovie.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent shareIntent = new Intent(Intent.ACTION_SEND);
                shareIntent.putExtra(Intent.EXTRA_TEXT, movieHomepage);
                shareIntent.setType("text/plain");
                startActivity(Intent.createChooser(shareIntent,null));
            }
        });
        addToFavourites.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(MovieInfoActivity.this, "Added to Favourites", Toast.LENGTH_SHORT).show();
                RequestQueue requestQueue = Volley.newRequestQueue(MovieInfoActivity.this);

                JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(
                        Request.Method.GET,
                        movieUrl,
                        null,
                        new Response.Listener<JSONObject>() {
                            @Override
                            public void onResponse(JSONObject response) {
                                try {
                                    boolean repeat = false;
                                    FavouriteMovie favouriteMovie = new FavouriteMovie(response.getBoolean("adult"),
                                            response.getString("original_title"), response.getString("release_date"),
                                            response.getString("original_language"), response.getDouble("vote_average"),
                                            response.getInt("id"), response.getString("poster_path"));
                                    for(int i=0;i<SplashActivity.favouriteMoviesList.size();i++){
                                        if(favouriteMovie.getId() == SplashActivity.favouriteMoviesList.get(i).getId()){
                                            repeat = true;
                                        }
                                    }
                                    if(!repeat){
                                        SplashActivity.favouriteMoviesList.add(favouriteMovie);
                                    } else {
                                        Toast.makeText(MovieInfoActivity.this, "Already in Favourites", Toast.LENGTH_SHORT).show();
                                    }

                                    addToFirestore(favouriteMovie);

                                } catch (Exception e){
                                    Toast.makeText(MovieInfoActivity.this, "Response: "+e, Toast.LENGTH_SHORT).show();
                                    Log.e(TAG, "onResponse&*&*&*&*&*&*&*&&**&*&: "+e.getLocalizedMessage());
                                }
                            }
                        },
                        new Response.ErrorListener() {
                            @Override
                            public void onErrorResponse(VolleyError error) {

                            }
                        }
                ) {
                    @Override
                    public Map<String, String> getHeaders() {
                        Map<String, String> headers = new HashMap<>();
                        headers.put("Content-Type", "application/json");
                        return headers;
                    }
                };

                requestQueue.add(jsonObjectRequest);
            }
        });

    }

    private void addToFirestore(FavouriteMovie favouriteMovie) {

        Map<String, Object> movies = new HashMap<>();

        movies.put("userId", FirebaseAuth.getInstance().getCurrentUser().getUid());
        movies.put("adult", favouriteMovie.isAdult());
        movies.put("original_title", favouriteMovie.getOriginalTitle());
        movies.put("release_date", favouriteMovie.getReleaseDate());
        movies.put("original_language", favouriteMovie.getOriginalLanguage());
        movies.put("vote_average", favouriteMovie.getAverage_vote());
        movies.put("id", favouriteMovie.getId());
        movies.put("poster_path", favouriteMovie.getPoster_path());

        database.collection("FavouriteMovies").document()
                .set(movies)
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void unused) {

                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {

                    }
                });

    }
}