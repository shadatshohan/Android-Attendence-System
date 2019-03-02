package com.example.asus.doit;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.google.firebase.auth.FirebaseAuth;

public class HeadActivity extends AppCompatActivity implements View.OnClickListener {
    private FirebaseAuth mAuth;
    private CardView PROFILE,VIEW,ATTENDENCE,RECORD;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_head);
        mAuth = FirebaseAuth.getInstance();
        this.setTitle("Menu");

        PROFILE=findViewById(R.id.profileid);
        VIEW=findViewById(R.id.viewid);
        ATTENDENCE=findViewById(R.id.attendenceid);
        RECORD=findViewById(R.id.recordid);

        PROFILE.setOnClickListener(this);
        VIEW.setOnClickListener(this);
        ATTENDENCE.setOnClickListener(this);
        RECORD.setOnClickListener(this);


    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_layout,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId()==R.id.signoutid)
        {

        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onClick(View view) {
        if(view.getId()==R.id.profileid)
        {
            Intent intent=new Intent(HeadActivity.this,ProfileActivity.class);
            startActivity(intent);
        }
        else if(view.getId()==R.id.viewid)
        {

        }
        else if(view.getId()==R.id.attendenceid)
        {

        }
        else if(view.getId()==R.id.recordid)
        {

        }

    }
}

