package com.example.asus.doit;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class ProfileActivity extends AppCompatActivity implements View.OnClickListener {

    private EditText NAME,ROLL,PHONE,HOMETOWN;
    private Button save;
    DatabaseReference databaseReference;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        this.setTitle("Student Registration");

        databaseReference= FirebaseDatabase.getInstance().getReference("Shohan Data");

        NAME=findViewById(R.id.edit_name);
        ROLL=findViewById(R.id.roll);
        PHONE=findViewById(R.id.contact);
        HOMETOWN=findViewById(R.id.register);
        save=findViewById(R.id.buttonSAVE);

        save.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {

        String name=NAME.getText().toString().trim();
        String roll=ROLL.getText().toString().trim();
        String phone=PHONE.getText().toString().trim();
        String hometown=HOMETOWN.getText().toString().trim();

        String key=databaseReference.push().getKey();
        Student student=new Student(name,roll,phone,hometown);
        databaseReference.child(key).setValue(student);
        Toast.makeText(this, "Student info is added", Toast.LENGTH_SHORT).show();

    }
}
