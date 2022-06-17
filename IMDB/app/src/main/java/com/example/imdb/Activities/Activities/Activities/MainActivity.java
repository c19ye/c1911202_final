package com.example.imdb.Activities.Activities.Activities;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;


import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;

import com.example.imdb.R;

public class MainActivity extends AppCompatActivity {

    private Button search_data_btn;
    private Button watchList_data_btn;

    private String movieName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.mainActivityFragmentLayout,new ImdbFragment(),"fragmentt")
                .setTransitionStyle(FragmentTransaction.TRANSIT_FRAGMENT_FADE)
                .commit();

        search_data_btn=findViewById(R.id.search_data_btn);
        watchList_data_btn=findViewById(R.id.watchList_data_btn);

        watchList_data_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               navigateMovieWatchListActivity();

            }
        });
        search_data_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //saveMovieNameToLocalDataSource(movie_input_edit_text.getText().toString());

                    //navigateSearchMovieActivity();
                getSupportFragmentManager().beginTransaction()
                        .replace(R.id.mainActivityFragmentLayout,new SearchandListFragment(),"fragment")
                        .setTransitionStyle(FragmentTransaction.TRANSIT_FRAGMENT_FADE)
                        .commit();


            }
        });
        movieName = getMovieNameFromLocalDataSource();


    }

    private void saveMovieNameToLocalDataSource(String movieName){
        this.movieName=movieName;

        String CONST_DATA="MOVIE_NAME";
        SharedPreferences preferences=this.getSharedPreferences(CONST_DATA,getApplicationContext().MODE_PRIVATE);
        SharedPreferences.Editor editor=preferences.edit();
        editor.putString(CONST_DATA,String.valueOf(movieName));
        editor.apply();

    }
    private String getMovieNameFromLocalDataSource(){
        String result;
        String CONST_DATA="MOVIE_NAME";
        SharedPreferences preferences=this.getSharedPreferences(CONST_DATA,getApplicationContext().MODE_PRIVATE);
        result=preferences.getString(CONST_DATA,"");
        return result;
    }
    private void navigateMovieWatchListActivity(){
        Intent movieListIntent=new Intent(MainActivity.this,WatchListActivity.class);
        startActivity(movieListIntent);
    }

    private void navigateSearchMovieActivity(){
        if (!movieName.equals("") && !movieName.equals(" ")){
        Intent searchMovieListIntent=new Intent(MainActivity.this,ListMoviesActivity.class);
        searchMovieListIntent.putExtra("movie_name",movieName);
        startActivity(searchMovieListIntent);}

    }
}