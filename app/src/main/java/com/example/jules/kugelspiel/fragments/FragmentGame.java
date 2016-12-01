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

import java.util.Timer;
import java.util.TimerTask;

/**
 * A simple {@link Fragment} subclass.
 */
public class FragmentGame extends Fragment {

    Map m;
    ImageView iv;
    public FragmentGame() {
        // Required empty public constructor
    }

    View view;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        boolean b=true;
        final View view = inflater.inflate(R.layout.fragment_game, container, false);

        iv = (ImageView) view.findViewById(R.id.imageView);
        Log.d("test", "main");
        m = new Map(2);

        Timer timer = new Timer();
        m.draw(iv);
        timer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                //update Kugel
                Log.d("fx","draw map");
            }

        }, 0, 1000 / 10);

        // Inflate the layout for this fragment
        return view;
    }

}
