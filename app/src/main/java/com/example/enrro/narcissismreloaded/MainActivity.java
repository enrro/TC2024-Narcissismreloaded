package com.example.enrro.narcissismreloaded;


import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;

/*
Main activity
    If the properties file exists load the name saved there.
    If there is no properties file whenever the user press the button save the name on it.
Menu activity
    The textview should be populated with the hobby of the user that should be saved on a SQLite DB.
My hobbies - When the user specifies its hobbies the hobby is saved on a SQLite DB.
My Friends - The interface will now show the following elements:
    An entry text for name
    An entry text for hobby
    A button to save - When the user presses this button the app saves the name/hobby on the entry texts as a record in a table in a SQLite DB.
    A button to search - When the user presses this button the app retrieves the text from the name and updates the hobby with the one recorded for that friend in the DB.
    A button to delete - When the user presses this button the app erases the record that has the name of the friend currently on the text entry.
 */

public class MainActivity extends AppCompatActivity {

    private EditText editText;
    private static final String PROPERTIES_FILE = "properties.xml";
    Properties properties;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editText = (EditText) findViewById(R.id.editText);

        File file = new File(getFilesDir(), PROPERTIES_FILE);
        properties = new Properties();

        try {
            if (file.exists()){
                FileInputStream fis = openFileInput(PROPERTIES_FILE);
                properties.loadFromXML(fis);
                editText.setText(properties.getProperty("demo"));
                fis.close();
            }else{

            }
        }catch (IOException ioe){
            ioe.printStackTrace();
        }


    }

    private void saveProperties() throws IOException{
        properties.setProperty("demo", editText.getText().toString());
        FileOutputStream fos = openFileOutput(PROPERTIES_FILE, Context.MODE_PRIVATE);
        properties.storeToXML(fos, null);
        fos.close();
    }

    public void saveToStorage(View v){
        try{
            saveProperties();
        }catch(IOException ioe){
            ioe.printStackTrace();
        }
        Toast.makeText(this, "SAVED TO STORAGE", Toast.LENGTH_SHORT).show();
    }

    public void buttonClicked(View v){
        Intent intent = new Intent(this, MenuActivity.class);
        startActivity(intent);
    }
}
