package com.example.imdb.Activities.Activities.Activities;

import static androidx.constraintlayout.motion.widget.MotionScene.TAG;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Handler;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Toast;

import com.example.imdb.Activities.Activities.Adapter.MovieResultAdapter;
import com.example.imdb.Activities.Activities.Entity.MovieResult;
import com.example.imdb.Activities.Activities.Entity.Result;
import com.example.imdb.R;
import com.google.gson.Gson;

import java.io.IOException;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;


public class SearchandListFragment extends Fragment {
    private String movieName;
    private RecyclerView movie_list_recyclerview;
    private EditText movie_input_edit_text;
    private long beginTime = 0, endTime = 0;
    View view;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        view=inflater.inflate(R.layout.fragment_searchand_list, container, false);
        // Inflate the layout for this fragment
        movie_list_recyclerview = view.findViewById(R.id.movie_list_recyclerview);


        //film ismi bilgisi bundle üzedrinden alınacak.
        //Bundle bundle=getIntent().getExtras();
        //movieName="";
        //if (bundle!=null){
        //   movieName=bundle.getString("movie_name");
        //}
        movie_input_edit_text=view.findViewById(R.id.movie_input_edit_text);
        movie_input_edit_text.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {


            }

            private Timer timer = new Timer();
            private final long DELAY = 1000; // Milliseconds

            @Override
            public void afterTextChanged(Editable s) {
                movieName=s.toString();

                //Toast.makeText(view.getContext(),String.valueOf(),Toast.LENGTH_LONG).show();
                timer.cancel();
                timer = new Timer();
                timer.schedule(
                        new TimerTask() {
                            @Override
                            public void run() {
                                if (movieName!=null && movieName.trim().length()>0){
                                    getMovieListFromNetwork(movieName);
                                }
                            }
                        },
                        DELAY
                );
            }
        });

        //movieName="fight";

        //network data alınacak
        //k_0vjizyi6
        //recycler view içerisinde data gösterecek
        return view;
    }
    private void getMovieListFromNetwork(String movieName){
        OkHttpClient client = new OkHttpClient().newBuilder()
                .build();
        Request request = new Request.Builder()
                .url("https://imdb-api.com/API/SearchMovie/k_0vjizyi6/"+movieName)
                .method("GET", null)
                .addHeader("accept", "application/json")
                .build();
        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(@NonNull Call call, @NonNull IOException e) {
                Log.d(TAG,"onFailure");
            }

            @Override
            public void onResponse(@NonNull Call call, @NonNull Response response) throws IOException {
                if(response.isSuccessful()){
                    final String responseBody=response.body().string();
                    MovieResult movieResult=new Gson().fromJson(responseBody,MovieResult.class);
                    getActivity().runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                    setAdapterRecyclerView(movieResult.getResults());
                        }
                    });


                    //recycler view içerisinde kullanacağımız data kaynağı movieResult results değişkeni


                    Log.d(TAG,"onResponse: ");
                }
            }
        });
    }
    private void setAdapterRecyclerView(List<Result> resultList){
        MovieResultAdapter adapter=new MovieResultAdapter(resultList);
        RecyclerView.LayoutManager mlayoutManager=new LinearLayoutManager(getContext());
        movie_list_recyclerview.setLayoutManager(mlayoutManager);
        movie_list_recyclerview.setAdapter(adapter);
    }
}