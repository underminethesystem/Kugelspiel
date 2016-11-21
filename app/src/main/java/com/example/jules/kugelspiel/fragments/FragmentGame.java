package com.example.jules.kugelspiel.fragments;



import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;

import com.example.jules.kugelspiel.Map;
import com.example.jules.kugelspiel.R;
/**
 * A simple {@link Fragment} subclass.
 */
public class FragmentGame extends Fragment {


    public FragmentGame() {
        // Required empty public constructor
    }

    View view;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_game, container, false);

        ImageView iv= (ImageView) view.findViewById(R.id.imageView);
        Log.d("test","main");
        Map m=new Map(2);
        m.draw(iv);

        // Inflate the layout for this fragment
        return view;
    }

}
