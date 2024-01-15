package com.movieexplorer.mobileapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.util.TypedValue;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;
import com.google.firebase.auth.FirebaseAuth;
import com.movieexplorer.mobileapp.Models.Movie;
import com.movieexplorer.mobileapp.Models.MovieAdapter;
import com.movieexplorer.mobileapp.Models.MyThread;

import org.json.JSONObject;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    private static final String MOVIE_DETAILS_URL = "https://app-vpigadas.herokuapp.com/api/movies/640146";
    private static final String MOVIE_URL = "https://app-vpigadas.herokuapp.com/api/movies/";
    private static final String MOVIE_DEMO_URL = "https://app-vpigadas.herokuapp.com/api/movies/demo/";
    private static final String MOVIE_DEMO_DETAILS_URL = "https://app-vpigadas.herokuapp.com/api/movies/demo/297761";



    BottomNavigationView bnv;
    Toolbar mainToolbar;
    ImageView logout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        new MyThread(MainActivity.this).start();

        bnv = findViewById(R.id.mainBottomNavigation_BNV_Id);
        mainToolbar = findViewById(R.id.mainToolbar);
        logout = findViewById(R.id.logout_IV_Id);

        getSupportFragmentManager().beginTransaction().replace(R.id.mainFrame_FL_Id, new MoviesFragment()).commit();

        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FirebaseAuth.getInstance().signOut();
                startActivity(new Intent(MainActivity.this, SignUpActivity.class));
                new SavedSharedPreferences(MainActivity.this).setBooleanValue(false);
                finish();
            }
        });

        bnv.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                if (item.getItemId()==R.id.homePage){
                    getSupportFragmentManager().beginTransaction().replace(R.id.mainFrame_FL_Id, new MoviesFragment()).commit();

                    return true;
                } else if (item.getItemId() == R.id.favouritesPage) {
                    getSupportFragmentManager().beginTransaction().replace(R.id.mainFrame_FL_Id, new FavouritesFragment()).commit();

                    return true;
                }
                return true;
            }
        });

    }


}