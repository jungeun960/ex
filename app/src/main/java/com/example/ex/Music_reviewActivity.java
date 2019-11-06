package com.example.ex;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Music_reviewActivity extends AppCompatActivity {

    static final String TAG = "생명주기 - 음악 리뷰작성 페이지 : ";
    private EditText et_title;
    private EditText et_content;
    private Button btn_register;
    private String title;
    private String content;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_music_review);
        //Toast.makeText(getApplicationContext(),"onCreate 호출됨",Toast.LENGTH_SHORT).show();
        Log.e(TAG, "onCreate");

        et_title = findViewById(R.id.et_title);
        et_content = findViewById(R.id.et_context);
        btn_register = findViewById(R.id.btn_register);
        btn_register.setOnClickListener(new View.OnClickListener() { // 버튼 클릭 시
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),Music2Activity.class); // 인텐트 생성

                title = et_title.getText().toString(); // 값 받아오기 바깥에 넣으면 초기값 받아서 안됨
                content = et_content.getText().toString();
                //Log.i("일정 공유", "제목"+title);
                //Log.i("일정 공유", "내용"+content);


                intent.putExtra("music_title",title); // 인텐트에 데이터 적재
                intent.putExtra("music_content",content);

                startActivity(intent);// 다음 액티비티로 넘김
            }
        });
    }
    @Override
    protected void onStart() {//2
        //Toast.makeText(getApplicationContext(),"onStart 호출됨",Toast.LENGTH_SHORT).show();
        Log.e(TAG, "onStart");
        super.onStart();
    }

    @Override
    protected void onPause() {//4
        //Toast.makeText(getApplicationContext(),"onPause 호출됨",Toast.LENGTH_SHORT).show();
        Log.e(TAG, "onPause");
        super.onPause();
    }

    @Override
    protected void onResume() {//3
        //Toast.makeText(getApplicationContext(),"onResume 호출됨",Toast.LENGTH_SHORT).show();
        //데이터 호출
        Log.e(TAG, "onResume");
        SharedPreferences pref = getSharedPreferences("gostop", Activity.MODE_PRIVATE); //불러올때 설정한 이름을 불러와야해
        if(pref != null){
            String music_title = pref.getString("title","");
            String music_content = pref.getString("content",""); //값이 없을 때 0을 기본값으로 넣겠다.(기본값)
            //Toast.makeText(getApplicationContext(),"점수 : "+score ,Toast.LENGTH_SHORT).show();
            //Toast.makeText(getApplicationContext(),"제목 : "+music_title +"\n 리뷰 : "+music_content,Toast.LENGTH_SHORT).show();
            et_title.setText(music_title);
            et_content.setText(music_content);
        }
        super.onResume();
    }

    @Override
    protected void onStop() {//5
        Log.e(TAG, "onStop");
        //Toast.makeText(getApplicationContext(),"onStop 호출됨",Toast.LENGTH_SHORT).show();
        super.onStop();
    }

    @Override
    protected void onDestroy() {//6
        Log.e(TAG, "onDestroy");
        //Toast.makeText(getApplicationContext(),"onDestroy 호출됨",Toast.LENGTH_SHORT).show();
        //앱이 도중에 잘못되어도 저장됨
        //데이터 저장
        //SharedPreferences는 해당 프로세스(어플리케이션)내에 File 형태로 Data를 저장해
        //해당 어플리케이션이 삭제되기 전까지 Data를 보관해 주는 기능
        //SharedPreferences 사용한 어플리케이션을 지우면 내용이 모두 삭제 됩니다. File이 삭제되는 것이지요.
        SharedPreferences pref = getSharedPreferences("gostop", Activity.MODE_PRIVATE); //"gostop"은 SharedPreferences 이름. 여러개가 있을 수 있음
        SharedPreferences.Editor edit = pref.edit(); //만들어서 저장
        title = et_title.getText().toString(); // 값 받아오기 바깥에 넣으면 초기값 받아서 안됨
        content = et_content.getText().toString();
        edit.putString("title",title);
        edit.putString("content",content); //종료해도 저장됨
        Toast.makeText(getApplicationContext(),"임시저장 되었습니다. ",Toast.LENGTH_SHORT).show();
        //Toast.makeText(getApplicationContext(),"제목 : "+title +"\n 리뷰 : "+content,Toast.LENGTH_SHORT).show();
        edit.commit(); //저장은 필수
        super.onDestroy();
    }
}
