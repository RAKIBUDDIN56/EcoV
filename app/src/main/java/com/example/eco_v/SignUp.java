package com.example.eco_v;


import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthUserCollisionException;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class SignUp extends AppCompatActivity implements View.OnClickListener {
    EditText etName,etAddress,etPhoneNumber,etEmailAddress,etPassword;
    Button btnsignUpSecond;

    private FirebaseAuth mAuth;
    ProgressBar progressBar;
    DatabaseReference dbRef;
    UsersDetailsDB userDB;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        dbRef= FirebaseDatabase.getInstance().getReference("Student");
        //This is for title
        this.setTitle("Sign Up");

        mAuth = FirebaseAuth.getInstance();

        progressBar=findViewById(R.id.progressBar);

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
        int phoneNumber=Integer.parseInt (etPhoneNumber.getText().toString());
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
//










//        Intent intent=new Intent(getApplicationContext(),MainActivity .class);
//        startActivity(intent);

        progressBar.setVisibility(View.VISIBLE);

        mAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        progressBar.setVisibility(View.GONE);
                        if (task.isSuccessful()) {

                            String name=etName.getText().toString().trim();
                            String address=etAddress.getText().toString().trim();
                            int phoneNumber=Integer.parseInt (etPhoneNumber.getText().toString());
                            String email=etEmailAddress.getText().toString().trim();
                            String password=etPassword.getText().toString().trim();

                            String key=dbRef.push().getKey();
                            userDB=new UsersDetailsDB(name,address,phoneNumber,email,password);

                            dbRef.child(key).setValue(userDB);

                            Toast.makeText(SignUp.this, "Registration is successful!", Toast.LENGTH_SHORT).show();

                            Intent intent=new Intent(getApplicationContext(),MainActivity .class);
                            startActivity(intent);

                        } else {
                            if(task.getException() instanceof FirebaseAuthUserCollisionException){
                                Toast.makeText(SignUp.this, "User already registered", Toast.LENGTH_SHORT).show();
                            }else{
                                Toast.makeText(SignUp.this, "Error :"+task.getException(), Toast.LENGTH_SHORT).show();
                            }
                        }


                    }
                });



    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(SignUp.this, MainActivity.class);
        startActivity(intent);
        finish();
    }
}