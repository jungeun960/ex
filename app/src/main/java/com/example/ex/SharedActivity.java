package com.example.ex;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.EditText;

public class SharedActivity extends AppCompatActivity {

    EditText et_save;
    String shared = "file";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shared);

        et_save = (EditText)findViewById(R.id.et_save);

        SharedPreferences sharedPreferences = getSharedPreferences(shared,0);
        String value = sharedPreferences.getString("hong",""); // 꺼내오는 것이기 때문에 빈칸
        et_save.setText(value);

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        // 앱이 죽었을 때 저장하기
        SharedPreferences sharedPreferences = getSharedPreferences(shared,0);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        String vaule = et_save.getText().toString();
        editor.putString("hong",vaule);
        editor.commit();//save를 완료하라
    }
}
