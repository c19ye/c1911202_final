package com.example.imdb.Activities.Activities.Activities;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.imdb.R;
import com.squareup.picasso.Picasso;


public class ImdbFragment extends Fragment {
    ImageView img;
    View view;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view=inflater.inflate(R.layout.fragment_imdb, container, false);
        img= view.findViewById(R.id.img_imdb);
        String url="https://cdn.icon-icons.com/icons2/2389/PNG/512/imdb_logo_icon_145171.png";
                Picasso.get().load(url)
                        .placeholder(R.drawable.ic_baseline_image_24)
                        .error(R.drawable.ic_baseline_image_not_supported_24)
                        .resize(200,200)
                        .into(img);
        return view;
    }
}