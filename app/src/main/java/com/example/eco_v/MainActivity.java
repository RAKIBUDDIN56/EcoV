package com.example.eco_v;





import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import java.util.zip.Inflater;

public class MainActivity extends AppCompatActivity  implements View.OnClickListener {
    public EditText etEmailSignIn,etPasswordSignIn;
    Button btnSignIn,btnSignUpFirst;
    ProgressBar proBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //This is for title
        this.setTitle("Sign In");

        etEmailSignIn=findViewById(R.id.etEmailSignIn);
        etPasswordSignIn=findViewById(R.id.etPasswordSignIn);
        btnSignIn=findViewById(R.id.btnSignIn);
        btnSignUpFirst=findViewById(R.id.btnSignUpFirst);

        proBar=findViewById(R.id.proBar);

        btnSignIn.setOnClickListener(this);
        btnSignUpFirst.setOnClickListener(this);



    }


    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.btnSignIn:
                signIn();
                break;
            case R.id.btnSignUpFirst:
                signUpFirst();
                break;
        }

    }

    @Override
    public void onBackPressed() {
        backButtonHandler();
        return;


    }

    private void backButtonHandler() {

        AlertDialog.Builder alertDialog = new AlertDialog.Builder(

                MainActivity.this);

        // Setting Dialog Title

        alertDialog.setTitle("Leave application?");

        // Setting Dialog Message

        alertDialog.setMessage("Are you sure you want to leave the application?");

        // Setting Icon to Dialog

        alertDialog.setIcon(R.drawable.happy);

        // Setting Positive "Yes" Button

        alertDialog.setPositiveButton("YES",

                new DialogInterface.OnClickListener() {

                    public void onClick(DialogInterface dialog, int which) {

                        finish();

                    }

                });

        // Setting Negative "NO" Button

        alertDialog.setNegativeButton("NO",

                new DialogInterface.OnClickListener() {

                    public void onClick(DialogInterface dialog, int which) {

                        // Write your code here to invoke NO event

                        dialog.cancel();

                    }

                });

        // Showing Alert Message

        alertDialog.show();

    }





    private void signUpFirst() {




        Intent intent=new Intent(getApplication(),SignUp.class);
        startActivity(intent);
    }

    private void signIn() {


        String  signInEmail=etEmailSignIn.getText().toString().trim();
        String signInPassword=etPasswordSignIn.getText().toString().trim();

//        if(signInEmail.isEmpty()){
//            etEmailSignIn.setError("Enter an email address");
//            etEmailSignIn.requestFocus();
//            return;
//        }
//
//
//
//        if(!Patterns.EMAIL_ADDRESS.matcher(signInEmail).matches()){
//            etEmailSignIn.setError("Enter valid password");
//            etEmailSignIn.requestFocus();
//            return;
//
//        }
//        if( signInPassword.length()<6){
//            etPasswordSignIn.setError("In valid password");
//            etPasswordSignIn.requestFocus();
//            return;
//        }
        //Progressbar showing
        //proBar.setVisibility(View.VISIBLE);
        Toast.makeText(this, "Registration is successful!!!", Toast.LENGTH_SHORT).show();


        String email=etEmailSignIn.getText().toString().trim();

        Intent intent=new Intent(MainActivity.this,com.example.eco_v.HomePage.class);
        intent.putExtra("email",email);
        startActivity(intent);
    }
}
