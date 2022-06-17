package com.example.imdb.Activities.Activities.Activities;



import static androidx.constraintlayout.motion.widget.MotionScene.TAG;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;

import com.example.imdb.Activities.Activities.Adapter.MovieResultAdapter;
import com.example.imdb.Activities.Activities.Entity.MovieResult;
import com.example.imdb.Activities.Activities.Entity.Result;
import com.example.imdb.R;
import com.google.gson.Gson;

import java.io.IOException;
import java.util.List;
import java.util.ArrayList;


import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class ListMoviesActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_movies);


    }

}