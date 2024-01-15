package com.movieexplorer.mobileapp.Models;

public class FavouriteMovie {

    boolean adult;
    String originalTitle, releaseDate, originalLanguage;
    double average_vote;
    long id;
    String poster_path;

    public String getPoster_path() {
        return poster_path;
    }

    public void setPoster_path(String poster_path) {
        this.poster_path = poster_path;
    }

    public FavouriteMovie(boolean adult, String originalTitle, String releaseDate, String originalLanguage, double average_vote, long id, String poster_path) {
        this.adult = adult;
        this.originalTitle = originalTitle;
        this.releaseDate = releaseDate;
        this.originalLanguage = originalLanguage;
        this.average_vote = average_vote;
        this.id = id;
        this.poster_path = poster_path;
    }

    public long getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public FavouriteMovie(boolean adult, String originalTitle, String releaseDate, String originalLanguage, double average_vote, int id) {
        this.adult = adult;
        this.originalTitle = originalTitle;
        this.releaseDate = releaseDate;
        this.originalLanguage = originalLanguage;
        this.average_vote = average_vote;
        this.id = id;
    }

    public FavouriteMovie(boolean adult, String originalTitle, String releaseDate, String originalLanguage, double average_vote) {
        this.adult = adult;
        this.originalTitle = originalTitle;
        this.releaseDate = releaseDate;
        this.originalLanguage = originalLanguage;
        this.average_vote = average_vote;
    }

    public boolean isAdult() {
        return adult;
    }

    public void setAdult(boolean adult) {
        this.adult = adult;
    }

    public String getOriginalTitle() {
        return originalTitle;
    }

    public void setOriginalTitle(String originalTitle) {
        this.originalTitle = originalTitle;
    }

    public String getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(String releaseDate) {
        this.releaseDate = releaseDate;
    }

    public String getOriginalLanguage() {
        return originalLanguage;
    }

    public void setOriginalLanguage(String originalLanguage) {
        this.originalLanguage = originalLanguage;
    }

    public double getAverage_vote() {
        return average_vote;
    }

    public void setAverage_vote(double average_vote) {
        this.average_vote = average_vote;
    }
}
