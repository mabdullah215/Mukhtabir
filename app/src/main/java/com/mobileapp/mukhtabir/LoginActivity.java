package com.mobileapp.mukhtabir;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.material.button.MaterialButton;
import com.mobileapp.mukhtabir.interactions.IResultData;
import com.mobileapp.mukhtabir.network.APIList;
import com.mobileapp.mukhtabir.network.NetworkManager;

import java.util.HashMap;

public class LoginActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        EditText etEmail=findViewById(R.id.et_email);
        EditText etPass=findViewById(R.id.et_password);
        etEmail.setText("teacher@gmail.com");
        etPass.setText("12345678");
        MaterialButton loginButton=findViewById(R.id.button_login);
        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {
                String email=etEmail.getText().toString().trim();
                String pass=etPass.getText().toString().trim();

                if(email.isEmpty()||pass.isEmpty())
                {
                    Toast.makeText(LoginActivity.this, "input field can not be empty", Toast.LENGTH_SHORT).show();
                }
                else
                {
                    loginUser(email,pass);
                }
            }
        });

    }

    public void loginUser(String email,String pass)
    {
        NetworkManager manager=NetworkManager.getInstance(this);
        HashMap<String,Object>map=new HashMap<>();
        map.put("user",email);
        map.put("password",pass);
        showLoading();
        manager.postRequest(APIList.LOGIN, map, new IResultData() {
            @Override
            public void notifyResult(String result)
            {
                hideLoading();
            }
        });
    }
}