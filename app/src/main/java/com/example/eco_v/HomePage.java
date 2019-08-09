package com.example.eco_v;

import android.content.Intent;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import android.view.View;

import androidx.core.view.GravityCompat;
import androidx.appcompat.app.ActionBarDrawerToggle;

import android.view.MenuItem;

import com.google.android.material.navigation.NavigationView;

import androidx.drawerlayout.widget.DrawerLayout;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.Menu;
import android.widget.Toast;

import technolifestyle.com.imageslider.FlipperLayout;
import technolifestyle.com.imageslider.FlipperView;

public class HomePage extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    FlipperLayout flipper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_page);

        flipper=findViewById(R.id.flipper);
        setLayout();


        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();
        navigationView.setNavigationItemSelectedListener(this);
    }

    private void setLayout() {

        int image[ ] = { R.drawable.image_2,
                R.drawable.image_1,
                R.drawable.image_3,
                R.drawable.image_4,
                R.drawable.image_5
        };

//       String url[]=new String[]{
//               "https://images.thestar.com/Myl7UGRdk6G_w2t5u3sXxwl8X7M=/1086x724/smart/filters:cb(2700061000)/https://www.thestar.com/content/dam/thestar/news/gta/2017/05/17/markhams-unique-recycling-program-diverts-textile-waste-from-landfill/markham-recycle-019.jpg",
//               "https://cdn.japantimes.2xx.jp/wp-content/uploads/2017/06/p10-hornyak-recycling-c-20170611.jpg",
//               "https://www.moneycrashers.com/wp-content/uploads/2011/08/reusable-plastic-bottle-garbage-1068x600.jpg",
//
//       };
       for(int i=0;i<5;i++) {
           FlipperView view = new FlipperView(getBaseContext());
           view.setImageDrawable(image[i]);
           //view.setImageUrl(url[i]);

           flipper.addFlipperView(view);

       }
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.home_page, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.search1) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.location) {
            startActivity(new Intent(getApplicationContext(),OurLocation.class));
        } else if (id == R.id.calender) {
            startActivity(new Intent(getApplicationContext(),Calender.class));

        }else if(id == R.id.material){
            startActivity(new Intent(getApplicationContext(),Materials.class));

        }

         else if (id == R.id.news) {
            startActivity(new Intent(getApplicationContext(),News.class));

        } else if (id == R.id.reminder) {
            startActivity(new Intent(getApplicationContext(),Reminder.class));

        } else if (id == R.id.report) {
            startActivity(new Intent(getApplicationContext(),Report.class));



        }else if(id==R.id.sign_out){
             startActivity(new Intent(getApplicationContext(),MainActivity.class));

        }

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
