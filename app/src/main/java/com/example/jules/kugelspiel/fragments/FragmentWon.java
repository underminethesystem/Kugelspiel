package com.example.jules.kugelspiel.fragments;


import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import com.example.jules.kugelspiel.Game;
import com.example.jules.kugelspiel.R;
import com.example.jules.kugelspiel.database.DataSource;
import com.example.jules.kugelspiel.database.Highscore;

import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class FragmentWon extends Fragment implements View.OnClickListener{

    private DataSource dataSource;

    public FragmentWon() {
        // Required empty public constructor
    }

    @Override
    public void onClick(View view) {
        Context context = getContext();
        FragmentManager fragmentManager = getFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        EditText et = (EditText) view.findViewById(R.id.etName) ;
        if(et.getText().toString().length() > 0)
        {
            dataSource = new DataSource(this.getActivity());
            dataSource.open();
            dataSource.createHighscore(et.getText().toString(), Game.seconds,0);
            dataSource.close();
        }

        fragmentTransaction.hide(this);
        fragmentTransaction.commit();
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        dataSource = new DataSource(this.getActivity());
        View view = inflater.inflate(R.layout.fragment_highscore, container, false);
        return view;
    }

}
