package com.example.eco_v;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class News extends AppCompatActivity {
    Button btnNews1,btnNews2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news);

        this.setTitle("Environmental News");

        //for back direction
        ActionBar actionBar=getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);

        btnNews1=findViewById(R.id.btnNews1);
        btnNews2=findViewById(R.id.btnNews2);

        btnNews2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(),InterNews.class));
            }
        });


        btnNews1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(),News_2.class));
            }
        });
    }
}
