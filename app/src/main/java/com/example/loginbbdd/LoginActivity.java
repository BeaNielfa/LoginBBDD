package com.example.loginbbdd;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.squareup.picasso.Picasso;

public class LoginActivity extends AppCompatActivity {
    ImageView logotipo;
    EditText editTextEmail;
    EditText editTextPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);


        logotipo = (ImageView) findViewById(R.id.imageViewLogotipo);
        editTextEmail = (EditText) findViewById(R.id.editTextEmail);
        editTextPassword = (EditText) findViewById(R.id.editTextContraseñ);
        Picasso.get()
                .load("https://www.hola.com/imagenes/estar-bien/20190820147813/razas-perros-pequenos-parecen-grandes/0-711-550/razas-perro-pequenos-grandes-m.jpg")
                .into(logotipo);
        SharedPreferences sharedPref = getSharedPreferences(getString(R.string.preferencias), Context.MODE_PRIVATE);
        boolean isLogin = sharedPref.getBoolean("islogin",false);

        if(isLogin){
            Intent i = new Intent(LoginActivity.this,MainActivity.class);
            startActivity(i);

        }

    }

    public void doLogin(View view) {
        String email = editTextEmail.getText().toString() ;
        String pass = editTextPassword.getText().toString();
        if(email.equals("jojo") && pass.equals("1234")){
            //LOGIN CORRECTO

            //GUARDAR LOS DATOS DEL LOGIN
            SharedPreferences sharedPref = getSharedPreferences(getString(R.string.preferencias), Context.MODE_PRIVATE);
            SharedPreferences.Editor editor = sharedPref.edit();
            editor.putString("email",email);
            editor.putBoolean("islogin",true);
            editor.commit();
            Intent i = new Intent(LoginActivity.this,MainActivity.class);
            startActivity(i);

        }else{
            Toast.makeText(this, "Email y/o contraseña incorrecto", Toast.LENGTH_SHORT).show();
        }

    }
}
