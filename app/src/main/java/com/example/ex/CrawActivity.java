package com.example.ex;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class CrawActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_craw);

        Button button11 = (Button)findViewById(R.id.button1);
        button11.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent8 = new Intent(getApplicationContext(),NaverActivity.class);
                startActivity(intent8);
            }
        });


        Button button12 = (Button)findViewById(R.id.button2);
        button12.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent8 = new Intent(getApplicationContext(),CrawlingActivity.class);
                startActivity(intent8);
            }
        });
    }
}
