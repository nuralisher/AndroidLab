package com.example.labfragment;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;

import android.os.Bundle;
import android.widget.Toast;

public class Main2Activity extends AppCompatActivity {

    FragmentManager fragmentManager;
    RightFragment rightFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        Mail mail = getIntent().getParcelableExtra("mail");
        setContentView(R.layout.activity_main2);

        fragmentManager = getSupportFragmentManager();
        rightFragment = new RightFragment();
        fragmentManager.beginTransaction()
                .add(R.id.left_container , new MainFragment())
                .add(R.id.right_container , rightFragment).commit();
    }


}
