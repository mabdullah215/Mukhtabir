package com.mobileapp.mukhtabir;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.blogspot.atifsoftwares.animatoolib.Animatoo;
import com.google.android.material.button.MaterialButton;
import com.mobileapp.mukhtabir.adapter.RoleListAdapter;

public class RoleActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_role);
        RecyclerView recyclerView=findViewById(R.id.role_list);
        recyclerView.setLayoutManager(new GridLayoutManager(this,2));
        RoleListAdapter adapter=new RoleListAdapter(this);
        recyclerView.setAdapter(adapter);
        adapter.setOnItemClickListener(new RoleListAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position)
            {
                adapter.setSelected(position);
            }
        });
        MaterialButton submitButton=findViewById(R.id.button_submit);
        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getBaseContext(),MainActivity.class));
                Animatoo.INSTANCE.animateSlideLeft(RoleActivity.this);
            }
        });
        ImageView imgBack=findViewById(R.id.img_back);
        imgBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {
                finish();
                Animatoo.INSTANCE.animateSlideRight(RoleActivity.this);
            }
        });
    }
}