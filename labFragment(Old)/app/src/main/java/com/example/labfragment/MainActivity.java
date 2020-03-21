package com.example.labfragment;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    FragmentManager fragmentManager;
    Button log;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//        fragmentManager = getSupportFragmentManager();
//        fragmentManager.beginTransaction()
//                .add(R.id.first_container,new MainFragment() , "first").addToBackStack("first").commit();
        log = (Button) findViewById(R.id.log);

    }

    public void log(View v){
        Intent intent = new Intent(this, Main2Activity.class);
        startActivity(intent);
    }

}
