package com.example.firebaseapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.FirebaseAuth;

public class ForgetPassword_Activity extends AppCompatActivity {

    TextInputEditText email;
    Button sendButton;

    FirebaseAuth auth = FirebaseAuth.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forget_password);

        email = findViewById(R.id.textEmail);
        sendButton = findViewById(R.id.sendButton);

        sendButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String userEmail = email.getText().toString();
                resetPassword(userEmail);

            }
        });

        }

    public void resetPassword(String userEmail)
    {
        auth.sendPasswordResetEmail(userEmail).addOnCompleteListener(this, new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {

                if(task.isSuccessful())
                {
                    Toast.makeText(getApplicationContext(),"Mail sent",Toast.LENGTH_SHORT).show();
                    finish();
                }

                else
                {
                    Toast.makeText(getApplicationContext(),"Error occured",Toast.LENGTH_SHORT).show();
                }

            }
        });
    }
}