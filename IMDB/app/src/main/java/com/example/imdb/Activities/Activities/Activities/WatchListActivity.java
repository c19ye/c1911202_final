package com.example.imdb.Activities.Activities.Activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.example.imdb.Activities.Activities.Adapter.MovieResultAdapter;
import com.example.imdb.Activities.Activities.Adapter.WatchMovieResultAdapter;
import com.example.imdb.Activities.Activities.DB.DB;
import com.example.imdb.Activities.Activities.Entity.Result;
import com.example.imdb.R;

import java.util.ArrayList;

public class WatchListActivity extends AppCompatActivity {

    private Result result;
    private RecyclerView watchmovie_list_recyclerview;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_watch_list);

        watchmovie_list_recyclerview = findViewById(R.id.watchmovie_list_recyclerview);

        this.setTitle("WATCHLIST");

        Bundle bundle=getIntent().getExtras();

        if (bundle!=null){
            result=bundle.getParcelable("result");
            //result nesnesini databaseye kaydedip çekeceğiz
            if(result!=null){
                DB.getInstance(this).addNewMovies(result.getId(),result.getResultType(),result.getImage(),result.getTitle(),result.getDescription());
            }
        }
        ArrayList<Result> movieArrayList=DB.getInstance(this).getMovieList();
        WatchMovieResultAdapter adapter=new WatchMovieResultAdapter(movieArrayList);
        RecyclerView.LayoutManager mlayoutManager=new LinearLayoutManager(getApplicationContext());
        watchmovie_list_recyclerview.setLayoutManager(mlayoutManager);
        watchmovie_list_recyclerview.setAdapter(adapter);
       if (bundle !=null){

           this.finish();
       }
    }


}