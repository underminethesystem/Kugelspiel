package com.example.jules.kugelspiel.fragments;



import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.util.Log;
import android.widget.ImageView;

import com.example.jules.kugelspiel.GameMap;
import com.example.jules.kugelspiel.Game;
import com.example.jules.kugelspiel.ObjectDecoder;
import com.example.jules.kugelspiel.R;
import com.example.jules.kugelspiel.database.DataSource;

import java.io.IOException;

/**
 * A simple {@link Fragment} subclass.
 */
public class FragmentGame extends Fragment {
    public FragmentGame() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_game, container, false);

        ImageView mv = (ImageView) view.findViewById(R.id.mapView);
        ImageView bv = (ImageView) view.findViewById(R.id.ballView);
        Log.d("test","main");

        ObjectDecoder oj = new ObjectDecoder();

        // init current map to some default map
        GameMap selectedMap = null;
        DataSource ds = new DataSource(this.getActivity());
        ds.open();
        try {
            // TODO: implement actual selected map gedöns
            selectedMap = (GameMap) oj.fromString(ds.getAllMaps().get(0).getSerializedMap());
        } catch (ClassNotFoundException | IOException e) {}
        ds.close();

        Game g = new Game(mv, bv);
        g.start();

        // Inflate the layout for this fragment
        return view;
    }

    @Override
    public void onPause() {
        super.onPause();
        if (Game.dm != null) {
            Game.dm.onPause();
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        if (Game.dm != null) {
            Game.dm.onResume();
        }
    }

    @Override
    public void onStop() {
        super.onStop();
        if (Game.dm != null) {
            Game.dm.onStop();
        }
    }


}
