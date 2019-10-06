package com.example.eco_v;

import androidx.appcompat.app.AppCompatActivity;

import android.database.SQLException;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

public class MaterialShow extends AppCompatActivity {
    TextView txtShow;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_material_show);

        txtShow=findViewById(R.id.txtShow);

        try{
            DBMaterial db=new DBMaterial(this);
            db.open();
            txtShow.setText(db.getData());
            db.close();


        }catch (SQLException e){
            Toast.makeText(this, e.getMessage(), Toast.LENGTH_SHORT).show();
        }


    }
}
