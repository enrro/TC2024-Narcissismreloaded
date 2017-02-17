package com.example.enrro.narcissismreloaded;

import android.app.Activity;
import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;


public class Hobbies extends AppCompatActivity {

    private DBHelper db;
    EditText editText;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hobbies);

        db =new DBHelper(this);
        editText = (EditText) findViewById(R.id.editText2);

    }

    public void onClickReturnAndSubmit(View view){
        Intent i = new Intent(this, MenuActivity.class);
        db.add(editText.getText().toString());
        i.putExtra("message", editText.getText().toString());
        Toast.makeText(this, "RECORD ADDED", Toast.LENGTH_SHORT).show();
        setResult(Activity.RESULT_OK, i);
        finish();

    }
}
