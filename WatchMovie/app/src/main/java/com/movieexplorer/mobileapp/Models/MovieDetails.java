package com.movieexplorer.mobileapp.Models;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class MovieDetails {
    public class BelongsToCollection{

        public BelongsToCollection(int id, String name, String poster_path, String backdrop_path) {
            this.id = id;
            this.name = name;
            this.poster_path = poster_path;
            this.backdrop_path = backdrop_path;
        }

        public int id;
        public String name;
        public String poster_path;
        public String backdrop_path;
    }

    public class Cast{

        public Cast(boolean adult, int gender, int id, String known_for_department, String name, String original_name, double popularity, String profile_path, int cast_id, String character, String credit_id, int order) {
            this.adult = adult;
            this.gender = gender;
            this.id = id;
            this.known_for_department = known_for_department;
            this.name = name;
            this.original_name = original_name;
            this.popularity = popularity;
            this.profile_path = profile_path;
            this.cast_id = cast_id;
            this.character = character;
            this.credit_id = credit_id;
            this.order = order;
        }

        public boolean adult;
        public int gender;
        public int id;
        public String known_for_department;
        public String name;
        public String original_name;
        public double popularity;
        public String profile_path;
        public int cast_id;
        public String character;
        public String credit_id;
        public int order;
    }

    public class Crew{

        public Crew(boolean adult, int gender, int id, String known_for_department, String name, String original_name, double popularity, String profile_path, String credit_id, String department, String job) {
            this.adult = adult;
            this.gender = gender;
            this.id = id;
            this.known_for_department = known_for_department;
            this.name = name;
            this.original_name = original_name;
            this.popularity = popularity;
            this.profile_path = profile_path;
            this.credit_id = credit_id;
            this.department = department;
            this.job = job;
        }

        public boolean adult;
        public int gender;
        public int id;
        public String known_for_department;
        public String name;
        public String original_name;
        public double popularity;
        public String profile_path;
        public String credit_id;
        public String department;
        public String job;
    }

    public class Genre{

        public Genre(int id, String name) {
            this.id = id;
            this.name = name;
        }

        public int id;
        public String name;
    }

    public class ProductionCompany{

        public ProductionCompany(int id, String logo_path, String name, String origin_country) {
            this.id = id;
            this.logo_path = logo_path;
            this.name = name;
            this.origin_country = origin_country;
        }

        public int id;
        public String logo_path;
        public String name;
        public String origin_country;
    }

    public class ProductionCountry{

        public ProductionCountry(String iso_3166_1, String name) {
            this.iso_3166_1 = iso_3166_1;
            this.name = name;
        }

        public String iso_3166_1;
        public String name;
    }

    public class Root{

        public Root(boolean adult, String backdrop_path, BelongsToCollection belongs_to_collection, int budget, ArrayList<Genre> genres, String homepage, int id, String imdb_id, String original_language, String original_title, String overview, double popularity, String poster_path, ArrayList<ProductionCompany> production_companies, ArrayList<ProductionCountry> production_countries, String release_date, int revenue, int runtime, ArrayList<SpokenLanguage> spoken_languages, String status, String tagline, String title, boolean video, double vote_average, int vote_count, ArrayList<Cast> cast, ArrayList<Crew> crew) {
            this.adult = adult;
            this.backdrop_path = backdrop_path;
            this.belongs_to_collection = belongs_to_collection;
            this.budget = budget;
            this.genres = genres;
            this.homepage = homepage;
            this.id = id;
            this.imdb_id = imdb_id;
            this.original_language = original_language;
            this.original_title = original_title;
            this.overview = overview;
            this.popularity = popularity;
            this.poster_path = poster_path;
            this.production_companies = production_companies;
            this.production_countries = production_countries;
            this.release_date = release_date;
            this.revenue = revenue;
            this.runtime = runtime;
            this.spoken_languages = spoken_languages;
            this.status = status;
            this.tagline = tagline;
            this.title = title;
            this.video = video;
            this.vote_average = vote_average;
            this.vote_count = vote_count;
            this.cast = cast;
            this.crew = crew;
        }

        public boolean adult;
        public String backdrop_path;
        public BelongsToCollection belongs_to_collection;
        public int budget;
        public ArrayList<Genre> genres;
        public String homepage;
        public int id;
        public String imdb_id;
        public String original_language;
        public String original_title;
        public String overview;
        public double popularity;
        public String poster_path;
        public ArrayList<ProductionCompany> production_companies;
        public ArrayList<ProductionCountry> production_countries;
        public String release_date;
        public int revenue;
        public int runtime;
        public ArrayList<SpokenLanguage> spoken_languages;
        public String status;
        public String tagline;
        public String title;
        public boolean video;
        public double vote_average;
        public int vote_count;
        public ArrayList<Cast> cast;
        public ArrayList<Crew> crew;
    }

    public class SpokenLanguage{

        public SpokenLanguage(String english_name, String iso_639_1, String name) {
            this.english_name = english_name;
            this.iso_639_1 = iso_639_1;
            this.name = name;
        }

        public String english_name;
        public String iso_639_1;
        public String name;
    }

}

