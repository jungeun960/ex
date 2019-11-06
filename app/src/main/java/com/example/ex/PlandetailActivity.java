package com.example.ex;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.TextView;

public class PlandetailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_plandetail);

        Intent intent = getIntent(); // 인텐트 받기
        String title = intent.getStringExtra("title"); // 받은 인텐트에 접근
        String content = intent.getStringExtra("content");

        TextView tv_title = (TextView)findViewById(R.id.textView);
        TextView tv_content = (TextView)findViewById(R.id.textView2);

        tv_title.setText(title);
        tv_content.setText(content);



    }
}
