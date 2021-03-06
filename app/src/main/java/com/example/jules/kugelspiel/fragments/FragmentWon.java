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
import android.widget.EditText;
import android.widget.TextView;

import com.example.jules.kugelspiel.Game;
import com.example.jules.kugelspiel.MainActivity;
import com.example.jules.kugelspiel.R;
import com.example.jules.kugelspiel.database.DataSource;
import com.example.jules.kugelspiel.database.Highscore;

import org.w3c.dom.Text;

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
        FragmentTransaction fragmentTransaction = MainActivity.fragmentManager.beginTransaction();
        EditText editText = (EditText) MainActivity.act.findViewById(R.id.textEdit1);
        if(editText.getText().toString().length() > 0)
        {
            dataSource = new DataSource(this.getActivity());
            dataSource.open();
            dataSource.createHighscore(editText.getText().toString(), Game.seconds, com.example.jules.kugelspiel.Map.mapId);
            dataSource.close();
        }
        FragmentManager fm = getActivity().getSupportFragmentManager();
        for(int i = 0; i < fm.getBackStackEntryCount(); ++i) {
            fm.popBackStack();
        }

        fragmentTransaction.hide(this);
        fragmentTransaction.commit();
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        dataSource = new DataSource(this.getActivity());
        View view = inflater.inflate(R.layout.fragment_won, container, false);
        Button bnOk = (Button) view.findViewById(R.id.btnOk);
        TextView sec = (TextView) view.findViewById(R.id.tvSekunden);
        sec.setText("Sekunden:" + Game.seconds);
        bnOk.setOnClickListener(this);
        return view;
    }

}
