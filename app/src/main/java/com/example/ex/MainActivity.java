package com.example.ex;

import androidx.appcompat.app.AppCompatActivity;

import android.app.SearchManager;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    static final String TAG = "생명주기 테스트 - 다음 페이지";

    private EditText et_Dial;
    private EditText et_Web;
    private EditText et_Google;
    private EditText et_Search;
    private EditText et_Sms_content;
    private EditText et_Sms_number;

    private String str;
    private String str1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setTitle("암시적 인텐트");

        Log.i(TAG, "onCreate");

        et_Dial = findViewById(R.id.et_Dial);
        et_Web = findViewById(R.id.et_Web);
        et_Google = findViewById(R.id.et_Google);
        et_Search = findViewById(R.id.et_Search);
        et_Sms_content = findViewById(R.id.et_Sms_content);
        et_Sms_number = findViewById(R.id.et_Sms_number);

        Button btnDial = (Button)findViewById(R.id.btnDial);
        Button btnWeb = (Button)findViewById(R.id.btnWeb);
        Button btnGoogle = (Button)findViewById(R.id.btnGoogle);
        Button btnSearch = (Button)findViewById(R.id.btnSearch);
        Button btnSms = (Button)findViewById(R.id.btnSms);
        Button btnPhoto = (Button)findViewById(R.id.btnPhoto);

        btnDial.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                str = et_Dial.getText().toString();
                Uri uri = Uri.parse("tel:"+str);
                Intent intent = new Intent(Intent.ACTION_DIAL,uri);
                startActivity(intent);
            }
        });

        btnWeb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                str = et_Web.getText().toString();
                Uri uri = Uri.parse("http://"+str);
                Intent intent = new Intent(Intent.ACTION_VIEW,uri);
                startActivity(intent);
            }
        });

        btnGoogle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                str = et_Google.getText().toString();
                Uri uri = Uri.parse("http://maps.google.com/maps?q="+str);
                Intent intent = new Intent(Intent.ACTION_VIEW,uri);
                startActivity(intent);
            }
        });

        btnSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                str = et_Search.getText().toString();
                Intent intent = new Intent(Intent.ACTION_WEB_SEARCH);
                intent.putExtra(SearchManager.QUERY,str);
                startActivity(intent);
            }
        });

        btnSms.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                str = et_Sms_content.getText().toString();
                str1 = et_Sms_number.getText().toString();

                Intent intent = new Intent(Intent.ACTION_SENDTO);
                intent.putExtra("sms_body",str);
                intent.setData(Uri.parse("smsto:"+Uri.encode(str1)));
                startActivity(intent);
            }
        });

        btnPhoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                startActivity(intent);
            }
        });
    }

    public void onStart() {
        super.onStart();
        Log.i(TAG, "onStart");
    }

    public void onResume() {
        super.onResume();
        Log.i(TAG, "onResume");
    }

    public void onPause() {
        super.onPause();
        Log.i(TAG, "onPause");
    }

    public void onRestart() {
        super.onRestart();
        Log.i(TAG, "onRestart");
    }

    public void onStop() {
        super.onStop();
        Log.i(TAG, "onStop");
    }

    public void onDestroy() {
        super.onDestroy();
        Log.i(TAG, "onDestroy");
    }
}
