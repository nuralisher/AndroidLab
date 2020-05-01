package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Login extends AppCompatActivity implements Profile.LogOutListener {
    Button log;
    EditText email, password;
    SharedPreferences sharedPreferences;
    static Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        context = this;
        log = (Button)findViewById(R.id.log);
        email = (EditText)findViewById(R.id.email);
        password = (EditText)findViewById(R.id.password);

        sharedPreferences = getPreferences(MODE_PRIVATE);
        String savedEmail = sharedPreferences.getString("email","");
        String savedPassword = sharedPreferences.getString("password", "");
        savedEmail = savedEmail.trim();
        savedPassword = savedPassword.trim();

        if( (!savedEmail.isEmpty()) && (!savedPassword.isEmpty())){
            Intent intent = new Intent(this, MainActivity.class);
            intent.putExtra("email", savedEmail);
            intent.putExtra("password", savedPassword);
            startActivity(intent);
        }


    }


    public void log(View view){
        String emailText = email.getText().toString().trim();
        String passwordText = password.getText().toString().trim();
        if(emailText.isEmpty() || passwordText.isEmpty()){
            Toast.makeText(this ,"Can not be empty email or password!" ,Toast.LENGTH_LONG).show();
        }else{
            this.save();
            Intent intent = new Intent(this, MainActivity.class);
            intent.putExtra("email", emailText);
            intent.putExtra("password", passwordText);
            startActivity(intent);
        }
    }

    public void save(){
        sharedPreferences = getPreferences(MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("email", email.getText().toString());
        editor.putString("password", password.getText().toString());
        editor.apply();
    }

    @Override
    public void out() {
        Log.e("mylog","Main logout1");
        sharedPreferences = getPreferences(MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("email", "");
        editor.putString("password", "");
        editor.apply();
    }
}
