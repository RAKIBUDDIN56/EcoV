package com.example.eco_v;


import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class SignUp extends AppCompatActivity implements View.OnClickListener {
    EditText etName,etAddress,etPhoneNumber,etEmailAddress,etPassword;
    Button btnsignUpSecond;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        //This is for title
        this.setTitle("Sign Up");

        etName=findViewById(R.id.etName);
        etAddress=findViewById(R.id.etAddress);
        etPhoneNumber=findViewById(R.id.etPhoneNumber);
        etEmailAddress=findViewById(R.id.etEmailAddress);
        etPassword=findViewById(R.id.etPassword);

        btnsignUpSecond=findViewById(R.id.btnSignUpSecond);
        btnsignUpSecond.setOnClickListener(this);


    }

    @Override
    public void onClick(View view) {

        String name=etName.getText().toString();
        String address=etAddress.getText().toString();
        //int phone=Integer.parseInt (etPhoneNumber.getText().toString());
        String email=etEmailAddress.getText().toString();
        String password=etPassword.getText().toString();

        if(name.isEmpty()){
            etName.setError("Enter name");
            etName.requestFocus();
            return;
        }
        if(address.isEmpty()){
            etAddress.setError("Enter address");
            etName.requestFocus();
            return;
        }




        if(!Patterns.EMAIL_ADDRESS.matcher(email).matches()){
            etEmailAddress.setError("Enter valid password");
            etEmailAddress.requestFocus();
            return;

        }
        if( password.length()<6){
            etPassword.setError("In valid password");
            etPassword.requestFocus();
            return;
        }


        Intent intent=new Intent(getApplicationContext(),MainActivity.class);
        startActivity(intent);

    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(SignUp.this, MainActivity.class);
        startActivity(intent);
        finish();
    }
}