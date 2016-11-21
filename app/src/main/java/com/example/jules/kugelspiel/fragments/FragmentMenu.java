package com.example.jules.kugelspiel.fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.jules.kugelspiel.R;
/**
 * A simple {@link Fragment} subclass.
 */
public class FragmentMenu extends Fragment implements View.OnClickListener{


    public FragmentMenu() {
        // Required empty public constructor
    }

    @Override
    public void onClick(View view) {
        FragmentManager fragmentManager = getFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

        switch(view.getId()) {

            case R.id.menu_Start:
                FragmentMapselector fG = new FragmentMapselector();
                fragmentTransaction.add(R.id.fragment_container, fG);
                fragmentTransaction.addToBackStack(null);
                break;

            case R.id.menu_Highscore:
                FragmentHighscore fHighscore = new FragmentHighscore();
                fragmentTransaction.add(R.id.fragment_container, fHighscore);
                fragmentTransaction.addToBackStack(null);
                break;

            case R.id.menu_settings:
                FragmentSettings fSettings = new FragmentSettings();
                fragmentTransaction.add(R.id.fragment_container, fSettings);
                fragmentTransaction.addToBackStack(null);
                break;

            case R.id.menu_about:
                FragmentAbout fAbout = new FragmentAbout();
                fragmentTransaction.add(R.id.fragment_container, fAbout);
                fragmentTransaction.addToBackStack(null);
                break;

        }
        fragmentTransaction.commit();

    }

    View view;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        if(container == null){
            return null;
        }
        View view = inflater.inflate(R.layout.fragment_menu, container, false);

        Button bnStart = (Button) view.findViewById(R.id.menu_Start);
        bnStart.setOnClickListener(this);

        Button bnHigh = (Button) view.findViewById(R.id.menu_Highscore);
        bnHigh.setOnClickListener(this);

        Button bnAbout = (Button) view.findViewById(R.id.menu_about);
        bnAbout.setOnClickListener(this);

        Button bnSettings = (Button) view.findViewById(R.id.menu_settings);
        bnSettings.setOnClickListener(this);

        // Inflate the layout for this fragment
        return view;
    }

}
