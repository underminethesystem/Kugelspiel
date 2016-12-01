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

import com.example.jules.kugelspiel.DirectionManager;
import com.example.jules.kugelspiel.MainActivity;
import com.example.jules.kugelspiel.Map;
import com.example.jules.kugelspiel.Game;
import com.example.jules.kugelspiel.R;
/**
 * A simple {@link Fragment} subclass.
 */
public class FragmentGame extends Fragment {

    DirectionManager dm;

    public FragmentGame() {
        // Required empty public constructor
    }

    View view;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        System.out.println("FragmentGame oncreate called");
        View view = inflater.inflate(R.layout.fragment_game, container, false);

        ImageView mv = (ImageView) view.findViewById(R.id.mapView);
        ImageView bv = (ImageView) view.findViewById(R.id.ballView);
        Log.d("test","main");

        Game g = new Game(mv, bv);
        g.start();

        //Map m=new Map(2);
        //m.draw(iv);

        // Inflate the layout for this fragment
        return view;
    }

}
