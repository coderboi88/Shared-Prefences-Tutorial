package com.example.sharedpreferenceapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    private TextView result;
    private EditText enterSomething;
    private Button saveButton,nextActivity;
    private SharedPreferences sharedPreferences;
    private static final String PREFS_NAME = "myPrefsFile";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        result = (TextView) findViewById(R.id.resultTv);
        enterSomething = (EditText) findViewById(R.id.enterName);
        nextActivity = (Button) findViewById(R.id.nextActivity);
        nextActivity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,ToDoList.class);
                startActivity(intent);
            }
        });
        saveButton = (Button) findViewById(R.id.saveButton);
        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sharedPreferences = getSharedPreferences(PREFS_NAME,0);
                SharedPreferences.Editor editor = sharedPreferences.edit();

                editor.putString("message",enterSomething.getText().toString());

                editor.commit();
            }
        });

        //GET BACK THE MESSAGE
        SharedPreferences prefs = getSharedPreferences(PREFS_NAME,0);

        if(prefs.contains("message")){
            String message = prefs.getString("message","not found");
            result.setText("Message : "+message);
        }
    }
}
