package com.example.enrro.narcissismreloaded;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class MenuActivity extends AppCompatActivity {

    public DBHelper db;
    private TextView textView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        textView = (TextView) findViewById(R.id.textView);
        db = new DBHelper(this);
    }

    public void goToHobbies(View view){
        Intent i1 = new Intent(this, Hobbies.class);
        startActivityForResult(i1, 0);
    }

    public void goToFriends(View view){
        Intent i2 = new Intent(this, Friends.class);
        startActivityForResult(i2, 0);
    }


    public void onActivityResult(int RequestCode, int ResultCode, Intent i){
        if (RequestCode == 0 && ResultCode == Activity.RESULT_OK){
            textView.setText("Your hobby is: " + db.find(i.getStringExtra("message")));
        }
    }

}
