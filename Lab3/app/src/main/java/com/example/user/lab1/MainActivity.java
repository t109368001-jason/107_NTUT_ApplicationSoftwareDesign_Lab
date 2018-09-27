package com.example.user.lab1;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        EditText gamer = (EditText) findViewById(R.id.gamer);
        TextView status = (TextView) findViewById(R.id.status);
        Button play = (Button) findViewById(R.id.play);
        RadioGroup radioGroup = (RadioGroup) findViewById(R.id.radioGroup);

        play.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }
}
