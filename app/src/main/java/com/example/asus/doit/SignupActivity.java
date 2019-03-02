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
import com.google.firebase.auth.FirebaseAuthUserCollisionException;

public class SignupActivity extends AppCompatActivity implements View.OnClickListener {
    private EditText signupemail,signuppass;
    private TextView signin;
    private Button signupbutton;
    private FirebaseAuth mAuth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        this.setTitle("Signup Activity");
        mAuth = FirebaseAuth.getInstance();

        signupemail=(EditText) findViewById(R.id.signupemailid);
        signuppass=(EditText) findViewById(R.id.signuppasswordid);
        signin=(TextView) findViewById(R.id.signintextid);
        signupbutton=(Button) findViewById(R.id.signupbuttonid);

        signupbutton.setOnClickListener(this);
        signin.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch(view.getId())
        {
            case R.id.signupbuttonid:
                userRegister();
                break;

            case R.id.signintextid:
                Intent intent=new Intent(getApplicationContext(),MainActivity.class);
                startActivity(intent);
                break;
        }

    }

    private void userRegister() {
        String email=signupemail.getText().toString().trim();
        String password=signuppass.getText().toString().trim();

        if(email.isEmpty())
        {
            signupemail.setError("Enter an email address");
            signupemail.requestFocus();
            return;
        }

        if(!Patterns.EMAIL_ADDRESS.matcher(email).matches())
        {
            signupemail.setError("Enter a valid email address");
            signupemail.requestFocus();
            return;
        }
        if(password.isEmpty())
        {
            signuppass.setError("Enter a password");
            signuppass.requestFocus();
            return;
        }
        if(password.length()<6)
        {
            signuppass.setError("Minimum length of password must be 6");
            signuppass.requestFocus();
            return;
        }
        mAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            finish();
                            Intent intent=new Intent(getApplicationContext(),HeadActivity.class);
                            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                            startActivity(intent);

                        }
                        else {
                            if(task.getException() instanceof FirebaseAuthUserCollisionException)
                            {
                                Toast.makeText(SignupActivity.this, "user already exist", Toast.LENGTH_SHORT).show();
                            }
                            else
                                {
                                Toast.makeText(SignupActivity.this, "error"+task.getException().getMessage(), Toast.LENGTH_SHORT).show();

                            }
                        }

                        // ...
                    }
                });
    }
}
