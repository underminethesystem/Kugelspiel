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
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.example.jules.kugelspiel.MainActivity;
import com.example.jules.kugelspiel.R;
import com.example.jules.kugelspiel.database.DataSource;
import com.example.jules.kugelspiel.database.Map;

import java.util.ArrayList;
import java.util.List;

import static android.R.attr.data;
import static android.R.attr.fragment;

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
        //FragmentManager fragmentManager = getFragmentManager();
        FragmentTransaction fragmentTransaction = MainActivity.fragmentManager.beginTransaction();
        com.example.jules.kugelspiel.Map.mapId = view.getId();
        FragmentGame fG = new FragmentGame();

        fragmentTransaction.add(R.id.fragment_container, fG);
        fragmentTransaction.addToBackStack(null);

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

        MainActivity.ds.open();
        List<Map> mapList = MainActivity.ds.getAllMaps();
        MainActivity.ds.close();

        LinearLayout ll = (LinearLayout) view.findViewById(R.id.ll);


        for(Map map : mapList) {
            Button button = new Button(this.getActivity());
            button.setText("Map" + map.getId());

            button.setId(map.getId());
            button.setTag("@+id/map" + map.getId());
            button.setOnClickListener(this);
            ll.addView(button);
        }

        RelativeLayout.LayoutParams params =
                new RelativeLayout.LayoutParams(
                        RelativeLayout.LayoutParams.WRAP_CONTENT, RelativeLayout.LayoutParams.WRAP_CONTENT);
        params.addRule(RelativeLayout.ALIGN_PARENT_END);
        params.addRule(RelativeLayout.ALIGN_PARENT_START);
        //ll.setLayoutParams(params);

        //Button bnMap1 = (Button) view.findViewById(R.id.map1);
        //bnMap1.setOnClickListener(this);
        //Inflate the layout for this fragment
        return view;


    }

}
