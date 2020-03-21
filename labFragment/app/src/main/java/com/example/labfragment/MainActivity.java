package com.example.labfragment;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements Main2Activity.LogOutListener {

    static Context context ;
    FragmentManager fragmentManager;
    Button log;
    final String SAVED_TEXT = "initial";
    EditText email , password;
    SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        context = this;
        Log.e("my" , "createMain");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//        fragmentManager = getSupportFragmentManager();
//        fragmentManager.beginTransaction()
//                .add(R.id.first_container,new MainFragment() , "first").addToBackStack("first").commit();
        sharedPreferences = getPreferences(MODE_PRIVATE);
        String savedEmail = sharedPreferences.getString("email" , "");
        String savedPassword = sharedPreferences.getString("password" , "");
        savedEmail = savedEmail.trim();
        savedPassword = savedPassword.trim();

        if( (!savedEmail.isEmpty()) && (!savedPassword.isEmpty()) ){
            Log.e("my" , "toMainPage " + savedEmail + " pass " + savedPassword);
            this.toMainPage();
        }

        log = (Button) findViewById(R.id.log);
        email = (EditText) findViewById(R.id.email);
        password = (EditText) findViewById(R.id.password);
    }

    public void log(View v){
        this.save();
        String emailText = email.getText().toString().trim();
        String passwordText = password.getText().toString().trim();
        if(emailText.isEmpty() || passwordText.isEmpty()){
            Toast.makeText(this ,"Can not be empty email or password!" ,Toast.LENGTH_LONG).show();
        }else{
            Toast.makeText(this, "email:" + emailText + "password:" + passwordText , Toast.LENGTH_LONG).show();
            this.toMainPage();
        }
    }

    public void toMainPage(){
        Intent intent = new Intent(this, Main2Activity.class);
        startActivity(intent);
        finish();
    }

    public void save(){
        sharedPreferences = getPreferences(MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("email" , email.getText().toString());
        editor.putString("password" , password.getText().toString());
        editor.apply();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    @Override
    public void out() {
        Log.e("my" , "MainActivity OUT");
        sharedPreferences = getPreferences(MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("email" , "");
        editor.putString("password", "");
        editor.apply();
    }
}
