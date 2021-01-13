package com.example.sharedpreferenceapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;

public class ToDoList extends AppCompatActivity {
    private EditText enterData;
    private Button saveData;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_to_do_list);
        enterData = (EditText) findViewById(R.id.enterData);
        saveData = (Button)  findViewById(R.id.saveData);
        saveData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!enterData.getText().toString().equals("")){
                    writeToFile(enterData.getText().toString());
                }
            }
        });

        try {
            if(readFromFile()!=null){
                enterData.setText(readFromFile());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void writeToFile(String message){

        try {
            OutputStreamWriter outputStreamWriter  = new OutputStreamWriter(openFileOutput("todoList.txt", Context.MODE_PRIVATE));
            outputStreamWriter.write(message);
            outputStreamWriter.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public String readFromFile() throws IOException {
        String result = "";
        InputStream inputStream = openFileInput("todoList.txt");

        if(inputStream!= null){
            InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
            BufferedReader reader = new BufferedReader(inputStreamReader);

            String tempString = "";
            StringBuilder builder = new StringBuilder();

            while ((tempString = reader.readLine())!=null){
                builder.append(tempString);
            }
            inputStream.close();
            result = builder.toString();

        }

        return result;
    }
}
