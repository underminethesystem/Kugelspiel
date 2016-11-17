package com.example.jules.kugelspiel;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;

import static android.R.attr.id;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ImageView iv= (ImageView) findViewById(R.id.imageView);
        Log.d("test","main");
        Map m=new Map(2);
        m.draw(iv);
    }
}