package com.example.ex;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class Plan1Activity extends AppCompatActivity {

    private EditText et_title;
    private EditText et_content;
    private Button btn_register;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_plan1);
        setTitle("일정 공유하기");

        et_title = findViewById(R.id.et_title);
        et_content = findViewById(R.id.et_context);
        btn_register = findViewById(R.id.btn_register);
        btn_register.setOnClickListener(new View.OnClickListener() { // 버튼 클릭 시
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),PlandetailActivity.class); // 인텐트 생성

                String title = et_title.getText().toString(); // 값 받아오기 바깥에 넣으면 초기값 받아서 안됨
                String content = et_content.getText().toString();
                Log.i("일정 공유", "제목"+title);
                Log.i("일정 공유", "내용"+content);


                intent.putExtra("title",title); // 인텐트에 데이터 적재
                intent.putExtra("content",content);

                startActivity(intent);// 다음 액티비티로 넘김
            }
        });

    }
}
