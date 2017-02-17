package com.example.enrro.narcissismreloaded;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MenuActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
    }

    public void goToHobbies(View view){
        Intent i1 = new Intent(this, Hobbies.class);
        startActivityForResult(i1, 0);
    }

    public void goToFriends(View view){
        Intent i2 = new Intent(this, Friends.class);
        startActivityForResult(i2, 0);
    }
}
