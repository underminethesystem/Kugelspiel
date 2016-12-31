package com.example.jules.kugelspiel.fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.example.jules.kugelspiel.R;
import com.example.jules.kugelspiel.database.DataSource;
import com.example.jules.kugelspiel.database.Highscore;
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
        v = inflater.inflate(R.layout.fragment_highscore, container, false);
        dataSource.open();
        Highscore high = dataSource.createHighscore("TEST", 1);

        List<Highscore> highScoreList = dataSource.getAllHighscores();

        ArrayAdapter<Highscore> highScoreArrayAdapter = new ArrayAdapter<>(
                this.getContext(),
                android.R.layout.simple_list_item_1,
                highScoreList);

        ListView highs = (ListView) v.findViewById(R.id.lvHigh);
        highs.setAdapter(highScoreArrayAdapter);

        dataSource.close();

        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_highscore, container, false);
    }
}
