package com.example.firebaseapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.FirebaseApiNotAvailableException;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class Login_Page extends AppCompatActivity {

    TextInputEditText email,password;
    Button loginBtn;
    TextView forgetPass,signUp;

    FirebaseAuth auth = FirebaseAuth.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_page);


        email = findViewById(R.id.textEmail);
        password = findViewById(R.id.textPassword);
        loginBtn = findViewById(R.id.signUpButton);
        forgetPass = findViewById(R.id.textForgetpass);
        signUp = findViewById(R.id.textSignUp);

        loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String userEmail = email.getText().toString();
                String userPassword = password.getText().toString();
                signInwithFirebase(userEmail,userPassword);

            }
        });

        forgetPass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(Login_Page.this,ForgetPassword_Activity.class);
                startActivity(intent);

            }
        });

        signUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(Login_Page.this,SignUp_Activity.class);
                startActivity(intent);
            }
        });

    }

    public void signInwithFirebase(String userEmail,String userPassword)
    {
        auth.signInWithEmailAndPassword(userEmail, userPassword).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {


                if (task.isSuccessful())
                {
                    Intent intent = new Intent(Login_Page.this,MainActivity.class);
                    startActivity(intent);
                    finish();
                    Toast.makeText(getApplicationContext(),"Login Successful",Toast.LENGTH_SHORT).show();
                }
                else
                {
                    Toast.makeText(getApplicationContext(),"Check your email and password",Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    protected void onStart(){
        super.onStart();

        FirebaseUser user = auth.getCurrentUser();

        if(user!=null)
        {
            Intent intent = new Intent(Login_Page.this,MainActivity.class);
            startActivity(intent);
            finish();
        }

    }

}