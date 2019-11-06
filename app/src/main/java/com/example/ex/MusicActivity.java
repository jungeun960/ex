package com.example.ex;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

public class MusicActivity extends AppCompatActivity {

    static final String TAG = "생명주기 - 음악 재생 페이지 : ";

    // MediaPlayer 객체생성
    MediaPlayer mediaPlayer;

    // 시작버튼
    Button startButton;
    //종료버튼
    Button stopButton;
    Button reviewButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_music);
        Log.e(TAG, "onCreate");

        startButton = findViewById(R.id.start);
        stopButton = findViewById(R.id.stop);
        reviewButton = findViewById(R.id.review);

        reviewButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),Music_reviewActivity.class); // 인텐트 생성
                startActivity(intent);// 다음 액티비티로 넘김
            }
        });


        startButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // MediaPlayer 객체 할당
                mediaPlayer = MediaPlayer.create(MusicActivity.this, R.raw.chris);
                mediaPlayer.start();
                //Log.e("음악", "재생");
            }
        });

        stopButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(mediaPlayer.isPlaying()) {
                    // 정지버튼
                    mediaPlayer.stop();
                    // 초기화
                    mediaPlayer.reset();
                }
            }
        });
    }

    // 액티비티 종료될때
    // MediaPlayer는 시스템 리소스를 잡아먹는다.
    // MediaPlayer는 필요이상으로 사용하지 않도록 주의해야 한다.
    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.e(TAG, "onDestroy");
        // MediaPlayer 해지
//        if(mediaPlayer != null) {
//            mediaPlayer.release();
//            mediaPlayer = null;
//        }
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

        if(mediaPlayer != null) {
            // 정지버튼
            mediaPlayer.stop();
            //Log.e("음악", "멈춤");
        }
    }

    public void onRestart() {
        super.onRestart();
        Log.e(TAG, "onRestart");
    }

    public void onStop() {
        super.onStop();
        Log.e(TAG, "onStop");
    }
}
