package com.example.eco_v;




import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;

import android.widget.TextView;

public class OurLocation extends AppCompatActivity  {
    TextView etLocation1,etLocation2;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_our_location);

        this.setTitle("Our Location");

       //for back direction
        ActionBar actionBar=getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);

        etLocation1=findViewById(R.id.etLocation1);
        etLocation2=findViewById(R.id.etLocation2);

        etLocation1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(Intent.ACTION_VIEW, Uri.parse("geo:0,0?q= 102/G2 B263,Kaduewla"));
               startActivity(intent);

            }
        });
        etLocation2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(Intent.ACTION_VIEW, Uri.parse("geo:0,0?q= 102/G2 B263,Kaduewla"));
                startActivity(intent);

            }
        });


    }

}
