package com.mobileapp.mukhtabir;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.blogspot.atifsoftwares.animatoolib.Animatoo;
import com.google.android.material.button.MaterialButton;
import com.mobileapp.mukhtabir.interactions.IResultData;
import com.mobileapp.mukhtabir.model.User;
import com.mobileapp.mukhtabir.network.APIList;
import com.mobileapp.mukhtabir.network.NetworkManager;

import java.util.HashMap;

public class RegisterActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        MaterialButton registerButton=findViewById(R.id.button_register);
        EditText etEmail=findViewById(R.id.et_email);
        EditText etFullname=findViewById(R.id.et_fullname);
        EditText etPhone=findViewById(R.id.et_phone);
        EditText etPass=findViewById(R.id.et_password);
        EditText etConfirmPass=findViewById(R.id.et_confirm_pass);
        registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {
                String email=etEmail.getText().toString().trim();
                String fullname=etFullname.getText().toString().trim();
                String phone=etPhone.getText().toString().trim();
                String pass=etPass.getText().toString().trim();
                String confirmPass=etConfirmPass.getText().toString().trim();
                if(email.isEmpty()||fullname.isEmpty()||phone.isEmpty()||pass.isEmpty()||confirmPass.isEmpty())
                {
                    showToast("Input field is missing");
                }
                else if(!pass.equalsIgnoreCase(confirmPass))
                {
                    showToast("Password does not match");
                }
                else
                {
                    HashMap<String,Object> map=new HashMap();
                    map.put("fullName",fullname);
                    map.put("email",email);
                    map.put("roleId",2);
                    map.put("money",0);
                    map.put("number",phone);
                    map.put("password",pass);
                    map.put("confirm_password",confirmPass);
                    registerUser(map);
                }
            }
        });
    }

    public void registerUser(HashMap<String,Object>map)
    {
        NetworkManager manager=NetworkManager.getInstance(this);
        showLoading();
        manager.postRequest(APIList.SIGNUP, map, new IResultData() {
            @Override
            public void notifyResult(String result)
            {
                hideLoading();
            }
        });
    }

}