package com.example.jules.kugelspiel;

import android.hardware.SensorManager;
import android.support.v7.app.AppCompatActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.os.Bundle;
import android.view.WindowManager;

import com.example.jules.kugelspiel.database.DataSource;
import com.example.jules.kugelspiel.fragments.FragmentMenu;

import java.io.IOException;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    boolean status = false;

    public static SensorManager sm;

    public static AppCompatActivity act;

    public static DataSource ds;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
        act = this;



        setContentView(R.layout.activity_main);

        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        FragmentMenu fM = new FragmentMenu();
        fragmentTransaction.add(R.id.fragment_container, fM);
        fragmentTransaction.commit();
        status = true;

        ds = new DataSource(this);

        MapArchive ma = new MapArchive();
        ArrayList<GameMap> mapList = ma.getMapList();
        ObjectDecoder oj = new ObjectDecoder();

        ds.open();
        if (ds.getAllMaps().size() != ma.getMapCount() ){
            try {
                for (GameMap m: mapList){
                    ds.createMap(oj.toString(m));
                }
            } catch(IOException e){

            }
        }
        ds.close();

        sm = (SensorManager) getSystemService(SENSOR_SERVICE);
    }
}