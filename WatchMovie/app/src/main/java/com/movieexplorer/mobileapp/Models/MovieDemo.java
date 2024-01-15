package com.movieexplorer.mobileapp.Models;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class MovieDemo {

    public MovieDemo(List<MovieItem> results) {
        this.results = results;
    }

    @SerializedName("results")
    private List<MovieItem> results;

    public List<MovieItem> getResults() {
        return results;
    }

    public static class MovieItem {

        public MovieItem(String posterPath, String overview, String releaseDate, long id, String originalTitle, String title, double popularity, int voteCount, double voteAverage, String backdropPath) {
            this.posterPath = posterPath;
            this.overview = overview;
            this.releaseDate = releaseDate;
            this.id = id;
            this.originalTitle = originalTitle;
            this.title = title;
            this.popularity = popularity;
            this.voteCount = voteCount;
            this.voteAverage = voteAverage;
            this.backdropPath = backdropPath;
        }

        @SerializedName("poster_path")
        private String posterPath;

        @SerializedName("overview")
        private String overview;

        @SerializedName("release_date")
        private String releaseDate;

        @SerializedName("id")
        private long id;

        @SerializedName("original_title")
        private String originalTitle;

        @SerializedName("title")
        private String title;

        @SerializedName("popularity")
        private double popularity;

        @SerializedName("vote_count")
        private int voteCount;

        @SerializedName("vote_average")
        private double voteAverage;

        @SerializedName("backdrop_path")
        private String backdropPath; // Added backdrop_path based on the last item in the response

        public String getPosterPath() {
            return posterPath;
        }

        public String getOverview() {
            return overview;
        }

        public String getReleaseDate() {
            return releaseDate;
        }

        public long getId() {
            return id;
        }

        public String getOriginalTitle() {
            return originalTitle;
        }

        public String getTitle() {
            return title;
        }

        public double getPopularity() {
            return popularity;
        }

        public int getVoteCount() {
            return voteCount;
        }

        public double getVoteAverage() {
            return voteAverage;
        }

        public String getBackdropPath() {
            return backdropPath;
        }
    }
}

