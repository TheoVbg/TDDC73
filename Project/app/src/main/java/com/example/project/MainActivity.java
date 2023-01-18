package com.example.project;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.widget.TextView;

import javax.security.auth.callback.PasswordCallback;

public class MainActivity extends AppCompatActivity implements PasswordField.passwordsValidsCallback{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        UserCarousel uCarousel = findViewById(R.id.UCarousel);
        String picture;
        String name;
        boolean status;

        PasswordField passwords;

        passwords = findViewById(R.id.passwords);
        passwords.setPasswordCallback(this);

        //Permits to print 20 test users
        for (int i = 0; i<20; i++)
        {
            picture = "_".concat(String.valueOf(i));
            name = "User".concat(String.valueOf(i));

            status = (i<12);

            uCarousel.addUser(name, picture, status);
        }


    }

    @Override
    public void toDO(boolean arePasswordsValids) {
        //Example usage of the callback function that execute code if the passwords are valids

        TextView passwordMatchView;
        passwordMatchView = findViewById(R.id.textPasswordMatch);

        if (arePasswordsValids)
        {
            passwordMatchView.setTextColor(Color.GREEN);
            passwordMatchView.setText("Passwords valids");
        }
        else
        {
            passwordMatchView.setTextColor(Color.RED);
            passwordMatchView.setText("Passwords not valids");
        }

    }

}