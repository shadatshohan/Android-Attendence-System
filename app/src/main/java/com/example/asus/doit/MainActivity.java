package com.example.asus.doit;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private EditText signinemail,signinpass;
    private TextView signup;
    private Button signinbutton;
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mAuth = FirebaseAuth.getInstance();

        this.setTitle("Sign In Activity");

        signinemail=(EditText) findViewById(R.id.signinemailid);
        signinpass=(EditText) findViewById(R.id.signinpasswordid);
        signup=(TextView) findViewById(R.id.signuptextid);
        signinbutton=(Button) findViewById(R.id.signinbuttonid);

        signinbutton.setOnClickListener(this);
        signup.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch(view.getId())
        {
            case R.id.signinbuttonid:
                userlogin();
                break;

            case R.id.signuptextid:
                Intent intent=new Intent(MainActivity.this,SignupActivity.class);
                startActivity(intent);
                break;


        }
    }

    private void userlogin() {
        String email=signinemail.getText().toString().trim();
        String password=signinpass.getText().toString().trim();

        if(email.isEmpty())
        {
            signinemail.setError("Enter an email address");
            signinemail.requestFocus();
            return;
        }

        if(!Patterns.EMAIL_ADDRESS.matcher(email).matches())
        {
            signinemail.setError("Enter a valid email address");
            signinemail.requestFocus();
            return;
        }
        if(password.isEmpty())
        {
            signinpass.setError("Enter a password");
            signinpass.requestFocus();
            return;
        }
        if(password.length()<6)
        {
            signinpass.setError("Minimum length of password must be 6");
            signinpass.requestFocus();
            return;
        }
        mAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            finish();
                            Intent intent=new Intent(getApplicationContext(),HeadActivity.class);
                            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                            startActivity(intent);

                        } else {
                            Toast.makeText(MainActivity.this, "Login unsuccessful", Toast.LENGTH_SHORT).show();
                        }

                        // ...
                    }
                });
    }

}

