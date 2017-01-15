package com.example.jules.kugelspiel.fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.jules.kugelspiel.R;
import com.example.jules.kugelspiel.database.DataSource;
import com.example.jules.kugelspiel.database.Highscore;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class FragmentHighscore extends Fragment {

    private DataSource dataSource;
    private View v;

    public FragmentHighscore() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        dataSource = new DataSource(this.getActivity());
        View view = inflater.inflate(R.layout.fragment_highscore, container, false);
        dataSource.open();

        List<Highscore> highScoreList = dataSource.getThreeHighscores();
        for(Highscore high : highScoreList) {
            dataSource.deleteHighscore(high);
        }
        dataSource.createHighscore("Hallo",1,1);
        dataSource.createHighscore("Hallo",2,2);
        dataSource.createHighscore("Hallo",3,3);

        ArrayAdapter<Highscore> highScoreArrayAdapter = new ArrayAdapter<>(
                this.getContext(),
                android.R.layout.simple_list_item_1,
                highScoreList);

        ListView highs = (ListView) view.findViewById(R.id.listView);
        highs.setAdapter(highScoreArrayAdapter);

        dataSource.close();

        // Inflate the layout for this fragment
        return view;
    }
}
