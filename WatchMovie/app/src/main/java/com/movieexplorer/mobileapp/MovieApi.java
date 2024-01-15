package com.movieexplorer.mobileapp;

import android.content.Context;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.movieexplorer.mobileapp.Models.Movie;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MovieApi {

    private static final String API_URL = "https://app-vpigadas.herokuapp.com/api/movies/";
    private static final String HEADER_CONTENT_TYPE = "Content-Type";
    private static final String HEADER_VALUE_JSON = "application/json";

    private final Context context;

    public MovieApi(Context context) {
        this.context = context;
    }

    public interface MovieListResponseListener {
        void onSuccess(List<Movie> movieList);

        void onError(String error);
    }

    public void getMovieList(final MovieListResponseListener responseListener) {
        RequestQueue requestQueue = Volley.newRequestQueue(context);

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(
                Request.Method.GET,
                API_URL,
                null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            List<Movie> movieList = parseMovieList(response);
                            responseListener.onSuccess(movieList);
                        } catch (JSONException e) {
                            responseListener.onError("Error parsing JSON");
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        responseListener.onError("Error: " + error.getMessage());
                    }
                }
        ) {
            @Override
            public Map<String, String> getHeaders() {
                Map<String, String> headers = new HashMap<>();
                headers.put(HEADER_CONTENT_TYPE, HEADER_VALUE_JSON);
                return headers;
            }
        };

        requestQueue.add(jsonObjectRequest);
    }

    private List<Movie> parseMovieList(JSONObject response) throws JSONException {
        List<Movie> movieList = new ArrayList<>();

        JSONArray results = response.getJSONArray("results");
        for (int i = 0; i < results.length(); i++) {
            JSONObject movieJson = results.getJSONObject(i);
            Movie movie = parseMovie(movieJson);
            movieList.add(movie);
        }

        return movieList;
    }

    private Movie parseMovie(JSONObject movieJson) throws JSONException {
        // Extracting fields from the JSON object
        boolean adult = movieJson.getBoolean("adult");
        String backdropPath = movieJson.getString("backdrop_path");

        List<Integer> genreIds = new ArrayList<>();
        JSONArray genreIdsArray = movieJson.getJSONArray("genre_ids");
        for (int i = 0; i < genreIdsArray.length(); i++) {
            genreIds.add(genreIdsArray.getInt(i));
        }

        long id = movieJson.getLong("id");
        String originalLanguage = movieJson.getString("original_language");
        String originalTitle = movieJson.getString("original_title");
        String overview = movieJson.getString("overview");
        double popularity = movieJson.getDouble("popularity");
        String posterPath = movieJson.getString("poster_path");
        String releaseDate = movieJson.getString("release_date");
        String title = movieJson.getString("title");
        boolean video = movieJson.getBoolean("video");
        double voteAverage = movieJson.getDouble("vote_average");
        int voteCount = movieJson.getInt("vote_count");

        // Creating and returning the Movie object
        return new Movie(adult, backdropPath, genreIds, id, originalLanguage,
                originalTitle, overview, popularity, posterPath, releaseDate,
                title, video, voteAverage, voteCount);
    }

}

