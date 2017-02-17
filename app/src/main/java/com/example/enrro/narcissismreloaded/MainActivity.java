package com.example.enrro.narcissismreloaded;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;

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


}
