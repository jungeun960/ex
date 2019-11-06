package com.example.ex;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

public class Music2Activity extends AppCompatActivity {

    static final String TAG = "생명주기 - 리뷰 확인 : ";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_music2);
        Log.e(TAG, "onCreate");

        Intent intent = getIntent(); // 인텐트 받기
        String title = intent.getStringExtra("music_title"); // 받은 인텐트에 접근
        String content = intent.getStringExtra("music_content");

        TextView tv_title = (TextView)findViewById(R.id.textView);
        TextView tv_content = (TextView)findViewById(R.id.textView2);

        tv_title.setText(title);
        tv_content.setText(content);
    }

    public void onStart() {
        super.onStart();
        Log.e(TAG, "onStart");
    }

    public void onResume() {
        super.onResume();
        Log.e(TAG, "onResume");
    }

    public void onPause() {
        super.onPause();
        Log.e(TAG, "onPause");
    }

    public void onRestart() {
        super.onRestart();
        Log.e(TAG, "onRestart");
    }

    public void onStop() {
        super.onStop();
        Log.e(TAG, "onStop");
    }

    public void onDestroy() {
        super.onDestroy();
        Log.e(TAG, "onDestroy");
    }
}
