package com.example.jules.kugelspiel.fragments;


import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.example.jules.kugelspiel.R;
/**
 * A simple {@link Fragment} subclass.
 */
public class FragmentMapselector extends Fragment implements View.OnClickListener{
    View view;

    public FragmentMapselector() {
        // Required empty public constructor
    }



    @Override
    public void onClick(View view) {
        Context context = getContext();
        int duration = Toast.LENGTH_SHORT;
        FragmentManager fragmentManager = getFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

        switch(view.getId()) {

            case R.id.map1:
                FragmentGame fG = new FragmentGame();
                fragmentTransaction.add(R.id.fragment_container, fG);
                fragmentTransaction.addToBackStack(null);
                break;


        }
        fragmentTransaction.hide(this);
        fragmentTransaction.commit();

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        if(container == null){
            return null;
        }
        View view = inflater.inflate(R.layout.fragment_mapselector, container, false);

        Button bnMap1 = (Button) view.findViewById(R.id.map1);
        bnMap1.setOnClickListener(this);
        // Inflate the layout for this fragment
        return view;


    }

}
