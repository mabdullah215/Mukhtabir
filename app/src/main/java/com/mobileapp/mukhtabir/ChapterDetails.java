package com.mobileapp.mukhtabir;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.blogspot.atifsoftwares.animatoolib.Animatoo;

public class ChapterDetails extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chapter_details);
        TextView tvTitle=findViewById(R.id.tv_title);
        tvTitle.setText("Chapter 2");
        ImageView imgRight=findViewById(R.id.img_right);
        imgRight.setImageResource(R.drawable.ic_edit);
        ImageView imgLeft=findViewById(R.id.img_left);
        imgLeft.setImageResource(R.drawable.ic_search);
        ImageView imgBack=findViewById(R.id.img_back);
        imgBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
                Animatoo.INSTANCE.animateSlideRight(ChapterDetails.this);
            }
        });
    }
}