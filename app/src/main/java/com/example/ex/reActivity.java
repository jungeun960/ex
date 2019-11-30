package com.example.ex;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class reActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_re);

        Button button6 = (Button)findViewById(R.id.recyclerview1);
        button6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent4 = new Intent(getApplicationContext(),RecyclerViewActivity.class);
                startActivity(intent4);
            }
        });

        Button button7 = (Button)findViewById(R.id.recyclerview2);
        button7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent5 = new Intent(getApplicationContext(),RecycleView2Activity.class);
                startActivity(intent5);
            }
        });

    }
}
