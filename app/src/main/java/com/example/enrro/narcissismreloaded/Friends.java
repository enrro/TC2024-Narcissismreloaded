package com.example.enrro.narcissismreloaded;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class Friends extends AppCompatActivity {

    private DBHelper db;
    private EditText editTextFriend, editTextHobby;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_friends);

        db = new DBHelper(this);

        editTextFriend = (EditText) findViewById(R.id.editText3);
        editTextHobby = (EditText) findViewById(R.id.editText4);

    }

    public void onClickAdd(View view){
        db.addFriend(editTextFriend.getText().toString(), editTextHobby.getText().toString());
        Toast.makeText(this, "SAVED ", Toast.LENGTH_SHORT).show();
    }

    public void onClickSearch(View view){
        Toast.makeText(this, "SEARCH " + db.find(editTextFriend.getText().toString(), "friend", 2), Toast.LENGTH_SHORT).show();
        editTextHobby.setText(db.find(editTextFriend.getText().toString(), "friend", 2));
    }

    public void onClickDelete(View view){
        Toast.makeText(this, "DELETE " + db.deleteFriend(editTextFriend.getText().toString()), Toast.LENGTH_SHORT).show();

    }
}
