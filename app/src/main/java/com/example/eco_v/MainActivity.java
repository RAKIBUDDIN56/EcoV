package com.example.eco_v;





import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.util.Patterns;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.zip.Inflater;

public class MainActivity extends AppCompatActivity   {
    public EditText etEmailSignIn,etPasswordSignIn;
    Button btnSignIn,btnSignUpFirst;
    //CustomCalendarView customCalendarView;

    private FirebaseAuth mAuth;
    ProgressBar proBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //This is for title
        this.setTitle("Sign In");



        mAuth = FirebaseAuth.getInstance();



        etEmailSignIn=findViewById(R.id.etEmailSignIn);
        etPasswordSignIn=findViewById(R.id.etPasswordSignIn);
        btnSignIn=findViewById(R.id.btnSignIn);
        btnSignUpFirst=findViewById(R.id.btnSignUpFirst);

        proBar=findViewById(R.id.proBar);

        btnSignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(MainActivity.this,com.example.eco_v.HomePage.class);
                startActivity(intent);
            }
        });
        //btnSignUpFirst.setOnClickListener(this);



    }



//    @Override
//    public void onClick(View view) {
//        switch (view.getId()){
//            case R.id.btnSignIn:
//                signIn();
//                break;
//            case R.id.btnSignUpFirst:
//                signUpFirst();
//                break;
//        }
//
//    }
//
//    @Override
//    public void onBackPressed() {
//        backButtonHandler();
//        return;
//
//
//    }
//
//    private void backButtonHandler() {
//
//        AlertDialog.Builder alertDialog = new AlertDialog.Builder(
//
//                MainActivity.this);
//
//        // Setting Dialog Title
//
//        alertDialog.setTitle("Leave application?");
//
//        // Setting Dialog Message
//
//        alertDialog.setMessage("Are you sure you want to leave the application?");
//
//        // Setting Icon to Dialog
//
//        alertDialog.setIcon(R.drawable.happy);
//
//        // Setting Positive "Yes" Button
//
//        alertDialog.setPositiveButton("YES",
//
//                new DialogInterface.OnClickListener() {
//
//                    public void onClick(DialogInterface dialog, int which) {
//
//                        finish();
//
//                    }
//
//                });
//
//        // Setting Negative "NO" Button
//
//        alertDialog.setNegativeButton("NO",
//
//                new DialogInterface.OnClickListener() {
//
//                    public void onClick(DialogInterface dialog, int which) {
//
//                        // Write your code here to invoke NO event
//
//                        dialog.cancel();
//
//                    }
//
//                });
//
//        // Showing Alert Message
//
//        alertDialog.show();
//
//    }
//
//
//
//
//
//    private void signUpFirst() {
//
//
//
//
//        Intent intent=new Intent(getApplication(),SignUp.class);
//        startActivity(intent);
//    }
//
//    private void signIn() {
//
//
//        String  signInEmail=etEmailSignIn.getText().toString().trim();
//        String signInPassword=etPasswordSignIn.getText().toString().trim();
//
////        if(signInEmail.isEmpty()){
////            etEmailSignIn.setError("Enter an email address");
////            etEmailSignIn.requestFocus();
////            return;
////        }
//
//
//
////        if(!Patterns.EMAIL_ADDRESS.matcher(signInEmail).matches()){
////            etEmailSignIn.setError("Enter valid password");
////            etEmailSignIn.requestFocus();
////            return;
////
////        }
////        if( signInPassword.length()<6){
////            etPasswordSignIn.setError("In valid password");
////            etPasswordSignIn.requestFocus();
////            return;
////        }
//        //Progressbar showing
//        proBar.setVisibility(View.VISIBLE);
//
//        mAuth.signInWithEmailAndPassword(signInEmail, signInPassword).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
//                    @Override
//                    public void onComplete(@NonNull Task<AuthResult> task) {
//                        proBar.setVisibility(View.GONE);
//                        if (task.isSuccessful()) {
//                            String email=etEmailSignIn.getText().toString().trim();
//
//                            Intent intent=new Intent(MainActivity.this,com.example.eco_v.HomePage.class);
//                            intent.putExtra("email",email);
//                            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
//                            startActivity(intent);
//
//                        } else {
//                            Toast.makeText(MainActivity.this, "Sign In unsuccessful !", Toast.LENGTH_SHORT).show();
//
//                        }
//
//                        // ...
//                    }
//                });
//
//
//
//
//
////
//    }
}
