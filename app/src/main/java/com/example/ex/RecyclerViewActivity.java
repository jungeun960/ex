package com.example.ex;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.Button;

import java.util.ArrayList;

public class RecyclerViewActivity extends AppCompatActivity {

    private ArrayList<MainData> arrayList; // 어댑터에 들어갈 list
    private MainAdapter mainAdapter;
    private RecyclerView recyclerView;
    private LinearLayoutManager linearLayoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycler_view);

        //toolbar = findViewById(R.id.toolBar);
        // 리사이클러뷰에 LinearLayoutManager 객체 지정.
        recyclerView = (RecyclerView)findViewById(R.id.rv);
        //수평(Horizontal) 방향으로 아이템을 배치
        linearLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(linearLayoutManager);

        // 리사이클러뷰에 표시할 데이터 리스트 생성.
        arrayList = new ArrayList<>();

        // 리사이클러뷰에 mainAdapter 객체 지정.
        mainAdapter = new MainAdapter(arrayList);
        recyclerView.setAdapter(mainAdapter);

        MainData mainData1 = new MainData(R.mipmap.ic_launcher, "최복치","마카롱 맛집 공유합니다.","파리 3박 4일");
        MainData mainData2 = new MainData(R.mipmap.ic_launcher, "절미","팬션 추천받습니다","강릉 여행");
        MainData mainData3 = new MainData(R.mipmap.ic_launcher, "마이크","엄지네 포장마차 강추","속초 맛집 추천");
        arrayList.add(mainData1); // 내용 추가
        arrayList.add(mainData2);
        arrayList.add(mainData3);
        mainAdapter.notifyDataSetChanged();

        Button btn_add = (Button)findViewById(R.id.btn_add);
        btn_add.setOnClickListener(new View.OnClickListener() {// 추가 버튼 클릭 시
            @Override
            public void onClick(View v) {
                MainData mainData = new MainData(R.mipmap.ic_launcher, "홍홍","우왕","리사이클러뷰");
                arrayList.add(mainData); // 내용 추가
                mainAdapter.notifyDataSetChanged(); // 새로고침해 반영
            }
        });

    }


    public boolean onCreateOptionsMeunu(Menu menu){
        getMenuInflater().inflate(R.menu.toolbar_menu,menu);
        return true;
    }

}
