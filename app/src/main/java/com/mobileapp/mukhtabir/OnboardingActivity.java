package com.mobileapp.mukhtabir;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.blogspot.atifsoftwares.animatoolib.Animatoo;
import com.google.android.material.button.MaterialButton;

public class OnboardingActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_onboarding);
        MaterialButton loginButton=findViewById(R.id.button_login);
        MaterialButton registerButton=findViewById(R.id.button_register);
        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {
                startActivity(new Intent(getBaseContext(),LoginActivity.class));
                Animatoo.INSTANCE.animateSlideLeft(OnboardingActivity.this);
            }
        });

        registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getBaseContext(),RegisterActivity.class));
                Animatoo.INSTANCE.animateSlideLeft(OnboardingActivity.this);
            }
        });
    }
}